package model;

import java.sql.*;

public class SQLConnector {
    private String url = "jdbc:mysql://localhost:3306/bibliotecajogos";
    private String usuario = "root";
    private String senha = "root";
    private Connection connection;

    public SQLConnector() {
        getConnection();
    }

    private void getConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Fazendo a conexão
            connection = DriverManager.getConnection(url, usuario, senha);

//            System.out.println("Database conectada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro estabelecendo conexão com a database: " + e.getMessage());
            System.out.println("O Programa será fechado!");
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySql não foi encontrado: " + e.getMessage());
        }
    }

    public boolean autenticarUsuario (String usuario, String senha){
        String query = "Select * from `bibliotecajogos`.`usuarios` where nome_usuario = ? and senha_usuario = ? ;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }catch(SQLException e){
            System.out.println("Houve um erro ao tentar autenticar o usuário!");
        }
        return false;
    }

    public int atualizarSaldo(double novoSaldo, String usuario) {
        String query = "update `bibliotecajogos`.`usuarios` set saldo = ? where nome_usuario = ? ;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setDouble(1, novoSaldo);
            stmt.setString(2, usuario);

            int colunasAfetadas = stmt.executeUpdate();
            return colunasAfetadas;
        }catch(SQLException e){
            System.out.println("Houve um erro ao atualizar seu saldo!");
        }
        return 0;
    }

    public double checarSaldo(String usuario) {
        String query = "Select saldo from `bibliotecajogos`.`usuarios` where nome_usuario = ? ;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, usuario);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getDouble("saldo");
        }catch(SQLException e){
            System.out.println("Houve um erro ao tentar checar seu saldo!");
        }
        return 0;
    }

    public int inserirNovoUsuario(String usuario, String senha) {
        String query = "insert into `bibliotecajogos`.`usuarios` (nome_usuario, senha_usuario, saldo) values ( ? , ? , 0.00);";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            return stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Não foi possível inserir um novo usuário!");
        }
        return 0;
    }

    public String pegarListaJogos() {
        String query = "select id_jogo, nome_jogo from `bibliotecajogos`.`jogos`;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            String lista = "\nLista de jogos:\n";
            while (rs.next()){
                lista += rs.getInt("id_jogo") + " - " + rs.getString("nome_jogo") + "\n";
            }
            return lista;
        }catch(SQLException e){
            System.out.println("Houve um erro ao pegar a lista de jogos!");
        }
        return "";
    }

    public String pegarNoticia(int id) {
        String query = "select noticia from `bibliotecajogos`.`noticias` where id_jogo = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            String noticias = "";
            int indice = 1;
            while(rs.next()){
                noticias += "Notícia " + indice + "\n" + rs.getString("noticia") + "\n";
            }
            return noticias;
        }catch(SQLException e){
            System.out.println("Houve um erro ao tentar encontrar a(s) notícia(s)!");
        }
        return "Nenhuma notícia encontrada!";
    }
}
