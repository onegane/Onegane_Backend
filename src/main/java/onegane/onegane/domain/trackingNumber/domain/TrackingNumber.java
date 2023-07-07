package onegane.onegane.domain.trackingNumber.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.global.Entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class TrackingNumber extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long trackingNumber;

    @ManyToOne
    private User user;
}
