package com.wongcu.repository;

import com.wongcu.model.ChatLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author WongCU
 * @date 2018/6/23
 */
public interface ChatLogRepository extends MongoRepository<ChatLog, String>{


    @Query("{\"fromAccount\": ?0 , \"to\": ?1 , \"msgTimestamp\":{$gt : ?2 ,$lt: ?3}}")
    Page<ChatLog> findAndPages(String fromAccount, String to, String msgTimestampStart, String msgTimestampEnd, Pageable pages);
}
