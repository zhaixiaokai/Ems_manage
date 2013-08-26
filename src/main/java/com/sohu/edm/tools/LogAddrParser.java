package com.sohu.edm.tools;

public class LogAddrParser {
	private String GRP_ID	=	"grp_id";
	private String GRP_NAME	=	"grp_name";
	private String MAIL_NUM	=	"mail_num";
	
	private String desc;
	
	public LogAddrParser(String desc){
		this.desc	=	desc;
	}
	
	public	String getGrpId(){
		return LogParser.getProperty(desc, GRP_ID);
	}
	public	String getGrpName(){
		return LogParser.getProperty(desc, GRP_NAME);
	}
	public	String getMailNum(){
		return LogParser.getProperty(desc, MAIL_NUM);
	}
}
