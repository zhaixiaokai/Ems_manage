package com.sohu.edm.tools;

import net.sf.json.JSONObject;

public class LogParser {
	
	public static String getProperty(String desc,String property){
		String retStr	=	"--";
		try{
			JSONObject jsonObject = JSONObject.fromObject(desc); 
			retStr	=	jsonObject.get(property).toString();
		}catch (Exception e) {
			//e.printStackTrace();
		}
		return retStr;
	}
	
	public static String parseOPDomain(int opType,String desc){
		String retStr	=	"--";
		switch(opType){
			case	1:
				break;
			case	2:
				retStr	=	new LogUserParser(desc).getUserId();
				break;
			case	3:
				LogOrgParser	orgPar	=	new	LogOrgParser(desc);
				retStr	=	orgPar.getOrgName()+"("+orgPar.getOrgId()+")";
				break;
			case	4:
				LogAddrParser	addPar	=	new	LogAddrParser(desc);
				retStr	=	addPar.getGrpName()+"("+addPar.getGrpId()+")";
				break;
			case	5:
				LogMailParser	mailPar	=	new	LogMailParser(desc);
				retStr	=	mailPar.getMailName()+"("+mailPar.getBatchId()+")";
				break;
			case	6:
				LogEntPaser	entPar	=	new	LogEntPaser(desc);
				retStr	=	entPar.getEntName()+"("+entPar.getEntId()+")";
				break;
			default:
				break;
		}
		return retStr;
	}
}
