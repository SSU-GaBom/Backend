package com.example.bom.gabom.service;

import com.example.bom.gabom.dto.FindUserDto;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.repository.UserRepository;
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
public class AuthMailService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    //그저 난수를 메일로 보내는 메소드 어떤 처리도 하지는 않음.
    public Boolean sendMail(Boolean sendable, String toEmail, Integer statusNum, Integer randNum) {
        String[] status = {"계정 생성", "아이디 찾기", "비밀번호 찾기(변경)"};

        if (sendable) {
            String setfrom = "springgabom@gmail.com";
            String title = "[가봄] " + status[statusNum] + " 인증 메일입니다.";
            String content = System.getProperty("line.separator")
                    + "안녕하세요 회원님" + System.getProperty("line.separator")
                    + "가봄" + status[statusNum] + " 인증번호는" + randNum + "입니다." + System.getProperty("line.separator");

            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");

                messageHelper.setFrom(setfrom);
                messageHelper.setTo(toEmail);
                messageHelper.setSubject(title);
                messageHelper.setText(content);

                mailSender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            return true;
        }
        return false;
    }

    //아이디나 비밀번호 찾기 위해서 존재함. user가 null이면 무조건 false 리턴 아니면 이메일 보내지느냐에 따라 다름~
    public Boolean authMail(FindUserDto findUserDto, int statusNum, HttpSession session) {
        //statusnum으로 1,2 인덱스 구분(야매임.)
        String[] info = new String[]{findUserDto.getEmail(), findUserDto.getUserName(), findUserDto.getUserId()};

        User user = userRepository.findByEmail(info[0]).orElseThrow();

        if (user != null) {
            //6자리 랜덤 숫자
            Random r = new Random();
            Integer randNum = r.nextInt(1000000);

            //원하는 유저랑 같으면 됨.
            Boolean sendable = false;

            if (statusNum == 1) {
                session.setAttribute(user.getEmail(), Long.toString(randNum));
                sendable = user.getUserName().equals(info[statusNum]);
            } else if (statusNum == 2) {
                session.setAttribute(user.getEmail(), Long.toString(randNum));
                sendable = user.getUserId().equals(info[statusNum]);

            }
            return sendMail(sendable, info[0], statusNum, randNum);
        }
        return false;
    }
}