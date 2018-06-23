package com.wongcu.listener;

import com.rabbitmq.client.Channel;
import com.wongcu.model.ChatLog;
import com.wongcu.service.ChatLogService;
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
    private ChatLogService chatLogService;

    private static final String UNIQUE_KEY = "msgidClient";

    @RabbitListener(queues = {"chatLogQueue"})
    public void chatLogProcess(ChatLog chatLog){
        log.debug("收到消息:{}",chatLog);
        if(chatLog.containsKey(UNIQUE_KEY)){
            chatLog.put("_id",chatLog.get(UNIQUE_KEY));
        }
        chatLogService.insert(chatLog);
    }
}
