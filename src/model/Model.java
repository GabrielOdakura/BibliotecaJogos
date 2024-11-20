package model;

public class Model {
    private String usuario;
    private String senha;
    private SQLConnector conector;

    public Model(){
        conector = new SQLConnector();
    }

    public boolean autenticarUsuario(String usuario, String senha){
        if(conector.autenticarUsuario(usuario,senha)){
            this.usuario = usuario;
            this.senha = senha;
            return true;
        }else return false;
    }

    public String pegarLojaJogos(){
        return conector.pegarListaTodosJogos();
    }

    public String pegarBibliotecaDoUsuario(){
        return conector.pegarListaJogosUsuario(usuario);
    }

    public Double consultarSaldo(){
        return conector.checarSaldo(usuario);
    }

    public int atualizarSaldo(double novoSaldo) {
        return conector.atualizarSaldo(novoSaldo, usuario);
    }

    public int adicionarJogoUsuario(String idJogo){
        return conector.adicionarJogoUsuario(idJogo,usuario);

    }
    public int inserirNovoUsuario(String usuario, String senha) {
        return conector.inserirNovoUsuario(usuario, senha);
    }

    public double pegarPrecoJogo(String idJogo){
        return conector.pegarPrecoJogo(idJogo);
    }

    public String pegarListaJogos() {
        return conector.pegarListaTodosJogos();
    }

    public String pegarNoticia(int id) {
        return conector.pegarNoticia(id);
    }
}
