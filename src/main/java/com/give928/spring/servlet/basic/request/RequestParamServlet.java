package com.give928.spring.servlet.basic.request;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 * <p>
 * 2. 동일한 파라미터 전송 가능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
@Slf4j
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        log.info("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator().forEachRemaining(paramName -> log.info(paramName + "=" + request.getParameter(paramName)));
        log.info("[전체 파라미터 조회] - end");
        log.info("");

        log.info("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        log.info("request.getParameter(username) = " + username);
        String age = request.getParameter("age");
        log.info("request.getParameter(age) = " + age);
        log.info("");

        log.info("[이름이 같은 복수 파라미터 조회]");
        log.info("request.getParameterValues(username)");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            log.info("username=" + name);
        }
        resp.getWriter().write("ok");
    }
}
