package com.example.bom.gabom.repository;

import com.example.bom.gabom.model.entity.Travel;
import com.example.bom.gabom.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class TravelRepositoryTest {
    @Autowired
    TravelRepository travelRepository;
    @Autowired
    UserRepository userRepository;



    @Test
    @DisplayName("왜 User_Travel끼리는 안됨?")
    public void context() {
        User createuser = new User("fish","123", "eee@naver.com", "sion", "auth", "2022-02-22", "2022-02-22", "path", 0);
        userRepository.save(createuser);
        List<User> all1 = userRepository.findAll();
        for (User user : all1) {
            System.out.println("user = " + user);
        }
        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
        Travel travel3 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");

        createuser.add(travel2);
        createuser.add(travel3); //이게 작동이 안되는것같음.
        //OK.

        travelRepository.save(travel2);
        travelRepository.save(travel3);

        List<Travel> all = travelRepository.findAll();
        for (Travel travel : all) {
            System.out.println("travel = " + travel);
        }
    }

    @Test
    public void IFNoID() {
        // given
        Travel byTravelId = travelRepository.findByTravelId(1L);
        // when

        // then
        Assertions.assertThat(byTravelId).isEqualTo(null);
    }



}