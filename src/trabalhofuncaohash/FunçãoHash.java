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
        char valor = 0;
        TabelaHash hash = criaTabela();

        int opcao = 10;
        do {
            hash.imprimir();
            System.out.println("1-Inserção 2-Deleçao 3-Busca 4-Impressão N>4-Cancelar");
            opcao = input.nextInt();
            switch (opcao) {
                //Inserir
                case 1:
                    imprimeTabela();
                    System.out.println("Inserir:");
                    valor = input.next().charAt(0);
                    Elemento elemento = buscarElemento(valor);
                    if (elemento != null) {
                        hash.insertItem(elemento);
                    } else {
                        System.out.println("Valor não existe...");
                    }
                    break;
                case 2:
                    System.out.println("Deletar:");
                    valor = input.next().charAt(0);
                    Elemento target = buscarElemento(valor);
                    if (target != null) {
                        hash.clearItem(target);
                    } else {
                        System.out.println("Valor não Existe...");
                    }
                    break;
                case 3:
                    System.out.println("Buscar:");
                    valor = input.next().charAt(0);
                    chave = hash.buscarByNode(valor);
                    if (chave != -1) {
                        System.out.println("\n"+valor + " na posição:" + chave);
                    } else {
                        System.out.println("\nValor não encontrado...");
                    }
                    break;
                case 4:
                    hash.imprimir();
                    break;
                default:
                    break;
            }
        } while (opcao < 5);
    }

    public static TabelaHash criaTabela() {
        int size = 0;
        Scanner input = new Scanner(System.in);
        criaEntradas();
        System.out.println("Tamanho do Hash?");
        size = input.nextInt();
        System.out.println("Escolha o incremento: ");
        int incremento = input.nextInt();
        return new TabelaHash(size, incremento);
    }

    public static void imprimeTabela() {
        String fita = "";
        for (int i = 0; i < tabela.size(); i++) {
            fita += "[" + tabela.get(i).valor + "]";
        }
        System.out.println("Qual deseja inserir?");
        System.out.println(fita);
    }

    public static void criaEntradas() {
        String fita = "";
        int value = 0;
        int i = 0;
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            tabela.add(new Elemento(ch, i));
            fita += "[" + ch + "]";
            i++;
        }
        System.out.println(fita + "\n");
    }

    public static Elemento buscarElemento(char valor) {
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
