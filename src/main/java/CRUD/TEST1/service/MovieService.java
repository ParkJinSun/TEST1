package CRUD.TEST1.service;

import CRUD.TEST1.Api.MovieApi;
import CRUD.TEST1.dto.requestDto.MovieRequestDto;
import CRUD.TEST1.dto.responseDto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieApi movieApi;
    public ResponseDto<?> getByActorName(MovieRequestDto requestDto){
        return movieApi.getByActorName(requestDto);
    }

    public ResponseDto<?> getBoxOffice(MovieRequestDto requestDto){
        return movieApi.getBoxOffice(requestDto);
    }

    public ResponseDto<?> getByDate(MovieRequestDto requestDto){
        return movieApi.getByDate(requestDto);
    }
}
