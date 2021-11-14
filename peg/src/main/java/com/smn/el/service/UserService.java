package com.smn.el.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.smn.el.config.JwtTokenUtil;
import com.smn.el.dto.UserDTO;
import com.smn.el.exception.DBExceptionManipulator;
import com.smn.el.mapper.UserMapper;
import com.smn.el.model.User;
import com.smn.el.repository.UserRepository;
import com.smn.el.utils.EncryptionUtil;
import com.smn.el.utils.StatusType;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	EmailService emailService;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
				user.get().getEncPassword(), new ArrayList<>());
	}

	public UserDTO registerUser(UserDTO userDTO) throws Exception {
		return saveOrUpdate(userDTO);
	}

	public UserDTO updateUser(UserDTO userDTO) throws Exception {
		Assert.notNull(userDTO.getEncUserId(), "Please Enter User ID");
		return saveOrUpdate(userDTO);
	}

	public UserDTO saveOrUpdate(UserDTO userDTO) throws Exception {
		try {
			User user = userRepository.saveAndFlush(userMapper.userDTOToUser(userDTO));
			userDTO = userMapper.UserToUserDTO(user);
			userDTO.setToken(JwtTokenUtil.generateToken(user.getEmail()));
			emailService.sendSimpleMessage(userDTO.getEmail(), "Thanking Message From Idirection", "You have succesfully register with idirection!");
			return userDTO;
		} catch (DataIntegrityViolationException ex) {
			throw DBExceptionManipulator.getValidationException(ex);
		} catch (ConstraintViolationException ex) {
			throw DBExceptionManipulator.getValidationException(ex);
		}
	}

	public UserDTO authenticateUser(UserDTO userDTO) throws Exception {
		User user = userRepository.findByEmailAndEncPasswordAndRoleAndStatus(userDTO.getEmail(),
				EncryptionUtil.encrypt(userDTO.getPassword()), userDTO.getRole(), StatusType.ACTIVE.toString());
		Assert.notNull(user, "No Record Found");
		userDTO = userMapper.UserToUserDTO(user);
		userDTO.setToken(JwtTokenUtil.generateToken(user.getEmail()));
		return userDTO;
	}

}
