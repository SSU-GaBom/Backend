package com.example.bom.gabom.service;

import com.example.bom.gabom.entity.Comment;
import com.example.bom.gabom.entity.Travel;
import com.example.bom.gabom.repository.CommentRepository;
import com.example.bom.gabom.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final TravelRepository travelRepository;

    public boolean CreateComment(Comment comment, Long travelId) { //로그인세션에 맞춰서 Travel 쓰도록 하기
        Travel travel = travelRepository.findByTravelId(travelId);
        if(travel==null){
            System.out.println("댓글 달기 중 travel 없음");
            return false;
        }
        comment.setTravel(travel);
        commentRepository.save(comment);
        return true;
    }
}
