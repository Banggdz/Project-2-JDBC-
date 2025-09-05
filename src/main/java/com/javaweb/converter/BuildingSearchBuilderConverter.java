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
	// public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> params,List<String> typeCode) {
	// 	BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
	// 			.setName((String) params.get("name"))
	//             .setFloorArea(params.get("floorArea") != null ? Long.parseLong(params.get("floorArea").toString()) : null)
	//             .setWard((String) params.get("ward"))
	//             .setStreet((String) params.get("street"))
	//             .setDistrictCode((String) params.get("districtId"))
	//             .setNumberOfBasement(params.get("numberOfBasement") != null ? Integer.parseInt(params.get("numberOfBasement").toString()) : null)
	//             .setManagerName((String) params.get("managerName"))
	//             .setManagerPhoneNumber((String) params.get("managerPhoneNumber"))
	//             .setRentPriceTo(params.get("rentPriceTo") != null ? Long.parseLong(params.get("rentPriceTo").toString()) : null)
	//             .setRentPriceFrom(params.get("rentPriceFrom") != null ? Long.parseLong(params.get("rentPriceFrom").toString()) : null)
	//             .setAreaFrom(params.get("areaFrom") != null ? Long.parseLong(params.get("areaFrom").toString()) : null)
	//             .setAreaTo(params.get("areaTo") != null ? Long.parseLong(params.get("areaTo").toString()) : null)
	//             .setStaffId(params.get("staffId") != null ? Long.parseLong(params.get("staffId").toString()) : null)
	//             .setTypeCode(typeCode)
	//             .build();
	// 	return buildingSearchBuilder;
	// }
}
