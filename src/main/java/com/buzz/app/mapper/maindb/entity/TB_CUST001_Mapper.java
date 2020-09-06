package com.buzz.app.mapper.maindb.entity;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import com.buzz.app.dto.maindb.entity.TB_CUST001_Entity;

import static com.buzz.app.entitysupport.maindb.TB_CUST001_TableSupport.*;

import java.util.Optional;

@Mapper
public interface TB_CUST001_Mapper {

	/* TB_CUST001 테이블 SELECT */
	@SelectProvider(type=SqlProviderAdapter.class, method="select")	
	Optional<TB_CUST001_Entity> selectOne(SelectStatementProvider selectStatement);
	
	BasicColumn[] selectList =
		    BasicColumn.columnList(seq, id, name, grade, updDtm,crtDtm);
	
	default Optional<TB_CUST001_Entity> selectOne(SelectDSLCompleter completer) {
	    return MyBatis3Utils.selectOne(this::selectOne, selectList , tb_cust001, completer);
	}
	
	
	
	/* TB_CUST001 테이블 INSERT */
	@InsertProvider(type=SqlProviderAdapter.class, method="insert")
	int insert(InsertStatementProvider<TB_CUST001_Entity> insertStatement);
	
	default int insert(TB_CUST001_Entity tb_cust001Dto) {
	    return MyBatis3Utils.insert(this::insert, tb_cust001Dto,tb_cust001 , c -> 
	        c.map(id).toProperty("id")
	        .map(name).toProperty("name")
	        .map(grade).toProperty("grade")
	        .map(updDtm).toPropertyWhenPresent("updDtm", tb_cust001Dto::getUdpDtm) /*값이 있는 경우에만*/
	        .map(crtDtm).toProperty("crtDtm")	        
	    );
	}
	
	/* TB_CUST001 테이블 UPDATE */
	@UpdateProvider(type=SqlProviderAdapter.class, method="update")
	int update(UpdateStatementProvider updateStatement);
	
	default int update(UpdateDSLCompleter completer) {
	    return MyBatis3Utils.update(this::update, tb_cust001, completer);
	}
	
	/* TB_CUST001 테이블 DELETE */
	@DeleteProvider(type=SqlProviderAdapter.class, method="delete")
	int update(DeleteStatementProvider deleteStatement);
	
	default int delete(DeleteDSLCompleter completer) {
	    return MyBatis3Utils.deleteFrom(this::update, tb_cust001, completer);
	}
}
