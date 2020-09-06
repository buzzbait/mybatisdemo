package com.buzz.app.dto.maindb.entity;



import java.util.Date;
//import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TB_CUST001_Entity {
	
	private int seq;
    private String id;
    private String name;
    private int grade;
    private Date	udpDtm;
    private Date   crtDtm;
}
