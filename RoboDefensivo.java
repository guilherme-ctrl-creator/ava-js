// Heranca por que o RoboOfensivo reutiliza as coisa do Robo
public class RoboDefensivo extends Robo {

    public RoboDefensivo(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() {
        return "Defensivo";
    }

    @Override
    protected int reduzirDanoRecebido(int dano) {
        return Math.max(2, dano - 3); 
    }
}
