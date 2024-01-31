package com.example.demo.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletResponse response,
                          HttpServletRequest request,
                          HttpMethod httpMethod,
                          Locale locale,//언어 => 우선순위 언어.. locale resolver가 있음
                          @RequestHeader MultiValueMap<String, String> headerMap, //키 - 밸류로 들어옴
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie
        ) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";

    }
}
