package com.wongcu.model.param;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author WongCU
 * @date 2018/6/23
 */
@Data
public class ChatLogQueryParam implements Serializable{
    private static final long serialVersionUID = 1L;

    @NotNull
    private String fromAccount;

    @NotNull
    private String to;

    @NotNull
    private String msgTimestampStart;

    @NotNull
    private String msgTimestampEnd;

    @NotNull
    private Integer pageNo = 0;

    @NotNull
    private Integer pageSize = 20;

    @NotNull
    @ApiModelProperty(value = "传 asc 或 desc")
    private String orderByMsgTimestamp = "asc";
}
