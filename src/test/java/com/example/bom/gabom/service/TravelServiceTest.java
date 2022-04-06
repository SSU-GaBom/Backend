package com.example.bom.gabom.service;

import com.example.bom.gabom.model.dto.travel.UpdateTravelDto;
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

    @Test
    public void UpdateTest() throws InterruptedException {
        // given
        User createuser = new User("fish","123", "eee@naver.com", "sion", "auth", "2022-02-22", "2022-02-22", "path", 0);
        userRepository.save(createuser);
        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
        Travel travel1 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");
        createuser.add(travel2);
        createuser.add(travel1);
        //when
        travelRepository.save(travel2);
        travelRepository.save(travel1);
        // when
        System.out.println("travel1.getCreatedAt() = " + travel1.getCreatedAt());
        System.out.println("travel1.UpdateAt() = " + travel1.getUpdateAt());
        UpdateTravelDto dto = new UpdateTravelDto();
        dto.setTravelId(3L);
        dto.setContent("new Content!!");
        dto.setTitle("new Title!");
        Thread.sleep(1000);
        List<Travel> all = travelRepository.findAll();
        System.out.println(" before---");
        for (Travel travel : all) {
            System.out.println("travel.getTitle() = " + travel.getTitle());
            System.out.println("travel.getUpdate() = " + travel.getUpdateAt());
        }
        System.out.println("dto.getTravelId() = " + dto.getTravelId());
        travelService.updateTravel(dto);
        List<Travel> all2 = travelRepository.findAll();
        System.out.println(" after---");
        for (Travel travel : all2) {
            System.out.println("travel.getTitle() = " + travel.getTitle());
            System.out.println("travel.getUpdate() = " + travel.getUpdateAt());
        }
        // then
        Travel findTravel = travelRepository.findByTravelId(travel1.getTravelId());
        System.out.println("travel1.getTravelId() = " + travel1.getTravelId());
        System.out.println("findTravel.getTravelId() = " + findTravel.getTravelId());
        assertThat(findTravel.getContent()).isEqualTo("new Content!!");
    }

    private void MakeUser_Travels(User createuser,Travel travel1,Travel travel2) {
        createuser = new User("fish","123", "eee@naver.com", "sion", "auth", "2022-02-22", "2022-02-22", "path", 0);
        userRepository.save(createuser);
        travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
        travel1 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");
        createuser.add(travel2);
        createuser.add(travel1);
        //when
        travelRepository.save(travel2);
        travelRepository.save(travel1);
    }

}