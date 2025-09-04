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
		// BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		// List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
		
		// List<BuildingDTO> result = new ArrayList<>();
		// for(BuildingEntity item : buildingEntities) {
		// 	BuildingDTO building = new BuildingDTO();

		// 	building.setName(item.getName());
		// 	building.setManagerName(item.getManagerName());
		// 	building.setManagerPhoneNumber(item.getManagerPhoneNumber());

		// 	building.setFloorArea(item.getFloorArea());
		// 	building.setRentArea(item.getRentArea());
		// 	building.setEmptyArea(item.getEmptyArea());

		// 	building.setRentPrice(item.getRentPrice());
		// 	building.setServiceFee(item.getServiceFee());
		// 	building.setBrokerageFee(item.getBrokerageFee());

		// 	result.add(building);
		// }
		return result;
	}
}
