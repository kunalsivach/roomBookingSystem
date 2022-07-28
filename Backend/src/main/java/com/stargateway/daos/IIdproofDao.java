package com.stargateway.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stargateway.entities.Idproof;

public interface IIdproofDao extends JpaRepository<Idproof, Integer>{

	Idproof findById(int id);
}
