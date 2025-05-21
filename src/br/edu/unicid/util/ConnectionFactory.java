package br.edu.unicid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConnectionFactory {

    // retorna uma Connection já configurada pro MySQL 8.x
    public static Connection getConnection() throws Exception {
        try {
            // registra o driver do MySQL 8
            Class.forName("com.mysql.cj.jdbc.Driver");

            // URL incluindo timezone pra evitar warning de fuso
            String url = "jdbc:mysql://localhost:3306/uniciddb?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String senha = "ana0104";

            return DriverManager.getConnection(url, user, senha);
        } catch (Exception e) {
            throw new Exception("Erro ao conectar: " + e.getMessage());
        }
    }

    // sobrecargas pra fechar conexões...
    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        close(conn, stmt, rs);
    }

    public static void closeConnection(Connection conn, Statement stmt) throws Exception {
        close(conn, stmt, null);
    }

    public static void closeConnection(Connection conn) throws Exception {
        close(conn, null, null);
    }

    private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        try {
            if (rs   != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            throw new Exception("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
