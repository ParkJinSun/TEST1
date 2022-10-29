package CRUD.TEST1.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetByDateResponseDto {
    String movieCd;
    String movieNm;
    String movieNmEn;
    String prdtYear;
    String openDt;
    String nationAlt;
    String genreAlt;
}
