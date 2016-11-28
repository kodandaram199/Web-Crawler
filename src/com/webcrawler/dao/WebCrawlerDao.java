package com.webcrawler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.webcrawler.model.WebCrawlerModel;
import com.webcrawler.util.CrawlerDb;

public class WebCrawlerDao {

	public static Boolean insertUrl(WebCrawlerModel model,String sql)throws SQLException
	{
		CrawlerDb db=new CrawlerDb();
		Connection con =db.getConnection();
		PreparedStatement ps=null;
		int i=0;
		
		try {
			ps=con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ps.setString(1, model.getURL());
		System.out.println(model.getURL());
		i=ps.executeUpdate();
		
		if(i>0)
		{
			return true;
		}
		
		return false;
	}

}
