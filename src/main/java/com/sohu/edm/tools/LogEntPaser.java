package com.sohu.edm.tools;

public class LogEntPaser {
	private String ENT_ID	=	"ent_id";
	private String ENT_NAME	=	"ent_name";
	private String POINT	=	"point";
	private String desc;
	public LogEntPaser(String desc){
		this.desc=desc;
	}
	
	public String getEntId(){
		return LogParser.getProperty(desc,ENT_ID);
	}
	public String getEntName(){
		return LogParser.getProperty(desc, ENT_NAME);
	}
	public String getPoint(){
		return LogParser.getProperty(desc, POINT);
	}
}
