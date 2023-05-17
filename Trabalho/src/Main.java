import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String resp;
        String opcao = null;

        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        String[] cores = { "Azul", "Vermelho", "Roxo", "Rosa", "Preto", "Branco" };

        System.out.println("Bem-vindo ao jogo\n");

        for (int i = 0; i < 6; i++) {
            System.out.println("Numero de jogadores atuais: (" + i + "/6)");
            System.out.println("Digite 0 para sair do jogo");
            System.out.println("Digite 1 para adicionar jogador");

            if (i >= 2) System.out.println("Digite 2 para comecar o jogo");

            System.out.print("\n" + "Resposta: ");
            opcao = teclado.next();

            if (opcao.equals("1")){
                System.out.print("\n");

                // Chama uma função estatica declarada lá embaixo para determinar o tipo do jogador
                Jogador parcial = determinarTipoJogador(cores[i]);

                // Deixa o segundo jogador com um tipo diferente do primeiro
                while (i == 1 && (jogadores.get(0).getTipo() == parcial.getTipo())) {
                    parcial = determinarTipoJogador(cores[i]);
                }
                jogadores.add(parcial);
                System.out.println("O jogador " + cores[i] + " do tipo " + parcial.getTipo() + " Foi adicionado " + "\n");
            }
            else if (opcao.equals("0")) {
                System.out.println("\n" + "Saindo do jogo...");
                System.exit(0);
            } else if ( i >= 2 && opcao.equals("2")) {
                break;
            } else {
                System.out.println("\n" + "Opcao inexistente" + "\n" );
                i--;
            }

        }

        // Menu de start e adicionar jogadores
        System.out.println("\n" + "Comecando o jogo..." + "\n");

        Tabuleiro tab = new Tabuleiro(jogadores);

        // Comecar jogo

        int vez = 0;

        while (true) {

            Jogador jogadorDaVez = jogadores.get(vez);
            int resDados;

            System.out.println("\n"+ "--------- Turno do jogador "+ jogadorDaVez.getCor() + " (" + jogadorDaVez.getTipo() + ") " +" ---------" + "\n");

            if (!jogadorDaVez.getPodeJogar() && !jogadorDaVez.getJogadaBonus()){
                System.out.println("Rodada pulada" + "\n");
                if ( vez < jogadores.size() - 1 ) vez++;
                else {
                    vez = 0;

                    System.out.println("--------- Fim da Rodada ---------" + "\n");
                    for(Jogador j: jogadores) {
                        System.out.println("O jogador " + j.getCor() + " ficou na posicao " + j.getPosicao());
                    }
    
                    System.out.println("\n" + "Deseja continuar a partida?");
                    System.out.println("Digite 0 para sair do jogo");
                    System.out.println("Digite 1 para continuar a partida");
        
                    System.out.print("\n" + "Resposta: ");
        
                    do {
                        opcao = teclado.next();
        
                        if (opcao.equals("0")) {
                            System.out.println("\n" + "Saindo do jogo...");
                            System.exit(0);
                        } else if (opcao.equals("1")) {
                            System.out.println("\n" + "Continuando..." + "\n");
                        }
                    } while (!opcao.equals("0") && !opcao.equals("1"));
                }
                
                jogadorDaVez.setPodeJogar(true);
                continue;
            }

            // Caso esteja na jogada bonus, o atributo eh botado como false
            if(jogadorDaVez.getJogadaBonus()) jogadorDaVez.setJogadaBonus(false);

            // Fase de lançamento de dados

            System.out.println("--------- Lancamento de Dados ---------" + "\n");

            System.out.print("Digite 1 para lancar os dados: ");

            do {
                resp = teclado.next();
            } while (!resp.equals("1"));

            do {
                resDados = jogadorDaVez.jogarDados();

                // Checa se o resultado dos dados eh invalido para o tipo de jogador
                if (resDados == 0) System.out.println("\n" + "Rejogando os dados..." + "\n");
            } while ( resDados == 0);

            // Fase de jogada

            System.out.println("\n" +"--------- Fase de jogada ---------" + "\n");

            int posicao = tab.moverJogador(jogadorDaVez, resDados);
            jogadorDaVez.setQtdJogadas(jogadorDaVez.getQtdJogadas() + 1);

            System.out.println("Caiu na casa " + posicao);

            switch (posicao){
                case 10: case 25: case 38:
                    System.out.println("Voce nao podera jogar proxima rodada");
                    tab.impedirProximaJogada(jogadorDaVez);
                    break;
                case 13:
                    jogadorDaVez = tab.puxarCartaAleatoria(jogadorDaVez);
                    System.out.println("O seu novo tipo eh: " + jogadorDaVez.getTipo());
                    break;
                case 5: case 15: case 30:
                    tab.andar3(jogadorDaVez);
                    break;
                case 17: case 27:
                    System.out.print("Digite a cor do jogador a ser mandado ao inicio: ");
                    String cor = teclado.next();
                    for(int i = 0; i < 6; i++){
                        if(cor.equals(cores[i])) break;
                        if(i == 5){
                            cor = teclado.next();
                            i = -1;
                        }
                    }
                    tab.levarAoInicio(cor);
                    break;
                case 20: case 35:
                    tab.trocarComOUltimo(jogadorDaVez);
                    System.out.println("Voce trocara de lugar com o ultimo jogador");
                    break;
            }

            System.out.println( "\n" + "Resultado do turno: " + "A posicao do jogador eh " + jogadorDaVez.getPosicao() + "\n");

            
            if( jogadorDaVez.getPosicao() >= 40){
                System.out.println("--------- Fim de jogo ---------" + "\n");

                for(Jogador j : jogadores){
                    System.out.println("O jogador " + j.getCor() + " ficou na posicao " + j.getPosicao());
                    System.out.println("E jogou " + j.getQtdJogadas() + " vezes ");
                }

                System.out.println("\n" + jogadorDaVez.getCor() + " ganhou o jogo!!!");
                break;
            }

            // Imprime os dados dos jogadores
            for(Jogador j: jogadores) {
                System.out.println("O jogador " + j.getCor() + " esta na posicao " + j.getPosicao());
            }
            
            System.out.println("\n");      


            // Volta no while caso o jogador tenha direito a rodada bônus

            if( jogadorDaVez.getJogadaBonus() ) continue;


            if ( vez < jogadores.size() - 1 ) vez++;
            else {
                System.out.println("--------- Fim da Rodada ---------" + "\n");
                for(Jogador j: jogadores) {
                    System.out.println("O jogador " + j.getCor() + " ficou na posicao " + j.getPosicao());
                }

                vez = 0;

                System.out.println("\n" + "Deseja continuar a partida?");
                System.out.println("Digite 0 para sair do jogo");
                System.out.println("Digite 1 para continuar a partida");
    
                System.out.print("\n" + "Resposta: ");
    
                do {
                    opcao = teclado.next();
    
                    if (opcao.equals("0")) {
                        System.out.println("\n" + "Saindo do jogo...");
                        System.exit(0);
                    } else if (opcao.equals("1")) {
                        System.out.println("\n" + "Continuando..." + "\n");
                    }
                } while (!opcao.equals("0") && !opcao.equals("1"));
            }

        }

        teclado.close();
    }

    public static Jogador determinarTipoJogador(String cor){
        Random rand = new Random();
        int resultado = rand.nextInt(3);
        Jogador tipoJogador = null;

        if(resultado == 0) {
            tipoJogador = new JogadorNormal(cor);
            return tipoJogador;
        } else if(resultado == 1) {
            tipoJogador = new JogadorSortudo(cor);
            return tipoJogador;
        } else {
            tipoJogador = new JogadorAzarado(cor);
            return tipoJogador;
        }
    }
}
