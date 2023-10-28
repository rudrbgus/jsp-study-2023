package com.jsp.chap01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

// 역할 : HTTP 요청 응답 처리에서 공통적인 부분을 쉽게 해결해주는 자바 API
@WebServlet("/hello") // 우리 웹서버에 /hello라는 url로 요청이 오면 이 서블릿을 실행시켜라
public class HelloServlet extends HttpServlet {

    public HelloServlet(){ //기본 생성자
        System.out.println("\n\n\n헬로 서블릿 작동 시작\n\n\n");
    }

    // 파싱된 요청정보 얻는 방법

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 클라이언트의 요청방식
        String method = req.getMethod();

        // 요청 URL
        String requestURI = req.getRequestURI();

        // 요청 헤더 읽기
        String header = req.getHeader("Cache-Control");

        System.out.println("method = " + method);
        System.out.println("requestURI = " + requestURI);
        System.out.println("header = " + header);


        //쿼리 파라미터 읽기 (? 뒤에를 쿼리 파리미터라고 함)
        String keyword = req.getParameter("keyword");
        System.out.println("keyword = " + keyword);
        String age = req.getParameter("age");
        System.out.println("age = " + age);


        // 응답 메세지에 HTMl문서 생성해서 응답하기
        // keyword님은 xxxx년생 입니다
        // 비지니스 로직 작성
        // 출생년도 구하기 : 2023 - age
        int year = LocalDate.now().getYear();
        int birthYear = year - Integer.parseInt(age)+1;

        // HTML 생성
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        // HTML을 작성할 펜같은거 생성
        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>\n");
        writer.write("<html>\n");
        writer.write("<head>\n");
        writer.write("</head>\n");
        writer.write("<body>\n");
        writer.write("<h1>\n");
        writer.write(String.format("%s님은 %d년생입니다.\n", keyword, birthYear));
        writer.write("</h1>\n");
        writer.write("</body>\n");
        writer.write("</html>\n");
        writer.flush();
        writer.close();


    }
}
