package com.practice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ValidForm {
//    @NotNull

    @NotBlank
    private String text;
    // html에서 값이 넘어올 때 태그에 name 속성이 없으면,
    // 공백 입력시 null이 아닌 공백으로 값이 들어감
    // @NotNull로 걸러낼 수 없음
    // 따라서, @NotBlank 사용

    @NotNull
    @Range(min = 0, max = 100)
    private Integer number;

    @Past
    private LocalDateTime dateTime;

}






