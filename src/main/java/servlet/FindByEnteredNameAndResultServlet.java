package servlet;

import database.ApplicantDAO;
import model.Applicant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "FindByEnteredNameAndResultServlet", urlPatterns = "/findByNameAndResult")
public class FindByEnteredNameAndResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("nameOfExam") != "" && req.getParameter("result") != "") {
            String nameOfExam = req.getParameter("nameOfExam");
            int result = Integer.parseInt(req.getParameter("result"));
            try {
                List<Applicant> list = ApplicantDAO.findApplicantsPassedExamGraterThanGivenMarkFilter(nameOfExam, result);
                req.setAttribute("list", list);
            } catch (SQLException | ClassNotFoundException e) {
                req.setAttribute("errorCode", e.toString());
            }
        }
        req.getServletContext().getRequestDispatcher("/findByEnteredNameAndResult.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}