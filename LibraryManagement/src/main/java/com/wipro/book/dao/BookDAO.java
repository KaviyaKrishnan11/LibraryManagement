package com.wipro.book.dao;

import java.sql.*;
import com.wipro.book.bean.BookBean;
import com.wipro.book.util.DBUtil;

public class BookDAO {

    public int createBook(BookBean bookBean) {

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("INSERT INTO BOOK_TBL VALUES (?,?,?,?,?)");

            ps.setString(1, bookBean.getIsbn());
            ps.setString(2, bookBean.getBookName());
            ps.setString(3, String.valueOf(bookBean.getBookType()));
            ps.setInt(4, bookBean.getAuthor().getAuthorCode());
            ps.setFloat(5, bookBean.getCost());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public BookBean fetchBook(String isbn) {

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM BOOK_TBL WHERE ISBN=?");
            ps.setString(1, isbn);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BookBean book = new BookBean();
                book.setIsbn(rs.getString("ISBN"));
                book.setBookName(rs.getString("BOOK_NAME"));
                book.setBookType(rs.getString("BOOK_TYPE").charAt(0));
                book.setAuthor(new AuthorDAO().getAuthor(rs.getInt("AUTHOR_CODE")));
                book.setCost(rs.getFloat("BOOK_COST"));
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
