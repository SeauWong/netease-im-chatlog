package com.wongcu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wongcu.model.ChatLog;
import com.wongcu.model.param.ChatLogQueryParam;
import com.wongcu.repository.CustomMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author WongCU
 * @date 2018/3/30
 */
@Service
@Slf4j
public class ChatLogService {
    @Autowired
    private CustomMongoRepository mongoRepository;

    public void insert(ChatLog chatLog) {
        try {
            mongoRepository.insert(chatLog);
        }catch (DuplicateKeyException e){
            log.warn("成功去除重复消息,ChatLog:{}",chatLog);
        }catch (Exception e){
            log.error("保存聊天记录发生异常",e);
        }
    }

    public void queryByParam(ChatLogQueryParam queryParam, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
//        Criteria criteria = Criteria
//                .where("msgTimestamp").gte(queryParam.getMsgTimestampStart()).lt(queryParam.getMsgTimestampEnd())
//                .and("fromAccount").is(queryParam.getFromAccount())
//                .and("to").is(queryParam.getTo());
//        Query query = new Query().
//                with(pageRequest)
//                .addCriteria(criteria);
        ChatLog chatLog = new ChatLog();
        chatLog.put("fromAccount",queryParam.getFromAccount());
        chatLog.put("to",queryParam.getTo());
        Example<ChatLog> example = Example.of(chatLog);
        List<ChatLog> all = mongoRepository.findAll(example);
        all.stream().forEach(n-> System.out.println(n));
//        List<ChatLog> content = chatLogs.getContent();
//        chatLogs.stream().forEach(n->{
//            System.out.println(n);
//        });
    }
}
