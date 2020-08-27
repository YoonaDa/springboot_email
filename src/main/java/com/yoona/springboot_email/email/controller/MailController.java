package com.yoona.springboot_email.email.controller;

import com.yoona.springboot_email.common.response.SystemResponse;
import com.yoona.springboot_email.email.service.MailService;
import com.yoona.springboot_email.email.vo.HtmlEmailVO;
import com.yoona.springboot_email.email.vo.TextEmailVO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Email: m15602498163@163.com
 * @Author: yoonada
 * @Date: 2020/8/27
 * @Time: 3:06 下午
 * @Msg:
 */
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailController {

    private final MailService mailService;

    /**
     * 发送简单的文本邮件
     * @param textEmailVO
     * @return
     */
    @ApiOperation("发送简单的文本邮件")
    @PostMapping("/sendSimpleTextMail")
    public SystemResponse sendSimpleTextMail(@Valid @RequestBody TextEmailVO textEmailVO){
        return mailService.sendSimpleTextMail(textEmailVO);
    }

    /**
     * 发送HTML邮件
     * @param htmlEmailVO
     * @return
     */
    @ApiOperation("发送HTML邮件")
    @PostMapping("/sendHtmlMail")
    public SystemResponse sendHtmlMail(@Valid @RequestBody HtmlEmailVO htmlEmailVO){
        return mailService.sendHtmlMail(htmlEmailVO);
    }

    /**
     * 发送模板邮件
     * @param htmlEmailVO
     * @return
     */
    @ApiOperation("发送模板邮件")
    @PostMapping("/sendTemplateMail")
    public SystemResponse sendTemplateMail(@Valid @RequestBody HtmlEmailVO htmlEmailVO){
        return mailService.sendTemplateMail(htmlEmailVO);
    }


}
