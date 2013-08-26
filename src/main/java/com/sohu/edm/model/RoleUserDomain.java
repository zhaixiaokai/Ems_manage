package com.sohu.edm.model;

import com.sohu.edm.console.dao.map.Sys_user;

@SuppressWarnings("serial")
public class RoleUserDomain extends Sys_user {
	private int inRoleFlag;

	public int getInRoleFlag() {
		return inRoleFlag;
	}

	public void setInRoleFlag(int inRoleFlag) {
		this.inRoleFlag = inRoleFlag;
	}

}
