package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFromServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFromServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // 컨트롤로에서 뷰로 이동...서블릿에서 다른 서블릿이나 jsp 재호출 가능
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);


        // => 오 이거 경로 http://localhost:8080/servlet-mvc/members/new-form 뜸! .jsp 안뜸~
        //WEB-INF => 경로 직접쳐서 진입 불가능... 항상 컨트롤러를 타야 부를 수 있음

    }
}
