package com.practice.rest.controller;

import com.practice.rest.dto.ComputerDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/get/{serial}")
    public ResponseEntity<ComputerDTO> getComputer(@PathVariable("serial") String serial) {
        ComputerDTO existComputer = computers.get(serial);
        if(Objects.isNull(existComputer)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(existComputer);
    }

//    @PostMapping("/post")
//    public void post(ComputerDTO computerDTO){
//        computers.put(computerDTO.getSerial(), computerDTO);
//    }

//    @PostMapping("/post")
//    public void post(@RequestBody String s){
//        System.out.println(s);
////        computers.put(computerDTO.getSerial(), computerDTO);
//    }

    @PostMapping("/post")
    public void post(@RequestBody ComputerDTO computerDTO){
        // 전달받은 데이터가 이미 생성이 되어 있는 데이터인가?
        if(!computers.containsKey(computerDTO.getSerial())){
            // 생성이 되어 있지 않으면 생성한다 (생성되어있으면 중복!)
            computers.put(computerDTO.getSerial(), computerDTO);
        }
    }

    @PutMapping("/put")
    public void put(@RequestBody ComputerDTO computerDTO){
        // 전달받은 데이터가 이미 생성이 되어 있는 데이터인가?
        if(computers.containsKey(computerDTO.getSerial())){
            // 덮어쓴다 (전체를 교체한다)
            computers.put(computerDTO.getSerial(), computerDTO);
        }
    }

    @PatchMapping("/patch")
    public void patch(@RequestBody ComputerDTO computerDTO){
        // 전달받은 데이터가 이미 생성이 되어 있는 데이터인가?
        if(computers.containsKey(computerDTO.getSerial())){
            // 기존 데이터를 불러온다 (해당 시리얼 번호를 가진 데이터)
            ComputerDTO existComputer = computers.get(computerDTO.getSerial());
            // Name이 존재한다!
            if(Objects.nonNull(computerDTO.getName())){
                // 기존 데이터의 일부를 받은 데이터로 교체한다
                existComputer.setName(computerDTO.getName());
            }
        }
    }

//    @DeleteMapping("/delete")
//    public void delete(@RequestBody String serial){
//        // computers에서 해당 serial Number를 찾아서 제거한다
//        // 없어도 그냥 지나가기때문에 괜찮다
//        computers.remove(serial);
//    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody String serial){
        // computers에서 해당 serial Number를 찾아서 제거한다
        // 없어도 그냥 지나가기때문에 괜찮다
        if(computers.containsKey(serial)){
            computers.remove(serial);
            return ResponseEntity.ok().build(); // 200
        }
        return ResponseEntity.notFound().build(); // 404
    }
}
