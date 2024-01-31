package com.example.demo.basic.request;

import com.example.demo.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    //@RestController = @Controller + @ResponseBody
    @ResponseBody // http메시지 바디에 넣어서 반환 Restcontroller랑 같음
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String membername,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", membername, memberAge);
        return "ok";
    }

    //1차 생략
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }
    //2차 생략
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = false) String username, //이건 빈문자도 빈문자로 들어옴
            @RequestParam(required = true) Integer age) { // age param이 안들어오면 오류남

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamRequired(
            @RequestParam(required = false, defaultValue = "guest") String username, //default의 경우 빈문자에도 적용됨
            @RequestParam(required = true, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-Map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }//키에 밸류가 여러개면 MultiValueMap 사용


    /**
     *
     * @param name
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String name, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setAge(age);
        helloData.setUsername(name);
        log.info("age = {}, username = {}", age, name);
        log.info("helloData = {}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1-2")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("age = {}, username = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "ok";
    }

    /**
     * 생략
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("age = {}, username = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "ok";
    }
}
