import java.util.Random;

public class RoboInteligente extends Robo {
    private boolean proximoMovimentoValido;

    public RoboInteligente () {
        proximoMovimentoValido = false;
    }

    public boolean mover(int direcao) throws MovimentoInvalidoException {
        int xmax = 5;
        int xmin = 0;
        int ymax = 5;
        int ymin = 0;

        Random rand = new Random();

        switch (direcao){
            case 1:
                if((y+1) <= ymax){
                    y++;
                    if(proximoMovimentoValido) proximoMovimentoValido = false;
                    return true;
                } else if (proximoMovimentoValido){
                    return mover(rand.nextInt(4) + 1);
                }
                else {
                    proximoMovimentoValido = true;
                    throw new MovimentoInvalidoException();
                }

            case 2:
                if((y-1) >= ymin){
                    y--;
                    if(proximoMovimentoValido) proximoMovimentoValido = false;
                    return true;
                } else if (proximoMovimentoValido){
                    return mover(rand.nextInt(4) + 1);
                }
                else {
                    proximoMovimentoValido = true;
                    throw new MovimentoInvalidoException();
                }

            case 3:
                if((x+1) <= xmax){
                    x++;
                    if(proximoMovimentoValido) proximoMovimentoValido = false;
                    return true;
                } else if (proximoMovimentoValido){
                    return mover(rand.nextInt(4) + 1);
                }
                else {
                    proximoMovimentoValido = true;
                    throw new MovimentoInvalidoException();
                }

            case 4:
                if((x-1) >= xmin){
                    x--;
                    if(proximoMovimentoValido) proximoMovimentoValido = false;
                    return true;
                } else if (proximoMovimentoValido){
                    return mover(rand.nextInt(4) + 1);
                }
                else {
                    proximoMovimentoValido = true;
                    throw new MovimentoInvalidoException();
                }
            default:
                return false;
        }
    }
}
