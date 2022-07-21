package com.example.ajakoserver.domain.user;


import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name= "user")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "USER_ID")
	private Long id;

	@Column(name = "PROVIDER_USER_ID")
	private String providerUserId;

	private String email;

	@Column(name = "enabled", columnDefinition = "BIT", length = 1)
	private boolean enabled;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "created_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedDate;

	private String password;

	private String provider;

	private String address;

	private String phoneNumber;


	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
	private Set<Role> roles;


	public User(){

	}
}
