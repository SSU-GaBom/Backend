package com.example.bom.gabom.controller;

import com.example.bom.gabom.advice.exception.CUserNotFoundException;
import com.example.bom.gabom.dto.UserDto;
import com.example.bom.gabom.entity.User;
import com.example.bom.gabom.repository.UserRepository;
import com.example.bom.gabom.model.response.CommonResult;
import com.example.bom.gabom.model.response.ListResult;
import com.example.bom.gabom.model.response.SingleResult;
import com.example.bom.gabom.service.ResponseService;
import com.example.bom.gabom.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Api(tags = {"2. User"}) // UserController를 대표하는 최상단 타이틀 영역에 표시될 값 세팅
@RequiredArgsConstructor // class 내부의 final 객체는 Constructor Injection 수행, @Autowired도 가능
@RestController // 결과를 JSON으로 도출
@RequestMapping(value = "/api/user") // api resource를 버전별로 관리, /v1 을 모든 리소스 주소에 적용
public class UserController {

    private final UserRepository userRepository; // Jpa를 활용한 CRUD 쿼리 가능
    private final ResponseService responseService; // 결과를 처리하는 Service
    private final UserService userService;

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
//                    required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.") // 각각의 resource에 제목과 설명 표시
//    @GetMapping(value = "/users") // user 테이블의 모든 정보를 읽어옴
//    public ListResult<User> findAllUser() { // 데이터가 1개 이상일 수 있기에 List<User>로 선언
//        return responseService.getListResult(userRepository.findAll()); // JPA를 사용하면 CRUD에 대해 설정 없이 쿼리 사용 가능 (select * from user 와 같음)
//        //결과 데이터가 여러개인 경우 getListResult 활용
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
//                    required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 단건 조회", notes = "회원번호(msrl)로 회원을 조회한다.")
//    @GetMapping(value = "/user")
//    public SingleResult<User> findUserById(@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String id = authentication.getName();
//        return responseService.getSingleResult(userRepository.findByUserId(id).orElseThrow(CUserNotFoundException::new));
//        // 결과 데이터가 단일건인 경우 getSingleResult를 이용하여 결과를 출력
//    }


    //crossorigin 수정해야함. id가 변경 가능하다면 변경하는걸로
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 수정", notes = "프로필 이미지를 제외한 회원정보를 수정한다.")
    @CrossOrigin("http://localhost:8080")
    @PutMapping(value = "/update/{user_id}")
    public SingleResult<User> update(
            @PathVariable(name = "user_id") String userId,
            @RequestBody UserDto userDto) {

        userService.updateProfile(userDto);

        return responseService.getSingleResult(userRepository.save(new User()));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 수정", notes = "프로필 이미지를 제외한 회원정보를 수정한다.")
    @CrossOrigin("http://localhost:8080")
    @PutMapping(value = "/update-profile/{user_id}")
    public SingleResult<User> updateProfile(
            @PathVariable(name = "user_id") String userId,
            @RequestBody UserDto userDto) {

        userService.updateProfile(userDto);

        return responseService.getSingleResult(userRepository.save(new User()));
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
//                    required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 삭제", notes = "msrl로 회원정보를 삭제한다.")
//    @DeleteMapping(value = "/user/{msrl}")
//    public CommonResult delete (
//            @ApiParam(value = "회원정보", required = true) @PathVariable long msrl ) {
//        userRepository.deleteById(msrl); // deleteById id를 받아 delete query 실행
//        return responseService.getSuccessResult();
//        // 성공 결과 정보만 필요한 경우 getSuccessResult()를 이용하여 결과를 출력
//    }

    @GetMapping("/confirm-email")
    public void viewConfirmEmail(@Valid @RequestParam  String token, HttpServletResponse response) throws IOException {
        userService.confirmEmail(token);
        String redirect_uri="http://localhost:8080/sign/sginin";
        response.sendRedirect(redirect_uri);
    }
}