package com.yoona.springboot_email.email.service;

import com.yoona.springboot_email.common.response.SystemResponse;
import com.yoona.springboot_email.email.vo.HtmlEmailVO;
import com.yoona.springboot_email.email.vo.TextEmailVO;

/**
 * @Email: m15602498163@163.com
 * @Author: yoonada
 * @Date: 2020/8/27
 * @Time: 3:07 下午
 * @Msg:
 */
public interface MailService {


    /**
     * 发送简单的文本邮件
     * @param textEmailVO
     * @return
     */
    SystemResponse sendSimpleTextMail(TextEmailVO textEmailVO);

    /**
     * 发送HTML邮件
     * @param htmlEmailVO
     * @return
     */
    SystemResponse sendHtmlMail(HtmlEmailVO htmlEmailVO);

    /**
     * 发送模板邮件
     * @param htmlEmailVO
     * @return
     */
    SystemResponse sendTemplateMail(HtmlEmailVO htmlEmailVO);


}
