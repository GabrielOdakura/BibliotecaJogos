package controller;

import model.Model;
import view.ModoTexto;

import java.util.Scanner;

public class ControllerTexto {
    private Model model = new Model();

    public ControllerTexto(){
        Scanner input = new Scanner(System.in);
        while(true){
            ModoTexto.imprimirMenuEntrada();
            switch(input.nextLine()){
                case "1" -> iniciarLogin();
                case "2" -> cadastrarNovoUsuario();
                case "0" -> System.exit(0);
            }
        }
    }

    private void cadastrarNovoUsuario(){
        Scanner input = new Scanner(System.in);

        ModoTexto.imprimirCadastrarUsuario();
        String usuario = input.nextLine();

        ModoTexto.imprimirCadastrarSenha();
        String senha = input.nextLine();

        if(model.inserirNovoUsuario(usuario, senha) == 1){
            ModoTexto.cadastroSucesso();
        }else {
            ModoTexto.cadastroFail();
        }
    }

    private void iniciarLogin() {
        ModoTexto.imprimirMenuLogin();
        Scanner input = new Scanner(System.in);

        ModoTexto.imprimirEntradaUsuario();
        String usuario = input.nextLine();

        ModoTexto.imprimirEntradaSenha();
        String senha = input.nextLine();

        if(model.autenticarUsuario(usuario,senha)){
            menuPrincipal(input);
        }else ModoTexto.imprimirUsuarioInvalido();
    }

    private void menuPrincipal(Scanner input){
        ModoTexto.imprimirQuebraLinha();
        while(true){
            ModoTexto.imprimirMenuPrincipal();
            String opcao = input.nextLine();
            switch (opcao){
                case "1" ->{
                    /*
                    precisa fazer esse q vai ser mostrar a lista de jogos com o id, nome do jogo, categoria do jogo e preço
                    ai precisa checar com o usuário se ele tem saldo pra adquirir o jogo, se tiver só atualizar o saldo e botar o jogo na biblioteca dele
                     */
                    ModoTexto.imprimirLista(model.pegarLojaJogos());
                }
                case "2" ->{
                    /*
                    esse é só pegar a biblioteca do usuário inteira
                    literal: select jogos_id_jogo, nome_jogo from bibliotecajogos.bibliotecas join bibliotecajogos.jogos on bibliotecas.jogos_id_jogo = jogos.id_jogo where bibliotecajogos.bibliotecas.id_usuario = ? ;
                     */
                    ModoTexto.imprimirLista(model.pegarBiblioteca());
                }
                case "3" ->{
                    ModoTexto.imprimirSaldoAtual(String.valueOf(model.consultarSaldo()));
                }
                case "4" ->{
                    ModoTexto.imprimirLista(model.pegarListaJogos());
                    ModoTexto.imprimirEscolherNoticias();
                    String entrada = input.nextLine();
                    try{
                        int id = Integer.parseInt(entrada);
                        ModoTexto.imprimirNoticia(model.pegarNoticia(id));
                    }catch (Exception e){
                        ModoTexto.imprimirOpcaoInvalida();
                    }
                }
                case "5" ->{
                    double saldoAtual = model.consultarSaldo();
                    ModoTexto.imprimirInsercaoSaldo(String.valueOf(saldoAtual));
                    String inputUsuario = input.nextLine();
                    if(!inputUsuario.equals("0")){
                        try{
                            double dinheiro = Double.parseDouble(inputUsuario);
                            int retorno = model.atualizarSaldo(saldoAtual + dinheiro);
                            if(retorno == 1){
                                ModoTexto.imprimirSaldoNovo(saldoAtual + dinheiro);
                            }else ModoTexto.imprimirErroSaldo();
                        }catch(Exception e){
                            System.out.println("O número digitado foi inválido, voltando ao menu principal");
                        }
                    }
                }
                case "6" ->{
                    ModoTexto.imprimirEncerracao();
                    System.exit(0);
                }
                default ->{
                    ModoTexto.imprimirOpcaoInvalida();
                }
            }
        }
    }
}
