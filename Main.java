package fibcode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        // Valores de entrada
        int start = 0; // Valor inicial de n
        int end = 50; // Valor final de n
        int step = 1; // Incremento de n

        try {
            FileWriter csvFile = new FileWriter("fibonacci_times.csv");
            PrintWriter csvWriter = new PrintWriter(csvFile);

            csvWriter.println("Input; Time(s)RECURSIVE; Time(s)ITERATIVE");

            for (int inputValue = start; inputValue <= end; inputValue += step) {
                long initialTime = System.nanoTime(); // Início da contagem

                fibonacciRecursive(inputValue);

                long finalTime = System.nanoTime(); // Fim da contagem
                long execTime = finalTime - initialTime; // Cálculo do tempo de execução
                double seconds = (double) execTime / 1_000_000_000.0; // Conversão para segundos

                // Conversão para 3 casas decimais
                double fator = Math.pow(10, 3); 
                seconds = Math.round(seconds * fator) / fator; 

                csvWriter.print(inputValue + ";" + seconds);

                initialTime = System.nanoTime();

                fibonacciIterative(inputValue);

                finalTime = System.nanoTime();
                execTime = finalTime - initialTime;
                seconds = (double) execTime / 1_000_000_000;
                
                seconds = Math.round(seconds * fator) / fator;

                csvWriter.println(";" + seconds);
            }
            
            csvWriter.close();
            csvFile.close();

            System.out.println("Dados salvos em 'fibonacci_times.csv'");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int fibonacciRecursive(int n) {
        if(n == 0) {return 0;}

        else if(n == 1) {return 1;}

        else {
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
    }

    private static int fibonacciIterative(int n) {
        if(n == 0) {return 0;}

        else if(n ==1) {return 1;}

        else {
            int value = 0;
            int nextValue = 1;
            int result = 0;
            
            for (int i = 2; i <= n; i++) {
                result = value + nextValue;
                value = nextValue;
                nextValue = result;
            }
            return result;
        }
    }
}
