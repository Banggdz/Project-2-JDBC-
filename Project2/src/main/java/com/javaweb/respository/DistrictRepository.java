package com.javaweb.respository;

import com.javaweb.respository.entity.DistrictEntity;

public interface DistrictRepository {
	DistrictEntity findNameById(Long id);
}
