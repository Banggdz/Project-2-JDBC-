package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

@RestController
public class NewAPI {
	@Autowired
	private BuildingService buildingService;
	@GetMapping("/api/building/")
	public Object getBuilding(@RequestParam Map<String,Object> params,
            @RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		if (typeCode != null) {
				params.put("typeCode", typeCode);  // cho vào lại map
			}
		return buildingService.findAll(params,typeCode);
}

}