import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner teclado = new Scanner(System.in);
        Robo r = new Robo();

        int alimentoX;
        int alimentoY;

        System.out.println("Bem vindo ao jogo do robo movimente-se com as teclas 1, 2, 3, 4 ou digitando up, down, right, left");
        System.out.println("Para ganhar voce deve levar o robo ao alimento");
        System.out.print("\n" + "Digite a coordenada X inicial do alimento: ");
        alimentoX = teclado.nextInt();
        System.out.print("\n" + "Digite a coordenada Y inicial do alimento: ");
        alimentoY = teclado.nextInt();

        System.out.println("\n" + "O robo esta na coordenada (" + r.getX() + "," + r.getY() + ")" + "\n");

        for(int j = 5; j >= 0 ; j--){
            for(int i = 0; i<= 5;i++){

                if(j == 0 && i == 0) {
                    System.out.print("r ");
                    continue;
                }

                if ( j == alimentoY && i == alimentoX) {
                    System.out.print("a ");
                    continue;
                }

                System.out.print("- ");
            }
            System.out.println("");
        }
        
        int respInt = 0;
        String resp = "";

        while(!r.encontrouAlimento(alimentoX, alimentoY)){
            respInt = 0;
            resp = "";

            System.out.print("\n" + "Digite o movimento desejado:");
            resp = teclado.next();

            System.out.print("\n");

            try{
                respInt = Integer.parseInt(resp);
            } catch (NumberFormatException ex) {};

            if(respInt == 0){
                try {
                    r.mover(resp);
                } catch (MovimentoInvalidoException e) {
                    System.out.println("O robo " + e + "\n");
                }
            } else {
                try {
                    r.mover(respInt);
                } catch (MovimentoInvalidoException e) {
                    System.out.println("O robo " + e + "\n");
                }
            }

            System.out.println("O robo esta na coordenada (" + r.getX() + "," + r.getY() + ")" + "\n");
            for(int j = 5; j >= 0 ; j--){
                for(int i = 0; i<= 5;i++){

                    if(j == r.getY() && i == r.getX()) {
                        System.out.print("r ");
                        continue;
                    }

                    if ( j == alimentoY && i == alimentoX) {
                        System.out.print("a ");
                        continue;
                    }

                    System.out.print("- ");
                }
                System.out.println("");
            }          
        }

        System.out.println("\n" + "O robo chegou no alimento !!!");
        teclado.close();
    } 
}
