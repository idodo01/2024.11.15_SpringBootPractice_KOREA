package com.practice.controller;

import org.springframework.ui.Model;
import com.practice.dto.ValidForm;
import com.practice.dto.ValidSuperForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/valid")
public class ValidController {

    @GetMapping("/error")
    public void get_error() {}

    @GetMapping("/main")
    public void get_main(Model model) {
        ValidSuperForm validSuperForm = new ValidSuperForm();
        ValidForm validForm = new ValidForm();
        validSuperForm.setValidForm(validForm);
        model.addAttribute("superForm", validSuperForm);
    }

    @PostMapping("/main")
    public String post_main(
//            @Validated ValidForm validForm,
            @Validated ValidSuperForm validSuperForm,
            BindingResult bindingResult, // 필수 구성요소, 없으면 바로 오류터짐
            Model model

    ) {
        model.addAttribute("superForm", validSuperForm);
        if(bindingResult.hasErrors()) {
            System.out.println("에러가 있어요.");

//            bindingResult.getAllErrors().forEach(
//                    error -> System.out.println(error)
//            );

            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            return "redirect:/valid/error";
        }
//        System.out.println(validForm);
        System.out.println(validSuperForm);
        return "redirect:/valid/main";
    }


}
