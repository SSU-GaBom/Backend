package com.example.bom.gabom.service;

import com.example.bom.gabom.model.dto.travel.UpdateTravelDto;
import com.example.bom.gabom.model.entity.Travel;
import com.example.bom.gabom.model.entity.User;
import com.example.bom.gabom.repository.TravelRepository;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TravelService {

    private final TravelRepository travelRepository;
    private final UserRepository userRepository;

    @Transactional
    public Travel save(String username, Travel travel){
        User user = userRepository.findByUserName(username);
          //만약 userName이 없으면? -> 에러처리. 근데 로그인세션으로 할거니까 그때 보고?하기.
        user.add(travel);
        return travelRepository.save(travel);
    }

    public Travel travel_info(Long travelId) {
        return travelRepository.findByTravelId(travelId);
    }

    //게시글 수정
    @Transactional
    public boolean updateTravel(UpdateTravelDto updateTravelDto){
        Travel travel = travelRepository.findByTravelId(updateTravelDto.getTravelId());
        if(travel==null){ //
            return false;
        }
        travel.updateTravel(updateTravelDto);
        return true;
    }

    public List<Travel> FindTravelsBypageRequest(Pageable pageable){
        return travelRepository.findAll(pageable).getContent();
    }

    public List<Travel> FindTravels(){
        return travelRepository.findAll();
    }

//    Pageable sortedByPriceDesc = PageRequest.of(0, 3, Sort.by("likeCount").descending());


    @Transactional
    public void deleteTravel(Long travelId) { travelRepository.deleteByTravelId(travelId);
    }

    //==비즈니스 로직==//

}
