package com.wongcu.repository;

import com.wongcu.model.ChatLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author WongCU
 * @date 2018/6/23
 */
public interface CustomMongoRepository extends MongoRepository<ChatLog, String> {
}
