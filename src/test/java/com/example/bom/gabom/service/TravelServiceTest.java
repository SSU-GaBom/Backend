package com.example.bom.gabom.service;

import com.example.bom.gabom.model.entity.Travel;
import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.model.repository.TravelRepository;
import com.example.bom.gabom.model.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TravelServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TravelRepository travelRepository;

    @Test
    @DisplayName("travel 들어가기")
    public void TravelMakeTest() {
        // given
        User createuser = new User(30L,"fish","123","eee@naver.com","sion","auth","2022-02-22","2022-02-22","path",0);
        userRepository.save(createuser);
        Travel travel = new Travel(10L,createuser,"Travel_title",true,0,"goodstate","goodcity");
        Travel travel2 = new Travel(createuser,"Travel_title",true,0,"goodstate","goodcity");
        System.out.println("travel = " + travel);
        System.out.println("travel2 = " + travel2);

        // when
        assertThat(travel.getUser()).isEqualTo(createuser);

        // then
    }
}