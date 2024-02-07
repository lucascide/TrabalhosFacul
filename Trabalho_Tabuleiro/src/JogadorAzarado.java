import java.util.Random;

public class JogadorAzarado extends Jogador {
    public JogadorAzarado(String cor){
        super(cor);
        tipo = "Azarado";
    }

    public int jogarDados(){
        Random rand = new Random();

        int valorDado1 = rand.nextInt(6) + 1;
        int valorDado2 = rand.nextInt(6) + 1;

        System.out.println("\n" + "O valor do dado 1 foi: " + valorDado1);
        System.out.println("O valor do dado 2 foi: " + valorDado2);
        System.out.println("A soma dos valores dos dados foi: " + (valorDado1 + valorDado2));

        if(valorDado1 + valorDado2 <= 6){
            if (valorDado1 == valorDado2) {
                setJogadaBonus(true);
                System.out.println("\n" + "Dados iguais, jogador tera direito a jogar novamente" + "\n");
            };
            return valorDado1 + valorDado2;
        }
            
        return 0;
        
    }
}
