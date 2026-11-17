// Heranca por que o RoboOfensivo reutiliza as coisa do Robo
public class RoboOfensivo extends Robo {

    public RoboOfensivo(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() {
        return "Ofensivo";
    }

    @Override
    protected int modificadorDeAtaque() {
        return 5;
    }
}
