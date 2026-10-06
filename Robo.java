public class Robo {
    int codigo;
    String nome;
    int ataque;
    int defesa;
    int energia;
    int vitorias;
    int derrotas;
    int empates;
    int pontos;

    Robo(int codigo, String nome, int ataque, int defesa) {
        this.codigo = codigo;
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.energia = 100;
        this.vitorias = 0;
        this.derrotas = 0;
        this.empates = 0;
        this.pontos = 0;
    }
}