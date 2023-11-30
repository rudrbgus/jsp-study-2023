package com.jsp.chap04;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dancer/delete")
public class DeleteServlet extends HttpServlet {

    // 저장소와 위임관게처리를 위해 의존관계를 설정
    private DancerRepository dancerRepository = new DancerRepository();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        dancerRepository.delete(name);
        System.out.println("삭제요청 들어옴 - " + name);

        // 목록으로 포워딩 - 데이터베이스 조히데이터를 들고 포워딩
        List<Dancer> all = dancerRepository.findAll();
        req.setAttribute("dl", all);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/chap04/dancer/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
