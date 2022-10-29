package CRUD.TEST1.dto.requestDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequestDto {
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\d]*${3,12}")
    private String memberName;
    @NotBlank
    @Pattern(regexp = "[a-z\\d]*${3,12}")
    private String password;

    private String passwordCheck;
}
