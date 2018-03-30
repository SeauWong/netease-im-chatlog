package com.wongcu.service;

import com.alibaba.fastjson.JSON;
import org.bson.Document;
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
    private MongoTemplate mongoTemplate;

    public void insert(Object charLog) {
        Document document = Document.parse(JSON.toJSONString(charLog));
        mongoTemplate.getCollection("ChatLog").insertOne(document);
    }
}
