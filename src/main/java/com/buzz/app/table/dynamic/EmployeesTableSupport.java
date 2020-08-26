package com.buzz.app.table.dynamic;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EmployeesTableSupport {

	public static final Eemployees employees = new Eemployees();
    public static final SqlColumn<Integer> id = employees.id;
    public static final SqlColumn<String> firstName = employees.firstName;
    public static final SqlColumn<String> lastName = employees.lastName;
    public static final SqlColumn<String> email = employees.email;
    
	public static final class Eemployees extends SqlTable {
    	  
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);
        public final SqlColumn<String> firstName = column("firstName", JDBCType.VARCHAR);
        public final SqlColumn<String> lastName = column("lastName", JDBCType.VARCHAR);
        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);
        
        public Eemployees() {
            super("employees");
        }
    }
	
}
