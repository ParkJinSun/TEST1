package CRUD.TEST1.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String memberName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
