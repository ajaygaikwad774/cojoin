package com.smn.el.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.smn.el.config.JwtTokenUtil;
import com.smn.el.dto.UserDTO;
import com.smn.el.model.User;
import com.smn.el.utils.EncryptionUtil;

@Mapper(componentModel = "spring", uses = {}, imports = {EncryptionUtil.class, JwtTokenUtil.class})
public interface UserMapper {

    @Mapping(target = "id" , expression = "java(EncryptionUtil.decryptLong(userDTO.getEncUserId()))")
    @Mapping(target = "encPassword" , expression = "java(null == userDTO.getPassword() ? null :  EncryptionUtil.encrypt(userDTO.getPassword()))")  
    @Mapping(target = "status" , defaultValue = "ACTIVE" )
    public User userDTOToUser(UserDTO userDTO)  throws Exception;

    @Mapping(target = "encUserId" , expression = "java(EncryptionUtil.encrypt(user.getId().toString()))")
    @Mapping(source = "encPassword",  target = "password")
    //@Mapping(target = "token" , expression = "java(JwtTokenUtil.generateToken(user.getEmail()))")
    public UserDTO UserToUserDTO(User user) throws Exception;
 
	public List<User> userDTOToUser(List<UserDTO> userDTO);

	public List<UserDTO> userToUserDTO(List<User> User);

}

