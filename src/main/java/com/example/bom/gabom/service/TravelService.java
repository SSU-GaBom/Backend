package com.example.bom.gabom.service;

import com.example.bom.gabom.model.entity.Travel;
import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.repository.TravelRepository;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TravelService {

    private final TravelRepository travelRepository;
    private final UserRepository userRepository;

    @Transactional
    public Travel save(String username, Travel travel) throws Exception{
        User user = userRepository.findByUserName(username);
          //만약 userName이 없으면? -> 에러처리. 근데 로그인세션으로 할거니까 그때 보고?하기.
        user.add(travel);
        return travelRepository.save(travel);
    }

    public Travel travel_info(Long travelId) {
//        travelRepository.existsById(travelId)
        if(travelRepository.findByTravelId(travelId) ==null) {
            System.out.println("findByTravelId is null");
        }else
        return travelRepository.findByTravelId(travelId);
        return null;
    }

    @Transactional
    public void deleteTravel(Long travelId) { travelRepository.deleteByTravelId(travelId);
    }

}
