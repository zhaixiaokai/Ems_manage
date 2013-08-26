package com.sohu.edm.tools;

public class LogUserParser {
	private	String USER_ID	="user_id";
	private String ORG_BEF	=	"org_before";
	private String ORG_AFT	=	"org_after";
	private String desc;
	
	
	public LogUserParser(String desc){
		this.desc	=	desc;
	}
	
	public String getUserId(){
		return LogParser.getProperty(desc, USER_ID);
	}

	public String getOrgBefore(){
		return LogParser.getProperty(desc, ORG_BEF);
	}
	
	public String getOrgAfter(){
		return LogParser.getProperty(desc, ORG_AFT);
	}
}
