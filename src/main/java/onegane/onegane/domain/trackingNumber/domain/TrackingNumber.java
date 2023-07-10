package onegane.onegane.domain.trackingNumber.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.global.Entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class TrackingNumber extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long trackingNumber;

    @Column(nullable = false)
    private String nickname;

    @ManyToOne
    private User user;

    @Builder
    public TrackingNumber(Long trackingNumber, String nickname, User user) {
        this.trackingNumber = trackingNumber;
        this.nickname = nickname;
        this.user = user;
    }
}
