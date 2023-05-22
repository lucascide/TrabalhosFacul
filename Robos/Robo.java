public class Robo {
    protected int x;
    protected int y;

    public Robo() {
        x = 0;
        y = 0;
    }

    public boolean mover (String direcao) throws MovimentoInvalidoException{
        int xmax = 5;
        int xmin = 0;
        int ymax = 5;
        int ymin = 0;

        switch (direcao){
            case "up":
                if((y+1) <= ymax){
                    y++;
                    return true;
                } else {
                    throw new MovimentoInvalidoException();
                }

            case "down":
                if((y-1) >= ymin){
                    y--;
                    return true;
                }
                else throw new MovimentoInvalidoException();

            case "right":
                if((x+1) <= xmax){
                    x++;
                    return true;
                }
                else throw new MovimentoInvalidoException();

            case "left":
                if((x-1) >= xmin){
                    x--;
                    return true;
                }
                else throw new MovimentoInvalidoException();
            default:
                return false;
        }
    }

    public boolean mover (int direcao) throws MovimentoInvalidoException {
        int xmax = 5;
        int xmin = 0;
        int ymax = 5;
        int ymin = 0;

        switch (direcao){
            case 1:
                if((y+1) <= ymax){
                    y++;
                    return true;
                }
                else throw new MovimentoInvalidoException();

            case 2:
                if((y-1) >= ymin){
                    y--;
                    return true;
                }
                else throw new MovimentoInvalidoException();

            case 3:
                if((x+1) <= xmax){
                    x++;
                    return true;
                }
                else throw new MovimentoInvalidoException();

            case 4:
                if((x-1) >= xmin){
                    x--;
                    return true;
                }
                else throw new MovimentoInvalidoException();
            default:
                return false;
        }
    }

    public boolean encontrouAlimento(int alimentoX, int alimentoY) {
        if(x == alimentoX && y == alimentoY)
            return true;
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y){
        this.y = y;
    }
}