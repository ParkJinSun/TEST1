package CRUD.TEST1.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieRequestDto {
//    공통 목록
    String curPage;
    String itemPerPage;
    String repNationCd;
//    영화인 목록
    String peopleNm;
    String filmoNames;

    //주간 박스오피스
    String targetDt;
    String weekGb;
    String multiMovieYn;
    String wideAreaCd;
//    영화 목록
    String movieNm;
    String directorNm;
    String openStartDt;
    String openEndDt;
    String prdtStartYear;
    String prdtEndYear;
    String movieTypeCd;

}
