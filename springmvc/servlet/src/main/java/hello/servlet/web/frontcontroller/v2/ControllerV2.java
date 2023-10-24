package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    
    //기존엔 컨트롤러가 알아서 포워드했지만 뷰를 반환
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
}
