package com.tistory.jaimemin.servlet.web.frontcontroller.v5;

import com.tistory.jaimemin.servlet.web.frontcontroller.ModelView;
import com.tistory.jaimemin.servlet.web.frontcontroller.MyView;
import com.tistory.jaimemin.servlet.web.frontcontroller.v3.ControllerV3;
import com.tistory.jaimemin.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.tistory.jaimemin.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.tistory.jaimemin.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.tistory.jaimemin.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.tistory.jaimemin.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.tistory.jaimemin.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.tistory.jaimemin.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.tistory.jaimemin.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import org.springframework.util.ObjectUtils;

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

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();

    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontControllerServletV5.service");

        Object handler = getHandler(request);

        if (ObjectUtils.isEmpty(handler)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView modelView = adapter.handle(request, response, handler);

        String viewName = modelView.getViewName(); // 논리 이름만
        MyView view = getView(viewName);

        view.render(modelView.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                return handlerAdapter;
            }
        }

        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        return handlerMappingMap.get(requestURI);
    }

    private Map<String, String> getParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName ->
                        paramMap.put(paramName, request.getParameter(paramName))
                );

        return paramMap;
    }

    private MyView getView(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
