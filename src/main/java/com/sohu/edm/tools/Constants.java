package com.sohu.edm.tools;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;


public class Constants {
		
	
	public static  String SESSION_USER= "USER";
	public static  int MAX_RECORD_PER_PAGE;
	public static  String LDAP_SERVER;
	public static  String EMS_SYS_ADDR;
	public static  String EMS_LOGIN_URL;
	public static  String DATA_START_TIME;
	
	static {
		try {
			
			InputStream f = Constants.class.getClassLoader()
					.getResourceAsStream("./conf.properties");
			
			Properties pros = new Properties();
			pros.load(f);
			MAX_RECORD_PER_PAGE=Integer.parseInt(pros.getProperty("MAX_RECORD_PER_PAGE"));
			LDAP_SERVER	=	pros.getProperty("LDAP_SERVER");
			EMS_SYS_ADDR	=	pros.getProperty("EMS_SYS_ADDR");
			EMS_LOGIN_URL	=	pros.getProperty("EMS_LOGIN_URL");	
			DATA_START_TIME	=	pros.getProperty("DATA_START_TIME");	
			pros.load(f);
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
	public static Integer getOpUserId(HttpServletRequest request){
		return Integer.valueOf((null==request.getSession().getAttribute((Constants.SESSION_USER))?"0":request.getSession().getAttribute(Constants.SESSION_USER).toString()));
	}
}
