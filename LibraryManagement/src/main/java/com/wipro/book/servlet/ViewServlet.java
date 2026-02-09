package com.wipro.book.servlet;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.wipro.book.bean.BookBean;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        BookBean book =
            (BookBean) request.getSession().getAttribute("book");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("Book Name : " + book.getBookName() + "<br>");
        out.println("Author : " + book.getAuthor().getAuthorName() + "<br>");
        out.println("Contact : " + book.getAuthor().getContactNo() + "<br>");
        out.println("Type : " + book.getBookType() + "<br>");
        out.println("Cost : " + book.getCost() + "<br>");
        out.println("ISBN : " + book.getIsbn());
        out.println("</body></html>");
    }
}
