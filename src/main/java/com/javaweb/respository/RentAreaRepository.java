package com.javaweb.respository;

import java.util.List;

import com.javaweb.respository.entity.RentAreaEntity;

public interface RentAreaRepository {
	List<RentAreaEntity> getValueByBuildingId(Long id);
}
