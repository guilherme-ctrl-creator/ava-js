
public abstract class Robo {
    // Encapsulamento por que os atributos ficam privados e sao acessados por metodos
    private int codigo;
    private String nome;
    private int ataque;
    private int defesa;
    private int energia;
    private int vitorias;
    private int derrotas;
    private int empates;
    private int pontos;

    public Robo(int codigo, String nome, int ataque, int defesa) {

        if (codigo <= 0) {
            throw new IllegalArgumentException("Codigo invalido.");
        }

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido.");
        }

        if (ataque < 10 || ataque > 30) {
            throw new IllegalArgumentException("Ataque invalido.");
        }

        if (defesa < 0 || defesa > 20) {
            throw new IllegalArgumentException("Defesa invalida.");
        }

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

    public abstract String getTipo();

    // Calcula o dano do ataque
    public int calcularDanoContra(Robo alvo, int rodada) {

        int ataqueDoGolpe = ataque + modificadorDeAtaque();

        int dano = ataqueDoGolpe - alvo.getDefesa();

        if (dano < 5) {
            dano = 5;
        }

        if (rodada % 2 == 0) {
            dano += 5;
        }

        return alvo.reduzirDanoRecebido(dano);
    }

    // Modificador de ataque padrao
    protected int modificadorDeAtaque() {
        return 0;
    }

    // Reducao de dano padrao
    protected int reduzirDanoRecebido(int dano) {
        return dano;
    }

    // Receber dano
    public void receberDano(int dano) {

        if (dano < 0) {
            throw new IllegalArgumentException("Dano invalido.");
        }

        energia -= dano;

        if (energia < 0) {
            energia = 0;
        }
    }

    // Registrar vitoria
    public void registrarVitoria() {
        vitorias++;
        pontos += 3;
    }

    // Registrar derrota
    public void registrarDerrota() {
        derrotas++;
    }

    // Registrar empate
    public void registrarEmpate() {
        empates++;
        pontos++;
    }

    // Ponto de folga da rodada geral
    public void receberPontoDeFolga() {
        pontos++;
    }

    // Recuperar energia
    public boolean recuperarEnergia(int quantidade) {

        if (quantidade <= 0 || quantidade % 10 != 0) {
            return false;
        }

        int pontosNecessarios = quantidade / 10;

        if (energia + quantidade > 100 || pontos < pontosNecessarios) {
            return false;
        }

        energia += quantidade;
        pontos -= pontosNecessarios;

        return true;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getEnergia() {
        return energia;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public int getPontos() {
        return pontos;
    }

    public boolean estaDisponivel() {
        return energia >= 30;
    }
}
