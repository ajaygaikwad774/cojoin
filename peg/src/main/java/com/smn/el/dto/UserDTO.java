package com.smn.el.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends AuditDetailsDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String encUserId;
	
	@NotBlank(message = "please enter email id")
	private String email;
	
	@NotBlank(message = "please enter  Password")
	private String password;
	
	@NotBlank(message = "please enter name")
	private String name;
	
	private String status;
	
	@NotBlank(message = "please enter role")
	private String role;
	
	private String token;


}
