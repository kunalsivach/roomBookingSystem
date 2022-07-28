package com.stargateway.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stargateway.entities.Facility;

public interface IFacilityDao extends JpaRepository<Facility, Integer>{

	Facility findByFacilityId(int facilityId);
}
