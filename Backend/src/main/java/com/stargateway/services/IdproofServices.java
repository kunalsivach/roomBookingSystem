package com.stargateway.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stargateway.daos.IIdproofDao;
import com.stargateway.daos.IRoomsCategoryDao;
import com.stargateway.daos.IRoomsDao;
import com.stargateway.dtos.DtoEntityConverter;
import com.stargateway.dtos.FacilityDto;
import com.stargateway.dtos.IdproofDto;
import com.stargateway.dtos.RoomsDto;
import com.stargateway.entities.Facility;
import com.stargateway.entities.Idproof;
import com.stargateway.entities.RoomCategory;
import com.stargateway.entities.Rooms;

@Transactional
@Service
public class IdproofServices {

	@Autowired
	private IIdproofDao  idproofDao;
	@Autowired
	private DtoEntityConverter converter;
	
	public IdproofDto findIdproofById(int id) {
		 Idproof idproof = null;
		 try {
			 idproof = idproofDao.findById(id);
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return converter.IdproofEntityToIdproofDto(idproof);
	}
	
	public List<IdproofDto> findAllIdproof(){
		List<Idproof> idproofList = null;
		List<IdproofDto> idproofDtoList = null;
		try {
			idproofList = idproofDao.findAll();
			idproofDtoList = idproofList.stream().map(Idproof -> converter.IdproofEntityToIdproofDto(Idproof)).collect(Collectors.toList());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return idproofDtoList;
	}
	
	public Map<String, Object> addIdproof(IdproofDto idproofDto) {
		if(idproofDto!=null) {
			Idproof idproofToBeSaved = null;
			Idproof idproofToSaved =null;
			try {
				idproofToBeSaved = converter.IdproofDtoToIdproofEntity(idproofDto);
				idproofToSaved = idproofDao.save(idproofToBeSaved);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return Collections.singletonMap("addedRows", 1);
		}
		return Collections.singletonMap("addedRows", 0);
	}

	public Map<String, Object> editIdproof(int id, IdproofDto idproofDto) {
		if (idproofDao.existsById(id)) {
			Idproof idproofToBeUpdated = null;
			Idproof idproofToUpdated = null;
			try {
				idproofToBeUpdated = converter.IdproofDtoToIdproofEntity(idproofDto);
				idproofToUpdated = idproofDao.save(idproofToBeUpdated);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return Collections.singletonMap("updatedRows", 1);
		}
		return Collections.singletonMap("updatedRows", 0);
	}

	public Map<String, Object> deleteIdproof(int id) {
		if (idproofDao.existsById(id)) {
			idproofDao.deleteById(id);
			return Collections.singletonMap("deletedRows", 1);
		}
		return Collections.singletonMap("deletedRows", 0);
	}
	
}
