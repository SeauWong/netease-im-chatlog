package com.wongcu.constants;

/**
 *
 * @author WongCU
 * @date 2018/6/26
 */
public enum MsgType {
    /**
     * 会话具体类型，PERSON、TEAM对应的通知消息类型：
     */
    TEXT,
    PICTURE,
    AUDIO,
    VIDEO,
    LOCATION ,
    NOTIFICATION,
    FILE, //文件消息 
    NETCALL_AUDIO, //网络电话音频聊天 
    NETCALL_VEDIO, //网络电话视频聊天 
    DATATUNNEL_NEW, //新的数据通道请求通知 
    TIPS, //提示类型消息 
    CUSTOM, //自定义消息

    /**
     * 会话具体类型CUSTOM_PERSON对应的通知消息类型：
     */
    FRIEND_ADD, //加好友
    FRIEND_DELETE, //删除好友
    CUSTOM_P2P_MSG, //个人自定义系统通知

    /**
     * 会话具体类型CUSTOM_TEAM对应的通知消息类型：
     */
    TEAM_APPLY, //申请入群
    TEAM_APPLY_REJECT, //拒绝入群申请
    TEAM_INVITE, //邀请进群
    TEAM_INVITE_REJECT, //拒绝邀请
    TLIST_UPDATE, //群信息更新 
    CUSTOM_TEAM_MSG, //群组自定义系统通知

    ;
}
