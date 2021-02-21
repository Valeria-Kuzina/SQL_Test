package ru.netology.web.data;

import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {
    private DataHelper() {
    }

    public static void cleanData() throws SQLException {
        val runner = new QueryRunner();
        val codes = "DELETE FROM auth_codes";
        val cards = "DELETE FROM cards";
        val users = "DELETE FROM users";
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            runner.update(conn, codes);
            runner.update(conn, cards);
            runner.update(conn, users);
        }
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidLoginAuthInfo() {
        return new AuthInfo("oleg", "qwerty123");
    }

    public static AuthInfo getInvalidPasswordAuthInfo() {
        return new AuthInfo("vasya", "1111");
    }

    public static String invalidPassword() {
        return "11112222";
    }

    public static String getInvalidVerificationCode() {
        return "00000";
    }

    @Value
    public static class VerificationCode {
        private val code;
    }

    public static String getVerificationCodeForVasya() throws SQLException {
        val verificationCode = "SELECT code FROM auth_codes WHERE user_id = 1;";
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(verificationCode)) {
                if (rs.next()) {
                    val code = rs.getString("code");
                    return code;
                }
            }
        }
        return null;
    }
}