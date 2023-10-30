package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //private Map<String, ControllerV4> controllerV4Map = new HashMap<>(); final은 지금 그닥 중요x
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //핸들러찾기
        Object handelr = getHandelr(request);

        if(handelr == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        //핸들러어댑터찾기
        MyHandlerAdapter adapter = getHandelrAdapter(handelr);

        ModelView mv = adapter.handle(request, response, handelr);

        String viewName = mv.getViewName();// 논리이름 new-form
        MyView view = veiwResolver(viewName);

        view.render(mv.getModel(), request, response);

    }

    private MyHandlerAdapter getHandelrAdapter(Object handelr) {
        //handlerAdapters.iter + enter

        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handelr)) {
               return adapter;
            }
        }
        //return null일 수도 있으니까.. 예외를 반환하겠음
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. hander = " + handelr);
    }

    private Object getHandelr(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private static MyView veiwResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
