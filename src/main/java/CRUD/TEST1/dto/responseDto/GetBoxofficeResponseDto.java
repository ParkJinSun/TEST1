package CRUD.TEST1.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBoxofficeResponseDto {
    String rank;
    String rankInten;
    String rankOldAndNew;
    String movieCd;
    String movieNm;
    String openDt;

}
