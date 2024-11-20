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
    private int pegarIdUsuario(String usuario){
        String query = "select id_usuario from `bibliotecajogos`.`usuarios` where nome_usuario = ? ";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, usuario);

            ResultSet rs = stmt.executeQuery();
            return rs.getInt("id_usuario");
        }catch(SQLException e){
            System.out.println("Houve um erro!");
        }
        return 0;
    }
    private int verificarExistenciaUsuarioBiblioteca(int idUsuario){
        String query = "select jogos_id_jogo from `bibliotecajogos`.`bibliotecas` join `bibliotecajogos`.`usuarios` on `bibliotecajogos`.`usuarios`.`id_usuario` = `bibliotecajogos`.`bibliotecas`.`id_usuario` ";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            return rs.getInt("id_usuario");
        }catch(SQLException e){
            System.out.println("Houve um erro!");
        }
        return 0;
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

    public int adicionarJogoUsuario(String idJogo,String nomeUsuario){
        
        String query = "insert into `bibliotecajogos`.`bibliotecas values ( ? , ? );";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            return stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Não foi possível realizar a compra");
        }
        return 0;
    }
    public double pegarPrecoJogo(String idJogo){
            String query = "Select preco_jogo from `bibliotecajogos`.`jogos` where id_jogo = ?;";
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
    
                stmt.setString(1, idJogo);
    
                ResultSet rs = stmt.executeQuery();
                rs.next();
                return rs.getDouble("preco_jogo");
            }catch(SQLException e){
                System.out.println("Houve um erro ao tentar encontrar o preco do jogo!");
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


    public String pegarListaJogosUsuario(String usuario) {
        String query = "select jogos_id_jogo, nome_jogo from `bibliotecajogos`.`bibliotecas` join `bibliotecajogos`.`jogos` on `bibliotecas`.`jogos_id_jogo` = `jogos`.`id_jogo` where `bibliotecajogos`.`bibliotecas`.`id_usuario` = ? ;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            String lista = "\nLista de jogos do usuario:\n";
            while (rs.next()){
                lista += rs.getInt("id_jogo") + " - " + rs.getString("nome_jogo") + "\n";
            }
            return lista;
        }catch(SQLException e){
            System.out.println("Houve um erro ao pegar a lista de jogos!");
        }
        return "";
    }

    public String pegarListaTodosJogos(){
        String query = "select id_jogo, nome_jogo, preco_jogo from `bibliotecajogos`.`jogos`;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            String lista = "\nLista de jogos:\n";
            while (rs.next()){
                lista += rs.getInt("id_jogo") + " - " + rs.getString("nome_jogo") + rs.getString("preco_jogo") + "\n";
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
