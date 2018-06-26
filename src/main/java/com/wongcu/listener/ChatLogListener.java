package com.wongcu.listener;

import com.alibaba.fastjson.JSONObject;
import com.wongcu.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author WongCU
 * @date 2018/4/2
 */
@Component
@Slf4j
public class ChatLogListener {
    @Autowired
    private MessageService messageService;

    private static final String UNIQUE_KEY = "msgidServer";

    @RabbitListener(queues = {"imCcMessageQueue"})
    public void imCcProcess(JSONObject message){
        log.debug("收到消息:{}",message);
        if(message.containsKey(UNIQUE_KEY)){
            message.put("_id",message.get(UNIQUE_KEY));
        }
        messageService.handlerMessage(message);
    }
}
