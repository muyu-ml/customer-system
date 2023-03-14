package com.lcl.galaxy.outsouring.shanghai.template;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementCallback {
	
	Object handleStatement(Statement statement) throws SQLException;  
}
