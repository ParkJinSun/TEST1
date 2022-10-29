package CRUD.TEST1.dto.requestDto;

import CRUD.TEST1.domain.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {
    @NotBlank(message = "빈 값을 허용하지 않습니다.")
    @Size(min = 2, max = 20, message = "1 이상 20 이하의 값을 입력 해주세요.")
    private String title;
    @NotBlank(message = "빈 값을 허용하지 않습니다")
    @Size(min = 2, max = 100, message = "1 이상 100 이하의 값을 입력 해주세요.")
    private String content;
    @NotBlank(message = "빈 값을 허용하지 않습니다")
    @Size(min = 2, max = 20, message = "1 이상 20 이하의 값을 입력 해주세요.")
    private String writer;
}
