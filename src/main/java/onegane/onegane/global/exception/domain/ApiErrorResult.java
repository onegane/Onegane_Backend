package onegane.onegane.global.exception.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ApiErrorResult {

    private String status;
    private String message;

    @Builder
    public ApiErrorResult(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
