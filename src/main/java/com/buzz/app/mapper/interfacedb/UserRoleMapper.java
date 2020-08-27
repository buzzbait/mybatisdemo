package com.buzz.app.mapper.interfacedb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {

	List<HashMap<String,Object>> selectAll();
}
