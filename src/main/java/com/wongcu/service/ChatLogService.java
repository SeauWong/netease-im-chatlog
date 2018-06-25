package com.wongcu.service;

import com.wongcu.model.ChatLog;
import com.wongcu.model.param.ChatLogQueryParam;
import com.wongcu.repository.CustomMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Page<ChatLog> queryByParam(ChatLogQueryParam queryParam){
        Sort.Order order = "asc".equals(queryParam.getOrderByMsgTimestamp()) ? Sort.Order.asc("msgTimestamp") : Sort.Order.desc("msgTimestamp");
        Page<ChatLog> pages = mongoRepository.findAndPages(queryParam.getFromAccount(),queryParam.getTo(),
                queryParam.getMsgTimestampStart(),queryParam.getMsgTimestampEnd(), PageRequest.of(queryParam.getPageNo(),queryParam.getPageSize(),Sort.by(order)));
        return pages;
    }
}
