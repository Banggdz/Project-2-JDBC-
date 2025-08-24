package com.javaweb.respository.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberUntil;
import com.javaweb.utils.StringUtil;
@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	
public static void joinTable(Map<String,Object> params,List<String> typeCode,StringBuilder sql) {
	String staffId = (String)params.get("staffId");
	if(staffId!=null) {
		sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
	}
	if(typeCode!=null && typeCode.size() != 0) {
		sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
		sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");		
	}
	String rentAreaTo = (String)params.get("areaTo");
	String rentAreaFrom = (String)params.get("areaFrom");
	if(StringUtil.checkString(rentAreaFrom) == true ||StringUtil.checkString(rentAreaTo) == true ) {
		sql.append(" INNER JOIN rentarea ON rentarea.buildingid = b.id ");
	}
	
}	
//Normal: Là trường dữ liệu có thể tìm kiếm trực tiếp trong bảng chính.
public static void queryNormal(Map<String,Object> params,StringBuilder where) {
	for(Map.Entry<String,Object> it : params.entrySet()) {
		if(!it.getKey().equals("staffId")&&!it.getKey().equals("typeCode")
				&&!it.getKey().startsWith("area")&&!it.getKey().startsWith("rentPrice")) {
			String value = it.getValue().toString();
			if(StringUtil.checkString(value)) {
				if(NumberUntil.isNumber(value)==true) {
					where.append(" AND b."+it.getKey()+" = "+value);
				}
				else {
					where.append(" AND b."+it.getKey()+" LIKE '%"+value+"%' ");
				}
			}
		}
	}
}

//Special: Là Field cần JOIN với bảng khác mới có thể tra cứu được.
public static void querySpecial(Map<String,Object> params,List<String> typeCode,StringBuilder where) {
	String staffId = (String)params.get("staffId");
	if(StringUtil.checkString(staffId)) {
		where.append(" AND assignmentbuilding.staffId = "+staffId);
	}
	String rentAreaTo = (String)params.get("areaTo");
	String rentAreaFrom = (String)params.get("areaFrom");
	if(StringUtil.checkString(rentAreaFrom)) {
		where.append(" AND rentarea.value >= " + rentAreaFrom);
	}
	if(StringUtil.checkString(rentAreaTo)) {
		where.append(" AND rentarea.value <= " + rentAreaTo);
	}
	String rentPriceTo = (String)params.get("rentPriceTo");
	String rentPriceFrom = (String)params.get("rentPriceFrom");
	if(StringUtil.checkString(rentAreaFrom) == true || StringUtil.checkString(rentAreaTo) == true) {
		where.append(" AND EXISTS (SELECT * FROM rentarea r where b.id = r.buildingid");
	if(StringUtil.checkString(rentPriceTo)) {
		where.append(" AND rentarea.value <= " + rentPriceTo);
	}
	if(StringUtil.checkString(rentPriceFrom)) {
		where.append(" AND rentarea.value >= " + rentPriceFrom);
	}   
		where.append(")");
	}
// Dùng exists để tránh trả về các phần tử trùng nhau do 1 building có nhiều rentarea, tránh phải dùng groupby để lọc
//	SELECT * FROM building b WHERE 1=1
//	  AND EXISTS (
//	      SELECT * FROM rentarea r 
//	      WHERE b.id = r.buildingid
//	      -- AND r.value >= rentAreaFrom  
//	      -- AND r.value <= rentAreaTo  
//	  )
 
//	java7 
//	if(typeCode != null && typeCode.size() !=0 ) {
//		List<String> code = new ArrayList<>();
//		for(String item : typeCode) {
//			code.add("'"+item+"'");		
//		}
//		where.append(" AND renttype.code IN("+String.join(",", code)+") ");
//	}
	//java8
	if(typeCode != null && typeCode.size() !=0 ) {
		where.append(" AND(");
		String sql = typeCode.stream().map(it -> "renttype.code LIKE" + "'%" + it + "%'").collect(Collectors.joining(" OR "));
		where.append(sql);
		where.append(") ");
	}
}

	@Override
	public List<BuildingEntity> findAll(Map<String,Object> params,List<String> typeCode) {
		StringBuilder sql = new StringBuilder(
			    "SELECT b.id, b.name, b.districtid, b.street, b.ward, " +
			    "b.numberofbasement, b.floorarea, b.managername, b.rentprice, " +
			    "b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b "
			);
		StringBuilder where = new StringBuilder("WHERE 1=1 ");
		joinTable(params,typeCode,sql);
		queryNormal(params,where);
		querySpecial(params,typeCode,where);
		sql.append(where);
		System.out.print(sql);
		List<BuildingEntity> result = new ArrayList<>();
		try(Connection conn = ConnectionJDBCUtil.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
				){
			while(rs.next()) {
				BuildingEntity buildingEntity  = new BuildingEntity();
				buildingEntity.setId(rs.getLong("b.id"));
				buildingEntity.setName(rs.getString("b.name"));
				buildingEntity.setWard(rs.getString("b.ward"));
				buildingEntity.setDistrictid(rs.getLong("b.districtid"));
				buildingEntity.setStreet(rs.getString("b.street"));
				buildingEntity.setFloorArea(rs.getLong("b.floorarea"));
				buildingEntity.setRentPrice(rs.getLong("b.rentprice"));
				buildingEntity.setServiceFee(rs.getLong("b.servicefee"));
				buildingEntity.setBrokerageFee(rs.getLong("b.brokeragefee"));
				buildingEntity.setManagerName(rs.getString("b.managername"));
				buildingEntity.setManagerPhoneNumber(rs.getString("b.managerphonenumber"));
				result.add(buildingEntity);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void DeleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BuildingEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
