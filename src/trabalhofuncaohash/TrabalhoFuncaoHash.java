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
public class TrabalhoFuncaoHash {
    public static ArrayList<Node> tabela = new ArrayList<Node>();

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int indice = 0;
        int item = 0;
        //Inicializa Hash e Tabela de Itens
        HashTable hash = init();
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
                    item = input.nextInt();
                    Node elemento = buscarNo(item);
                    if (elemento != null) {
                        hash.insertItem(elemento);
                    } else {
                        System.out.println("Valor não encontrado...");
                    }
                    break;
                case 2:
                    System.out.println("*************** Deletar ***************");
                    System.out.println("Deletar valor:");
                    item = input.nextInt();
                    Node target = buscarNo(item);
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
                        item = input.nextInt();
                        Node no = hash.buscarByIndice(item);
                        if (no != null) {
                            System.out.println("Encontrado " + no.item + " no indice " + no.indice);
                        } else {
                            System.out.println("Indice vazio...");
                        }
                    }
                    if (escolha == 2) {
                        System.out.println("Buscar o Nó:");
                        item = input.nextInt();
                        indice = hash.buscarByNode(item);
                        if (indice != -1) {
                            System.out.println("\nEcontrado " + item + " no indice " + indice);
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

    public static HashTable init() {
        Scanner input = new Scanner(System.in);

        System.out.println("*************** Linear Pobring (Bi-direcional)***************");
        System.out.println("Escolha a quantidade de entradas: ");
        int size = input.nextInt();
        tabela = new ArrayList<Node>(size);
        preencherTabela(size);

        System.out.println("Escolha o tamanho da Tabela Hash: ");
        size = input.nextInt();
        System.out.println("Escolha o incremento: ");
        int incremento = input.nextInt();
        return new HashTable(size, incremento);
    }

    public static void imprimeTabela() {
        String fita = "";
        String posicoes = "";
        for (int i = 0; i < tabela.size(); i++) {
            posicoes += "-" + i + "-";
            fita += "[" + tabela.get(i).item + "]";
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
            tabela.add(new Node(value, i));
            fita += "[" + value + "]";
        }
        System.out.println(fita + "\n");
    }

    public static int buscarIndice(int item) {
        for (int i = 0; i < tabela.size(); i++) {
            Node no = tabela.get(i);
            if (no != null) {
                if (no.item == item) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static Node buscarNo(int item) {
        for (int i = 0; i < tabela.size(); i++) {
            Node no = tabela.get(i);
            if (no != null) {
                if (no.item == item) {
                    return no;
                }
            }
        }
        return null;
    }
}
