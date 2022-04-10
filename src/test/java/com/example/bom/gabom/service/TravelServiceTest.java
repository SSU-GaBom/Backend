package com.example.bom.gabom.service;

import com.example.bom.gabom.dto.UpdateTravelDto;
import com.example.bom.gabom.entity.Travel;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.repository.TravelRepository;
import com.example.bom.gabom.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//import static com.example.bom.gabom.model.entity.Travel.CreateTravel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

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
    public void OneUserAndTwo_Travel_Add() throws InterruptedException { // 한 유저에 여러 트레블 안됨.
        //+ 그냥 두 유저에 세 트레블 연결안됨
        // given
        User createuser = new User();
        createuser.setUserName("시온");
        userRepository.save(createuser);
        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
        Travel travel3 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");

        createuser.add(travel2);
        createuser.add(travel3);

        //when
        travelRepository.save(travel2);
        travelRepository.save(travel3);

        System.out.println("travel2.getTravelId() = " + travel2.getTravelId());
        Thread.sleep(10000);
        Travel FindTravel = travelService.travel_info(travel2.getTravelId());
        Thread.sleep(10000);

        System.out.println("FindTravel.getState() = " + FindTravel.getState());
        Thread.sleep(10000);
            //then
            
        
//        assertThat(FindTravel).isEqualTo(travel2);
//
//        assertThat(FindTravel.getCity()).isEqualTo("goodcit2y");

    }

//    @Test
//    public void UpdateTest() throws InterruptedException {
//        // given
//        User createuser = new User("fish","123", "eee@naver.com", "sion", "auth", "2022-02-22", "2022-02-22", "path", 0);
//        userRepository.save(createuser);
//        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
//        Travel travel1 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");
//        createuser.add(travel2);
//        createuser.add(travel1);
//        //when
//        travelRepository.save(travel2);
//        travelRepository.save(travel1);
//        // when
//        System.out.println("travel1.getCreatedAt() = " + travel1.getCreatedAt());
//        System.out.println("travel1.UpdateAt() = " + travel1.getUpdateAt());
//        UpdateTravelDto dto = new UpdateTravelDto();
//        dto.setTravelId(3L);
//        dto.setContent("new Content!!");
//        dto.setTitle("new Title!");
//        Thread.sleep(1000);
//        List<Travel> all = travelRepository.findAll();
//        System.out.println(" before---");
//        for (Travel travel : all) {
//            System.out.println("travel.getTitle() = " + travel.getTitle());
//            System.out.println("travel.getUpdate() = " + travel.getUpdateAt());
//        }
//        System.out.println("dto.getTravelId() = " + dto.getTravelId());
//        travelService.updateTravel(dto);
//        List<Travel> all2 = travelRepository.findAll();
//        System.out.println(" after---");
//        for (Travel travel : all2) {
//            System.out.println("travel.getTitle() = " + travel.getTitle());
//            System.out.println("travel.getUpdate() = " + travel.getUpdateAt());
//        }
//        // then
//        Travel findTravel = travelRepository.findByTravelId(travel1.getTravelId());
//        System.out.println("travel1.getTravelId() = " + travel1.getTravelId());
//        System.out.println("findTravel.getTravelId() = " + findTravel.getTravelId());
//        assertThat(findTravel.getContent()).isEqualTo("new Content!!");
//    }

//    private void MakeUser_Travels(User createuser,Travel travel1,Travel travel2) {
//        createuser = new User("fish","123", "eee@naver.com", "sion", "auth", "2022-02-22", "2022-02-22", "path", 0);
//        userRepository.save(createuser);
//        travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
//        travel1 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");
//        createuser.add(travel2);
//        createuser.add(travel1);
//        //when
//        travelRepository.save(travel2);
//        travelRepository.save(travel1);
//    }

    @Test
    @DisplayName("페이징 테스트(list)")
    void list() throws InterruptedException {
        User createuser = new User();
        createuser.setUserName("시온");
        userRepository.save(createuser);
        travelRepository.save(new Travel("Travel1_title", true, 1, "goo2dstate", "goo3city"));
        travelRepository.save(new Travel("Travel2_title", true, 7, "goo2dstate", "goo3city"));
        travelRepository.save(new Travel("Travel3_title", true, 100, "goo2dstate", "goo3city"));
        travelRepository.save(new Travel("Travel4_title", true, 6, "goo2dstate", "goo3city"));
        travelRepository.save(new Travel("Travel5_title", true, 10, "goo2dstate", "goo3city"));
        // given

        // when
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "likedCount"));
        Page<Travel> memberPages = travelRepository.findAll(pageRequest);

        for (Travel memberPage : memberPages) {
            System.out.println("memberPage.getTitle() = " + memberPage.getTitle());
            System.out.println("memberPage.getLikeCount() = " + memberPage.getLikedCount());
        }
        System.out.println("end");
        // then
    }
}