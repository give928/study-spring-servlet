package com.give928.spring.servlet.basic.request;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
@Slf4j
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    //start line 정보
    private void printStartLine(HttpServletRequest request) {
        log.info("--- REQUEST-LINE - start ---");
        log.info("request.getMethod() = " + request.getMethod()); // GET
        log.info("request.getProtocal() = " + request.getProtocol()); // HTTP/1.1
        log.info("request.getScheme() = " + request.getScheme()); // http // http://localhost:8080/request-header
        log.info("request.getRequestURL() = " + request.getRequestURL()); // /request-test
        log.info("request.getRequestURI() = " + request.getRequestURI()); //username=hi
        log.info("request.getQueryString() = " + request.getQueryString());
        log.info("request.isSecure() = " + request.isSecure()); //https 사용 유무
        log.info("--- REQUEST-LINE - end ---");
        log.info("");
    }

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        log.info("--- Headers - start ---");
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> log.info(headerName + ":" + request.getHeader(headerName)));
        log.info("--- Headers - end ---");
        log.info("");
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        log.info("--- Header 편의 조회 start ---");
        log.info("[Host 편의 조회]");
        log.info("request.getServerName() = " + request.getServerName()); //Host 헤더
        log.info("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        log.info("");
        log.info("[Accept-Language 편의 조회]");
        request.getLocales().asIterator().forEachRemaining(locale -> log.info("locale = " + locale));
        log.info("request.getLocale() = " + request.getLocale());
        log.info("");
        log.info("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                log.info(cookie.getName() + ": " + cookie.getValue());
            }
        }
        log.info("");
        log.info("[Content 편의 조회]");
        log.info("request.getContentType() = " + request.getContentType());
        log.info("request.getContentLength() = " + request.getContentLength());
        log.info("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        log.info("--- Header 편의 조회 end ---");
        log.info("");
    }

    //기타 정보
    private void printEtc(HttpServletRequest request) {
        log.info("--- 기타 조회 start ---");
        log.info("[Remote 정보]");
        log.info("request.getRemoteHost() = " + request.getRemoteHost());
        log.info("request.getRemoteAddr() = " + request.getRemoteAddr()); //
        log.info("request.getRemotePort() = " + request.getRemotePort()); //
        log.info("");
        log.info("[Local 정보]");
        log.info("request.getLocalName() = " + request.getLocalName()); //
        log.info("request.getLocalAddr() = " + request.getLocalAddr()); //
        log.info("request.getLocalPort() = " + request.getLocalPort()); //
        log.info("--- 기타 조회 end ---");
        log.info("");
    }
}
