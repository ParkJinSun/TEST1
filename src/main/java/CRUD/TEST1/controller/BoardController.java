package CRUD.TEST1.controller;

import CRUD.TEST1.dto.requestDto.BoardRequestDto;
import CRUD.TEST1.dto.responseDto.ResponseDto;
import CRUD.TEST1.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public ResponseDto<?> read(HttpServletRequest request){
        return boardService.findBoard(request);
    }

    @GetMapping("/board/{id}")
    public ResponseDto<?> readById(@PathVariable Long id,HttpServletRequest request){
        return boardService.findBoardById(id,request);
    }

    @GetMapping("/board/name/{writer}")
    public ResponseDto<?> readByWriter(@PathVariable String writer,HttpServletRequest request){
        return boardService.findBoardByWriter(writer,request);
    }

    @PostMapping("/board")
    public ResponseDto<?> save(@RequestBody @Validated BoardRequestDto requestDto, HttpServletRequest request){
        return boardService.createBoard(requestDto, request);
    }

    @PutMapping("/board/{id}")
    public ResponseDto<?> update(@RequestBody @Validated BoardRequestDto requestDto
            ,@PathVariable Long id, HttpServletRequest request){
        return boardService.updateBoard(id, requestDto, request);
    }

    @DeleteMapping("/board/{id}")
    public ResponseDto<?> delete(@PathVariable Long id, HttpServletRequest request){
        return boardService.deleteBoardById(id,request);
    }

}
