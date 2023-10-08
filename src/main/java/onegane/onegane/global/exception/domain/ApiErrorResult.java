package onegane.onegane.global.exception.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class ApiErrorResult {

    private Integer status;
    private String summary;
    private String message;
}
