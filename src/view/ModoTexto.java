package view;

public class ModoTexto {
    public static void imprimirQuebraLinha(){
        System.out.println();
    }
    public static void imprimirMenuLogin(){
        System.out.println("\nBem vindo a biblioteca de jogos, por favor digite seu usuário e senha!");
    }

    public static void imprimirEntradaUsuario(){
        System.out.print("Digite seu usuário: ");
    }

    public static void imprimirEntradaSenha(){
        System.out.print("Digite sua senha: ");
    }

    public static void imprimirUsuarioInvalido() {
        System.out.println("O usuário inserido é inválido!\n");
    }

    public static void imprimirMenuPrincipal() {
        System.out.println("Menu Principal\n1 - Adquirir Jogos\n2 - Sua Biblioteca\n3 - Consultar Saldo\n4 - Noticias de Jogos\n5 - Adicionar Saldo\n6 - Fechar o programa");
        System.out.print("Digite a opção desejada: ");
    }

    public static void imprimirOpcaoInvalida() {
        System.out.println("Esta opção é inválida, tente novamente!");
    }

    public static void imprimirEncerracao() {
        System.out.println("O Programa será encerrado agora!");
        System.out.println("Obrigado por utilizar o nosso programa!");
    }

    public static void imprimirLista(String s){
        System.out.println(s);
    }

    public static void imprimirInsercaoSaldo(String s) {
        System.out.print("Seu saldo atual é : " + s + "\nCaso gostaria de adicionar mais saldo digite o valor, caso não, digite 0: ");
    }

    public static void imprimirSaldoNovo(double saldoNovo) {
        System.out.println("Seu novo saldo é: " + saldoNovo);
    }

    public static void imprimirMenuEntrada() {
        System.out.print("1 - Login\n2 - Cadastrar novo usuário\n0 - Fechar programa\nInsira a opção desejada: ");
    }

    public static void imprimirErroSaldo() {
        System.out.println("Não foi possível alterar o seu saldo!");
    }

    public static void imprimirSaldoAtual(String s) {
        System.out.println("Seu saldo atual é : " + s);
    }

    public static void cadastroSucesso() {
        System.out.println("Cadastro realizado com sucesso!\n");
    }

    public static void cadastroFail() {
        System.out.println("Cadastro não realizado! (O usuário pode já ter sido utilizado)\n");
    }

    public static void imprimirCadastrarUsuario() {
        System.out.print("Digite seu nome de usuário para o cadastro: ");
    }

    public static void imprimirCadastrarSenha() {
        System.out.print("Digite a senha desejada: ");
    }

    public static void imprimirEscolherNoticias() {
        System.out.print("Escolha o jogo que gostaria de ver notícias: ");
    }

    public static void imprimirNoticia(String s){
        if(s.equals("")){
            System.out.println("Nenhuma notícia foi encontrada!");
        }else System.out.println(s);
    }

    public static void imprimirOpcaoJogoCompra(){
        System.out.println("Escolha o jogo que deseja comprar: ");
    }
}
