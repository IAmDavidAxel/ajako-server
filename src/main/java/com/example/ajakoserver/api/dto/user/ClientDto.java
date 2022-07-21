package com.example.ajakoserver.api.dto.user;

import com.example.ajakoserver.validator.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class ClientDto {
	private final String providerUserID;
	@NotEmpty
	private final String displayName;
	private String email;
	@Size(min = 6, message = "{Size.userDto.password}")
	private String password;
	private SocialProvider socialProvider;
	private String providerUserId;

	@NotEmpty
	private String matchingPassword;

	public ClientDto(String providerUserID, String displayName, String email, String password, SocialProvider socialProvider) {

		this.providerUserID = providerUserID;
		this.displayName = displayName;
		this.email = email;
		this.password = password;
		this.socialProvider = socialProvider;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CharSequence getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SocialProvider getSocialProvider() {
		return socialProvider;
	}

	public void setSocialProvider(SocialProvider socialProvider) {
		this.socialProvider = socialProvider;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public static class Builder {
		private String providerUserID;
		private String displayName;
		private String email;
		private String password;
		private SocialProvider socialProvider;

		public Builder addProviderUserID(final String userID) {
			this.providerUserID = userID;
			return this;
		}

		public Builder addDisplayName(final String displayName) {
			this.displayName = displayName;
			return this;
		}

		public Builder addEmail(final String email) {
			this.email = email;
			return this;
		}

		public Builder addPassword(final String password) {
			this.password = password;
			return this;
		}

		public Builder addSocialProvider(final SocialProvider socialProvider) {
			this.socialProvider = socialProvider;
			return this;
		}

		public ClientDto build() {
			return new ClientDto(providerUserID, displayName, email, password, socialProvider);
		}
	}
}
