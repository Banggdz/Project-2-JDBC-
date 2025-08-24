package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.respository.DistrictRepository;
import com.javaweb.respository.RentAreaRepository;
import com.javaweb.respository.entity.BuildingEntity;
import com.javaweb.respository.entity.DistrictEntity;
import com.javaweb.respository.entity.RentAreaEntity;

//annotation gốc, dùng chung cho bất kì class nào, đánh dấu class là bean
@Component
public class BuildingDTOConverter {
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
//		BuildingDTO building = new BuildingDTO();
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictid());
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtEntity.getName());
		List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(item.getId());
		String areaResult = rentAreas.stream()
		    .map(it -> it.getValue().toString())
		    .collect(Collectors.joining(","));
		building.setRentArea(areaResult);
		
		
//		building.setName(item.getName());
//		building.setBrokerageFee(item.getBrokerageFee());
//		building.setEmptyArea(item.getEmptyArea());
//		building.setFloorArea(item.getFloorArea());
//		building.setManagerName(item.getManagerName());
//		building.setManagerPhoneNumber(item.getManagerPhoneNumber());

		return building;
	}
}
