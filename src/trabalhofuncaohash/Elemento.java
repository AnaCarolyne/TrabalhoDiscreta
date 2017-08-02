/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofuncaohash;

/**
 *
 * @author anacarolyne.franca
 */
public class Elemento {

    int valor;
    int chave;

    public Elemento(int valor, int chave) {
        this.valor = valor;
        this.chave = chave;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor= valor;
    }
}
