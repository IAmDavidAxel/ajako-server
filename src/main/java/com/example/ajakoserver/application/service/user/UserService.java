package com.example.ajakoserver.application.service.user;

import com.example.ajakoserver.api.dto.CartDto;
import com.example.ajakoserver.api.dto.user.ClientDto;
import com.example.ajakoserver.api.dto.user.SocialProvider;
import com.example.ajakoserver.api.exception.OAuth2AuthenticationProcessingException;
import com.example.ajakoserver.application.domain.user.LocalUser;
import com.example.ajakoserver.application.utils.GeneralUtils;
import com.example.ajakoserver.domain.user.*;
import com.example.ajakoserver.application.service.exception.AccountCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;


//	@Transactional(value = 'transactionManager')
	public  User create(ClientDto clientDto) throws Exception {
		verifyUserExistence(clientDto);
		User user = createUser(clientDto);
		save(user);

		return user;
	}

	public synchronized User findUserByEmail(final String email){
		return userRepository.findByEmail(email);
	}

	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Transactional
	public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo){

		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
		if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
			throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
		} else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		ClientDto userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
		User user = findUserByEmail(oAuth2UserInfo.getEmail());
		if (user != null) {
			if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
				throw new OAuth2AuthenticationProcessingException(
						"Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = createUser(userDetails);
		}

		return LocalUser.create(user, attributes, idToken, userInfo);
	}

	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setDisplayName(oAuth2UserInfo.getName());
		return userRepository.save(existingUser);
	}

	private ClientDto toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
		return ClientDto.getBuilder().addProviderUserID(oAuth2UserInfo.getId()).addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
				.addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
	}

	private void verifyUserExistence(ClientDto clientDto) throws UserAlreadyExistAuthenticationException {
		if (userRepository.existsByEmail(clientDto.getEmail()))
			throw new UserAlreadyExistAuthenticationException(
					"User with email :" + clientDto.getEmail() +
					" already exist");
	}


	private User createUser(ClientDto clientDto) {
		User user = new User();
		final HashSet<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName(Role.ROLE_USER));

		user.setEmail(clientDto.getEmail());
		user.setPassword(passwordEncoder.encode(clientDto.getPassword()));
		user.setRoles(roles);

		user.setProvider(clientDto.getSocialProvider().getProviderType());

		user.setEnabled(true);
		user.setProviderUserId(clientDto.getProviderUserId());

		Date now = Calendar.getInstance().getTime();
		user.setCreatedDate(now);
		user.setModifiedDate(now);
		return user;
	}

	private void save(User user) {
		 userRepository.save(user);
		userRepository.flush();
	}



	public void addToCart(CartDto cartDto) {

	}
}
