package com.stargateway.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stargateway.dtos.DtoEntityConverter;
import com.stargateway.dtos.FacilityDto;
import com.stargateway.dtos.IdproofDto;
import com.stargateway.dtos.RoomsCategoryDto;
import com.stargateway.dtos.RoomsDto;
import com.stargateway.entities.Rooms;
import com.stargateway.services.IdproofServices;
import com.stargateway.services.RoomCategoryServices;
import com.stargateway.services.RoomServices;
import com.stargateway.utils.Response;

@CrossOrigin(origins = "*")
@RestController	
public class IdproofController {
	
	@Autowired
	private IdproofServices idproofServices;
	
	@Autowired
	private DtoEntityConverter converter;

	@GetMapping("/idproof/{id}")
	public ResponseEntity<?> idproofById(@PathVariable("id") int id) {
		IdproofDto idproofDto  = idproofServices.findIdproofById(id);
		if(idproofDto==null) {
			return Response.status(HttpStatus.NOT_FOUND);
		}
		return Response.success(idproofDto);
	}
	
	@GetMapping("/idproof/allidproof")
	public ResponseEntity<?> allidproof() {
		List<IdproofDto> idproofDtoList = idproofServices.findAllIdproof();
		if(idproofDtoList==null) {
			return Response.status(HttpStatus.NOT_FOUND);
		}
		return Response.success(idproofDtoList);
	}

	@PostMapping("/idproof/add")
	public ResponseEntity<?> addIdproof(@RequestBody IdproofDto idproofDto) {
		Map<String, Object> result = idproofServices.addIdproof(idproofDto);
		if(result.containsValue(1)) {
			return Response.success(result);
		}
		return Response.status(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/idproof/edit/{id}")
	public ResponseEntity<?> editIdproof(@PathVariable("id") int id, @RequestBody  IdproofDto idproofDto) {
		Map<String, Object> result = idproofServices.editIdproof(id, idproofDto);
		if(result.containsValue(1)) {
			return Response.success(result);
		}
		return Response.status(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/idproof/delete/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable("id") int id) {
		Map<String, Object> result = idproofServices.deleteIdproof(id);
		if(result.containsValue(1)) {
			return Response.success(result);
		}
		return Response.status(HttpStatus.NOT_FOUND);
	}
}
