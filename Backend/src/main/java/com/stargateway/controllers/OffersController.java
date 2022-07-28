package com.stargateway.controllers;

import java.util.List;
import java.util.Map;

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

import com.stargateway.dtos.FacilityDto;
import com.stargateway.dtos.IdproofDto;
import com.stargateway.entities.Enquiry;
import com.stargateway.entities.Facility;
import com.stargateway.entities.Offers;
import com.stargateway.services.EnquiryServices;
import com.stargateway.services.FacilityServices;
import com.stargateway.services.OffersServices;
import com.stargateway.utils.Response;

@CrossOrigin(origins = "*")
@RestController	
public class OffersController {

	@Autowired
	private OffersServices offersServices;

	@GetMapping("/offers/{id}")
	public ResponseEntity<?> offersById(@PathVariable("id") int offersId) {
		//FacilityDto facilityDto  = facilityServices.findFacilityById(facilityId);
		Offers offers = offersServices.findOffersById(offersId);
		if(offers==null) {
			return Response.status(HttpStatus.NOT_FOUND);
		}
		return Response.success(offers);
	}
	
	@GetMapping("/offers/alloffers")
	public ResponseEntity<?> allOffers() {
		List<Offers> offersList = offersServices.findAllOffers();
		if(offersList==null) {
			return Response.status(HttpStatus.NOT_FOUND);
		}
		return Response.success(offersList);
	}

	@PostMapping("/offers/add")
	public ResponseEntity<?> addEnquiry(@RequestBody Offers offers) {
		Map<String, Object> result = offersServices.addOffers(offers);
		if(result.containsValue(1)) {
			return Response.success(result);
		}
		return Response.status(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/offers/edit/{id}")
	public ResponseEntity<?> editOffers(@PathVariable("id") int id, @RequestBody Offers offers) {
		Map<String, Object> result = offersServices.editOffers(id, offers);
		if(result.containsValue(1)) {
			return Response.success(result);
		}
		return Response.status(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/offers/delete/{id}")
	public ResponseEntity<?> deleteOffers(@PathVariable("id") int id) {
		Map<String, Object> result = offersServices.deleteOffers(id);
		if(result.containsValue(1)) {
			return Response.success(result);
		}
		return Response.status(HttpStatus.NOT_FOUND);
	}
}
