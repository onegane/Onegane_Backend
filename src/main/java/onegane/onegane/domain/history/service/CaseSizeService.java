package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.domain.CaseSize;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.domain.ParcelSize;
import onegane.onegane.domain.history.domain.State;
import onegane.onegane.domain.history.repository.CaseSizeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaseSizeService {

    private final CaseSizeRepository caseSizeRepository;

    public Long updateOrSave(ParcelSize parcelSize, History history) {
        CaseSize caseSize = caseSizeRepository.findByState(history.getState())
                .orElse(null);

        if (caseSize == null) {
            return caseSizeRepository.save(
                    CaseSize.builder()
                            .x(120 - parcelSize.getX())
                            .z(120 - parcelSize.getZ())
                            .state(history.getState())
                            .build()
            ).getId();
        }

        return caseSizeRepository.save(caseSize.update(parcelSize)).getId();
    }

    public CaseSize get(State state) {
        return caseSizeRepository.findByState(state)
                .orElse(null);
    }
}
