package com.sohu.edm.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.console.dao.ConFunDao;
import com.sohu.edm.console.dao.ConPermDao;
import com.sohu.edm.console.dao.ConRoleUserDao;
import com.sohu.edm.console.dao.map.Sys_fun;
import com.sohu.edm.model.FunTreeNode;

@Service("treeActionService")
public class TreeActionService {
	@Autowired
	ConFunDao conFunDao;
	@Autowired
	ConPermDao conPermDao;

	@Autowired
	ConRoleUserDao conRoleUserDao;
	/**
	 * 有点击事件的所有功能树
	 * @return
	 */
	public String getAllNodes(){
		List<FunTreeNode> funNodeList	=	new	ArrayList<FunTreeNode>();
		for(Sys_fun fun:conFunDao.getAll()){
			FunTreeNode node	=	new	FunTreeNode();
			node.setId(fun.getFunId());
			node.setName(fun.getFunName());
			node.setpId(fun.getPid());
			node.setUrl(fun.getUrl());
			node.setOpen(true);
			node.setTarget("rightFrame");
			funNodeList.add(node);
		}
		JSONArray	jsobj	=	JSONArray.fromObject( funNodeList );
		return jsobj.toString();
	}
	/**
	 * 没有点击事件的拥有全部节点的功能树
	 * @return
	 */
	public String getAllNodesNoUrl(){
		List<FunTreeNode> funNodeList	=	new	ArrayList<FunTreeNode>();
		for(Sys_fun fun:conFunDao.getAll()){
			FunTreeNode node	=	new	FunTreeNode();
			node.setId(fun.getFunId());
			node.setName(fun.getFunName());
			node.setpId(fun.getPid());
			node.setOpen(true);
			funNodeList.add(node);
		}
		JSONArray	jsobj	=	JSONArray.fromObject( funNodeList );
		return jsobj.toString();
	}
	
	/**
	 * 有功能权限的功能树
	 * @param sessionUser
	 * @return
	 */
	public String getSessionUserPermNodes(int sessionUser){
		List<Integer>	user2Roles	=	conRoleUserDao.getRolesIdByUserId(sessionUser);		
		List<Integer> finalPermFunc	=	conPermDao.getByRoleListAndUserId(sessionUser,user2Roles);
		List<Sys_fun> permTreeNodes	=	conFunDao.getByPermList(finalPermFunc);
		List<FunTreeNode> funNodeList	=	new	ArrayList<FunTreeNode>();
		for(Sys_fun fun:permTreeNodes){
			FunTreeNode node	=	new	FunTreeNode();
			node.setId(fun.getFunId());
			node.setName(fun.getFunName());
			node.setpId(fun.getPid());
			node.setUrl(fun.getUrl());
			node.setOpen(true);
			node.setTarget("rightFrame");
			funNodeList.add(node);
		}
		JSONArray	jsobj	=	JSONArray.fromObject( funNodeList );
		return jsobj.toString();
	}
}
