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

import com.stargateway.dtos.BookingDto;
import com.stargateway.dtos.CheckDto;
import com.stargateway.dtos.DtoEntityConverter;
import com.stargateway.dtos.FacilityDto;
import com.stargateway.dtos.RoomsCategoryDto;
import com.stargateway.dtos.RoomsDto;
import com.stargateway.entities.Rooms;
import com.stargateway.services.BookingServices;
import com.stargateway.services.RoomCategoryServices;
import com.stargateway.services.RoomServices;
import com.stargateway.utils.Response;

@CrossOrigin(origins = "*")
@RestController	
public class BookingController {
	
	@Autowired
	private BookingServices bookingServices;
	
	@Autowired
	private RoomServices roomsServices;
	
	@Autowired
	private DtoEntityConverter converter;
	
	@GetMapping("/booking/{id}")
	public ResponseEntity<?> BookingById(@PathVariable("id") int bookingId) {
		BookingDto bookingDto = bookingServices.findBookingById(bookingId);
		if(bookingDto==null) {
			return Response.status(HttpStatus.NOT_FOUND);
		}
		return Response.success(bookingDto);
	}

		
	@GetMapping("/booking/allbooking")
	public ResponseEntity<?> allBooking() {
		List<BookingDto> bookingList = bookingServices.findAllBooking();
		if(bookingList==null) {
			return Response.status(HttpStatus.NOT_FOUND);
		}
		return Response.success(bookingList);
	}
	
	@GetMapping("/booking/userbooking/{id}")
	public ResponseEntity<?> bookingByUserId(@PathVariable("id") int userId) {
		List<BookingDto> bookingList = bookingServices.findAllBookingByUserId(userId);
		if(bookingList==null) {
			return Response.status(HttpStatus.NOT_FOUND);
		}
		return Response.success(bookingList);
	}

	@PostMapping("/booking/add")
	public ResponseEntity<?> addBooking(@RequestBody BookingDto bookingDto) {
		Map<String, Object> result = bookingServices.addBooking(bookingDto);
		if(result.containsValue(1)) {
			return Response.success(result);
		}
		return Response.status(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@DeleteMapping("/booking/delete/{id}")
	public ResponseEntity<?> deleteBooking(@PathVariable("id") int id) {
		Map<String, Object> result = bookingServices.deleteBooking(id);
		if(result.containsValue(1)) {
			return Response.success(result);
		}
		return Response.status(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/booking/checkavailablerooms")
	public ResponseEntity<?> checkAvailableRooms(@RequestBody CheckDto checkDto) {
		List<RoomsDto> availableRoomsList = bookingServices.findAvailabeRoomsByFromAndToDateTest(checkDto);
		return Response.success(availableRoomsList);
	}
	
	@PostMapping("/booking/bookingbydate")
	public ResponseEntity<?> bookingByDate(@RequestBody CheckDto checkDto) {
		List<BookingDto> bookingList = bookingServices.findAllBookingByDate(checkDto);
		return Response.success(bookingList);
	}
	
	@GetMapping("/booking/bookingcount")
	public ResponseEntity<?> bookingCount() {
		int bookingCount = bookingServices.findBookingCount();
		return Response.success(bookingCount);
	}

	
}
