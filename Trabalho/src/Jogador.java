public abstract class Jogador {
    protected String cor;
    protected int posicao;
    protected boolean podeJogar;
    protected boolean jogadaBonus;
    protected String tipo;
    protected int qtdJogadas;

    public Jogador(String cor){
        this.cor = cor;
        posicao = 0;
        podeJogar = true;
        tipo = null;
        jogadaBonus = false;
        qtdJogadas = 0;
    }

    public abstract int jogarDados();

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public boolean getPodeJogar() {
        return podeJogar;
    }

    public void setPodeJogar(boolean podeJogar) {
        this.podeJogar = podeJogar;
    }

    public String getTipo(){
        return tipo;
    }


    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public boolean getJogadaBonus() {
        return jogadaBonus;
    }

    public void setJogadaBonus(boolean jogadaBonus) {
        this.jogadaBonus = jogadaBonus;
    }

    public int getQtdJogadas() {
        return qtdJogadas;
    }

    public void setQtdJogadas(int qtdJogadas) {
        this.qtdJogadas = qtdJogadas;
    }
    
}
