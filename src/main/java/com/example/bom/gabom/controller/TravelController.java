package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.entity.Travel;
import com.example.bom.gabom.repository.TravelRepository;
import com.example.bom.gabom.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel")
@RequiredArgsConstructor
//@Transactional
public class TravelController {

    private final TravelService travelService;
    private final TravelRepository travelRepository;


    @PostMapping("")
    public String writeTravel(@RequestBody Travel travel) { //로그인세션에 맞춰서 Travel 쓰도록 하기
        String name = "사사사"; //name이 중복되거나 없으면 안되네
        try{
            travelService.save(name, travel);
        }catch(Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "write";
    }


    @GetMapping("/{travelId}") // Travel 자세한 내용 리턴. 지금은 그냥 Title 리턴하게 잠시.
     public String Travel_info(@PathVariable Long travelId){ //string으로 들어오나?
//        try{
            Travel travel = travelService.travel_info(travelId);
            return travel.getTitle();
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
    }

//    @PostMapping("/{travelId}") // Travel 자세한 내용 리턴. 지금은 그냥 Title 리턴하게 잠시.
//    //업데이트를 뭘로할지. 그냥 모든걸 다 업데이트 할수있게??
//    public String TravelUpdate(@RequestBody Travel travel,@PathVariable Long travelId){ //string으로 들어오나?
//            Travel before_travel = travelRepository.findByTravelId(travelId);
//            before_travel.set
//            return travel.getTitle();
//        }catch (Exception e){
//            return null;
//        }
//    }

    //Put으로 바꿀지 Post로 할지 말 필요.
//    @PutMapping("/{travelId}")
//    public String replaceBoard(@RequestBody Travel newTravel, @PathVariable Long travelId) {
//        travelRepository.findById(travelId)
//                .map(Board -> {
//                    Board.setTitle(newTravel.getTitle());
//                    Board.setContent(newTravel.getContent());
//                    travelRepository.save(Board);
//                })
//                .orElseGet(() -> { //replace할게 없으면
//                    newTravel.setTravelId(travelId);
//                    return travelRepository.save(newTravel);
//                });
//    }


    @GetMapping("/zzim/{userId}")
    public void func(){

    }

    @GetMapping("/recommend") //전국에서 추천 많은순 여행리스트
    public void func2(){

    }

    @GetMapping("recommend/more") //
    public void func4(){

    }


    //redirect 생각
    @DeleteMapping("/{travelId}")
    String deleteTravel(@PathVariable Long travelId) {
        deleteTravel(travelId);
        return "deleted";
    }



}
