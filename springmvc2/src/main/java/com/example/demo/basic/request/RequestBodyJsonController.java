package com.example.demo.basic.request;

import com.example.demo.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper(); //Jacson

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    }

    @ResponseBody // return을 문자열로 보내겠다.
    @PostMapping("/request-body-json-v2") //requestbody는 들어오는 것 중 메시지 바디에서 읽겠다.
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }
    /**
     * 메시지 컨버터가 아래의 기능을 자동으로 수행
     * HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
     * .@RequestBody 생략하면 안됨 => 컨버팅하지 못해 name=null primeType인 int는 0이 들어옴..
     * ==>생략했다는 뜻은 @ModelAttribute가 되어버림
     * 만약 단순한 String이나 int 이면 @RequestParam..이건 int로 아무것도 안들어오면 null되지만 modelattribute는 좀 더 관대
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3") //modelAttribute처럼 바로 객체에
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * HttpEntity
     */
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV3(HttpEntity<HelloData> body) throws IOException {
        HelloData helloData = body.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     *
     * @param helloData
     * @return HelloData
     * //메시지컨버터가 나갈 때도 적용되어 객체를 return 가능
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }
}
