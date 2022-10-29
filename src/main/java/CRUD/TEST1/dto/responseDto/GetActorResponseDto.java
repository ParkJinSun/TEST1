package CRUD.TEST1.dto.responseDto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GetActorResponseDto {
    String peopleCd;
    String peopleNm;
    String peopleNmEn;
    String repRoleNm;
    String filmoNames;

    @Builder
    public GetActorResponseDto(String peopleCd, String peopleNm, String peopleNmEn, String repRoleNm, String filmoNames, String boxofficeType, String showRange, String rnum, String rank, String rankInten, String rankOldAndNew, String movieCd, String movieNm, String openDt, String salesAmt, String salesShare, String salesInten, String salesChange, String salesAcc, String audiCnt, String audiInten, String audiChange, String audiAcc, String scrnCnt, String showCnt) {
        this.peopleCd = peopleCd;
        this.peopleNm = peopleNm;
        this.peopleNmEn = peopleNmEn;
        this.repRoleNm = repRoleNm;
        this.filmoNames = filmoNames;
    }
}
