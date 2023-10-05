package onegane.onegane.global.exception.domain;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class ApiErrorResult {

    private Integer status;
    private String summary;
    private String message;
}
