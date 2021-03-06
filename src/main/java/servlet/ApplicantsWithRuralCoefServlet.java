package servlet;

import database.ApplicantDAO;
import model.Applicant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "ApplicantsWithRuralCoefServlet", urlPatterns = "/ruralCoef")
public class ApplicantsWithRuralCoefServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Applicant> list = ApplicantDAO.findApplicantWithRuralCoef();
            req.setAttribute("list", list);
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("errorCode", e.toString());
        }
        req.getServletContext().getRequestDispatcher("/ruralCoefApplicants.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}