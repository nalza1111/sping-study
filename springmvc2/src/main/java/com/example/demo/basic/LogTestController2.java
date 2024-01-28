package com.example.demo.basic;

import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController2 {

    //private final Logger log = LoggerFactory.getLogger(getClass());
    // 롬복은 위의 줄을 쓰지 않고 바로 log로 쓸 수 있음
    @RequestMapping("/log-test2")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);
        
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); // 디버그 개발서버
        log.info("info log={}", name);   //중요한 비즈니스 정보..혹은 운영
        log.warn("warn log={}", name);  // 경고 위험
        log.error("error log={}", name); //에러
        //application.properties에서 출력레벨 설정(기본은 info라 설정안하면 info)
        //운영서버에서는 info 레벨정도로 찍음
        //로컬은 trace 개발서버는 debug ...여기는 개발자취향인 듯

        return "ok";
    }
}
