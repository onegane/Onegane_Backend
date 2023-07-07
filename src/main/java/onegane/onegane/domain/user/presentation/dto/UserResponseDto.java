package onegane.onegane.domain.user.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private Integer grade;
    private Integer classNo;
    private String name;
    private String email;

    @Builder
    public UserResponseDto(Integer grade, Integer classNo, String name, String email) {
        this.grade = grade;
        this.classNo = classNo;
        this.name = name;
        this.email = email;
    }
}
