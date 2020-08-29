package com.buzz.app.mapper.maindb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import static com.buzz.app.table.dynamic.EmployeesTableSupport.*;


@Mapper
public interface EmployeeMapper {

	List<HashMap<String,Object>> selectAll();
	
	
	@SelectProvider(type=SqlProviderAdapter.class, method="select")		
	List<HashMap<String,Object>> select(SelectStatementProvider selectStatement);
	
	@UpdateProvider(type=SqlProviderAdapter.class, method="update")		
	int update(UpdateStatementProvider updateStatement);
	
	
	//함수형으로 구현한 COUNT
	@SelectProvider(type=SqlProviderAdapter.class, method="select")
	long count(SelectStatementProvider selectStatement);
	
	default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, employees, completer);
    }
	
}
