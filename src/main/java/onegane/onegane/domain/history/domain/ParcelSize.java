package onegane.onegane.domain.history.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ParcelSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "history_id", unique = true)
    private History history;

    @Column(nullable = false)
    private Integer x;

    @Column(nullable = false)
    private Integer z;

    @Builder
    public ParcelSize(History history, Integer x, Integer z) {
        this.history = history;
        this.x = x;
        this.z = z;
    }
}
