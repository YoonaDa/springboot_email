package com.yoona.springboot_email.email.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Email: m15602498163@163.com
 * @Author: yoonada
 * @Date: 2020/8/27
 * @Time: 3:10 下午
 * @Msg: HTML邮件的VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HtmlEmailVO {

    /**
     * 电子邮箱
     */
    @Email(message = "接收方邮箱不正确")
    @NotBlank(message = "邮箱不能为空")
    private String receiver;

    @NotBlank(message = "主题不能为空")
    private String subject;

}
