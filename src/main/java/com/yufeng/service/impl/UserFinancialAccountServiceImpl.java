package com.yufeng.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yufeng.dao.UserFinancialAccountDao;
import com.yufeng.entity.UserFinancialAccountInfo;

/**
 * 用户金融业务处理层
 * @author dh
 */
@Service
public class UserFinancialAccountServiceImpl {
	
	@Autowired
    private UserFinancialAccountDao userFinancialAccountDao;
	
	//查询金融账户信息
	public List<UserFinancialAccountInfo> getUserFinancialAccountByCode(String code){
		List<UserFinancialAccountInfo> info=userFinancialAccountDao.getUserFinancialAccountByCode(code);
		return info;
	}
	
	//查询金融账户信息
	public UserFinancialAccountInfo getUserFinancialAccountById(String id){
		UserFinancialAccountInfo info=userFinancialAccountDao.getUserFinancialAccountById(id);
		return info;
	}
			
	//新建金融账户信息
	public String insertUserFinancialAccount(UserFinancialAccountInfo userFinancialAccountInfo){
		UserFinancialAccountInfo info=userFinancialAccountDao.getUserFinancialAccountByAccountId(userFinancialAccountInfo.getFinancialConsumeAccountId());
		if(info!=null){
			return "2";//存在重复
		}
		userFinancialAccountInfo.setFoundTime(new Date());
		return userFinancialAccountDao.insertUserFinancialAccount(userFinancialAccountInfo)+"";
	}
				
	//金融账户信息修改
	public String updateUserFinancialAccount(UserFinancialAccountInfo userFinancialAccountInfo){
		UserFinancialAccountInfo info=userFinancialAccountDao.getUserFinancialAccountById(userFinancialAccountInfo.getId());
		if(info==null){
			return "0";
		}
		userFinancialAccountInfo.setUpdateTime(new Date());
		return userFinancialAccountDao.updateUserFinancialAccount(userFinancialAccountInfo)+"";
	}
				
	//删除金融账户信息
	public String deleteUserFinancialAccount(UserFinancialAccountInfo userFinancialAccountInfo){
		UserFinancialAccountInfo info=userFinancialAccountDao.getUserFinancialAccountById(userFinancialAccountInfo.getId());
		if(info==null){
			return "0";
		}
		return userFinancialAccountDao.deleteUserFinancialAccount(userFinancialAccountInfo)+"";	
	}

}