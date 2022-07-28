package com.stargateway;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stargateway.controllers.UserConroller;
import com.stargateway.dtos.BookingDto;
import com.stargateway.dtos.CheckDto;
import com.stargateway.dtos.RoomsDto;
import com.stargateway.entities.Booking;
import com.stargateway.entities.Rooms;
import com.stargateway.services.BookingServices;
import com.stargateway.services.RoomServices;

@SpringBootTest
public class BookingServicesTest {

	@Autowired
	private BookingServices bookingServices;
	@Autowired
	private UserConroller usercontroller;
	
	@Autowired
	private RoomServices roomServices;
	
	
//	@Test
//	void findByBookingId() {
//		BookingDto book= bookingServices.findBookingById(1);
//		System.out.println(book);
//	}
	
//	@Test
//	void findAllByBooking() {
//		List<BookingDto> bookingList = bookingServices.findAllBooking();
//		System.out.println(bookingList);
//	}
	
//	@Transactional
//	@Test
//	void findBookingByUserId() {
//		List<BookingDto> list= bookingServices.findAllBookingByUserId(3);
//		list.stream().forEach(System.out::println);
//	}
	
	
//	@Transactional
//	@Test
//	void findAvailabeRoomsIdsByFromAndToDateTest() {
//		
//		 String fromDate ="2022-03-22";  
//		 String toDate  ="2022-03-23";
//		 Date from = Date.valueOf(fromDate);
//		 Date to = Date.valueOf(toDate);
//		 List<Integer> listInt = bookingServices.findAvailabeRoomsIdsByFromAndToDate(from, to);
//		 List<RoomsDto> roomsList = roomServices.availableRoom(listInt);
//		 roomsList.stream().forEach(System.out::println);
//	}
//	
	
//	@Transactional
//	@Test
//	void findAvailabeBookingByFromAndToDateTest() {
//		
//		 String fromDate ="2022-03-01";  
//		 String toDate  ="2022-04-30";
//		 Date from = Date.valueOf(fromDate);
//		 Date to = Date.valueOf(toDate);
//		 CheckDto check = new CheckDto(from, to);
//		 List<BookingDto> bookingList= bookingServices.findAllBookingByDate(check);
//		 
//				 bookingList.stream().forEach(System.out::println);
//	}
//	
	
	
//	@Transactional
//	@Test
//	void addBooking() {
//		BookingDto newBooking = new BookingDto();
//		 String fromDate ="2022-03-22";  
//		 String toDate  ="2022-03-23";
//		 Date from = Date.valueOf(fromDate);
//		 Date to = Date.valueOf(toDate);
//		newBooking.setCheckIn(from);
//		newBooking.setCheckOut(to);
//		newBooking.setRoomId(2);
//		newBooking.setUserId(3);
//		newBooking.setStatus("approved");
//		newBooking.setPayment("paid");
//		newBooking.setRemark("abcd");
//		
//		 
//		System.out.println(bookingServices.addBooking(newBooking));
//		//u.getBookingList().stream().forEach(System.out::println);
//	}
//	
	
}


//List<Article> result = repository.findAllByPublicationDate(
//         new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-01"));
