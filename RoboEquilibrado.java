// Heranca por que o RoboOfensivo reutiliza as coisa do Robo
public class RoboEquilibrado extends Robo {

    public RoboEquilibrado(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() {
        return "Equilibrado";
    }

    @Override
    protected int modificadorDeAtaque() {
        return 0;
    }

    @Override
    protected int reduzirDanoRecebido(int dano) {
        return dano;
    }
}
