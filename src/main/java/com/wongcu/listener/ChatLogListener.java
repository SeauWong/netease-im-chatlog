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

    @RabbitListener(queues = {"chatLogQueue"})
    public void chatLogProcess(ChatLog chatLog){
        log.debug("收到消息:{}",chatLog);
        chatLogService.insert(chatLog);
    }
}
