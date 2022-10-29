package CRUD.TEST1.dto.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Getter
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank
    String memberName;
    @NotBlank
    String password;
}
