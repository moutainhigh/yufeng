package com.yufeng.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yufeng.dao.BankCardDao;
//import com.yufeng.dao.GuaranteeRelationshipDao;
import com.yufeng.dao.RewardPunishmentInfoDao;
import com.yufeng.dao.SchoolExperienceInfoDao;
import com.yufeng.dao.UserBasicInfoDao;
import com.yufeng.dao.UserFamilyDao;
import com.yufeng.dao.UserFinancialAccountDao;
import com.yufeng.entity.InterviewServiceEntity;
import com.yufeng.entity.RewardPunishmentInfo;
import com.yufeng.entity.SchoolExperienceInfo;
import com.yufeng.entity.UserBankCardInfo;
import com.yufeng.entity.UserBasicInfo;
import com.yufeng.entity.UserFamilyInfo;
import com.yufeng.entity.UserFinancialAccountInfo;
import com.yufeng.entity.UserInfo;
import com.yufeng.service.InterviewServiceEntityService;
import com.yufeng.service.UserInfoService;
import com.yufeng.util.Utils;

/**
 * Created by fishyu on 5/8/26.
 */

public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserBasicInfoDao userBasicInfoDao;
	
	@Autowired
	private SchoolExperienceInfoDao schoolExperienceInfoDao;
	
	@Autowired
	private UserFinancialAccountDao userFinancialAccountDao;
	
	@Autowired
	private BankCardDao bankCardDao;
	
	@Autowired
	private UserFamilyDao userFamilyDao;
	
	@Autowired
	private RewardPunishmentInfoDao rewardPunishmentInfoDao;
	
//	@Autowired
//	private GuaranteeRelationshipDao guaranteeRelationshipDao;
	
	@Autowired
	private InterviewServiceEntityService interviewServiceEntityService;
	
	
	
	
	public UserInfo getUserInfo(String internalCode) {
		
		UserInfo userInfo = new UserInfo();
		
		//获得客户基本信息
		UserBasicInfo userBasicInfo = new UserBasicInfo();	
		
		System.out.println("获得客户基本信息开始");
		userBasicInfo = userBasicInfoDao.getUserBasicInfoByInternalCode(internalCode);
		if(userBasicInfo!=null){
			System.out.println("userBasicInfo=="+userBasicInfo.toString());
		}else{
			System.out.println("userBasicInfo is null");
		}
		System.out.println("获得客户基本信息结束");
		
		//获得客户学业信息
		System.out.println("获得客户学业信息开始");
		List<SchoolExperienceInfo> schoolExperienceInfoList = new ArrayList<SchoolExperienceInfo>();		
		schoolExperienceInfoList = schoolExperienceInfoDao.getSchoolExperienceInfoByInternalCodeForCreditLine(internalCode);
		if(schoolExperienceInfoList!=null){
			System.out.println("schoolExperienceInfoList=="+schoolExperienceInfoList.size());
		}else{
			System.out.println("schoolExperienceInfoList is null");
		}		
		System.out.println("获得客户学业信息结束");		
		
		//获得客户银行卡信息
		System.out.println("获得客户银行卡信息开始");
		List<UserBankCardInfo> userBankCardInfoList = new ArrayList<UserBankCardInfo>();
		userBankCardInfoList = bankCardDao.getBankCardByCode(internalCode);
		
		if(userBankCardInfoList!=null){
			System.out.println("userBankCardInfoList=="+userBankCardInfoList.size());
		}else{
			System.out.println("userBankCardInfoList is null");
		}
		
		System.out.println("获得客户银行卡信息结束");
		//获得客户金融账户信息
		System.out.println("获得客户金融账户信息开始");
		List<UserFinancialAccountInfo> userFinancialAccountInfoList = new ArrayList<UserFinancialAccountInfo>();
		userFinancialAccountInfoList = userFinancialAccountDao.getUserFinancialAccountByCode(internalCode);
		if(userFinancialAccountInfoList!=null){
			System.out.println("userFinancialAccountInfoList=="+userFinancialAccountInfoList.size());
		}else{
			System.out.println("userFinancialAccountInfoList is null");
		}
		System.out.println("获得客户金融账户信息结束");
		//获得客户奖惩信息
		System.out.println("获得客户奖惩信息开始");
		List<RewardPunishmentInfo> rewardPunishmentInfoList = new ArrayList<RewardPunishmentInfo>();
		rewardPunishmentInfoList = rewardPunishmentInfoDao.getRewardPunishmentInfoByInternalCode(internalCode);
		if(rewardPunishmentInfoList!=null){
			System.out.println("rewardPunishmentInfoList=="+rewardPunishmentInfoList.size());
		}else{
			System.out.println("rewardPunishmentInfoList is null");
		}
		System.out.println("获得客户奖惩信息结束");
		//获得客户联系人信息
		System.out.println("获得客户联系人信息开始");
		List<UserFamilyInfo> userFamilyInfoList = new ArrayList<UserFamilyInfo>();
		userFamilyInfoList = userFamilyDao.getUserFamilyByCode(internalCode);
		if(userFamilyInfoList!=null){
			System.out.println("userFamilyInfoList=="+userFamilyInfoList.size());
		}else{
			System.out.println("userFamilyInfoList is null");
		}
		System.out.println("获得客户联系人信息结束");
		
		userInfo.setUserBasicInfo(userBasicInfo);
		userInfo.setSchoolExperienceInfoList(schoolExperienceInfoList);
		userInfo.setUserBankCardInfoList(userBankCardInfoList);
		userInfo.setUserFinancialAccountInfoList(userFinancialAccountInfoList);
		userInfo.setRewardPunishmentInfoList(rewardPunishmentInfoList);
		userInfo.setUserFamilyInfoList(userFamilyInfoList);
		
		return userInfo;
		
	}


	
	/**获得客户信息录入级别
	 * 初级，客户提供身份证、学校信息、银行卡绑定；
	 * 一级，客户提供身份证、学校信息、银行卡绑定，联系人信息；
	 * 二级，客户提供身份证、学校信息、银行卡绑定，联系人信息；并通过面签；
	 * 三级，客户提供身份证、学校信息、银行卡绑定，联系人信息；并提供担保圈；//去掉这一级
	 * 暂时去掉第三级，即没有担保圈的判定规则
	 * */
	public int getStudentUserLevel(UserInfo userInfo) {
		
		int userLevel = Utils.NO_LEVEL;
		
		
		//获得面签结果
		//暂时判定面签的规则是：多次面签只要有通过就算通过
		String interviewReslut = "f";
		
		List<InterviewServiceEntity> listInterviewServiceEntity = new ArrayList<InterviewServiceEntity>();
		
		listInterviewServiceEntity = interviewServiceEntityService.getInterviewServiceEntityByInternalCode(userInfo.getUserBasicInfo().getInternalCode());
		
		for(InterviewServiceEntity tmpInterviewServiceEntity:listInterviewServiceEntity){
					
			if(tmpInterviewServiceEntity.getInterviewResult().equals("t")){
				interviewReslut = "t";
			}
		}
		
		
//		//获得担保圈数据
//		int totalGuarantee = guaranteeRelationshipDao.getByCode(userInfo.getUserBasicInfo().getInternalCode()).size();
//		int totalRelateGuarantee = guaranteeRelationshipDao.getByRelateCode(userInfo.getUserBasicInfo().getInternalCode()).size();
		
//		String guaranteeResult = "f";
		
//		if(totalGuarantee==Utils.TOTAL_GUARANTEE && totalRelateGuarantee==Utils.TOTAL_GUARANTEE){
//			guaranteeResult = "t";
//		}
		
		if(userInfo.getUserBasicInfo().getIdNo()!=null&&!userInfo.getUserBasicInfo().getIdNo().equals("")
				&&userInfo.getSchoolExperienceInfoList().size()>0
				&&userInfo.getUserBankCardInfoList().size()>0
				&&userInfo.getUserFamilyInfoList().size()==0
//				&&interviewReslut.equals("f")
//				&&guaranteeResult.equals("f")
				){
			
			userLevel = Utils.LEVEL_0;
		}
		
		if(userInfo.getUserBasicInfo().getIdNo()!=null&&!userInfo.getUserBasicInfo().getIdNo().equals("")
				&&userInfo.getSchoolExperienceInfoList().size()>0
				&&userInfo.getUserBankCardInfoList().size()>0
				&&userInfo.getUserFamilyInfoList().size()>0
				&&interviewReslut.equals("f")
//				&&guaranteeResult.equals("f")
				){
			
			userLevel = Utils.LEVEL_1;
		}
		
		if(userInfo.getUserBasicInfo().getIdNo()!=null&&!userInfo.getUserBasicInfo().getIdNo().equals("")
				&&userInfo.getSchoolExperienceInfoList().size()>0
				&&userInfo.getUserBankCardInfoList().size()>0
				&&userInfo.getUserFamilyInfoList().size()>0
				&&interviewReslut.equals("t")
//				&&guaranteeResult.equals("f")
				){
			
			userLevel = Utils.LEVEL_2;
		}
		
//		if(userInfo.getUserBasicInfo().getIdNo()!=null&&!userInfo.getUserBasicInfo().getIdNo().equals("")
//				&&userInfo.getSchoolExperienceInfoList().size()>0
//				&&userInfo.getUserBankCardInfoList().size()>0
//				&&userInfo.getUserFamilyInfoList().size()>0
//				&&interviewReslut.equals("t")
//				&&guaranteeResult.equals("t")){
//			
//			userLevel = Utils.LEVEL_3;
//		}
		
		return userLevel;
	}
	
}
