package CRUD.TEST1.controller;

import CRUD.TEST1.dto.requestDto.LoginRequestDto;
import CRUD.TEST1.dto.requestDto.MemberRequestDto;
import CRUD.TEST1.dto.responseDto.ResponseDto;
import CRUD.TEST1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/login")
    public ResponseDto<?> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        log.info("login 호출");
        return memberService.login(requestDto,response);
    }

    @PostMapping("/member/signup")
    public ResponseDto<?> signup(@RequestBody MemberRequestDto requestDto) {
        log.info("signup 호출");
        return memberService.createMember(requestDto);
    }

    @PostMapping("/member/logout")
    public ResponseDto<?> logout(HttpServletRequest request){
        log.info("logout 호출");
        return memberService.logout(request);
    }
}
