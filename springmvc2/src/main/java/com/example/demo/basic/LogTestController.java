package com.example.demo.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller => view를 리턴..디스패쳐서블릿이 뷰리졸버로..뷰가 랜더링
//@RestController => 반환값이 http메시지 바디에 바로 입력
@Slf4j //롬복이 제공
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    //private static final Logger log2 = LoggerFactory.getLogger(XXX.class)

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        //이렇게 쓰면 안됨 //=>문자 더하기가 되면서 쓸데없는 리소스가 사용됨..계산할거 하고 파라미터 넘김..
        //but trace는 이걸 출력안해서 쓸 모 없는 연산만 함
        log.trace(" trace log="+ name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name); // 디버그 개발서버
        log.info("  info log={}", name);   //중요한 비즈니스 정보..혹은 운영
        log.warn("  warn log={}", name);  // 경고 위험
        log.error("error log={}", name); //에러
        //application.properties에서 출력레벨 설정(기본은 info라 설정안하면 info)
        //운영서버에서는 info 레벨정도로 찍음
        //로컬은 trace 개발서버는 debug ...여기는 개발자취향인 듯

        return "ok";
    }
}
