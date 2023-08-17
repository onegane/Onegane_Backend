package onegane.onegane.domain.user.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import onegane.onegane.domain.user.domain.User;

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

    public UserResponseDto(User user) {
        this.grade = user.getGrade();
        this.classNo = user.getClassNo();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
