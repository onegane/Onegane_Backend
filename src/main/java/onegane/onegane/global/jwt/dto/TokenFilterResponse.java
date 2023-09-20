package onegane.onegane.global.jwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenFilterResponse {

    private Boolean isValid;
    private String message;

    @Builder
    public TokenFilterResponse(Boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }
}
