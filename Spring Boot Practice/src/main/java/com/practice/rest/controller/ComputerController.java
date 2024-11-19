package com.practice.rest.controller;

import com.practice.rest.dto.ComputerDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/rest/computer")
public class ComputerController {
    Map<String, ComputerDTO> computers = new HashMap<>();

    @PostConstruct
    public void init() {
        computers.put("C-1",
                ComputerDTO.builder()
                        .serial("C-1")
                        .name("슈퍼컴")
                        .price(5000000)
                        .release(LocalDate.now())
                        .build()
        );
        computers.put("C-2",
                ComputerDTO.builder()
                        .serial("C-2")
                        .name("테스트컴")
                        .price(100000)
                        .release(LocalDate.of(2022,11,20))
                        .build()
        );
        computers.put("C-3",
                ComputerDTO.builder()
                        .serial("C-3")
                        .name("대충컴")
                        .price(300000)
                        .release(LocalDate.of(2024, 10, 11))
                        .build()
        );
        computers.put("C-4",
                ComputerDTO.builder()
                        .serial("C-4")
                        .name("조선컴")
                        .price(10000)
                        .release(LocalDate.of(1999,1,1))
                        .build()
        );
    }

    @GetMapping("/get")
    public List<ComputerDTO> getComputers() {
        List<ComputerDTO> computerDTOs = new ArrayList<>();
        Set<String> keySet = computers.keySet();
        for (String key : keySet) {
            ComputerDTO computerDTO = computers.get(key);
            computerDTOs.add(computerDTO);
        }
        return computerDTOs;
    }


//    @PostMapping("/post")
//    public void post(ComputerDTO computerDTO){
//        computers.put(computerDTO.getSerial(), computerDTO);
//    }

    @PostMapping("/post")
    public void post(@RequestBody String s){
        System.out.println(s);
//        computers.put(computerDTO.getSerial(), computerDTO);
    }
}
