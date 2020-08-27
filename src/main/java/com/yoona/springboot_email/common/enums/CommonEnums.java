package com.yoona.springboot_email.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Email: m15602498163@163.com
 * @Author: yoonada
 * @Date: 2020/8/27
 * @Time: 11:05 上午
 * @Msg: 通用状态枚举
 */
@Getter
@AllArgsConstructor
public enum CommonEnums {

    /**
     * 状态枚举
     */
    NORMAL(1, "操作成功"),
    UN_KNOW_ERROR(-1, "未知错误"),
    MAIL_SENT_SUCCESSFULLY(2110,"邮件发送成功"),
    MAIL_SENT_FAIL(2111,"邮件发送失败"),
    ;

    private final int code;
    private final String msg;

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

}
