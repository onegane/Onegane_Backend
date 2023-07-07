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
    private Integer grade;

    @Column(nullable = false)
    private Integer classNo;

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
    public User(Integer grade, Integer classNo, String name, Integer entranceYear, String email, Role role) {
        this.grade = grade;
        this.classNo = classNo;
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
