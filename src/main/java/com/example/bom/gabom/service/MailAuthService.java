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

    private final int EMAIL = 0;
    private final int USER_NAME = 1;
    private final int USERID = 2;


    private final JavaMailSender mailSender;

    public void mailAuth(User user, String[] info, int statusnum, HttpSession session){
        int randomnum = 0;

        //인덱스에 따라 용도가 달라짐
        String[] status = {"계정 생성", "아이디 찾기", "비밀번호 찾기(변경)" };

        if(user != null){
            //6자리 랜덤 숫자
            Random r = new Random();
            randomnum = r.nextInt(1000000);

            //원하는 유저랑 같으면 됨.
            if(user.getUserName().equals(info[USER_NAME])){
                session.setAttribute(user.getEmail(), randomnum);

                //
                String setfrom = "springgabom@gmail.com";
                String tomail = info[EMAIL];
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
