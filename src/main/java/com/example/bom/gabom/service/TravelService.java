package com.example.bom.gabom.service;

import com.example.bom.gabom.model.entity.Travel;
import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.repository.TravelRepository;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final UserRepository userRepository;

    public Travel save(String username, Travel travel) throws Exception{
        User user = userRepository.findByUserName(username);
          //만약 userName이 없으면? -> 에러처리.

        travel.setUser(user);
        return travelRepository.save(travel);
    }
    public Travel travel_info(Long travelId) {
        if(travelRepository.findByTravelId(travelId) ==null) {
            System.out.println("findByTravelId is null");
        }else
        return travelRepository.findByTravelId(travelId);
        return null;
    }

}
