package com.wipro.book.dao;

import java.sql.*;
import com.wipro.book.bean.AuthorBean;
import com.wipro.book.util.DBUtil;

public class AuthorDAO {

    public AuthorBean getAuthor(int authorCode) {
        AuthorBean author = null;

        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM AUTHOR_TBL WHERE AUTHOR_CODE=?");
            ps.setInt(1, authorCode);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                author = new AuthorBean();
                author.setAuthorCode(rs.getInt("AUTHOR_CODE"));
                author.setAuthorName(rs.getString("AUTHOR_NAME"));
                author.setContactNo(rs.getLong("CONTACT_NO"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }
}
