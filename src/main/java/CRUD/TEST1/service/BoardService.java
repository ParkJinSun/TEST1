package CRUD.TEST1.service;

import CRUD.TEST1.domain.Board;
import CRUD.TEST1.domain.Member;
import CRUD.TEST1.dto.requestDto.BoardRequestDto;
import CRUD.TEST1.dto.responseDto.BoardResponseDto;
import CRUD.TEST1.dto.responseDto.ResponseDto;
import CRUD.TEST1.jwt.TokenProvider;
import CRUD.TEST1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final TokenProvider tokenProvider;

    public ResponseDto<?> createBoard(BoardRequestDto requestDto, HttpServletRequest request){
        if(null != validateToken(request)){
            return validateToken(request);
        }

        Board board = Board.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .writer(requestDto.getWriter())
                .build();

        boardRepository.save(board);

        return ResponseDto.success(BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .modifiedDate(board.getModifiedAt())
                .createdDate(board.getCreatedAt())
                .build()
        );
    }
    public ResponseDto<?> findBoard(HttpServletRequest request) {

        if(null != validateToken(request)){
            return validateToken(request);
        }

        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();

        for (Board board : boardList) {
            boardResponseDtoList.add(
               BoardResponseDto.builder()
                        .title(board.getTitle())
                        .content(board.getContent())
                        .writer(board.getWriter())
                        .createdDate(board.getCreatedAt())
                        .build()
            );
        }
        return ResponseDto.success(boardResponseDtoList);
    }
    public ResponseDto<?> findBoardById(Long id,HttpServletRequest request) {

        if(null != validateToken(request)){
            return validateToken(request);
        }

        Board board = getPresentBoard(id);
        if(null == board){
            return ResponseDto.fail("진또리 경고","ID가 존재하지 않습니다.");
        }

        return ResponseDto.success(BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdDate(board.getCreatedAt())
                .build()
        );
    }

    public ResponseDto<?> findBoardByWriter(String writer,HttpServletRequest request) {

        if(null != validateToken(request)){
            return validateToken(request);
        }

        Board board = boardRepository.findByWriter(writer).orElse(null);
        if(null == board){
            return ResponseDto.fail("진또리 경고","작성자가 존재하지 않습니다.");
        }

        return ResponseDto.success(BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdDate(board.getCreatedAt())
                .build()
        );
    }
    @Transactional
    public ResponseDto<?> updateBoard(Long id, BoardRequestDto requestDto, HttpServletRequest request) {

        if(null != validateToken(request)){
            return validateToken(request);
        }

        Board board = boardRepository.findById(id).orElse(null);
        if(board == null){
            return ResponseDto.fail("진또리 경고","ID가 존재하지 않습니다.");
        }

        board.update(requestDto);

        return ResponseDto.success(BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdDate(board.getCreatedAt())
                .modifiedDate(board.getModifiedAt())
                .build()
        );
    }

    public ResponseDto<?> deleteBoardById(Long id, HttpServletRequest request){

        if(null != validateToken(request)){
            return validateToken(request);
        }

        Board board = boardRepository.findById(id).orElse(null);
        if(board == null){
            return ResponseDto.fail("진또리 경고","ID가 존재하지 않습니다.");
        }

        boardRepository.deleteById(id);

        return ResponseDto.success(BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdDate(board.getCreatedAt())
                .modifiedDate(board.getModifiedAt())
                .build()
        );
    }
    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }

    private Board getPresentBoard(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    private ResponseDto<?> validateToken(HttpServletRequest request) {
        if(null == request.getHeader("Refresh-Token")){
            return ResponseDto.fail("진또리 경고", "로그인이 필요합니다.");
        }
        if(null == request.getHeader("Authorization")){
            return ResponseDto.fail("진또리 경고", "로그인이 필요합니다.");
        }
        if(null == validateMember(request)){
            return ResponseDto.fail("진또리 경고", "유효하지 않은 토큰 입니다.");
        }
        return null;
    }
}
