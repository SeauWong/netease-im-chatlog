package com.wongcu.controller;

import com.wongcu.model.ChatLog;
import com.wongcu.model.param.ChatLogQueryParam;
import com.wongcu.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author WongCU
 * @date 2018/6/23
 */
@RestController
@RequestMapping("/api/test")
public class TestControler {

    @Autowired
    ChatLogService chatLogService;

    @GetMapping("/mongo")
    public void test(){
        ChatLog chatLog = new ChatLog();
        chatLog.put("name","Wongcu");
        chatLog.put("_id","88666");
        chatLogService.insert(chatLog);
    }

    @PostMapping("/query_by_param")
    public void queryByParam(@RequestBody ChatLogQueryParam queryParam){
        chatLogService.queryByParam(queryParam,1,20);
    }
}
