package com.practice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ValidSuperForm {
    @NotBlank
    private String id;
    @Valid
    private ValidForm validForm;
}
