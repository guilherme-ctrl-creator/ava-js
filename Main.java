import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        //Polimorfismo por que usa uma lista pra ter objeto dos 3 tipo de robo
        ArrayList<Robo> robos = new ArrayList<>();

        int proximoCodigo = 1;
        int opt = -1;

        do {
            System.out.println();
            System.out.println("------------------------");
            System.out.println("Campeonato de Roboludos");
            System.out.println("------------------------");
            System.out.println("  0. Sair");
            System.out.println("  1. Cadastrar robo");
            System.out.println("  2. Consultar robos");
            System.out.println("  3. Realizar combate");
            System.out.println("  4. Recuperar energia");
            System.out.println("  5. Rodada geral");
            System.out.println("Digite um numero: ");
            opt = buscarOperacao(s);
            System.out.println("------------------------");
            
            switch (opt) {
                case 0:
                    System.out.println();
                    System.out.println("Top");
                    break;

                case 1: {
                    System.out.println();
                    System.out.println("CADASTRAR ROBO");
                    System.out.println("Codigo: " + proximoCodigo);
                    System.out.println("Digite o nome do robo: ");
                    String nome = s.next();
                    int ataque = 0;

                    do {
                        ataque = lerInteiroNoIntervalo(s, "Digite o ataque do robo (10 a 30): ", 10, 30);
                    } while (ataque < 10 || ataque > 30);

                    int defesa = -1;

                    do {
                        defesa = lerInteiroNoIntervalo(s, "Digite a defesa do robo (0 a 20): ", 0, 20);
                    } while (defesa < 0 || defesa > 20);

                    System.out.println("Selecione o tipo do robo:");
                    System.out.println("1. Ofensivo");
                    System.out.println("2. Defensivo");
                    System.out.println("3. Equilibrado");
                    int tipo = lerInteiroNoIntervalo(s, "Tipo: ", 1, 3);

                    Robo novoRobo;

                    if (tipo == 1) {
                        novoRobo = new RoboOfensivo(proximoCodigo, nome, ataque, defesa);
                    } else if (tipo == 2) {
                        novoRobo = new RoboDefensivo(proximoCodigo, nome, ataque, defesa);
                    } else {
                        novoRobo = new RoboEquilibrado(proximoCodigo, nome, ataque, defesa);
                    }

                    robos.add(novoRobo);

                    proximoCodigo++;
                    System.out.println();
                    System.out.println("Robo cadastrado");
                    break;
                }

                case 2:
                    System.out.println();
                    System.out.println("LISTA DOS ROBOS");

                    if (robos.isEmpty()) {
                        System.out.println("Nenhum robo cadastrado");
                    } else {
                        for (Robo p : robos) {
                            System.out.println("------------------------------");
                            System.out.println("Codigo: " + p.getCodigo());
                            System.out.println("Nome: " + p.getNome());
                            System.out.println("Tipo: " + p.getTipo());
                            System.out.println("Ataque: " + p.getAtaque());
                            System.out.println("Defesa: " + p.getDefesa());
                            System.out.println("Energia: " + p.getEnergia());
                            System.out.println("Vitorias: " + p.getVitorias());
                            System.out.println("Derrotas: " + p.getDerrotas());
                            System.out.println("Empates: " + p.getEmpates());
                            System.out.println("Pontos: " + p.getPontos());

                            if (p.getEnergia() >= 30) {
                                System.out.println("Disponivel");
                            } else {
                                System.out.println("Em recuperacao");
                            }
                        }
                    }
                    break;

                case 3: {
                    System.out.println();
                    System.out.println("Quebra Pau");
                    System.out.println("Insira o codigo do primeiro robo: ");
                    int codigo1 = buscarOperacao(s);
                    System.out.println("Insira o codigo do segundo robo: ");
                    int codigo2 = buscarOperacao(s);

                    Robo robo1 = null;
                    Robo robo2 = null;

                    for (Robo r : robos) {
                        if (r.getCodigo() == codigo1) {
                            robo1 = r;
                        }
                        if (r.getCodigo() == codigo2) {
                            robo2 = r;
                        }
                    }
                    if (codigo1 == codigo2) {
                        System.out.println("Os robos devem ser diferentes");
                        break;
                    }
                    if (robo1 == null || robo2 == null) {
                        System.out.println("Nao tem robo com esse codigo");
                        break;
                    }
                    if (robo1.getEnergia() < 30 || robo2.getEnergia() < 30) {
                        System.out.println("Os dois robos precisam ter pelo menos 30 de energia");
                        break;
                    }

                    Robo primeiro;
                    Robo segundo;

                    if (robo1.getPontos() == robo2.getPontos()) {
                        primeiro = robo1.getCodigo() > robo2.getCodigo() ? robo2 : robo1;
                        segundo = primeiro == robo1 ? robo2 : robo1;
                    } else if (robo1.getPontos() < robo2.getPontos()) {
                        primeiro = robo1;
                        segundo = robo2;
                    } else {
                        primeiro = robo2;
                        segundo = robo1;
                    }
                    realizarCombate(primeiro, segundo);
                    break;
                }

                case 4: {
                    System.out.println();
                    System.out.println("Recuperar energia");
                    System.out.println("Insira o codigo do robo: ");
                    int roboCodigo = buscarOperacao(s);
                    int energiaRecarga = 0;
                    Robo roboRecarga = null;

                    for (Robo r : robos) {
                        if (r.getCodigo() == roboCodigo) {
                            roboRecarga = r;
                        }
                    }
                    if (roboRecarga == null) {
                        System.out.println("Robo nao achado");
                        break;
                    }

                    do {
                        System.out.println("Insira quanto quer recuperar: ");
                        energiaRecarga = buscarOperacao(s);

                        if (energiaRecarga == 0 || energiaRecarga % 10 != 0) {
                            System.out.println("A quantidade deve ser maior que zero e multipla de 10");
                        }

                    } while (energiaRecarga == 0 || energiaRecarga % 10 != 0);

                    if (roboRecarga.getEnergia() + energiaRecarga > 100) {
                        System.out.println("O limite e 100 de energia");
                        break;
                    }

                    if (roboRecarga.recuperarEnergia(energiaRecarga)) {
                        System.out.println("Energia recuperada");
                    } else {
                        System.out.println("Robo nao tem ponto suficiente");
                    }
                    break;
                }

                case 5: {
                    System.out.println();
                    System.out.println("Rodada geral");
                    ArrayList<Robo> disponiveis = new ArrayList<>();
                    int qtd = 0;

                    for (Robo r : robos) {
                    if (r.getEnergia() >= 30) {
                            disponiveis.add(r);
                            qtd++;
                        }
                    }
                    if (qtd < 2) {
                        System.out.println("Nao tem robos suficientes");
                        break;
                    }

                    ArrayList<Robo> restantes = disponiveis;
                    ArrayList<Robo> ordenados = new ArrayList<>();

                    for (int k = 0; k < qtd; k++) {
                        Robo melhor = null;
                        for (Robo r : restantes) {
                            boolean maisForte = false;
                            if (melhor == null) {
                                maisForte = true;
                            } else if (r.getPontos() != melhor.getPontos()) {
                                maisForte = r.getPontos() > melhor.getPontos();
                            } else if (r.getVitorias() != melhor.getVitorias()) {
                                maisForte = r.getVitorias() > melhor.getVitorias();
                            } else if (r.getEnergia() != melhor.getEnergia()) {
                                maisForte = r.getEnergia() > melhor.getEnergia();
                            } else {
                                maisForte = r.getCodigo() < melhor.getCodigo();
                            }
                            if (maisForte) {
                                melhor = r;
                            }
                        }

                        ordenados.add(melhor);
                        ArrayList<Robo> novos = new ArrayList<>();

                        for (Robo r : restantes) {
                            if (r != melhor) {
                                novos.add(r);
                            }
                        }
                        restantes = novos;
                    }

                    System.out.println("CONFRONTOS");
                    Robo esperando = null;

                    for (Robo r : ordenados) {
                        if (esperando == null) {
                            esperando = r;
                        } else {
                            System.out.println(esperando.getNome() + " x " + r.getNome());
                            esperando = null;
                        }
                    }

                    if (esperando != null) {
                        esperando.receberPontoDeFolga();
                        System.out.println(esperando.getNome() + " recebeu 1 ponto de folga");
                    }

                    System.out.println();
                    System.out.println("COMBATES");

                    esperando = null;

                    for (Robo r : ordenados) {
                        if (esperando == null) {
                            esperando = r;
                        } else {
                            Robo lutador1 = esperando;
                            Robo lutador2 = r;
                            esperando = null;
                            Robo atacante;
                            Robo defensor;

                            if (lutador1.getPontos() == lutador2.getPontos()) {
                                atacante = lutador1.getCodigo() > lutador2.getCodigo() ? lutador2 : lutador1;
                                defensor = atacante == lutador1 ? lutador2 : lutador1;

                            } else if (lutador1.getPontos() < lutador2.getPontos()) {
                                atacante = lutador1;
                                defensor = lutador2;
                            } 

                            else {
                                atacante = lutador2;
                                defensor = lutador1;
                            }
                            System.out.println();
                            System.out.println(atacante.getNome() + " x " + defensor.getNome());
                            realizarCombate(atacante, defensor);
                        }
                    }
                    break;
                }
                default:
                    System.out.println("Coloque uma das opcoes");
                    break;
            }
        } while (opt != 0);
        s.close();
    }

    // matuta
    public static void realizarCombate(Robo primeiro, Robo segundo) {

        System.out.println();
        System.out.println(primeiro.getNome() + " comeca atacando");

        boolean terminou = false;

        for (int i = 1; i <= 5 && !terminou; i++) {
            System.out.println();
            System.out.println("Rodada " + i + ":");
            int dano = primeiro.calcularDanoContra(segundo, i);

            segundo.receberDano(dano);

            System.out.println(primeiro.getNome() + " atacou " + segundo.getNome() + " causando " + dano + " de dano");
            System.out.println("Energia de " + segundo.getNome() + ": " + segundo.getEnergia());

            if (segundo.getEnergia() == 0) {

                primeiro.registrarVitoria();
                segundo.registrarDerrota();

                System.out.println("Vencedor: " + primeiro.getNome());
                terminou = true;
            } else {

                dano = segundo.calcularDanoContra(primeiro, i);
                primeiro.receberDano(dano);

                System.out.println(segundo.getNome() + " atacou " + primeiro.getNome() + " causando " + dano + " de dano");
                System.out.println("Energia de " + primeiro.getNome() + ": " + primeiro.getEnergia());

                if (primeiro.getEnergia() == 0) {
                    segundo.registrarVitoria();
                    primeiro.registrarDerrota();

                    System.out.println("Vencedor: " + segundo.getNome());
                    terminou = true;
                }
            }
        }

        if (!terminou) {

            if (primeiro.getEnergia() == segundo.getEnergia()) {
                primeiro.registrarEmpate();
                segundo.registrarEmpate();

                System.out.println("Empate");
            } else if (primeiro.getEnergia() > segundo.getEnergia()) {
                primeiro.registrarVitoria();
                segundo.registrarDerrota();

                System.out.println("Vencedor: " + primeiro.getNome());
            } else {
                segundo.registrarVitoria();
                primeiro.registrarDerrota();

                System.out.println("Vencedor: " + segundo.getNome());
            }
        }
    }

    public static int lerInteiroNoIntervalo(Scanner s, String mensagem, int minimo, int maximo) {
        int valor;

        do {
            System.out.println(mensagem);
            valor = buscarOperacao(s);

            if (valor < minimo || valor > maximo) {
                System.out.println("Valor invalido");
            }
        } while (valor < minimo || valor > maximo);
        return valor;
    }

    public static int buscarOperacao(Scanner s) {
        int opt = -1;
        do {
            try {
                opt = s.nextInt();
            } catch (InputMismatchException e) {
                s.next();
                System.out.println();
                System.out.println("Nao tem isso ai");
                System.out.println("Digite novamente a informacao");
                opt = -1;
            }
        } while (opt < 0);
        return opt;
    }
}