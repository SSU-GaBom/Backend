package com.example.bom.gabom.service;

import com.example.bom.gabom.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class MailAuthService {

    private final JavaMailSender mailSender;

    public void mailAuth(User user, String email, String name, int statusnum, HttpSession session){
        int randomnum = 0;

        String[] status = {"계정 생성", "아이디 찾기", "비밀번호 찾기(변경)" };

        if(user != null){
            Random r = new Random();
            randomnum = r.nextInt(1000000);

            if(user.getUserName().equals(name)){
                session.setAttribute(user.getEmail(), randomnum);

                String setfrom = "springgabom@gmail.com";
                String tomail = email;
                String title = "[가봄] 비밀번호 변경 인증 메일입니다.";
                String content = System.getProperty("line.separator")
                        + "안녕하세요 회원님" + System.getProperty("line.separator")
                        + "가봄" + status[statusnum] + " 인증번호는" + randomnum + "입니다." + System.getProperty("line.separator");

                try{
                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");

                    messageHelper.setFrom(setfrom);
                    messageHelper.setTo(tomail);
                    messageHelper.setSubject(title);
                    messageHelper.setText(content);

                    mailSender.send(message);
                }catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
