package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handelr) {
        return (handelr instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);
        //return viewName; return안맞음
        //어댑터가 형식을 맞춰줌
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
        //기존은 각각 프론트컨트롤러'서블릿' - 인터페이스컨트롤러를extends한 콘트롤러... => 서블릿에서 이 컨트롤러를 갖다가 썼지
        //어댑터는 프론트컨트롤러'서블릿' - 어댑터등록(이게 위에서의 프론트컨트롤러서블릿처럼작동) -> extends한 컨트롤러 갖다 씀
        //1.프론트컨트롤러에서 extends컨트롤러찾음->어댑터찾음..오 전수조사니까return boolean을 하는구나->각extends컨트롤러인자에맞게어댑트+어댑터에서동작(프론트컨트롤러타입에맞게바꿔서)->프론트컨트롤러로
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
