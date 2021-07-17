package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.lang.Integer;
import compute.Compute;

public class ComputeFibonacci {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0],Integer.parseInt(args[1]));
            Compute comp = (Compute) registry.lookup(name);
        
            // pegando o N referente ao numero o qual calcularemos fibonnaci
            int n = Integer.valueOf(args[2]);
            if(n >= 0){
                System.out.println("\nSolicitando o servico Fibonacci ao servidor...");
                //criando a task Fibonacci e executando
                Fibonacci task = new Fibonacci(n);
                Integer fibonacci_number = comp.executeTask(task);

                //mostrando a resposta do servi;co
                System.out.println("\nResposta recebida do servico Fibonacci: ");
                System.out.println("Numero de Fibonacci para n = " + n + ": " + fibonacci_number);
            
            }
            else{
                throw new IllegalArgumentException("\n N tem que ser maior ou igual a 0 \n");
            }
        } catch (Exception e) {
            System.err.println("\nErro ao executar ComputeFibonacci:");
            e.printStackTrace();
        }
    }    
}

