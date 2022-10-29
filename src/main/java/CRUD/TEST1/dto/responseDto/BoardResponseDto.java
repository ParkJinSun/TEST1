package CRUD.TEST1.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Getter
public class BoardResponseDto {

    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public BoardResponseDto(String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
