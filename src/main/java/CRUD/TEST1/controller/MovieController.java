package CRUD.TEST1.controller;

import CRUD.TEST1.dto.requestDto.MovieRequestDto;
import CRUD.TEST1.dto.responseDto.ResponseDto;
import CRUD.TEST1.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movie/name")
    public ResponseDto<?> getByActorName(@RequestBody MovieRequestDto requestDto){
        return movieService.getByActorName(requestDto);
    }

    @PostMapping("/movie/boxOffice")
    public ResponseDto<?> getBoxOffice(@RequestBody MovieRequestDto requestDto){
        return movieService.getBoxOffice(requestDto);
    }

    @PostMapping("/movie/date")
    public ResponseDto<?> getByDate(@RequestBody MovieRequestDto requestDto){
        return movieService.getByDate(requestDto);
    }
}
