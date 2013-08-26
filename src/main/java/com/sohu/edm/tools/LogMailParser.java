package com.sohu.edm.tools;

public class LogMailParser {
	private String BATCH_ID	=	"batch_id";
	private String MAIL_NAME	=	"mail_name";
	
	private String desc;
	
	public LogMailParser(String desc){
		this.desc	=	desc;
	}
	
	public String getBatchId(){
		return LogParser.getProperty(desc, BATCH_ID);
	}
	public String getMailName(){
		return LogParser.getProperty(desc, MAIL_NAME);
	}
}
