package com.buzz.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.mybatis.dynamic.sql.render.RenderingStrategies;

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buzz.app.mapper.maindb.EmployeeMapper;
//import com.buzz.app.table.dynamic.EmployeesTableSupport;
import static com.buzz.app.table.dynamic.EmployeesTableSupport.*;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLessThan;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.sql.SqlBuilder.update;

@Service
@Transactional
public class DemoService {

	@Autowired
	private EmployeeMapper employeeMapper;
		
	public HashMap<String,Object> getAllEmplpyee() {
		
		HashMap<String, Object> result = new  HashMap<String, Object>();
		
		List< HashMap<String, Object>> queryResult = this.employeeMapper.selectAll();
		
		result.put("status", 0);
		result.put("data",queryResult);		
		
		return result;
	}
	
	public HashMap<String,Object> selectDynamic() {
		
		HashMap<String, Object> result = new  HashMap<String, Object>();
				
		String findfirstName = "";
				
		
		SelectStatementProvider selectStatement = select(employees.allColumns())
                .from(employees)
                .where(employees.firstName, isEqualTo(findfirstName).when(Objects::nonNull) )
                .build()
                .render(RenderingStrategies.MYBATIS3);
				 
		
		List< HashMap<String, Object>> queryResult = this.employeeMapper.select(selectStatement);
				
		
		result.put("status", 0);
		result.put("data",queryResult);		
		
		return result;
	}
	
	public HashMap<String,Object> updateDynamic(){
		HashMap<String, Object> result = new  HashMap<String, Object>();

		UpdateStatementProvider updateStatement = update(employees)
			    .set(employees.lastName).equalTo("Alice")
			    //.set(lastName).equalToWhenPresent(record::getLastName)
			    .where(employees.id, isEqualTo(1))			   
			    .build()
			    .render(RenderingStrategies.MYBATIS3);
		
		this.employeeMapper.update(updateStatement);
		
		result.put("status", 0);
		result.put("data","success");		
		
		return result;
	}
	
}
