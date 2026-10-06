import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    ArrayList<Robo> robos = new ArrayList<>();
    int proximoCodigo = 1;
    int opt = -1;
    do {
        System.out.println();
        System.out.println("Campeonato de Roboludos");
        System.out.println("  0. Sair");
        System.out.println("  1. Cadastrar robo");
        System.out.println("  2. Consultar robos");
        System.out.println("  3. Realizar combate");
        System.out.println("  4. Recuperar energia");
        System.out.println("  5. Rodada geral");
        System.out.println("Digite um numero: ");
        opt = buscarOperacao(s);
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
                    try {
                        System.out.println("Digite o ataque do robo (10 a 30): ");
                        ataque = s.nextInt();
                        if (ataque < 10 || ataque > 30) {
                            System.out.println("Ataque invalido");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Digite apenas numeros");
                        s.next();
                        ataque = 0;
                    }
                } while (ataque < 10 || ataque > 30);
                int defesa = -1;
                do {
                    try {
                        System.out.println("Digite a defesa do robo (0 a 20): ");
                        defesa = s.nextInt();
                        if (defesa < 0 || defesa > 20) {
                            System.out.println("Defesa invalida");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Digite apenas numeros");
                        s.next();
                        defesa = -1;
                    }
                } while (defesa < 0 || defesa > 20);
                robos.add(new Robo(proximoCodigo, nome, ataque, defesa));
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
                        System.out.println("Codigo: " + p.codigo);
                        System.out.println("Nome: " + p.nome);
                        System.out.println("Ataque: " + p.ataque);
                        System.out.println("Defesa: " + p.defesa);
                        System.out.println("Energia: " + p.energia);
                        System.out.println("Vitórias: " + p.vitorias);
                        System.out.println("Derrotas: " + p.derrotas);
                        System.out.println("Empates: " + p.empates);
                        System.out.println("Pontos: " + p.pontos);
                        if (p.energia >= 30) {
                            System.out.println("Disponivel");
                        } else {
                            System.out.println("Em recuperação");
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
                    if (r.codigo == codigo1) {
                        robo1 = r;
                    }
                    if (r.codigo == codigo2) {
                        robo2 = r;
                    }
                }
                if (codigo1 == codigo2) {
                    System.out.println("Os robos devem ser diferentes");
                    continue;
                }
                if (robo1 == null || robo2 == null) {
                    System.out.println("Não tem robo com esse codigo");
                    continue;
                }
                if (robo1.energia < 30 || robo2.energia < 30) {
                    System.out.println("Os dois robos precisam ter pelo menos 30 de energia");
                    continue;
                }
                Robo primeiro;
                Robo segundo;
                if (robo1.pontos == robo2.pontos) {
                    primeiro = robo1.codigo > robo2.codigo ? robo2 : robo1;
                    segundo = robo1.codigo > robo2.codigo ? robo1 : robo2;
                } else if (robo1.pontos < robo2.pontos) {
                    primeiro = robo1;
                    segundo = robo2;
                } else {
                    primeiro = robo2;
                    segundo = robo1;
                }
                System.out.println();
                System.out.println(primeiro.nome + " começa atacando");
                boolean terminou = false;
                for (int i = 1; i <= 5 && !terminou; i++) {
                    System.out.println();
                    System.out.println("Rodada " + i + "ª: ");
                    int dano = primeiro.ataque - segundo.defesa;
                    if (dano < 5) {
                        dano = 5;
                    }
                    if (i % 2 == 0) {
                        dano += 5;
                    }
                    segundo.energia -= dano;
                    if (segundo.energia < 0) {
                        segundo.energia = 0;
                    }
                    System.out.println(primeiro.nome + " atacou " + segundo.nome + " causando " + dano + " de dano");
                    System.out.println("Energia de " + segundo.nome + ": " + segundo.energia);
                    if (segundo.energia == 0) {
                        primeiro.vitorias++;
                        primeiro.pontos += 3;
                        segundo.derrotas++;
                        System.out.println("Vencedor: " + primeiro.nome);
                        terminou = true;
                    } else {
                        dano = segundo.ataque - primeiro.defesa;
                        if (dano < 5) {
                            dano = 5;
                        }
                        if (i % 2 == 0) {
                            dano += 5;
                        }
                        primeiro.energia -= dano;
                        if (primeiro.energia < 0) {
                            primeiro.energia = 0;
                        }
                        System.out.println(segundo.nome + " atacou " + primeiro.nome + " causando " + dano + " de dano");
                        System.out.println("Energia de " + primeiro.nome + ": " + primeiro.energia);
                        if (primeiro.energia == 0) {
                            segundo.vitorias++;
                            segundo.pontos += 3;
                            primeiro.derrotas++;
                            System.out.println("Vencedor: " + segundo.nome);
                            terminou = true;
                        }
                    }
                }
                if (!terminou) {
                    if (primeiro.energia == segundo.energia) {
                        primeiro.pontos++;
                        primeiro.empates++;
                        segundo.pontos++;
                        segundo.empates++;
                        System.out.println("Empate");
                    } else if (primeiro.energia > segundo.energia) {
                        primeiro.pontos += 3;
                        primeiro.vitorias++;
                        segundo.derrotas++;
                        System.out.println("Vencedor: " + primeiro.nome);
                    } else {
                        segundo.pontos += 3;
                        segundo.vitorias++;
                        primeiro.derrotas++;
                        System.out.println("Vencedor: " + segundo.nome);
                    }
                }
                break;
            }
            case 4: {
                System.out.println();
                System.out.println("Recuperar energia");
                System.out.println("Insira o codigo do robo: ");
                int roboCodigo = buscarOperacao(s);
                int energiaRecarga = 0;
                do {
                    System.out.println("Insira quanto quer recupera: ");
                    energiaRecarga = buscarOperacao(s);
                    if (energiaRecarga == 0 || energiaRecarga % 10 != 0) {
                        System.out.println("A tem que ser maior que 10 e multiplo por 10");
                    }
                } while (energiaRecarga == 0 || energiaRecarga % 10 != 0);
                Robo roboRecarga = null;
                for (Robo r : robos) {
                    if (r.codigo == roboCodigo) {
                        roboRecarga = r;
                    }
                }
                if (roboRecarga == null) {
                    System.out.println("Robo não achado");
                    break;
                }
                if ((roboRecarga.energia + energiaRecarga) > 100) {
                    System.out.println("O limite é 100 de energia");
                    break;
                }
                int pontosAReduzir = energiaRecarga / 10;
                if (roboRecarga.pontos < pontosAReduzir) {
                    System.out.println("Robo não tem ponto suficiente");
                    break;
                }
                roboRecarga.energia += energiaRecarga;
                roboRecarga.pontos -= pontosAReduzir;
                System.out.println("Energia recuperada");
                break;
            }
            case 5: {
                System.out.println();
                System.out.println("Rodada geral");
                ArrayList<Robo> disponiveis = new ArrayList<>();
                int qtd = 0;
                for (Robo r : robos) {
                    if (r.energia >= 30) {
                        disponiveis.add(r);
                        qtd++;
                    }
                }
                if (qtd < 2) {
                    System.out.println("Não tem robos suficientes");
                    continue;
                }
                ArrayList<Robo> restantes = disponiveis;
                ArrayList<Robo> ordenados = new ArrayList<>();
                for (int k = 0; k < qtd; k++) {
                    Robo melhor = null;
                    for (Robo r : restantes) {
                        boolean maisForte = false;
                        if (melhor == null) {
                            maisForte = true;
                        } else if (r.pontos != melhor.pontos) {
                            maisForte = r.pontos > melhor.pontos;
                        } else if (r.vitorias != melhor.vitorias) {
                            maisForte = r.vitorias > melhor.vitorias;
                        } else if (r.energia != melhor.energia) {
                            maisForte = r.energia > melhor.energia;
                        } else {
                            maisForte = r.codigo < melhor.codigo;
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
                        System.out.println(esperando.nome + " x " + r.nome);
                        esperando = null;
                    }
                }
                if (esperando != null) {
                    esperando.pontos++;
                    System.out.println(esperando.nome + " recebeu 1 ponto de folga");
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
                        if (lutador1.pontos == lutador2.pontos) {
                            atacante = lutador1.codigo > lutador2.codigo ? lutador2 : lutador1;
                            defensor = lutador1.codigo > lutador2.codigo ? lutador1 : lutador2;
                        } else if (lutador1.pontos < lutador2.pontos) {
                            atacante = lutador1;
                            defensor = lutador2;
                        } else {
                            atacante = lutador2;
                            defensor = lutador1;
                        }
                        System.out.println();
                        System.out.println(atacante.nome + " x " + defensor.nome);
                        boolean acabou = false;
                        for (int i = 1; i <= 5 && !acabou; i++) {
                            int golpe = atacante.ataque - defensor.defesa;
                            if (golpe < 5) {
                                golpe = 5;
                            }
                            if (i % 2 == 0) {
                                golpe += 5;
                            }
                            defensor.energia -= golpe;
                            if (defensor.energia < 0) {
                                defensor.energia = 0;
                            }
                            if (defensor.energia == 0) {
                                atacante.vitorias++;
                                atacante.pontos += 3;
                                defensor.derrotas++;
                                System.out.println("Vencedor: " + atacante.nome);
                                acabou = true;
                            } else {
                                golpe = defensor.ataque - atacante.defesa;
                                if (golpe < 5) {
                                    golpe = 5;
                                }
                                if (i % 2 == 0) {
                                    golpe += 5;
                                }
                                atacante.energia -= golpe;
                                if (atacante.energia < 0) {
                                    atacante.energia = 0;
                                }
                                if (atacante.energia == 0) {
                                    defensor.vitorias++;
                                    defensor.pontos += 3;
                                    atacante.derrotas++;
                                    System.out.println("Vencedor: " + defensor.nome);
                                    acabou = true;
                                }
                            }
                        }
                        if (!acabou) {
                            if (atacante.energia == defensor.energia) {
                                atacante.pontos++;
                                atacante.empates++;
                                defensor.pontos++;
                                defensor.empates++;
                                System.out.println("Empate");
                            } else if (atacante.energia > defensor.energia) {
                                atacante.pontos += 3;
                                atacante.vitorias++;
                                defensor.derrotas++;
                                System.out.println("Vencedor: " + atacante.nome);
                            } else {
                                defensor.pontos += 3;
                                defensor.vitorias++;
                                atacante.derrotas++;
                                System.out.println("Vencedor: " + defensor.nome);
                            }
                        }
                    }
                }
                break;
            }
            default:
                System.out.println("Coloque uma das opção");
                break;
            }
        } while (opt != 0);
        s.close();
    }
    public static int buscarOperacao(Scanner s) {
        int opt = -1;
        do {
            try {
                opt = s.nextInt();
            } catch (InputMismatchException e) {
                s.next();
                System.out.println();
                System.out.println("Não tem isso ai");
                System.out.println("Digite novamente a informação");
                opt = -1;
            }
        } while (opt < 0);
        return opt;
    }
}