package onegane.onegane.domain.history.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.global.Entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class History extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long trackingNumber;

    @Column(nullable = false)
    private String parcelNickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    private User user;

    @Builder
    public History(Long trackingNumber, String parcelNickname, State state, User user) {
        this.trackingNumber = trackingNumber;
        this.parcelNickname = parcelNickname;
        this.state = state;
        this.user = user;
    }

    public History update(Long trackingNumber, String parcelNickname) {
        this.trackingNumber = trackingNumber;
        this.parcelNickname = parcelNickname;
        return this;
    }
}
