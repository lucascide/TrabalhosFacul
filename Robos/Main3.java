import java.util.Random;
import java.util.Scanner;

public class Main3 {
    public static void main(String args[]) throws InterruptedException{
        Scanner teclado = new Scanner(System.in);
        Random rand = new Random();

        Robo r1 = new Robo();
        Robo r2 = new RoboInteligente();

        System.out.println("Bem vindo ao jogo do robo");
        System.out.println("Ambos os robôs devem chegar ao alimento");

        System.out.print("\n" + "Digite a coordenada X inicial do alimento: ");
        int alimentoX = teclado.nextInt();

        System.out.print("\n" + "Digite a coordenada Y inicial do alimento: ");
        int alimentoY = teclado.nextInt();

        System.out.println("\n" + "O robo 1 esta na coordenada (" + r1.getX() + "," + r1.getY() + ")");
        System.out.println("\n" + "O robo 2 esta na coordenada (" + r2.getX() + "," + r2.getY() + ")");

        for(int j = 5; j >= 0 ; j--){
            for(int i = 0; i<= 5;i++){

                if(j == 0 && i == 0) {
                    System.out.print("r1r2 ");
                    continue;
                }
                if ( j == alimentoY && i == alimentoX) {
                    System.out.print("alim ");
                    continue;
                }
                System.out.print("---- ");
            }
            System.out.println("");
        }

        
        int movR1 = 0;
        int movR2 = 0;
        int movValR1 = 0;
        int movInvalR1 = 0;
        int movValR2 = 0;
        int movInvalR2 = 0;
        

        while(!r1.encontrouAlimento(alimentoX, alimentoY) || !r2.encontrouAlimento(alimentoX, alimentoY)){
            if(!r1.encontrouAlimento(alimentoX, alimentoY)){
                movR1 = rand.nextInt(4) + 1;

                //Delay em java
                Thread.sleep(750);

                try{
                    r1.mover(movR1);
                    movValR1++;
                } catch (MovimentoInvalidoException e) {
                    System.out.println("\n" + "O robo 1 " + e);
                    movInvalR1++;
                }
    
                System.out.println("\n" + "O robo 1 esta na coordenada (" + r1.getX() + "," + r1.getY() + ")");
                System.out.println("O robo 2 esta na coordenada (" + r2.getX() + "," + r2.getY() + ")" + "\n");
    
    
                for(int j = 5; j >= 0 ; j--){
                    for(int i = 0; i<= 5;i++){
        
                        if(j == r1.getY() && i == r1.getX()) {
                            if(j == r2.getY() && i == r2.getX()) {
                                System.out.print("r1r2 ");
                                continue;
                            }
                            if(j == alimentoY && i == alimentoX) {
                                System.out.print("r1al ");
                                continue;
                            }
                            System.out.print("r1-- ");
                            continue;
                        }
        
                        if(j == r2.getY() && i == r2.getX()) {
                            if(j == alimentoY && i == alimentoX) {
                                System.out.print("r2al ");
                                continue;
                            }
                            System.out.print("r2-- ");
                            continue;
                        }
        

                        if ( j == alimentoY && i == alimentoX) {
                            System.out.print("alim ");
                            continue;
                        }
        
                        System.out.print("---- ");
                    }
                    System.out.println("");
                }
    
                if(r1.encontrouAlimento(alimentoX, alimentoY)){
                    System.out.println("\n" + "o robo 1 chegou no alimento !!!");
                }
    
            }

            if(!r2.encontrouAlimento(alimentoX, alimentoY)){
                movR2 = rand.nextInt(4) + 1;
                //Delay em java
                Thread.sleep(750);
            

                try{
                    r2.mover(movR2);
                    movValR2++;
                } catch (MovimentoInvalidoException e) {
                    System.out.println("\n" + "O robo 2 " + e);
                    movInvalR2++;
                }
        
                System.out.println("\n" + "O robo 1 esta na coordenada (" + r1.getX() + "," + r1.getY() + ")");
                System.out.println("O robo 2 esta na coordenada (" + r2.getX() + "," + r2.getY() + ")" + "\n");

                for(int j = 5; j >= 0 ; j--){
                    for(int i = 0; i<= 5;i++){
                    
                        if(j == r1.getY() && i == r1.getX()) {
                            if(j == r2.getY() && i == r2.getX()) {
                                 System.out.print("r1r2 ");
                              continue;
                            }
                            if(j == alimentoY && i == alimentoX) {
                                System.out.print("r1al ");
                                continue;
                            }
                            System.out.print("r1-- ");
                            continue;
                        }
    
                        if(j == r2.getY() && i == r2.getX()) {
                            if(j == alimentoY && i == alimentoX) {
                                System.out.print("r2al ");
                                continue;
                            }
                            System.out.print("r2-- ");
                            continue;
                        }
    
                        if ( j == alimentoY && i == alimentoX) {
                            System.out.print("alim ");
                            continue;
                        }
    
                        System.out.print("---- ");
                    }
                    System.out.println("");
                }

                if(r2.encontrouAlimento(alimentoX, alimentoY)){
                    System.out.println("\n" + "o robo 2 chegou no alimento !!!");
                }   
            }   
        }




        System.out.println("\n" + "O numero de movimento validos do R1 foi: " + movValR1);
        System.out.println("O numero de invalidos foi: " + movInvalR1);
        System.out.println("O numero de movimento validos do R2 foi: " + movValR2);
        System.out.println("O numero de invalidos foi: " + movInvalR2);
        teclado.close();
    } 
}
