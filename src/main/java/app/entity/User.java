package app.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Size(min = 2)
	@Email
	@Column(name = "email", nullable = false)
	private String username;

	@NotBlank
	@Size(min = 2)
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotBlank
	@Size(min = 2)
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Size(min = 8, max = 8)
	@Column(name = "salt")
	private String salt;

	@NotBlank
	@Size(min = 4, max = 16)
	@Column(name = "password")
	private String password;
	@Transient
	private String passwordConfirm;

	@Column(name = "active", nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean active;

	@Size(min = 128, max = 128)
	@Column(name = "activation_code")
	private String activationCode;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(@NotBlank @Size(min = 2) @Email String username, @NotBlank @Size(min = 2) String firstName,
			@NotBlank @Size(min = 2) String lastName, @Size(min = 8, max = 8) String salt,
			@NotBlank @Size(min = 4, max = 16) String password, String passwordConfirm, boolean active,
			@Size(min = 128, max = 128) String activationCode) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salt = salt;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.active = active;
		this.activationCode = activationCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", salt=" + salt + ", password=" + password + ", passwordConfirm=" + passwordConfirm + ", active="
				+ active + ", activationCode=" + activationCode + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
