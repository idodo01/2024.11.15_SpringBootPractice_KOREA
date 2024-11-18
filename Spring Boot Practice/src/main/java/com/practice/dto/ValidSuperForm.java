package com.practice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@ToString
public class ValidSuperForm {
    @NotBlank(message = "(2) 슈퍼폼) (아이디) id는 비울 수 없습니다")
    private String id;
    @NotNull(message = "(2) 슈퍼폼) (나이) {0}은 비울 수 없습니다")
//    @Min(value = 0, message = "0이상의 값을 사용하세요")
    @Range(min = 0, max = 200, message = "(2) 슈퍼폼) (나이) {0}는 {2} ~ {1} 값만 넣으세요")
    private Integer age;

    @NotBlank
    private String addr;

    private String phone;
    private String email;
    //    @Valid
    private ValidForm validForm;
}
