package com.wipro.book.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    Administrator administrator = new Administrator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if ("AddBook".equals(operation)) {

            String result = addBook(request);

            if ("SUCCESS".equals(result))
                response.sendRedirect("SUCCESS.html");
            else if ("INVALID".equals(result))
                response.sendRedirect("INVALID.html");
            else
                response.sendRedirect("FAILURE.html");

        } else if ("Search".equals(operation)) {

            String isbn = request.getParameter("isbn");
            BookBean book = administrator.viewBook(isbn);

            if (book == null) {
                response.sendRedirect("INVALID.html");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("book", book);
                RequestDispatcher rd =
                        request.getRequestDispatcher("ViewServlet");
                rd.forward(request, response);
            }
        }
    }

    private String addBook(HttpServletRequest request) {

        try {
            BookBean book = new BookBean();
            book.setIsbn(request.getParameter("isbn"));
            book.setBookName(request.getParameter("bookName"));
            book.setBookType(request.getParameter("bookType").charAt(0));
            book.setCost(Float.parseFloat(request.getParameter("cost")));

            int authorCode =
                Integer.parseInt(request.getParameter("authorCode"));
            book.setAuthor(new AuthorDAO().getAuthor(authorCode));

            return administrator.addBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            return "FAILURE";
        }
    }
}
