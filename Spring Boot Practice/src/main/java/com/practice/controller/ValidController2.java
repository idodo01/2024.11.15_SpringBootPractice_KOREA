package com.practice.controller;

import com.practice.dto.ValidForm;
import com.practice.dto.ValidSuperForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/valid")
public class ValidController2 {
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
            @ModelAttribute("superForm") ValidSuperForm validSuperForm,
            BindingResult bindingResult
    ) {
        //// ,,.,,,,,,
        /// 에러를 커스텀으로 추가할 수 있고 추가하는 순간 오류라고 판단함
        // ObjectError: 어떤 Form객체와 상관없이 전역으로 사용하고 싶은 에러
        bindingResult.addError(new ObjectError("myGlobal", "글로벌 에러 났어요!"));
        // FieldError: 어떤 Form객체에 사용하고 싶은 에러
        // objectName: 오류가 발생한 객체의 이름
        // field: 오류 필드(객체안의 프로퍼티명) ***
        // rejectedValue: 사용자가 입력한 값
        // bindingFailure: 타임오류 같은 바인딩 실패인지(true), 검증 실패인지 구분 값(false)
        // codes: 메세지 코드(messages.properties에 작성되는 값 불러오는 용도)
        // arguments: 작성한 오류 메세지에 {} 에 넣는 인자 값들
        // defaultMessage: messages.properties에 오류 메세지가 없을 때 나오는 값
        bindingResult.addError(new FieldError("superForm" , "id", "id거절되었음!", false, null, null, "아이디가 틀림ㅡㅡ"));
        bindingResult.addError(new FieldError(
                "superForm",
                "age",
                "age거절되었음!",
                false,
                null,
                new Object[]{"당신", 15},
                "{0}의 {1}에 대한 나이가 이상함")
        );

//        bindingResult.reject("message", "메세지 에러");
        // field: 오류 필드명, errorCode: 오류 코드,
        // errorArgs: 오류 메세지에서 {}부분을 치환할 값들
        // defaultMessage: 오류 메세지를 찾을 수 없을때 사용하는 기본 메세지
        bindingResult.rejectValue("addr", "addr", new Object[]{"431-1111", "대구 중구"}, "{0} / {1} 주소가 잘못됨");
        bindingResult.rejectValue("phone", "p", new Object[]{"010", "1234", "5678"}, "DEFAULT");
        bindingResult.rejectValue("email", "e", new Object[]{"DEPRECATED"}, "DEFAULT");


        bindingResult.getAllErrors().forEach(System.out::println);

        return "valid/main";
    }

    /******************************************************************************/
    @GetMapping("/main2")
    public void get_valid_main2(
            @ModelAttribute("superForm") ValidSuperForm validSuperForm
    ) {
        System.out.println("get_valid_main2 - " + validSuperForm);
    }

    @PostMapping("/main2")
    public void post_valid_main2(
            @ModelAttribute("superForm") @Validated ValidSuperForm validSuperForm,
            BindingResult bindingResult
    ){

        // Bean Validation 메세지를 찾는 순서
        // 1) 생성된 메세지 코드 순서대로 messageSource 에서 찾기 (messages.properties)
        // 2) 애너테이션에 걸어준 message 속성 => @NotBlank(message= ???) (dto > ValidSuperForm)
        // 3) 라이브러리가 제공하는 기본 값 사용 => "공백일 수 없습니다"

        ///// Global ERROR를 설정하고 싶으면...
//        bindingResult.reject("addr", new Object[]{"444-1111", "대구 동구"}, "DEFAULT ADDR MESSAGE");
        ///// Field ERROR를 설정하고 싶으면...
//        bindingResult.rejectValue();
    }














}
