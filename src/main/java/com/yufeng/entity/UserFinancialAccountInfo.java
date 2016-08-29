package com.yufeng.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dh
 * 用户金融账户信息
 */
public class UserFinancialAccountInfo implements Serializable{
	请修改做了标记的字段，按照java规范，并参考UserFamilyInfo中的字段名进行修改。
11	private String id;
	//内码
11	private String internal_code;
	//信息标识ID
	private String signId;
	//金融消费账户ID
	private String financialConsumeAccountId;
	//金融消费公司
	private String financialConsumeCompany;
	//金融消费账户状态
	private String financialConsumeAccountState;
	//信用评分
	private Integer creditScoring;
	//是否授权
	private String authorize;
	//创建时间
11	private Date foundTime;
	//更新时间
11	private Date updateTime;
	//是否有效
11	private Boolean info_status;
	//删除操作者
11	private String delete_operator;
	
	
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
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public String getFinancialConsumeAccountId() {
		return financialConsumeAccountId;
	}
	public void setFinancialConsumeAccountId(String financialConsumeAccountId) {
		this.financialConsumeAccountId = financialConsumeAccountId;
	}
	public String getFinancialConsumeCompany() {
		return financialConsumeCompany;
	}
	public void setFinancialConsumeCompany(String financialConsumeCompany) {
		this.financialConsumeCompany = financialConsumeCompany;
	}
	public String getFinancialConsumeAccountState() {
		return financialConsumeAccountState;
	}
	public void setFinancialConsumeAccountState(String financialConsumeAccountState) {
		this.financialConsumeAccountState = financialConsumeAccountState;
	}
	public Integer getCreditScoring() {
		return creditScoring;
	}
	public void setCreditScoring(Integer creditScoring) {
		this.creditScoring = creditScoring;
	}
	public String getAuthorize() {
		return authorize;
	}
	public void setAuthorize(String authorize) {
		this.authorize = authorize;
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
	public Boolean getInfo_status() {
		return info_status;
	}
	public void setInfo_status(Boolean info_status) {
		this.info_status = info_status;
	}
	public String getDelete_operator() {
		return delete_operator;
	}
	public void setDelete_operator(String delete_operator) {
		this.delete_operator = delete_operator;
	}
	
	
	
}
