package com.stargateway.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stargateway.entities.RoomCategory;
import com.stargateway.entities.Rooms;

public interface IRoomsCategoryDao extends JpaRepository<RoomCategory, Integer>{

	RoomCategory findBycategoryId(int id);
	

}
