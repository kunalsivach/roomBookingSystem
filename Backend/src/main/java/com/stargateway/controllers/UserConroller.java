package com.stargateway.controllers;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stargateway.dtos.BookingDto;
import com.stargateway.dtos.ChangePasswordDto;
import com.stargateway.dtos.Credentials;
import com.stargateway.dtos.DtoEntityConverter;
import com.stargateway.dtos.IdproofDto;
import com.stargateway.dtos.RoomsCategoryDto;
import com.stargateway.dtos.RoomsDto;
import com.stargateway.utils.Response;
import com.stargateway.dtos.UserDto;
import com.stargateway.entities.Idproof;
import com.stargateway.services.BookingServices;
import com.stargateway.services.IdproofServices;
import com.stargateway.services.UserServices;

@CrossOrigin(origins = "*")
@RestController	
public class UserConroller {

	@Autowired
	private UserServices userService;
	@Autowired
	private IdproofServices idproofServices;
	@Autowired
	private DtoEntityConverter converter;
	
	@PostMapping("/user/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody Credentials cred) {
		UserDto userDto = userService.findUserByEmailAndPassword(cred);
		if(userDto == null)
			return Response.status(HttpStatus.NOT_FOUND);
			//return Response.error("not fount");
		return Response.success(userDto);
	}
	
	@PostMapping("/user/signup")
	public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
		UserDto result = userService.saveUser(userDto);
		if(result==null) {
			return Response.status(HttpStatus.NOT_ACCEPTABLE);
		}
		return Response.success(result);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> userById(@PathVariable("id") int userId) {
		UserDto userDto = userService.findUserById(userId);
		if(userDto==null) {
			return Response.status(HttpStatus.NOT_FOUND);
		}
		 IdproofDto idproofDto =idproofServices.findIdproofById(userDto.getIdproofs());
		 userDto.setIdproofObject(converter.IdproofDtoToIdproofEntity(idproofDto));
		return Response.success(userDto);
	}
	
	@GetMapping("/user/allusers")
	public ResponseEntity<?> allUsers() {
		List<UserDto> usersList = userService.findAllUsers();
		if(usersList==null) {
			return Response.status(HttpStatus.NOT_FOUND);
		}
		return Response.success(usersList);
	}
	
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int userId) {
		Map<String, Object> result = userService.deleteUserById(userId);
		if(result.containsValue(1)) {
			return Response.success(result);
			
		}
		return Response.status(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/user/updateuserrole/{id}")
	public ResponseEntity<?> updateUserRole(@PathVariable("id") int id,@RequestBody UserDto userDto) {
		Map<String, Object> result = userService.upadateUserRole(id,userDto);
		if(result.containsValue(1)) {
			return Response.success(result);
			
		}
		return Response.status(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/user/updateuserprofile")
	public ResponseEntity<?> updateUserProfile(@RequestBody UserDto userDto) {
		Map<String, Object> result = userService.upadateUser(userDto);
		if(result.containsValue(1)) {
			return Response.success(result);
			
		}
		return Response.status(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/user/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
		Map<String, Object> result = userService.upadateUserPassword(changePasswordDto);
		if(result.containsValue(1)) {
			//System.out.println(result.containsValue(1));
			return Response.success(result);
		}else {
			//System.out.println(result.containsValue(1));
			//return Response.error("password not matched");
			return Response.status(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@GetMapping("/users/userscount")
	public ResponseEntity<?> usersCount() {
		int usersCount = userService.findUsersCount();
		return Response.success(usersCount);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class) 
	public ResponseEntity<?> emailExistsHandler(DataIntegrityViolationException ex)	{
		return Response.error(ex.getMessage());
	}
	
	
}
