<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<%@ page import="java.util.Date" %>
<HTML>
<head>
<script type="text/javascript">
	function comit(){
		if(validate()){
			$("#entName").val($.trim($("#entName").val()));
			$("#freq").val($.trim($("#freq").val()));
			$("#singleTransNum").val($.trim($("#singleTransNum").val()));
			$("#addForm").submit();
		}else{
			alert("请填写正确信息！");
		}
			
	}
	function save(){
		if(validate()){
			$("#entName").val($.trim($("#entName").val()));
			$("#freq").val($.trim($("#freq").val()));
			$("#singleTransNum").val($.trim($("#singleTransNum").val()));
			$("#addForm").submit();
		}else{
			alert("请填写正确信息！");
		}
	}
	function validate(){
		if(!validateEntName())
			return false;
		if(!validateDomain())
			return false;
		if(!validateAdminAccount())
			return false;
		if(!validatePsw())
			return false;
		if(!validateOnce())
			return false;
		if(!validateFreq())
			return false;
		return true;
	}
	function IsNum(num){
		  var reNum=/^\d*$/;
		  return(reNum.test(num));
	}

	function validateEntName(){
		if(null==$.trim($("#entName").val())||""==$.trim($("#entName").val())){
			$("#entNameInfo").html("企业名称不能为空！");
			$("#entName").val("");
			return false;
		}
		if($.trim($("#entName").val()).length>30){
			$("#entNameInfo").html("企业名称长度不超过30个字符");
			return false;
		}
		if($.trim("${operate}") == $.trim("add")){
			emsDwr.getEntNameNun($.trim($("#entName").val()),function(num){
				if(num>0){
					$("#entNameInfo").html("企业名称重复！");
					$("#entName").val("");
					return false;
				}
				$("#entNameInfo").html("");
			});
		}else{
			emsDwr.getEntNameNunModify($.trim($("#entName").val()),${eid},function(num){
				if(num>0){
					$("#entNameInfo").html("企业名称重复！");
					$("#entName").val("");
					return false;
				}
				$("#entNameInfo").html("");
			});
		}
		return true;
	}
	function validateDomain(){
		if(null==$("#domain").val()||""==$("#domain").val()){
			$("#domainInfo").html("发送域名不能为空！");
			return false;
		}else if(!(/^([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test($("#domain").val()))){
			$("#domainInfo").html("域名格式不正确，例:(sohu.com)");
			$("#domain").val("");
			return false;
		}
		$("#domainInfo").html("");
		return true;
	}
	function validateAdminAccount(){
		if(null==$("#adminAccount").val()||""==$("#adminAccount").val()){
			$("#adminAccountInfo").html("管理员用户名不能为空！");
			return false;
		}else if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test($("#adminAccount").val()))){
			$("#adminAccountInfo").html("邮箱格式");
			$("#adminAccount").val("");
			return false;
		}
		$("#adminAccountInfo").html("");
		if($.trim("${operate}") == $.trim("add")){
			emsDwr.getAdminAccountNum($("#adminAccount").val(),function(num){
				if(num>0){
					$("#adminAccountInfo").html("管理员用户名重复！");
					$("#adminAccount").val("");
					return false;
				}
				$("#adminAccountInfo").html("");
			});
		}else{
			emsDwr.getAdminAccountNumModify($("#adminAccount").val(),${eid},function(num){
				if(num>0){
					$("#adminAccountInfo").html("管理员用户名重复！");
					$("#adminAccount").val("");
					return false;
				}
				$("#adminAccountInfo").html("");
			});
		}
		return true;
	}
	function validatePsw(){
		if(null==$("#password").val()||""==$("#password").val()){
			$("#passwordInfo").html("管理员密码不能为空！");
			return false;
		}
		if($("#password").val().length<6||$("#password").val().length>20){
			$("#passwordInfo").html("6~20个字符");
			$("#password").val("");
			return false;
		}
		$("#passwordInfo").html("");
		return true;
	}
	function validateFreq(){
		if($.trim($("#freq").val())=="")	return true;
		if(!IsNum($.trim($("#freq").val()))){
			$("#freqInfo").html("必须为数字!");
			return false;
		}
		if($("#freq").val()>100000000){
			$("#freqInfo").html("不能超过100000000!");
			$("#freq").val("");		
			return false;			
		}
		$("#freqInfo").html("");
		return true;
	}
	function validateOnce(){
		if($.trim($("#singleTransNum").val())=="")	return true;
		if(!IsNum($.trim($("#singleTransNum").val()))){
			$("#singleTransNumInfo").html("必须为数字!");
			return false;
		}
		if($("#singleTransNum").val()>100000000){
			$("#singleTransNumInfo").html("不能超过100000000!");
			$("#singleTransNum").val("");		
			return false;
		}
		$("#singleTransNumInfo").html("");
		return true;
	}
	$(document).ready(function(){
		$("#entName").bind('blur',function(){
			validateEntName();
		});

		$("#domain").bind('blur',function(){
			validateDomain();
		});
		$("#adminAccount").bind('blur',function(){
			validateAdminAccount();
		});
		
		$("#password").bind('blur',function(){
			validatePsw();
		});


		$("#freq").bind('blur',function(){
			validateFreq();
		});

		$("#singleTransNum").bind('blur',function(){
			validateOnce();
		});
		$("#trade").val(${entInfo.trade});
		$("#unit").val(${entInfo.unit});
	});
</script>
</head>
<BODY>
<h2>
<c:choose>
	<c:when test="${operate=='add'}">添加企业</c:when>
	<c:otherwise>修改企业信息</c:otherwise>
</c:choose>
</h2>

<form 
<c:choose>
<c:when test="${operate=='add'}"> action="/ent/addAction"</c:when>
<c:otherwise>action="/ent/modifyAction"</c:otherwise>
</c:choose>
 id="addForm" method="post">
 <input type="hidden" value="${entInfo.id}" name="eid">
<table>
	<tr>
		<td>开户时间：</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd"  value='<%=new Date()%>'></fmt:formatDate></td>
		<td></td>
	</tr>
	<tr>
		<td>企业名称：</td>
		<td><input type="text" name="entName" value="${entInfo.entName }" id="entName"></input></td>
		<td><span style="color: red;">*</span></td>
		<td id="entNameInfo" style="color: red;"></td>
	</tr>
	<tr>
		<td>所属行业：</td>
		<td><select style="width: 155px;" name="trade" id="trade">
			<option value="0">------请选择-----</option>
			<option value="1">计算机硬件及网络设备</option>
			<option value="2">计算机软件</option>
			<option value="3">IT服务（系统/数据/维护）/多领域经营</option>
			<option value="4">互联网/电子商务</option>
			<option value="5">网络游戏</option>
			<option value="6">通讯（设备/运营/增值服务）</option>
			<option value="7">电子技术/半导体/集成电路</option>
			<option value="8">仪器仪表及工业自动化</option>
			<option value="9">金融/银行/投资/基金/证券</option>
			<option value="10">保险</option>
			<option value="11">房地产/建筑/建材/工程</option>
			<option value="12">家居/室内设计/装饰装潢</option>
			<option value="13">物业管理/商业中心</option>
			<option value="14">广告/会展/公关/市场推广</option>
			<option value="15">媒体/出版/影视/文化/艺术</option>
			<option value="16">印刷/包装/造纸</option>
			<option value="17">咨询/管理产业/法律/财会</option>
			<option value="18">教育/培训</option>
			<option value="19">检验/检测/认证</option>
			<option value="20">中介服务</option>
			<option value="21">贸易/进出口</option>
			<option value="22">零售/批发</option>
			<option value="23">快速消费品（食品/饮料/烟酒/化妆品）</option>
			<option value="24">耐用消费品（服装服饰/纺织/皮革/家具/家电）</option>
			<option value="25">办公用品及设备</option>
			<option value="26">礼品/玩具/工艺美术/收藏品</option>
			<option value="27">大型设备/机电设备/重工业</option>
			<option value="28">加工制造（原料加工/模具）</option>
			<option value="29">汽车/摩托车（制造/维护/配件/销售/服务）</option>
			<option value="30">交通/运输/物流</option>
			<option value="31">医药/生物工程</option>
			<option value="32">医疗/护理/美容/保健</option>
			<option value="33">医疗设备/器械</option>
			<option value="34">酒店/餐饮</option>
			<option value="35">娱乐/体育/休闲</option>
			<option value="36">旅游/度假</option>
			<option value="37">石油/石化/化工</option>
			<option value="38">能源/矿产/采掘/冶炼</option>
			<option value="39">电气/电力/水利</option>
			<option value="40">航空/航天</option>
			<option value="41">学术/科研</option>
			<option value="42">政府/公共事业/非盈利机构</option>
			<option value="43">环保</option>
			<option value="44">农/林/牧/渔</option>
			<option value="45">跨领域经营</option>
			
		</select></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>发送域名：</td>
		<td><input type="text" name="domain" value="${entInfo.domain }" id="domain"></input></td>
		<td><span style="color: red;">*</span></td>
		<td id="domainInfo" style="color: red;"></td>
	</tr>
	<tr>
		<td>企业官网：</td>
		<td><input type="text" name="website" value="${entInfo.website }"></input></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>管理员帐号：</td>
		<td><input type="text" name="adminAccount" value="${entInfo.adminAccount }" id="adminAccount"></input></td>
		<td><span style="color: red;">*</span></td>
		<td id="adminAccountInfo" style="color: red;"></td>
	</tr>
	<tr>
		<td>管理员密码：</td>
		<td><input type="password" name="adminPsw" value="${entInfo.adminPsw }" id="password"></input></td>
		<td><span style="color: red;">*</span></td>
		<td id="passwordInfo" style="color: red;"></td>
	</tr>
	<tr>
		<td>联系人：</td>
		<td><input type="text" name="contact" value="${entInfo.contact }"></input></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>联系人电话：</td>
		<td><input type="text" name="contactTel" value="${entInfo.contactTel }"></input></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>发送频次：</td>
		<td><input type="text" size="10" name="freq" value="${entInfo.freq }" id="freq"></input>次/
			<select name="unit" id="unit">
				<option value="0">月</option>
				<option value="1" selected>周</option>
				<option value="2">天</option>
			</select></td>
		<td></td>
		<td><span id="freqInfo" style="color: red;"></span></td>
	</tr>
	<tr>
		<td>单次发送量：</td>
		<td><input type="text" name="singleTransNum" value="${entInfo.singleTransNum }" id="singleTransNum"></input></td>
		<td></td>
		<td><span id="singleTransNumInfo" style="color: red;"></span></td>
	</tr>
	<tr>
		<td></td>
		<td>
			<c:choose>
				<c:when test="${operate=='add'}"><input type="button" value="提交" onclick="comit()"></c:when>
				<c:otherwise><input type="button" value="提交" onclick="save()"></c:otherwise>
			</c:choose>		
		</td>
	</tr>
</table>
</form>

<c:choose>
	<c:when test="${operate=='modify'}">
		<a href="/ent">返回</a>
			</c:when>
</c:choose>
</BODY>
</HTML>