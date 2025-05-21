
package br.edu.unicid.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.unicid.model.Leitor;
import br.edu.unicid.util.ConnectionFactory;

public class LeitorDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public LeitorDAO() throws Exception {
        this.conn = ConnectionFactory.getConnection();
    }

    public void salvar(Leitor leitor) throws Exception {
        if (leitor == null)
            throw new Exception("O valor passado nao pode ser nulo");
        try {
            String SQL = "INSERT INTO tbleitor (codLeitor, nomeLeitor, tipoLeitor) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, leitor.getCodLeitor());
            ps.setString(2, leitor.getNomeLeitor());
            ps.setString(3, leitor.getTipoLeitor());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados: " + sqle.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
    }

    public void alterar(Leitor leitor) throws Exception {
        if (leitor == null)
            throw new Exception("O valor passado nao pode ser nulo");
        try {
            String SQL = "UPDATE tbleitor SET nomeLeitor = ?, tipoLeitor = ? WHERE codLeitor = ?";
            ps = conn.prepareStatement(SQL);
            ps.setString(1, leitor.getNomeLeitor());
            ps.setString(2, leitor.getTipoLeitor());
            ps.setInt(3, leitor.getCodLeitor());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao alterar dados: " + sqle.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
    }

    public void excluir(int codLeitor) throws Exception {
        try {
            String SQL = "DELETE FROM tbleitor WHERE codLeitor = ?";
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, codLeitor);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados: " + sqle.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
    }

    public Leitor consultar(int codLeitor) throws Exception {
        Leitor leitor = null;
        try {
            String SQL = "SELECT * FROM tbleitor WHERE codLeitor = ?";
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, codLeitor);
            rs = ps.executeQuery();
            if (rs.next()) {
                leitor = new Leitor(
                        rs.getInt("codLeitor"),
                        rs.getString("nomeLeitor"),
                        rs.getString("tipoLeitor")
                );
            }
            return leitor;
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, ps, rs);
        }
    }

    public List<Leitor> listarTodos() throws Exception {
        List<Leitor> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement("SELECT * FROM tbleitor");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Leitor(
                        rs.getInt("codLeitor"),
                        rs.getString("nomeLeitor"),
                        rs.getString("tipoLeitor")
                ));
            }
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, ps, rs);
        }
    }
}