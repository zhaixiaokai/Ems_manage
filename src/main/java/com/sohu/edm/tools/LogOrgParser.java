package com.sohu.edm.tools;

public class LogOrgParser {
	private String ORG_ID	=	"org_id";
	private String ORG_NAME	=	"org_name";
	private String POINT	=	"point";
	private String NAME_BEF	=	"name_before";
	private String NAME_AFT	=	"name_after";
	
	private String desc;
	public LogOrgParser(String desc){
		this.desc	=	desc;
	}
	
	public String getOrgId(){
		return LogParser.getProperty(desc, ORG_ID);
	}
	public String getOrgName(){
		return LogParser.getProperty(desc, ORG_NAME);
	}
	public String getOrgPoint(){
		return LogParser.getProperty(desc, POINT);
	}
	public String getOrgNameBef(){
		return LogParser.getProperty(desc, NAME_BEF);
	}
	public String getOrgNameAft(){
		return LogParser.getProperty(desc, NAME_AFT);
	}
}
