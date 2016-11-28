package com.webcrawler.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webcrawler.dao.WebCrawlerDao;
import com.webcrawler.model.WebCrawlerModel;
import com.webcrawler.util.CrawlerDb;
import com.webcrawler.util.MainPage;


/**
 * Servlet implementation class DataController
 */
@WebServlet("/DataController")
public class DataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DataController() {
        super();
        
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebCrawlerModel model=null;
		PrintWriter out=response.getWriter();
		boolean inserted=false;
		String URL=request.getParameter("url");
		WebCrawlerModel wcm=new WebCrawlerModel();
		MainPage mainpage=new MainPage();
		CrawlerDb db=new CrawlerDb();
		wcm.setURL(URL);
		String sql=null;
		String sql1="truncate record";
		Connection con =db.getConnection();
		Statement st = null;
		try {
			st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			boolean truncated=st.execute(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainpage.getUrls(URL);
	}

}

