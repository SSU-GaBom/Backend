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

import static com.example.bom.gabom.model.entity.Travel.CreateTravel;
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


        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
        Travel travel3 = new Travel("Travel_t2itle", true, 0, "goodst2ate", "g2oodcity");

        User createuser = new User("fish","123","eee@naver.com","sion","auth","2022-02-22","2022-02-22","path",0);
        System.out.println("1 = " + 1);
        userRepository.save(createuser);
        System.out.println("1 = " + 2);
//        Travel travel2 = CreateTravel(createuser, "Travel_title", true, 0, "goodstate", "goodcity");
//        Travel travel3 = CreateTravel(createuser, "Travel_ti1tle", true, 0, "goo2dstate", "go3odcity");
//         createuser.getMyTravelList().add(travel2);
//         createuser.getMyTravelList().add(travel3);
//        System.out.println("createuser.getMyTravelList() = " + createuser.getMyTravelList());

        System.out.println("createuser.getUserNo() = " + createuser.getUserNo());
        createuser.add(travel2);
        createuser.add(travel3);
        
        travelRepository.save(travel2);
        System.out.println("travel2.getTravelId() = " + travel2.getTravelId());
//        System.out.println("travelService.travel_info(travel2.getTravelId()) = " + travelService.travel_info(travel2.getTravelId()));
        travelRepository.save(travel3);
        System.out.println("travel3.getTravelId() = " + travel3.getTravelId());
        System.out.println("travelService.travel_info(travel3.getTravelId()) = " + travelService.travel_info(travel3.getTravelId()));



//        Travel findtravel2 = travelRepository.findByTravelId(travel2.getTravelId());
//        System.out.println("findtravel2 = " + findtravel2);
//        System.out.println("travel2 = " + travel2);
        //when
        /*Long id =travel2.getTravelId();
//        Long id2 =travel3.getTravelId();
        Travel findtravel = travelService.travel_info(id);
//        Travel findtravel2 = travelService.travel_info(id2);
        // then
        assertThat(findtravel).isEqualTo(travel2);*/
//        assertThat(findtravel2).isEqualTo(travel3);
    }
    @Test
    public void travelmake() {
        // given
        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
        travelRepository.save(travel2);
        // when
        System.out.println("1 = " + 1);
        // then
    }
    @Test
    @DisplayName("travel 정보 조회")
    public void realtest() {
        // given
        User createuser = new User("fish","123","eee@naver.com","sion","auth","2022-02-22","2022-02-22","path",0);
        User createuser2 = new User("f2ish","1232","eee@naver.com","sion","auth","2022-02-22","2022-02-22","path",0);
        userRepository.save(createuser);
        userRepository.save(createuser2);
        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
        Travel travel3 = new Travel("Travel_tit2le", true, 0, "goodstat2e", "good2city");
        Travel travel4 = new Travel("Travel_2tit2le", true, 0, "goodst2at2e", "g2ood2city");
         //travel이 저장이 안됨.
        System.out.println("2");
        System.out.println("travel2 = " + travel2);
        travelRepository.save(travel2);
        Travel tt = travelRepository.findAll().get(0);
        System.out.println("tt = " + tt);
//        travelRepository.findByTravelId(travel2.getTravelId());
        System.out.println("travel2 = " + travel2);

        System.out.println("1 = " + 1);
        travelRepository.save(travel3);
        System.out.println("44 = " + 44);
        travelRepository.save(travel4);
        System.out.println("55 = " + 55);
        
//        System.out.println("hello!!");
//        travelRepository.save(travel2);
//        System.out.println("hello33!!");
//        travelRepository.save(travel3);
//
//        System.out.println("hello2!!");
//        createuser.add(travel2);
//        createuser.add(travel3);
//
//        System.out.println("qwerasd");



        System.out.println("sibal");
        List<Travel> all = travelRepository.findAll();
        for (Travel travel : all) {
            System.out.println("travel.getTravelId() = " + travel.getTravelId());
        }

        // when
        System.out.println("travel2.getTravelId() = " + travel2.getTravelId());
        Travel Found = travelRepository.findByTravelId(travel2.getTravelId());
        // then

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