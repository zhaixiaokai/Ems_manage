package com.sohu.edm.dao.map;

import java.util.Date;


@SuppressWarnings("serial")
public class T_cost_record implements java.io.Serializable {
	private long id;
	private int ent_id;
	private int org_id;
	private String org_name;
	private String user_id;
	private String csm_type;
	private String points;
	private int batch_id;
	private int proj_id;
	private String explain;
	private Date create_time;
	private Date update_time;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getEnt_id() {
		return ent_id;
	}
	public void setEnt_id(int entId) {
		ent_id = entId;
	}
	public int getOrg_id() {
		return org_id;
	}
	public void setOrg_id(int orgId) {
		org_id = orgId;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String orgName) {
		org_name = orgName;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date updateTime) {
		update_time = updateTime;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getCsm_type() {
		return csm_type;
	}
	public void setCsm_type(String csmType) {
		csm_type = csmType;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public int getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(int batchId) {
		batch_id = batchId;
	}
	public int getProj_id() {
		return proj_id;
	}
	public void setProj_id(int projId) {
		proj_id = projId;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}