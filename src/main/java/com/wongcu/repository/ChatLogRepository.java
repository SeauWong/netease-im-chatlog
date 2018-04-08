package com.wongcu.repository;

import com.wongcu.model.ChatLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author WongCU
 * @date 2018/4/2
 */
public interface ChatLogRepository extends MongoRepository<ChatLog, String> {
}
