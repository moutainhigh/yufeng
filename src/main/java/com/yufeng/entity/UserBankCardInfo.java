package com.yufeng.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dh
 * 用户银行卡信息
 */
public class UserBankCardInfo implements Serializable{
	
	private String id;
	//内码
	private String internal_code;
	//银行卡号码
	private String bankCardNumber;
	//银行卡类型
	private String bankCardType;
	//到期时间
	private Date expireTime;
	//是否为转入账户
	private boolean transferAccount;
	//银行卡状态
	private String bankCardState;
	//创建时间
	private Date foundTime;
	//更新时间
	private Date updateTime;
	//是否有效
	private boolean info_status;
	//删除操作者
	private String delete_operator;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInternal_code() {
		return internal_code;
	}
	public void setInternal_code(String internal_code) {
		this.internal_code = internal_code;
	}
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	public String getBankCardType() {
		return bankCardType;
	}
	public void setBankCardType(String bankCardType) {
		this.bankCardType = bankCardType;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public boolean isTransferAccount() {
		return transferAccount;
	}
	public void setTransferAccount(boolean transferAccount) {
		this.transferAccount = transferAccount;
	}
	public String getBankCardState() {
		return bankCardState;
	}
	public void setBankCardState(String bankCardState) {
		this.bankCardState = bankCardState;
	}
	public Date getFoundTime() {
		return foundTime;
	}
	public void setFoundTime(Date foundTime) {
		this.foundTime = foundTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getDelete_operator() {
		return delete_operator;
	}
	public void setDelete_operator(String delete_operator) {
		this.delete_operator = delete_operator;
	}
	public boolean isInfo_status() {
		return info_status;
	}
	public void setInfo_status(boolean info_status) {
		this.info_status = info_status;
	}
	
	
	
	
	
	
	
	
	
	

}
