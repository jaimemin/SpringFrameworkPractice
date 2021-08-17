package com.tistory.jaimemin.servlet.web.frontcontroller.v3;

import com.tistory.jaimemin.servlet.web.frontcontroller.ModelView;
import com.tistory.jaimemin.servlet.web.frontcontroller.MyView;
import com.tistory.jaimemin.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.tistory.jaimemin.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.tistory.jaimemin.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI();
        ControllerV3 controllerV3 = controllerMap.get(requestURI);

        if (ObjectUtils.isEmpty(controllerV3)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return;
        }

        // paramMap
        Map<String, String> paramMap = getParamMap(request);
        ModelView modelView = controllerV3.process(paramMap);

        String viewName = modelView.getViewName(); // 논리 이름만
        MyView view = getView(viewName);

        view.render(modelView.getModel(), request, response);
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
