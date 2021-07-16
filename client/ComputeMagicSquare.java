package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.lang.Integer;
import compute.Compute;

public class ComputeMagicSquare {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0],Integer.parseInt(args[1]));
            Compute comp = (Compute) registry.lookup(name);
        
            //Um "Magic Square" é uma matriz de dimensão N >= 3 onde a soma de todas as linhas, colunas e diagonais é a mesma. Ex: N = 3, soma = 15.
            // 2   7   6
            // 9   5   1
            // 4   3   8
            //Receberemos apenas o N na linha de comando e o Magic Square será gerado e exibido!

            //pegando o N do magic square, de modo que N >= 3
            int n = Integer.valueOf(args[2]);
            if(n >= 3){
             	System.out.println("\nSolicitando o servico Magic Square ao servidor...");
                //criando a task Magic Square e executando
                MagicSquare task = new MagicSquare(n);
                Vector<Vector<Integer>> magic_square = comp.executeTask(task);

                //mostrando o magic square
                System.out.println("\nResposta recebida do servico Magic Square: ");
                System.out.println("Magic Square para N = " + n + ":");
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < n; j++){
                    	System.out.format("%5d", magic_square.get(i).get(j));
                    }
                    System.out.println();
                }
                System.out.println("Soma de cada linha/coluna/diagonal = " + n*(n*n+1)/2 + "\n");
            }
            else{
                throw new IllegalArgumentException("\n'N' tem que ser maior ou igual a 3!");
            }
        } catch (Exception e) {
            System.err.println("\nErro ao executar ComputeMagicSquare:");
            e.printStackTrace();
        }
    }    
}

