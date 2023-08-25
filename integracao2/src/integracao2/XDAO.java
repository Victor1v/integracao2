package integracao2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XDAO {
    private Connection connection;

    public XDAO() {
        // Configurar a conexão com o banco de dados
        String url = "jdbc:postgresql://localhost:5432/teste";
        String user = "ti2cc";
        String password = "ti@cc";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(X x) {
        String sql = "INSERT INTO X (nome, idade) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, x.getNome());
            statement.setInt(2, x.getIdade());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public X consultarPorId(int id) {
        String sql = "SELECT * FROM X WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new X(resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getInt("idade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<X> listarTodos() {
        List<X> xList = new ArrayList<>();
        String sql = "SELECT * FROM X";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                X x = new X(resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getInt("idade"));
                xList.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return xList;
    }

    public void atualizar(X x) {
        String sql = "UPDATE X SET nome = ?, idade = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, x.getNome());
            statement.setInt(2, x.getIdade());
            statement.setInt(3, x.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM X WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fecharConexao() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
