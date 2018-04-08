package com.wongcu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wongcu.model.ChatLog;
import com.wongcu.service.ChatLogService;
import com.wongcu.util.CheckSumBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author WongCU
 * @date 2018/3/30
 */
@RestController
@RequestMapping("/api/chatlogs")
@Slf4j
public class ChatLogAPIController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${asl.investor.im.appSecret}")
    private String appSecret;

    private Boolean verifyChatLog(String md5, String curTime, String checkSum, ChatLog chatLog) {
        String localMd5 = CheckSumBuilder.getMD5(JSON.toJSONString(chatLog));
        if (!localMd5.equals(md5)) {
            log.debug("当前md5:{}，计算md5:{}，不匹配", md5, localMd5);
            return false;
        }
        String localCheckSum = CheckSumBuilder.getCheckSum(appSecret, md5, curTime);
        if (!localCheckSum.equals(checkSum)) {
            log.debug("当前checkSum:{}，计算checkSum:{}，不匹配", checkSum, localCheckSum);
            return false;
        }
        return true;
    }

    @PostMapping("/save")
    public JSONObject saveChatLog(@RequestHeader("MD5") String md5,
                                  @RequestHeader("CurTime") String curTime,
                                  @RequestHeader("CheckSum") String checkSum,
                                  @RequestBody ChatLog chatLog) {
        log.debug("入参:md5:{},curTime:{},checkSum:{},chatLog:{}", md5, curTime, checkSum, chatLog);
        Boolean passVerify = verifyChatLog(md5, curTime, checkSum, chatLog);
        if (passVerify) {
            rabbitTemplate.convertAndSend("directExchange","chatLog",chatLog);
        }
        JSONObject result = new JSONObject(1);
        result.put("code", "200");
        return result;
    }
}
