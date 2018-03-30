package com.wongcu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wongcu.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WongCU
 * @date 2018/3/30
 */
@RestController
@RequestMapping("/api/chatlogs")
public class ChatLogAPIController {
    @Autowired
    private ChatLogService chatLogService;

    @PostMapping("/save")
    public JSONObject saveChatLog(@RequestBody Object charLog) {
        System.out.println(JSON.toJSONString(charLog));
        chatLogService.insert(charLog);
        JSONObject result = new JSONObject(1);
        result.put("code", "200");
        return result;
    }
}
