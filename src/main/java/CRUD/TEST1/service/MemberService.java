package CRUD.TEST1.service;

import CRUD.TEST1.domain.Member;
import CRUD.TEST1.dto.requestDto.LoginRequestDto;
import CRUD.TEST1.dto.requestDto.MemberRequestDto;
import CRUD.TEST1.dto.requestDto.TokenDto;
import CRUD.TEST1.dto.responseDto.MemberResponseDto;
import CRUD.TEST1.dto.responseDto.ResponseDto;
import CRUD.TEST1.jwt.TokenProvider;
import CRUD.TEST1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;


    public ResponseDto<?> login(LoginRequestDto requestDto, HttpServletResponse response){

        Member member = getPresentMember(requestDto.getMemberName());
        if(null == member){
            return ResponseDto.fail("진또리 경고 3번", "이름이 존재하지 않습니다.");
        }
        if(!member.validatePassword(passwordEncoder, requestDto.getPassword())){
            return ResponseDto.fail("진또리 경고 4번", "패스워드가 맞지 않습니다.");
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(member);
        tokenToHeader(tokenDto,response);

        return ResponseDto.success(MemberResponseDto.builder()
                .id(member.getId())
                .memberName(member.getMemberName())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .build()
        );
    }

    public ResponseDto<?> createMember(MemberRequestDto requestDto) {
        if(null != getPresentMember(requestDto.getMemberName())){
            return ResponseDto.fail("진또리 경고 1번","중복된 이름 입니다.");
        }
        if(!requestDto.getPassword().equals(requestDto.getPasswordCheck())){
            return ResponseDto.fail("진또리 경고 2번","패스워드가 다릅니다 다시 확인해주세요.");
        }
        Member member = Member.builder()
                .memberName(requestDto.getMemberName())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();

        memberRepository.save(member);

        return ResponseDto.success(MemberResponseDto.builder()
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .memberName(member.getMemberName())
                .id(member.getId())
                .build());
    }

    public ResponseDto<?> logout(HttpServletRequest request) {
        if(!tokenProvider.validateToken(request.getHeader("RefreshToken"))){
            return ResponseDto.fail("진또리 경고 5번", "Token이 유효하지 않습니다.");
        }
        Member member = tokenProvider.getMemberFromAuthentication();
        if(member == null){
            return ResponseDto.fail("진또리 경고 3번", "이름이 존재하지 않습니다.");
        }
        return tokenProvider.deleteRefreshToken(member);
    }

    public Member getPresentMember(String memberName){
        Optional<Member> optionalMember = memberRepository.findByMemberName(memberName);
        return optionalMember.orElse(null);
    }

    public void tokenToHeader(TokenDto tokenDto, HttpServletResponse response){
        response.addHeader("Authorization","Bearer" + tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());
        response.addHeader("Access-Token-Expire-Time", tokenDto.getAccessTokenExpiresIn().toString());

    }
}
