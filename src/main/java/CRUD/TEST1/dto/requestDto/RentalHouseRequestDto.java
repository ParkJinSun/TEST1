package CRUD.TEST1.dto.requestDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RentalHouseRequestDto {
    String brtcCode;
    String signguCode;
    String numOfRows;
    String pageNo;
}
