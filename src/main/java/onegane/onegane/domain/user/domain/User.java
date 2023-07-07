package onegane.onegane.domain.user.domain;

import leehj050211.bsmOauth.dto.resource.BsmUserResource;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer entranceYear;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long studentNumber, String name, Integer entranceYear, String email, Role role) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.entranceYear = entranceYear;
        this.email = email;
        this.role = role;
    }

    public User update(BsmUserResource resource) {
        this.name = resource.getNickname();
        return this;
    }
}
