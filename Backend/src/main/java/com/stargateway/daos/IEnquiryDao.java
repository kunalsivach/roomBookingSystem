package com.stargateway.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stargateway.entities.Enquiry;
import com.stargateway.entities.Facility;

public interface IEnquiryDao extends JpaRepository<Enquiry, Integer>{

	Enquiry findByEnquiryId(int enquiryId);
}
