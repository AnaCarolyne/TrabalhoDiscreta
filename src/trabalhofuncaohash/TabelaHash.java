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
public class TabelaHash {

    public static boolean direcao = true;
    Elemento[] hash;
    int size;
    int incremento;

    TabelaHash() {
    }

    TabelaHash(int size, int incremento) {
        this.size = size;
        this.incremento = incremento;
        this.hash = new Elemento[size];
    }

    public void imprimir() {
        String fita = "";
        for (int i = 0; i < size; i++) {
            if (hash[i] != null) {
                fita += "[" + hash[i].valor + "]";
            } else {
                fita += "[ ]";
            }
        }
        System.out.println("Hash: " + fita);
    }

    public int mod(int chave) {
        return chave % size;
    }

    public void insertItem(Elemento valor) {
        int atual = 0;
        int mod = mod(valor.chave);
        int colisoes = 0;
        if (mod < size) {
            if (hash[mod] == null) {
                hash[mod] = valor;
            } else {
                while (inserir(atual, mod, valor)) {
                    colisoes++;
                    if (direcao) {
                        atual = atual + incremento;
                    }
                }
                System.out.println("Colisões: " + colisoes);
            }
        } else {
            System.out.println("Não existe uma posiçao apropriada! Aumente a tabela Hash...");
        }
    }

    public boolean inserir(int atual, int mod, Elemento valor) {
        if (direcao) {
            int target = mod - atual;
            if (target < 0) {
                if (hash[size + target] == null) {
                    hash[size + target] = valor;
                    return false;
                }
            }
            if (target >= 0) {
                if (hash[target] == null) {
                    hash[target] = valor;
                    return false;
                }
            }
            direcao = false;
        } else {
            int target = mod + atual;
            if (target > size) {
                if (hash[target - size] == null) {
                    hash[target - size] = valor;
                    return false;
                }
            }
            if (target <= size) {
                if (hash[target] == null) {
                    hash[target] = valor;
                    return false;
                }
            }
            direcao = true;
        }
        return true;
    }

    public void clearItem(Elemento no) {
        int atual = 0;
        int colisoes = 0;
        int mod = mod(no.chave);
        if (hash[mod] != null) {
            if (hash[mod].valor == no.valor) {
                hash[mod] = null;
                reOrdenar();
            } else {
                while (deletar(atual, mod, no)) {
                    colisoes++;
                    if (direcao) {
                        atual = atual + incremento;
                    }
                }
                System.out.println("Item " + no.valor + " deletado... " + colisoes + " colisões.");
            }
        } else {
            System.out.println("Nó inexistente.");
        }
    }

    public boolean deletar(int atual, int mod, Elemento valor) {
        if (direcao) {
            int target = mod - atual;
            if (target < 0) {
                if (hash[size + target] != null) {
                    if (hash[size + target].valor == valor.valor) {
                        System.out.println("#" + hash[size + target].valor);
                        System.out.println(valor.valor);
                        System.out.println("1");
                        hash[size + target] = null;
                        reOrdenar();
                        return false;
                    }
                }
            }
            if (target >= 0) {
                if (hash[target] != null) {
                    if (hash[target].valor == valor.valor) {
                        System.out.println("2");
                        hash[target] = null;
                        reOrdenar();
                        return false;
                    }
                }
            }
            direcao = false;
        } else {
            int target = mod + atual;
            if (target > size) {
                if (hash[target - size] != null) {
                    if (hash[target - size].valor == valor.valor) {
                        System.out.println("3");
                        hash[target - size] = null;
                        reOrdenar();
                        return false;
                    }
                }
            }
            if (target <= size) {
                if (hash[target] != null) {
                    if (hash[target].valor == valor.valor) {
                        System.out.println("4");
                        hash[target] = null;
                        reOrdenar();
                        return false;
                    }
                }
            }
            direcao = true;
        }
        return true;
    }

    public void reOrdenar() {
        Elemento[] clone = hash.clone();
        limpar();
        for (int i = 0; i < size; i++) {
            if (clone[i] != null) {
                insertItem(clone[i]);
            }
        }
    }

    public void limpar() {
        for (int i = 0; i < size; i++) {
            hash[i] = null;
        }
    }

    public Elemento buscarByIndice(int indice) {
        int hash1 = mod(indice);
        while (hash[hash1] != null) {
            return hash[hash1];
        }
        return null;
    }

    public int buscarByNode(int item) {
        for (int i = 0; i < this.size; i++) {
            if (hash[i] != null) {
                if (hash[i].valor == item) {
                    return i;
                }
            }
        }
        return -1;
    }
}
