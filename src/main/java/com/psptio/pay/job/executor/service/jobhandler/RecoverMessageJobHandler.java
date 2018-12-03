package com.psptio.pay.job.executor.service.jobhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psptio.reliable.message.service.IFailMessageService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

/**
 * @description: 消息恢复job处理器
 * @copyright: Copyright (c) 2018
 * @company: tinklabs
 * @author: xiazhonghui
 * @version: 1.0
 * @date: 2018 Nov 30, 2018 11:25:17 AM
 */
@JobHandler(value = "recoverMessageHandler")
@Component
public class RecoverMessageJobHandler  extends IJobHandler {
	@Autowired
	public IFailMessageService failMessageService;
	
	@Override 
	public ReturnT<String> execute(String param) throws Exception {
		boolean excute=failMessageService.recoverBathMessage();
		if(excute)
		{
			return ReturnT.SUCCESS;
		}else
		{
			return ReturnT.FAIL;
		}
	}

}
