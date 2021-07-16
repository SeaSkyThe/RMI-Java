package client;

import compute.Task;
import java.io.Serializable;
import java.util.*;
import java.lang.Integer;

public class MagicSquare implements Task<Vector<Vector<Integer>>>, Serializable {

    private static final long serialVersionUID = 227L;

    private final int n;
    public MagicSquare(int n) {
        this.n = n;
    }

	//Chama a funcao que faz o magic square
    public Vector<Vector<Integer>> execute() {
        return computeMagicSquare(n);
    }
    // Aqui o magicsquare é criado
    // Logica do magicSquare:
    // As posições são lidas como (i, j)
    // O primeiro numero é sempre guardado na posição (n/2, n-1). O próximo número a partir do primeiro vai ser sempre na posição (i - 1, j + 1)
    // Ou seja, basta decrementar a linha do número anterior por 1 e incrementar a coluna em 1.
    // 1. Caso, a linha calculada se torne -1, nós transformamos em n-1. Se a coluna calculada se tornar n, transformamos em 0.
    // 2. Caso já tenha um número preenchendo a posição calculada, decrementamos a coluna em 2 ao invés de 1, e a linha será incrementada em 1 ao invés de decrementada.
    // 3. Caso a linha calculada se torne -1 e a posição da coluna seja n, a nova posição sera (0, n-2)
    public Vector<Vector<Integer>> computeMagicSquare(int n) {
        Vector<Vector<Integer>> magic_square = new Vector<Vector<Integer>>(n);

        //inicializando o magic_square com 0 em todas as posicoes
        Collections.nCopies(n, new Vector<Integer>(Collections.nCopies(n, 0))).forEach((list) -> magic_square.add(new Vector<Integer>(list)));
        
        //se for de ordem impar
        if(n%2 != 0){
            //inicializando posições para o 1 (n/2, n - 1)
            int i = n/2;
            int j = n - 1;

            Vector<Integer> temp;
            //colocando os valores no magic square
            for(int num_atual = 1; num_atual <= n*n; ){
                //condição 3.
                if(i == -1 && j == n){
                    j = n - 2;
                    i = 0;
                }
                //condição 1.
                else{
                    if(j == n){
                        j = 0;
                    }
                    if(i < 0){
                        i = n - 1;
                    }
                }
                //condicao 2.
                if(magic_square.get(i).get(j) != 0){
                    j -= 2;
                    i++;
                    continue;
                }
                else{
                    //pega linha
                    temp = magic_square.get(i);
                    //remove elemento da coluna especifica
                    temp.remove(j);
                    //adiciona o elemento novo
                    temp.add(j, num_atual++);

                    //remove a linha do original e insere a nova
                    magic_square.remove(i);
                    magic_square.add(i, temp);
                }
                //condicao base
                j++;
                i--;
            }
        }
        //ordem multiplo de 4
        else if(n%4 == 0){
            Vector<Integer> linha_temp;
            int temp2;
            //preenchendo a matriz com os valores na ordem de contagem
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    //removendo a linha, editando e recolocando
                    linha_temp = magic_square.get(i);
                    magic_square.remove(i);
                    //editando linha
                    linha_temp.remove(j);
                    linha_temp.add(j, (n*i) + j + 1);
                    //adicionando linha de volta
                    magic_square.add(i, linha_temp);
                }
            }

            //editando valores de cada canto da matriz, baseando-se na regra:
            // (n*n+1) - arr[i][j]
            //canto superior esquerdo
            for(int i = 0; i < n/4; i++){
                for(int j = 0; j < n/4; j++){
                    //removendo a linha, editando e recolocando
                    linha_temp = magic_square.get(i);
                    magic_square.remove(i);
                    //editando linha
                    temp2 = linha_temp.get(j);
                    linha_temp.remove(j);
                    linha_temp.add(j, (n*n + 1) - temp2);
                    //adicionando linha de volta
                    magic_square.add(i, linha_temp);
                }
            }
            //canto superior direito
            for(int i = 0; i < n/4; i++){
                for(int j = 3*(n/4); j < n; j++){
                    //removendo a linha, editando e recolocando
                    linha_temp = magic_square.get(i);
                    magic_square.remove(i);
                    //editando linha
                    temp2 = linha_temp.get(j);
                    linha_temp.remove(j);
                    linha_temp.add(j, (n*n + 1) - temp2);
                    //adicionando linha de volta
                    magic_square.add(i, linha_temp);
                }
            }
            //canto inferior esquerdo
            for(int i = 3*(n/4); i < n; i++){
                for(int j = 0; j < n/4; j++){
                    //removendo a linha, editando e recolocando
                    linha_temp = magic_square.get(i);
                    magic_square.remove(i);
                    //editando linha
                    temp2 = linha_temp.get(j);
                    linha_temp.remove(j);
                    linha_temp.add(j, (n*n + 1) - temp2);
                    //adicionando linha de volta
                    magic_square.add(i, linha_temp);
                }
            }

            //canto inferior direito
            for(int i = 3*(n/4); i < n; i++){
                for(int j = 3*(n/4); j < n; j++){
                    //removendo a linha, editando e recolocando
                    linha_temp = magic_square.get(i);
                    magic_square.remove(i);
                    //editando linha
                    temp2 = linha_temp.get(j);
                    linha_temp.remove(j);
                    linha_temp.add(j, (n*n + 1) - temp2);
                    //adicionando linha de volta
                    magic_square.add(i, linha_temp);
                }
            }
            
            //centro da matriz
            for(int i = n/4; i < 3*(n/4); i++){
                for(int j = n/4; j < 3*(n/4); j++){
                    //removendo a linha, editando e recolocando
                    linha_temp = magic_square.get(i);
                    magic_square.remove(i);
                    //editando linha
                    temp2 = linha_temp.get(j);
                    linha_temp.remove(j);
                    linha_temp.add(j, (n*n + 1) - temp2);
                    //adicionando linha de volta
                    magic_square.add(i, linha_temp);
                }
            }
        }
        //ordem do tipo (4*n + 2) - 6, 10, 14 - singly even order - TERMINAR
        
        return magic_square;
    }
 
}
