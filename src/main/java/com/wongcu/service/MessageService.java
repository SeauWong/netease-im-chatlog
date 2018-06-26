package com.wongcu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wongcu.constants.ConvType;
import com.wongcu.constants.MsgType;
import com.wongcu.model.ChatLog;
import com.wongcu.model.SysNotice;
import com.wongcu.model.param.ChatLogQueryParam;
import com.wongcu.model.param.SysNoticeQueryParam;
import com.wongcu.repository.ChatLogRepository;
import com.wongcu.repository.SysNoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author WongCU
 * @date 2018/3/30
 */
@Service
@Slf4j
public class MessageService {

    @Autowired
    private ChatLogRepository chatLogRepository;
    @Autowired
    private SysNoticeRepository sysNoticeRepository;

    /**
     * 抄送消息类型
     */
    private static final String EVENT_TYPE = "eventType";

    /**
     * 会话类型
     */
    private static final String EVENT_TYPE_CONVERSATION = "1";

    /**
     * 会话具体类型
     */
    private static final String CONV_TYPE = "convType";

    /**
     * 通知消息类型
     */
    private static final String MSG_TYPE = "msgType";



    public void handlerMessage(JSONObject message){
        //目前只保存会话类型的消息,包括P2P聊天消息，群组聊天消息，群组操作，好友操作
        String evenType = String.valueOf(message.get(EVENT_TYPE));
        if(!EVENT_TYPE_CONVERSATION.equals(evenType)){
            return;
        }
        String convType = String.valueOf(message.get(CONV_TYPE));
        if(ConvType.PERSON.name().equals(convType)){
            saveChatLog(JSON.parseObject(message.toJSONString(),ChatLog.class));
        }else if(ConvType.CUSTOM_PERSON.name().equals(convType)){
            String msgType = String.valueOf(message.get(MSG_TYPE));
            if(MsgType.CUSTOM_P2P_MSG.name().equals(msgType)){
                saveSysNotice(JSON.parseObject(message.toJSONString(),SysNotice.class));
            }
        }else {
            log.error("未定义的消息类型,message:{}",message);
        }
    }

    private void saveChatLog(ChatLog chatLog) {
        try {
            chatLogRepository.insert(chatLog);
        }catch (DuplicateKeyException e){
            log.warn("成功去除重复消息,ChatLog:{}",chatLog);
        }catch (Exception e){
            log.error("保存聊天记录发生异常",e);
        }
    }

    private void saveSysNotice(SysNotice sysNotice) {
        try {
            sysNoticeRepository.insert(sysNotice);
        }catch (DuplicateKeyException e){
            log.warn("成功去除重复消息,SysNotice:{}",sysNotice);
        }catch (Exception e){
            log.error("保存系统通知发生异常",e);
        }
    }

//    public Page<ChatLog> queryChatLogByParam(ChatLogQueryParam queryParam){
//        Sort.Order order = "asc".equals(queryParam.getOrderByMsgTimestamp()) ? Sort.Order.asc("msgTimestamp") : Sort.Order.desc("msgTimestamp");
//        Page<ChatLog> pages = chatLogRepository.findAndPages(queryParam.getFromAccount(),queryParam.getTo(),
//                queryParam.getMsgTimestampStart(),queryParam.getMsgTimestampEnd(), PageRequest.of(queryParam.getPageNo(),queryParam.getPageSize(),Sort.by(order)));
//        return pages;
//    }

    public Page<ChatLog> queryChatLogByParam(@NotNull ChatLogQueryParam queryParam){
        Sort.Order order = "asc".equals(queryParam.getOrderByMsgTimestamp()) ?
                new Sort.Order(Sort.Direction.ASC,"msgTimestamp") : new Sort.Order(Sort.Direction.DESC,"msgTimestamp");
        Page<ChatLog> pages = chatLogRepository.findAndPages(queryParam.getFromAccount(),queryParam.getTo(),
                queryParam.getMsgTimestampStart(),queryParam.getMsgTimestampEnd(), new PageRequest(queryParam.getPageNo(),queryParam.getPageSize(), new Sort(order)));
        return pages;
    }

    public Page<SysNotice> querySysNoticeByParam(@NotNull SysNoticeQueryParam queryParam){
        Sort.Order order = "asc".equals(queryParam.getOrderByMsgTimestamp()) ?
                new Sort.Order(Sort.Direction.ASC,"msgTimestamp") : new Sort.Order(Sort.Direction.DESC,"msgTimestamp");
        Page<SysNotice> pages = sysNoticeRepository.findAndPages(queryParam.getTo(),new PageRequest(queryParam.getPageNo(),queryParam.getPageSize(), new Sort(order)));
        return pages;
    }
}
