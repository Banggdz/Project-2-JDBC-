package com.javaweb.respository;

import java.util.List;
import java.util.Map;

import com.javaweb.respository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll (Map<String,Object>params,List<String> typeCode);
	List<BuildingEntity> findAll ();
	void DeleteById(Long id);
}
