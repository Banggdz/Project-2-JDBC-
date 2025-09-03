package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.utils.MapUtil;

import builder.BuildingSearchBuilder;
//Đánh đấu đây là 1 Bean (ko có hàm khởi tạo)
@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> params,List<String> typeCode) {
//		method chaining
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
				.setName(MapUtil.getObject(params, "name", String.class))
				.setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
				.setWard(MapUtil.getObject(params, "ward", String.class))
				.setStreet(MapUtil.getObject(params, "street", String.class))
				.setDistrictCode(MapUtil.getObject(params, "districtId", String.class))
				.setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
				.setManagerName(MapUtil.getObject(params, "managerName", String.class))
				.setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
				.setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
				.setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
				.setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
				.setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
				.setStaffId(MapUtil.getObject(params, "staffId", Long.class))
				.setTypeCode(typeCode)
				.build();
		return buildingSearchBuilder;
	}
}
