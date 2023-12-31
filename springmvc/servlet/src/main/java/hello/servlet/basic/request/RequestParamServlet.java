package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파리미터 전송 기능
 *  http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();
        System.out.println("[단일 파라미터 조회]");
        String userName = request.getParameter("userName");
        System.out.println("userName = " + userName);
        String age = request.getParameter("age");
        System.out.println("age = " + age);

        //userName 파라미터가 두개인경우 위와 같은 파라미터 조회시 처음 것만 나옴
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        //파라미터이름이 같은 게 둘이상일 시 getParameterValues 쓰기~
        //파라미터 이름은 되도록 중복을 피하자. 최소 인지하고 사용~
        String[] userNames = request.getParameterValues("userName");
        //iter + enter

        /* 밑에 post 전송 시 에러 남 */
        //for (String name : userNames) {
        //    System.out.println("name = " + name);
        //}

        response.getWriter().write("ok");
    }
}
