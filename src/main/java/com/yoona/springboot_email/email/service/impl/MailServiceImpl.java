package com.yoona.springboot_email.email.service.impl;

import com.yoona.springboot_email.common.enums.CommonEnums;
import com.yoona.springboot_email.common.response.SystemResponse;
import com.yoona.springboot_email.email.service.MailService;
import com.yoona.springboot_email.email.vo.HtmlEmailVO;
import com.yoona.springboot_email.email.vo.TextEmailVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Email: m15602498163@163.com
 * @Author: yoonada
 * @Date: 2020/8/27
 * @Time: 3:07 下午
 * @Msg:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    /**
     * 发送方邮箱
     */
    @Value("${spring.mail.username}")
    private String username;

    /**
     * 发送方昵称
     */
    @Value("${spring.mail.nickname}")
    private String nickname;

    /**
     * HTML邮件内容模板
     */
    private static final String HTML_TEMPLATE="<body>\n" +
            "    <div style=\"text-align: center;\">\n" +
            "        <h2>感谢您注册了YoonaDa的账号</h2>\n" +
            "        <h2>使用过程中有任何问题,请联系m15602498163@163.com</h2>\n" +
            "        <img src=\"http://images.yoonada.cn/04.jpg\" width=\"800px\" height=\"450px\"/> \n" +
            "    </div>\n" +
            "</body>";

    /**
     * 发送简单的文本邮件
     * @param textEmailVO
     * @return
     */
    @Override
    public SystemResponse sendSimpleTextMail(TextEmailVO textEmailVO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(nickname + '<' + username + '>');
        message.setTo(textEmailVO.getReceiver());
        message.setSubject(textEmailVO.getSubject());
        message.setText(textEmailVO.getContent());
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return SystemResponse.response(CommonEnums.MAIL_SENT_FAIL);
        }
        return SystemResponse.response(CommonEnums.MAIL_SENT_SUCCESSFULLY);
    }

    /**
     * 发送一个HTML邮件
     * @param htmlEmailVO
     * @return
     */
    @Override
    public SystemResponse sendHtmlMail(HtmlEmailVO htmlEmailVO) {
        try {
            getMessage(htmlEmailVO, HTML_TEMPLATE);
        } catch (Exception e) {
            return SystemResponse.response(CommonEnums.MAIL_SENT_FAIL);
        }
        return SystemResponse.response(CommonEnums.MAIL_SENT_SUCCESSFULLY);
    }

    /**
     * 按照模板发送(类似于HTML)
     * 模板在templates包下,可以使用thymeleaf
     * @param htmlEmailVO
     * @return
     */
    @Override
    public SystemResponse sendTemplateMail(HtmlEmailVO htmlEmailVO) {
        try {
            Context context = new Context();
            context.setVariable("project", "YoonaDaSystem");
            context.setVariable("url","https://gitlab.com/YoonaDa/quick-start.git");
            String emailContent = templateEngine.process("temp", context);
            getMessage(htmlEmailVO, emailContent);
        }catch (Exception e){
            e.printStackTrace();
            return SystemResponse.response(CommonEnums.MAIL_SENT_FAIL);
        }
        return SystemResponse.response(CommonEnums.MAIL_SENT_SUCCESSFULLY);
    }



    /**
     * 封装了一下
     * @param htmlEmailVO
     * @param content
     * @throws MessagingException
     */
    private void getMessage(HtmlEmailVO htmlEmailVO, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(nickname + '<' + username + '>');
        helper.setTo(htmlEmailVO.getReceiver());
        helper.setSubject(htmlEmailVO.getSubject());
        helper.setText(content, true);
        mailSender.send(message);
    }
}
