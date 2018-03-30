package com.wongcu.orm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WongCU
 * @date 2018/3/30
 */
@Data
public class CharLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fromAccid;

    private String toAccid;

    private String content;
}
