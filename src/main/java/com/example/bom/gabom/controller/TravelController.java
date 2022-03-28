package com.example.bom.gabom.controller;

import com.example.bom.gabom.model.entity.Travel;
import com.example.bom.gabom.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;



    @PostMapping("")
    public String writeTravel(@RequestBody Travel travel) {
        String name = "사사사"; //name이 중복되거나 없으면 안되네
        try{
            travelService.save(name, travel);
        }catch(Exception e) {
            e.printStackTrace();
            return "false";
        }

        return "write";
    }

    @GetMapping("/{travelId}") // Travel 자세한 내용
    public Travel Travel_info(@PathVariable String travelId){ //string으로 들어오나?
        Long id = Long.parseLong(travelId);
        try{
            return travelService.travel_info(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error<123> = " + e);
            return null;
        }
    }


    @GetMapping("/zzim/{userId}")
    public void func(){

    }

    @GetMapping("/recommend") //전국에서 추천 많은순 여행리스트
    public void func2(){

    }

    @GetMapping("recommend/more") //
    public void func4(){

    }




}
