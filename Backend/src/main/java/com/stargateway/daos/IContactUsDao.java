package com.stargateway.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stargateway.entities.ContactUs;

public interface IContactUsDao extends JpaRepository<ContactUs, Integer>{

	ContactUs findById(int facilityId);
}
