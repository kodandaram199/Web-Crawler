package com.webcrawler.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CrawlerDb {
	
	private Connection connection;
    public CrawlerDb()
    {
	connection=null;
    }
    
    
    public Connection getConnection(){
	
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	try {
	    this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3309/webcrawler","root","pdr16418@@@###");
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return this.connection;
    }

}
