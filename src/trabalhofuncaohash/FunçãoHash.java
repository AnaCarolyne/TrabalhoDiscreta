/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofuncaohash;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anacarolyne.franca
 */
public class FunçãoHash {

    public static ArrayList<Elemento> tabela = new ArrayList<Elemento>();

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int chave = 0;
        int valor = 0;
        //Inicializa Hash e Tabela de Itens
        TabelaHash hash = init();
        //Menu
        int escolha = 10;
        do {
            System.out.println("\n*************** Operações Hash ***************");
            hash.imprimir();
            System.out.println("1 - Inserir     2 - Deletar     3 - Buscar ");
            System.out.println("4 - Imprimir    5 - Sair");
            escolha = input.nextInt();
            System.out.println("");
            switch (escolha) {
                //Inserir
                case 1:
                    System.out.println("*************** Inserção ***************");
                    imprimeTabela();

                    System.out.println("Inserir item:");
                    valor = input.nextInt();
                    Elemento elemento = buscarNo(valor);
                    if (elemento != null) {
                        hash.insertItem(elemento);
                    } else {
                        System.out.println("Valor não encontrado...");
                    }
                    break;
                case 2:
                    System.out.println("*************** Deletar ***************");
                    System.out.println("Deletar valor:");
                    valor = input.nextInt();
                    Elemento target = buscarNo(valor);
                    if (target != null) {
                        hash.clearItem(target);
                    } else {
                        System.out.println("Entrada inválida.");
                    }
                    break;
                case 3:
                    System.out.println("Buscar: 1- Por Indice       2- Por Nó");
                    escolha = input.nextInt();
                    if (escolha == 1) {
                        System.out.println("Buscar o Indice:");
                        valor = input.nextInt();
                        Elemento no = hash.buscarByIndice(valor);
                        if (no != null) {
                            System.out.println("Encontrado " + no.valor + " no indice " + no.chave);
                        } else {
                            System.out.println("Indice vazio...");
                        }
                    }
                    if (escolha == 2) {
                        System.out.println("Buscar o Nó:");
                        valor = input.nextInt();
                        chave = hash.buscarByNode(valor);
                        if (chave != -1) {
                            System.out.println("\nEcontrado " + valor + " no indice " + chave);
                        } else {
                            System.out.println("\nValor não encontrado...");
                        }
                    }
                    break;
                case 4:
                    hash.imprimir();
                    break;
                case 5:
                    Runtime.getRuntime().exec("taskkill /F /IM <processname>.exe");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (escolha != 5);
    }

    public static TabelaHash init() {
        Scanner input = new Scanner(System.in);

        System.out.println("*************** Linear Pobring (Bi-direcional)***************");
        System.out.println("Escolha a quantidade de entradas: ");
        int size = input.nextInt();
        tabela = new ArrayList<Elemento>(size);
        preencherTabela(size);

        System.out.println("Escolha o tamanho da Tabela Hash: ");
        size = input.nextInt();
        System.out.println("Escolha o incremento: ");
        int incremento = input.nextInt();
        return new TabelaHash(size, incremento);
    }

    public static void imprimeTabela() {
        String fita = "";
        String posicoes = "";
        for (int i = 0; i < tabela.size(); i++) {
            posicoes += "-" + i + "-";
            fita += "[" + tabela.get(i).valor + "]";
        }
        System.out.println("-Chaves-   [Valores]");
        System.out.println(posicoes);
        System.out.println(fita);
    }

    public static void preencherTabela(int size) {
        int value = 0;
        Scanner input = new Scanner(System.in);

        String fita = "";
        System.out.println("Progressão para entradas:");
        int prog = input.nextInt();
        for (int i = 0; i < size; i++) {
            value = value + prog;
            tabela.add(new Elemento(value, i));
            fita += "[" + value + "]";
        }
        System.out.println(fita + "\n");
    }

    public static int buscarIndice(int valor) {
        for (int i = 0; i < tabela.size(); i++) {
            Elemento no = tabela.get(i);
            if (no != null) {
                if (no.valor == valor) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static Elemento buscarNo(int valor) {
        for (int i = 0; i < tabela.size(); i++) {
            Elemento no = tabela.get(i);
            if (no != null) {
                if (no.valor == valor) {
                    return no;
                }
            }
        }
        return null;
    }
}
