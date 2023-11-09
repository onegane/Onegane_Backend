package onegane.onegane.domain.history.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CaseSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private State state;

    @Column(nullable = false)
    private Integer x;

    @Column(nullable = false)
    private Integer z;

    @Builder
    public CaseSize(State state, Integer x, Integer z) {
        this.state = state;
        this.x = x;
        this.z = z;
    }

    public CaseSize update(ParcelSize parcelSize) {
        this.x -= parcelSize.getX();
        this.z -= parcelSize.getZ();
        return this;
    }
}