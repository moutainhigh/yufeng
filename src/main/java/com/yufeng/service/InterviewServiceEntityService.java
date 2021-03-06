package com.yufeng.service;

import com.yufeng.entity.InterviewServiceEntity;

import java.util.List;

/**
 * Created by kingstones on 16/8/7.
 */
public interface InterviewServiceEntityService {


    //插入面签服务entity
    public InterviewServiceEntity insertInterviewServiceEntity(InterviewServiceEntity interviewServiceEntity);

    //更新面签服务
    public InterviewServiceEntity updateInterviewServiceEntity(InterviewServiceEntity interviewServiceEntity);

    //根据客户经理ID查询面签服务
    public List<InterviewServiceEntity> getInterviewServiceEntityByWorkerId(int workerId);

    //根据用户内码查询面签服务
    public List<InterviewServiceEntity> getInterviewServiceEntityByInternalCode(String internalCode);

    //获取用户的面签结果
    public String getInterviewResultByInternalCode(String internalCode);


}
