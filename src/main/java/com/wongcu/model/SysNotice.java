package com.wongcu.model;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author WongCU
 * @date 2018/3/30
 */
@Document(collection = "sys_notice")
public class SysNotice extends JSONObject {
}
