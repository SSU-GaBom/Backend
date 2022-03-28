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

//    @Test
//    @DisplayName("travel 들어가기")
//    public void TravelMakeTest() {
//        // given
//        User createuser = new User("fish","123","eee@naver.com","sion","auth","2022-02-22","2022-02-22","path",0);
//        userRepository.save(createuser);
//        Travel travel2 = new Travel(createuser,"Travel_title",true,0,"goodstate","goodcity");
//        travelRepository.save(travel2);
//
//        //when
//        Long id =travel2.getTravelId();
//        Travel findtravel = travelService.travel_info(id);
//        // then
//        assertThat(findtravel).isEqualTo(travel2);
//    }
    @Test
    @DisplayName("한 유저에 travel 여러개")
    public void put_travels() {
        // given
        User createuser = new User("fish","123","eee@naver.com","sion","auth","2022-02-22","2022-02-22","path",0);
        userRepository.save(createuser);
        Travel travel2 = new Travel(createuser,"Travel_title",true,0,"goodstate","goodcity");
        Travel travel3 = new Travel(createuser,"Travel_2title",true,0,"goods2tate","good2city");

//         createuser.getMyTravelList().add(travel2);
//         createuser.getMyTravelList().add(travel3);
//        System.out.println("createuser.getMyTravelList() = " + createuser.getMyTravelList());

        travelRepository.save(travel2);
//        travelRepository.save(travel2);
//        Travel findtravel2 = travelRepository.findByTravelId(travel2.getTravelId());
//        System.out.println("findtravel2 = " + findtravel2);
//        System.out.println("travel2 = " + travel2);
        travelRepository.save(travel3);

        //when
        Long id =travel2.getTravelId();
        Long id2 =travel3.getTravelId();
        Travel findtravel = travelService.travel_info(id);
//        Travel findtravel2 = travelService.travel_info(id2);
        // then
        assertThat(findtravel).isEqualTo(travel2);
        assertThat(findtravel2).isEqualTo(travel3);
    }
//    @Test
//    @DisplayName("travel find")
//    public void travelinfotest() {
//        // given
//        User createuser = new User(30L,"fish","123","eee@naver.com","sion","auth","2022-02-22","2022-02-22","path",0);
//        userRepository.save(createuser);
//        Travel travel2 = new Travel(30L,createuser,"Travel_title",true,0,"goodstate","goodcity");
//        travelRepository.save(travel2);
//        // when
//        Long id = travel2.getTravelId();
//        System.out.println("travel2 id = " + id);
//        Travel findtravel = travelService.travel_info(id);
//
//        // then
//        assertThat(findtravel.getTravelId()).isEqualTo(id);
//    }

}