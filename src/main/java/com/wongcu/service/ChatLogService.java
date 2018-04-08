package com.wongcu.service;

import com.wongcu.model.ChatLog;
import com.wongcu.repository.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author WongCU
 * @date 2018/3/30
 */
@Service
public class ChatLogService {
    @Autowired
    private ChatLogRepository chatLogRepository;

    public void insert(ChatLog charLog) {
        chatLogRepository.insert(charLog);
    }
}
