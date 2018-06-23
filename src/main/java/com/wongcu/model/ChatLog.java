package com.wongcu.model;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author WongCU
 * @date 2018/3/30
 */
@Document(collection = "chat_log")
public class ChatLog extends JSONObject {
}
