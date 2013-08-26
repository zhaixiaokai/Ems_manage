package com.sohu.edm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.dao.EmsUserDao;

@Service("emsUserService")
public class EmsUserService {
	@Autowired
	EmsUserDao	emsUserDao;
	
	public Integer getUserNumByUserName(String userName){
		return this.emsUserDao.getUserNumByUserName(userName);
	}
}
