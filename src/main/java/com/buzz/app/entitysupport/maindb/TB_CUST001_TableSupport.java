package com.buzz.app.entitysupport.maindb;



import java.sql.JDBCType;
//import java.sql.Date;
import java.util.Date;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;


public class TB_CUST001_TableSupport {
	public static final TB_CUST001  tb_cust001 = new TB_CUST001();
	public static final SqlColumn<Integer> seq = tb_cust001.seq;
    public static final SqlColumn<String> id = tb_cust001.id;
    public static final SqlColumn<String> name = tb_cust001.name;
    public static final SqlColumn<Integer> grade = tb_cust001.grade;
    public static final SqlColumn<Date> updDtm = tb_cust001.updDtm;
    public static final SqlColumn<Date> crtDtm = tb_cust001.crtDtm;
    
	public static final class TB_CUST001 extends SqlTable {
    	  
        public final SqlColumn<Integer> seq = column("SEQ", JDBCType.INTEGER);
        public final SqlColumn<String> id = column("ID", JDBCType.VARCHAR);
        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);
        public final SqlColumn<Integer> grade = column("GRADE", JDBCType.INTEGER);
        
        /***********************************************************************************************
         * JDBCType.DATE 는 yyyy-mm-dd 까지만 저장 된다.
         * 시간까지 저장되는 필드타입이면 TIMESTAMP로 지정 한다.
         * java 타입도 날짜만 저장 되면 import java.sql.Date 를 사용하지만 시간까지 가져온다면
         * import java.util.Date 를 사용한다.
         ***********************************************************************************************/
        public final SqlColumn<Date> updDtm = column("UPD_DTM", JDBCType.TIMESTAMP);
        public final SqlColumn<Date> crtDtm = column("CRT_DTM", JDBCType.TIMESTAMP);
        
        public TB_CUST001() {
            super("TB_CUST001");
        }
    }
}
