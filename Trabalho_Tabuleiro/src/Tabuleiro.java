import java.util.ArrayList;
import java.util.Random;

public class Tabuleiro {
    private ArrayList<Jogador> listaJogadores = new ArrayList<Jogador>();
    private Jogador jogadorDaRodada = null;

    public Tabuleiro(ArrayList<Jogador> jogadores){
        listaJogadores = jogadores;
    }

    public void adicionarJogador(Jogador j){
        listaJogadores.add(j);
    }

    public int moverJogador(Jogador j, int resDados) {
        if(j.getPosicao() +  resDados <= 40)
            j.setPosicao(j.getPosicao() +  resDados);
        else
            j.setPosicao(40);

        return j.getPosicao();
    }

    public void impedirProximaJogada(Jogador j ){
        j.setPodeJogar(false);
    }

    public Jogador puxarCartaAleatoria(Jogador j) {
        Random rand = new Random();

        int carta;
        int cartaJogadorNormal = 0;
        int cartaJogadorSortudo = 1;
        int cartaJogadorAzarado = 2;

        Jogador jogadorMudado = null;

        if(j instanceof JogadorNormal){
            while( (carta = rand.nextInt(3)) != 0);
            if( carta == cartaJogadorAzarado){
                jogadorMudado = new JogadorAzarado(j.getCor());
            } else {
                jogadorMudado = new JogadorSortudo(j.getCor());
            }
        }

        if(j instanceof JogadorSortudo){
            while( (carta = rand.nextInt(3)) != 1);
            if( carta == cartaJogadorNormal){
                jogadorMudado = new JogadorNormal(j.getCor());
            } else {
                jogadorMudado = new JogadorAzarado(j.getCor());
            }
        }

        if(j instanceof JogadorAzarado){
            while( (carta = rand.nextInt(3)) != 2);
            if( carta == cartaJogadorSortudo){
                jogadorMudado = new JogadorSortudo(j.getCor());
            } else {
                jogadorMudado = new JogadorNormal(j.getCor());
            }
        }

        jogadorMudado.setPosicao(j.getPosicao());
        jogadorMudado.setQtdJogadas(j.getQtdJogadas());
        jogadorMudado.setJogadaBonus(j.getJogadaBonus());
        

        listaJogadores.set(listaJogadores.indexOf(j), jogadorMudado);
        return jogadorMudado;
    }

    public void andar3(Jogador j) {
        if(!(j instanceof JogadorAzarado)){
            System.out.println("Jogador andara 3 casas para frente");
            j.setPosicao(j.getPosicao() + 3);
        }
    }

    public void levarAoInicio(String cor) {
        for(Jogador j:listaJogadores){
            if(j.getCor().equals(cor)) {
                j.setPosicao(0);
            }
        }
    }

    public void trocarComOUltimo (Jogador jogador) {
        int i = listaJogadores.indexOf(jogador);

        Jogador jogadorMenosPos = listaJogadores.get(i);

        for(Jogador j: listaJogadores) {
            if(jogadorMenosPos.getPosicao() > j.getPosicao()){
                i = listaJogadores.indexOf(j);
                jogadorMenosPos = listaJogadores.get(i);
            } 
        }

        int guardadorPosicao = jogador.getPosicao();
        jogador.setPosicao(jogadorMenosPos.getPosicao());
        jogadorMenosPos.setPosicao(guardadorPosicao);
    }

    public Jogador getJogadorRodada(){
        return this.jogadorDaRodada;
    }

    public void setJogadorRodada(Jogador novoJogadorRodada){
        jogadorDaRodada = novoJogadorRodada;
    }
}
