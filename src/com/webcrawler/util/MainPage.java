package com.webcrawler.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webcrawler.dao.WebCrawlerDao;
import com.webcrawler.model.WebCrawlerModel;

public class MainPage {
	
	WebCrawlerModel model=null;
	
	boolean inserted=false;
	
	
	CrawlerDb db=new CrawlerDb();
	
	String sql=null;
	
	Connection con =db.getConnection();
	Statement st = null;
	public void getUrls(String URL)
	{
	String sql = "select * from record where URL = '"+URL+"'";
	
	
	try {
		Statement	st = con.createStatement();
	
	ResultSet rs1=st.executeQuery(sql);
	if(rs1.next()){

	}else{
		//store the URL to database to avoid parsing again
		
	sql="insert into webcrawler values(null,?)";
	
		
		inserted=WebCrawlerDao.insertUrl(model, sql);
	}
	}
	 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(inserted)
	{
		Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		if(doc.text().contains("research")){
			System.out.println(URL);
		}

		//get all links and recursively call the processPage method
		Elements links = doc.select("a[href]");
		for(Element link: links){
			if(link.attr("href").contains("mit.edu"))
				getUrls(link.attr("abs:href"));
		}
	}
	
	}

}
