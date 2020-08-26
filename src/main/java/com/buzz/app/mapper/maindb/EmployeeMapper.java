package com.buzz.app.mapper.maindb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;


@Mapper
public interface EmployeeMapper {

	List<HashMap<String,Object>> selectAll();
	
	
	@SelectProvider(type=SqlProviderAdapter.class, method="select")		
	List<HashMap<String,Object>> select(SelectStatementProvider selectStatement);
	
	@UpdateProvider(type=SqlProviderAdapter.class, method="update")		
	int update(UpdateStatementProvider updateStatement);
	
	
	
	
}
