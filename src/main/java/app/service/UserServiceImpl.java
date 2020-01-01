package app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Role;
import app.entity.User;
import app.repository.RoleRepository;
import app.repository.UserRepository;
import app.util.PassayUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private PassayUtil passayUtil;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.getOne(1L));
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Override
	public void register(User user) {

		// Generate salt
		String salt = passayUtil.generateSalt();

		// Generate password
		String rawPassword = passayUtil.generatePassword();

		// bCript password
		String password = bCryptPasswordEncoder.encode(salt + rawPassword);

		// Generate activation code
		String activationCode = bCryptPasswordEncoder.encode(Long.toString(System.currentTimeMillis()));

		// Set User role
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.getOne(1L));

		user.setSalt(salt);
		user.setPassword(password);
		user.setActivationCode(activationCode);
		user.setRoles(roles);
		
		System.err.println(user);

		// Save user
		userRepository.save(user);

	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
