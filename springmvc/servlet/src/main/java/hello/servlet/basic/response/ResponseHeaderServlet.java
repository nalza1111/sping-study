package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setStatus(200); 과 같은 거지만 매직코드... 아래와 같이 적는 게 좋음
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK);
        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        //[response-headers]
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        content(response); //편의메소드로
        
        //캐시 무효화
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragme", "no-cache");

        //내 헤더
        response.setHeader("my-header", "hello");

        PrintWriter writer = response.getWriter();
        writer.print("ok");
        //writer.println("ok");  => 이건 ln이라 세글자
    
        //쿠키 편의메소드
        cookie(response);
        //리다이렉트 편의메소드
        redirect(response);
    }

    //헤더 간편메소드
    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength("3"); => 이건 생략시 자동 생성.. print
    }

    //쿠키 간편메소드
    private void cookie(HttpServletResponse response) {
        //response.setHeader("set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    //리다이렉트 간편메소드
    private void redirect(HttpServletResponse response) throws IOException {
        //status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND);
        //response.setHeader("Location", "/basic/hello-form.html");

        /* 아래가 편함! */
        response.sendRedirect("/basic/hello-form.html");
    }
}
