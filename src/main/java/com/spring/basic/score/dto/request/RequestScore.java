package com.spring.basic.score.dto.request;

import com.spring.basic.score.entity.Score;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode @ToString
public class RequestScore {

    @NotBlank(message = "이름을 입력해주세요.")
    private String studentName;

    @Min(value = 0, message = "0 이상 입력해주세요")
    @Max(value = 100, message = "100 이하 입력해주세요")
    private int korean;  // 소문자로 변경

    @Min(value = 0, message = "0 이상 입력해주세요")
    @Max(value = 100, message = "100 이하 입력해주세요")
    private int english;  // 소문자로 변경

    @Min(value = 0, message = "0 이상 입력해주세요")
    @Max(value = 100, message = "100 이하 입력해주세요")
    private int math;  // 소문자로 변경

    // DTO -> Entity
    public Score toEntity() {
        return Score.builder()
                .name(studentName)
                .kor(korean)
                .eng(english)
                .math(math)
                .build();
    }
}
