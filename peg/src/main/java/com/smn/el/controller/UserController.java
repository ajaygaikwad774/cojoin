package com.smn.el.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smn.el.dto.UserDTO;
import com.smn.el.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/registerUser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
		return new ResponseEntity<UserDTO>(userService.registerUser(userDTO), HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(value = "/authenticateUser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserDTO> authenticateUser(@RequestBody UserDTO userDTO) throws Exception {
		return new ResponseEntity<UserDTO>(userService.authenticateUser(userDTO), HttpStatus.ACCEPTED);
	}
}
