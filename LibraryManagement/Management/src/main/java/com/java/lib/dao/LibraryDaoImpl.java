package com.java.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.lib.model.Books;
import com.java.lib.model.LibUsers;
import com.java.lib.util.ConnectionHelper;
import com.java.lib.util.EncryptPassword;

public class LibraryDaoImpl implements LibraryDao {

    Connection connection;
    PreparedStatement psmt;

    @Override
    public String createUser(LibUsers libUsers) throws ClassNotFoundException, SQLException {
        String encr = EncryptPassword.getCode(libUsers.getPassWord());
        connection = ConnectionHelper.getConnection();
        String cmd = "Insert into LibUsers(UserName,Password) values(?,?)";
        psmt = connection.prepareStatement(cmd);
        psmt.setString(1, libUsers.getUserName());
        psmt.setString(2, encr);
        psmt.executeUpdate();
        return "User Account Created Successfully...";
    }

    @Override
    public int login(LibUsers libUsers) throws ClassNotFoundException, SQLException {
        String encr = EncryptPassword.getCode(libUsers.getPassWord());
        connection = ConnectionHelper.getConnection();
        String cmd = "select count(*) cnt from LibUsers where UserName = ? AND Password = ?";
        psmt = connection.prepareStatement(cmd);
        psmt.setString(1, libUsers.getUserName());
        psmt.setString(2, encr);
        ResultSet rs = psmt.executeQuery();
        rs.next();
        int count = rs.getInt("cnt");
        return count;
    }

    @Override
    public List<Books> searchBooks(String searchType, String searchValue) throws ClassNotFoundException, SQLException {
        String cmd;
        boolean isValid = true;

        if (searchType.equals("id")) {
            cmd = "SELECT * FROM Books WHERE Id = ?";
        } else if (searchType.equals("bookname")) {
            cmd = "SELECT * FROM Books WHERE Name = ?";
        } else if (searchType.equals("authorname")) {
            cmd = "SELECT * FROM Books WHERE Author = ?";
        } else if (searchType.equals("dept")) {
            cmd = "SELECT * FROM Books WHERE Dept = ?";
        } else {
            isValid = false;
            cmd = "SELECT * FROM Books";
        }

        connection = ConnectionHelper.getConnection();
        psmt = connection.prepareStatement(cmd);

        if (isValid) {
            psmt.setString(1, searchValue);
        }

        ResultSet rs = psmt.executeQuery();
        Books books = null;
        List<Books> booksList = new ArrayList<Books>();

        while (rs.next()) {
            books = new Books();
            books.setId(rs.getInt("id"));
            books.setName(rs.getString("name"));
            books.setAuthor(rs.getString("author"));
            books.setEdition(rs.getString("edition"));
            books.setDept(rs.getString("dept"));
            books.setNoOfBooks(rs.getInt("TotalBooks"));
            booksList.add(books);
        }

        return booksList;
    }

    // ✅ New method added to issue a book
    @Override
    public String issueBook(String userName, int bookId) throws ClassNotFoundException, SQLException {
        connection = ConnectionHelper.getConnection();

        // Step 1: Check availability
        String checkQuery = "SELECT TotalBooks FROM Books WHERE id = ?";
        PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
        checkStmt.setInt(1, bookId);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            int count = rs.getInt("TotalBooks");
            if (count <= 0) {
                return "Book ID " + bookId + " is not available.";
            }
        } else {
            return "Book ID " + bookId + " does not exist.";
        }

        // Step 2: Insert into IssuedBooks
        String insertCmd = "INSERT INTO IssuedBooks(UserName, BookID, IssueDate) VALUES (?, ?, CURRENT_DATE)";
        PreparedStatement insertStmt = connection.prepareStatement(insertCmd);
        insertStmt.setString(1, userName);
        insertStmt.setInt(2, bookId);
        insertStmt.executeUpdate();

        // Step 3: Update Books table to reduce count
        String updateCmd = "UPDATE Books SET TotalBooks = TotalBooks - 1 WHERE id = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateCmd);
        updateStmt.setInt(1, bookId);
        updateStmt.executeUpdate();

        return "Book ID " + bookId + " issued successfully to " + userName + ".";
    }
}
