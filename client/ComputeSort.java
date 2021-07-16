package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.lang.Integer;
import compute.Compute;

public class ComputeSort {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0],Integer.parseInt(args[1]));
            Compute comp = (Compute) registry.lookup(name);

            //É entrado como input o nome do algoritmo (bubblesort, insertionsort, selectionsort) e a cadeia de numeros a ser ordenada, 
            //como ultimo argumento basta colocar a letra "e""que irá indicar o fim da cadeia de numeros

            //criando o array de numeros a partir dos argumentos para enviar para o calculo
            Vector<Integer> number_list = new Vector<Integer>();
            String algorithm = args[2].toLowerCase();

            if(algorithm.equals("selectionsort") || algorithm.equals("insertionsort") || algorithm.equals("bubblesort")){
                int i = 3; // a partir do argumento 3 - pois o argumento 2 é o algoritmo a ser utilizado 
                while(!args[i].equals("e")){ // a condição de parada é o caractere e de "end" que tem que ser enviado apos a sequencia de numeros
                    number_list.add(Integer.valueOf(args[i]));
                    i += 1;
                }
                
                System.out.println("\nSolicitando o servico de Sort (ordenacao) ao servidor...");
                //criando e chamando a task de ordenacao
                Sort task = new Sort(algorithm, number_list);
                Vector<Integer> sorted_list = comp.executeTask(task);

                //mostrando a lista ordenada na tela
                System.out.print("Resposta recebida do servico de Sort: ");
                System.out.print("\nLista ordenada: ");
                for(int j = 0; j < sorted_list.size(); j++){
                    System.out.print(sorted_list.get(j) + " ");
                }
                System.out.println();  
            }
            else{
                throw new IllegalArgumentException("\nVoce escolheu um algoritmo indisponivel, os validos sao:\n- SelectionSort\n- InsertionSort\n- BubbleSort");
            }
            

        } catch (Exception e) {
            System.err.println("\nErro ao executar ComputeSort:");
            e.printStackTrace();
        }
    }    
}

