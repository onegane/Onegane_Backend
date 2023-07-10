package onegane.onegane.domain.history.domain;

import lombok.Getter;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.global.Entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
public class History extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    private User user;
}
