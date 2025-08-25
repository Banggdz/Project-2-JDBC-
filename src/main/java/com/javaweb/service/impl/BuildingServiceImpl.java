package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
@Service
public class BuildingServiceImpl implements BuildingService{
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;
	
	@Override
	public List<BuildingDTO> findAll(Map<String,Object> params,List<String> typeCode) {
		List<BuildingEntity> BuildingEntites = buildingRepository.findAll(params,typeCode);
		List<BuildingDTO> result = new ArrayList<>();
		for(BuildingEntity item:BuildingEntites) {
			BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
			result.add(building);
		}
		return result;
	}
}
