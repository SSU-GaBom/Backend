package com.example.bom.gabom.service;

import com.example.bom.gabom.dto.UpdateTravelDto;
import com.example.bom.gabom.entity.Travel;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.repository.TravelRepository;
import com.example.bom.gabom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public boolean save(String username, Travel travel){
        User user = userRepository.findByUserName(username);
          //만약 userName이 없으면? -> 에러처리. 근데 로그인세션으로 할거니까 그때 보고?하기.
        user.add(travel);
        travelRepository.save(travel);
        return true;
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


    public Page<Travel> TravelsByLikeCount(int Page,int Size){
        PageRequest pageRequest = PageRequest.of(Page, Size, Sort.by(Sort.Direction.DESC, "likedCount"));
        Page<Travel> memberPages = travelRepository.findAll(pageRequest);
        return memberPages;
    }

//    Pageable sortedByPriceDesc = PageRequest.of(0, 3, Sort.by("likeCount").descending());



    @Transactional
    public void deleteTravel(Long travelId) { travelRepository.deleteByTravelId(travelId);
    }

    //==비즈니스 로직==//

}
