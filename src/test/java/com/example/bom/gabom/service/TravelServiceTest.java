package com.example.bom.gabom.service;

import com.example.bom.gabom.model.entity.Travel;
import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.repository.TravelRepository;
import com.example.bom.gabom.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//import static com.example.bom.gabom.model.entity.Travel.CreateTravel;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TravelServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TravelRepository travelRepository;
    @Autowired
    TravelService travelService;


    @Test
    public void OneUserAndTwo_Travel_Add() { // 한 유저에 여러 트레블 안됨.
        //+ 그냥 두 유저에 세 트레블 연결안됨
        // given
        User createuser = new User("fish","123", "eee@naver.com", "sion", "auth", "2022-02-22", "2022-02-22", "path", 0);
        userRepository.save(createuser);
        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
        Travel travel3 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");

        createuser.add(travel2);
        createuser.add(travel3);

        //when
        travelRepository.save(travel2);
        travelRepository.save(travel3);

        System.out.println("travel2.getTravelId() = " + travel2.getTravelId());
        Travel FindTravel = travelService.travel_info(travel2.getTravelId());
        //then
        assertThat(FindTravel).isEqualTo(travel2);


    }

}