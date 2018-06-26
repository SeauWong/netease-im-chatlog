package com.wongcu.controller;

import com.alibaba.fastjson.JSONObject;
import com.wongcu.model.ChatLog;
import com.wongcu.model.SysNotice;
import com.wongcu.model.param.ChatLogQueryParam;
import com.wongcu.model.param.SysNoticeQueryParam;
import com.wongcu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * @author WongCU
 * @date 2018/6/23
 */
@RestController
@RequestMapping("/api/test")
public class TestControler {

    @Autowired
    MessageService messageService;

    @GetMapping("/mongo")
    public void test(){
        JSONObject sysNoticeMessage = new JSONObject();
        sysNoticeMessage.put("name","Wongcu");
        sysNoticeMessage.put("_id","88666");
        sysNoticeMessage.put("convType","CUSTOM_PERSON");
        sysNoticeMessage.put("msgType","CUSTOM_P2P_MSG");
        messageService.handlerMessage(sysNoticeMessage);

        JSONObject chatLogMessage = new JSONObject();
        chatLogMessage.put("name","Wongcu");
        chatLogMessage.put("_id","88666");
        chatLogMessage.put("convType","PERSON");
        chatLogMessage.put("msgType","CUSTOM");
        messageService.handlerMessage(chatLogMessage);
    }

    @GetMapping("/query_chat_log_by_param")
    public Page<ChatLog> queryChatLogByParam(@Valid ChatLogQueryParam queryParam){
        return messageService.queryChatLogByParam(queryParam);
    }

    @GetMapping("/query_sys_notice_by_param")
    public Page<SysNotice> querySysNoticeByParam(@Valid SysNoticeQueryParam queryParam){
        return messageService.querySysNoticeByParam(queryParam);
    }
}
