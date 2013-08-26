package com.sohu.edm.dao.map;

import java.util.Date;

@SuppressWarnings("serial")
public class T_mail_send implements java.io.Serializable {
	private long id;
	private long task_id;
	private long batch_id;
	private int proj_id;
	private int drt_id;
	private int ent_id;
	private int org_id;
	private String mail_name;
	private String mail_subject;
	private String mail_fromname;
	private String mail_fromaddr;
	private String mail_file;
	private String mail_source;
	private String mail_style;
	private String unsubscribe_style;
	private String mail_tos;
	private int mail_num;
	private String mail_condition;
	private int send_type;
	private Date send_time;
	private Date start_time;
	private Date end_time;
	private String op_userid;
	private Date create_time;
	private Date update_time;
	private short deliveryStatus;
	private String sendlist;
	private String use_maillist;
	private int sc_num;
	private short upload_status;
	private int hook_num;
	private Date archive_time;
	private Date retpoint_time;
	private short archive_status;
	private String raw_message;
	private String attachment;
	
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public short getArchive_status() {
		return archive_status;
	}

	public void setArchive_status(short archiveStatus) {
		archive_status = archiveStatus;
	}

	public String getRaw_message() {
		return raw_message;
	}

	public void setRaw_message(String rawMessage) {
		raw_message = rawMessage;
	}

	public Date getRetpoint_time() {
		return retpoint_time;
	}

	public void setRetpoint_time(Date retpointTime) {
		retpoint_time = retpointTime;
	}

	public int getSc_num() {
		return sc_num;
	}

	public void setSc_num(int scNum) {
		sc_num = scNum;
	}

	public short getUpload_status() {
		return upload_status;
	}

	public void setUpload_status(short uploadStatus) {
		upload_status = uploadStatus;
	}

	public int getHook_num() {
		return hook_num;
	}

	public void setHook_num(int hookNum) {
		hook_num = hookNum;
	}

	public Date getArchive_time() {
		return archive_time;
	}

	public void setArchive_time(Date archiveTime) {
		archive_time = archiveTime;
	}

	public String getUse_maillist() {
		return use_maillist;
	}

	public void setUse_maillist(String useMaillist) {
		use_maillist = useMaillist;
	}

	public short getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(short deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getSendlist() {
		return sendlist;
	}

	public void setSendlist(String sendlist) {
		this.sendlist = sendlist;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getTask_id() {
		return task_id;
	}

	public void setTask_id(long taskId) {
		task_id = taskId;
	}
	public long getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(long batchId) {
		batch_id = batchId;
	}

	public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int projId) {
		proj_id = projId;
	}

	public int getDrt_id() {
		return drt_id;
	}

	public void setDrt_id(int drtId) {
		drt_id = drtId;
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

	public String getMail_name() {
		return mail_name;
	}

	public void setMail_name(String mailName) {
		mail_name = mailName;
	}

	public String getMail_subject() {
		return mail_subject;
	}

	public void setMail_subject(String mailSubject) {
		mail_subject = mailSubject;
	}

	public String getMail_fromname() {
		return mail_fromname;
	}

	public void setMail_fromname(String mailFromname) {
		mail_fromname = mailFromname;
	}

	public String getMail_fromaddr() {
		return mail_fromaddr;
	}

	public void setMail_fromaddr(String mailFromaddr) {
		mail_fromaddr = mailFromaddr;
	}

	public String getMail_file() {
		return mail_file;
	}

	public void setMail_file(String mailFile) {
		mail_file = mailFile;
	}

	public String getMail_source() {
		return mail_source;
	}

	public void setMail_source(String mailSource) {
		mail_source = mailSource;
	}

	public String getMail_style() {
		return mail_style;
	}

	public void setMail_style(String mailStyle) {
		mail_style = mailStyle;
	}

	public String getUnsubscribe_style() {
		return unsubscribe_style;
	}

	public void setUnsubscribe_style(String unsubscribeStyle) {
		unsubscribe_style = unsubscribeStyle;
	}

	public String getMail_tos() {
		return mail_tos;
	}

	public void setMail_tos(String mailTos) {
		mail_tos = mailTos;
	}

	public int getMail_num() {
		return mail_num;
	}

	public void setMail_num(int mailNum) {
		mail_num = mailNum;
	}

	public String getMail_condition() {
		return mail_condition;
	}

	public void setMail_condition(String mailCondition) {
		mail_condition = mailCondition;
	}

	public int getSend_type() {
		return send_type;
	}

	public void setSend_type(int sendType) {
		send_type = sendType;
	}

	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date sendTime) {
		send_time = sendTime;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date startTime) {
		start_time = startTime;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date endTime) {
		end_time = endTime;
	}

	public String getOp_userid() {
		return op_userid;
	}

	public void setOp_userid(String opUserid) {
		op_userid = opUserid;
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

}