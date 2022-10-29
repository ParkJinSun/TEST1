package CRUD.TEST1.domain;

import CRUD.TEST1.dto.requestDto.BoardRequestDto;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Board extends Time{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String content;
    @Column(nullable = false)
    String writer;

    @Builder
    public Board(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
    }

}
