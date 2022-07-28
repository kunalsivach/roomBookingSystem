package com.stargateway.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stargateway.entities.Enquiry;
import com.stargateway.entities.Facility;
import com.stargateway.entities.Offers;

public interface IOffersDao extends JpaRepository<Offers, Integer>{

	Offers findByOfferId(int offerId);
}
