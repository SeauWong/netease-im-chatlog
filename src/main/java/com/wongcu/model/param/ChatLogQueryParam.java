package com.wongcu.model.param;

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
}
