package onegane.onegane.domain.user.domain;

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
    private Integer studentNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer entranceYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Integer studentNumber, String name, Integer entranceYear, Role role) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.entranceYear = entranceYear;
        this.role = role;
    }
}
