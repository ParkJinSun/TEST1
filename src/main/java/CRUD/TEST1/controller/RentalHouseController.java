package CRUD.TEST1.controller;

import CRUD.TEST1.dto.requestDto.RentalHouseRequestDto;
import CRUD.TEST1.dto.responseDto.ResponseDto;
import CRUD.TEST1.service.RentalHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class RentalHouseController {
    RentalHouseService rentalHouseService;

//    public ResponseDto<?> getRentalHouse(@RequestBody RentalHouseRequestDto requestDto){
////        rentalHouseService.
//    }
}
