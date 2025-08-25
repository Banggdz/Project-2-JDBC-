package com.javaweb.respository.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.respository.RentAreaRepository;
import com.javaweb.respository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionJDBCUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{
	
	@Override
	public List<RentAreaEntity> getValueByBuildingId(Long id){
		String sql="SELECT * FROM rentarea WHERE rentarea.buildingid = "+id;
		List<RentAreaEntity> rentAreas = new ArrayList<>();
		try(Connection conn = ConnectionJDBCUtil.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString())){	
			while(rs.next()) {
				RentAreaEntity areaEntity = new RentAreaEntity();
				areaEntity.setValue(rs.getString("value"));
				rentAreas.add(areaEntity);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rentAreas;
	}

}
