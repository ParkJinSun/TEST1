package CRUD.TEST1.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken extends Time{

    @Id
    @Column(nullable = false)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    Member member;

    @Column(nullable = false)
    String token;

    public void updateToken(String token){
        this.token = token;
    }

    @Builder
    public RefreshToken(Long id, Member member, String token) {
        this.id = id;
        this.member = member;
        this.token = token;
    }
}
