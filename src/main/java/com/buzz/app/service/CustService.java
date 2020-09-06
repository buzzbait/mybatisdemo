package com.buzz.app.service;

import java.util.Date;
//import java.sql.Date;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buzz.app.dto.maindb.entity.TB_CUST001_Entity;
import com.buzz.app.mapper.maindb.entity.TB_CUST001_Mapper;
import com.buzz.app.entitysupport.maindb.TB_CUST001_TableSupport;

//이클립스가 자동으로 참조하지 못하는 경우가 있기 때문에 주의
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
//import static org.mybatis.dynamic.sql.SqlBuilder.isLessThan;

@Service
public class CustService {

	@Autowired
	private TB_CUST001_Mapper tbCust001Mapper;
	
	public HashMap<String,Object> findCustomer()
	{
		HashMap<String, Object> result = new  HashMap<String, Object>();
			
		
		Optional<TB_CUST001_Entity> findCustomer = this.tbCust001Mapper.selectOne( c ->
			c.where(TB_CUST001_TableSupport.name ,isEqualTo("홍길동"))
		);
	    
		
		result.put("status", 0);
		result.put("data", findCustomer);
		
		
		return result;
	}
	
	public HashMap<String,Object> newCustomer(){
		
		HashMap<String, Object> result = new  HashMap<String, Object>();

		//
		//java.sql.Date nowDate = new java.sql.Date(new java.util.Date().getTime());
		Date nowDate = new Date();
				
		TB_CUST001_Entity tbCust001Dto = TB_CUST001_Entity.builder()
				.id("ddssss")
				.name("홍길동")
				.grade(10)
				.crtDtm(nowDate)
				.build();
		
		tbCust001Mapper.insert(tbCust001Dto);
		
		
		result.put("status", 0);		
		
		return result;
	}
	
	public HashMap<String,Object> updateCustomer(){
		
		HashMap<String, Object> result = new  HashMap<String, Object>();

		Date nowDate = new Date();		
		
		tbCust001Mapper.update(c ->
			c.set(TB_CUST001_TableSupport.grade).equalTo(12)
			.set(TB_CUST001_TableSupport.updDtm).equalTo(nowDate)
		    .where(TB_CUST001_TableSupport.seq, isEqualTo(10))	); 
					
		result.put("status", 0);		
		
		return result;
	}
	
	public HashMap<String,Object> deleteCustomer(){
		
		HashMap<String, Object> result = new  HashMap<String, Object>();
			
		//seq로 삭제
		tbCust001Mapper.delete(c ->			
		    c.where(TB_CUST001_TableSupport.seq, isEqualTo(10))	); 
		
		//id로 삭제
		tbCust001Mapper.delete(c ->			
	    	c.where(TB_CUST001_TableSupport.id, isEqualTo("id0001")));
					
		result.put("status", 0);		
		
		return result;
	}
}
