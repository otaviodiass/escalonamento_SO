
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        LinkedList<Processo> fila = new LinkedList<>();

        System.out.print("Qual é a quantidade de processos? ");
        int N = teclado.nextInt();

        for (int i = 0; i < N; i++) {
            System.out.print("Informe o tempo do P" + i + ": ");
            int temp = teclado.nextInt();
            System.out.print("Informe a prioridade do P" + i + ": ");
            int prioridade = teclado.nextInt();
            System.out.print("Interrupção P" + i + ": ");
            char resp = teclado.next().charAt(0);
            int parada, tempParada;
            if (resp == 's') {
                System.out.print("Informe o tempo de parada: ");
                parada = teclado.nextInt();
                System.out.print("Quantidade de tempo da interrupção: ");
                tempParada = teclado.nextInt();
                fila.add(new Processo(i, temp, prioridade, parada, tempParada));
            } else {
                fila.add(new Processo(i, temp, prioridade));
            }
        }

        System.out.print("Informe o quantum se hover: ");
        int quantum = teclado.nextInt();

        System.out.println();
        FCFS(fila);
        System.out.println();
        SJF(fila);
        System.out.println();
        Duling(fila);
        System.out.println();
        RR(fila, quantum);

        teclado.close();

    }

    public static void FCFS(LinkedList<Processo> list) {
        System.out.println("FCFS");
        int tempoTotal = 0;
        try {
            for (int i = 0; i < list.size(); i++) {
                int aux = list.get(i).getTempo() * 1000;
                tempoTotal += aux;
                System.out.println("P" + list.get(i).getId());
                Thread.sleep(aux);
            }
        } catch (InterruptedException ex) {
            System.out.println("erro");
        }
        System.out.println("Tempo total da execução: " + tempoTotal / 1000 + " segundos");
    }

    public static void SJF(LinkedList<Processo> list) {
        System.out.println("SJF");
        int tempoTotal = 0;
        Collections.sort(list, Comparator.comparing(Processo::getTempo));
        try {
            for (Processo processo : list) {
                int aux = processo.tempo * 1000;
                tempoTotal += aux;
                System.out.println("P" + processo.getId());
                Thread.sleep(aux);
            }
        } catch (InterruptedException e) {
            System.out.println("erro");
        }
        System.out.println("Tempo total da execução: " + tempoTotal / 1000 + " segundos");
    }

    public static void Duling(LinkedList<Processo> list) {
        System.out.println("Duling");
        int tempoTotal = 0;
        Collections.sort(list, Comparator.comparing(Processo::getPrioridade));
        try {
            for (Processo processo : list) {
                int aux = processo.tempo * 1000;
                System.out.println("P" + processo.getId());
                Thread.sleep(aux);
            }
        } catch (InterruptedException e) {
            System.out.println("erro");
        }
        System.out.println("Tempo total da execução: " + tempoTotal / 1000 + " segundos");
    }

    public static void RR(LinkedList<Processo> list, int quantum) {
        System.out.println("Round robin");

        int contadorFila = list.size() - 1;
        int primeiraPosicao = 0;

        while (contadorFila != 0) {
            while (list.get(primeiraPosicao).getTempo() <= 0) {
                primeiraPosicao++;
                if (primeiraPosicao >= contadorFila)
                    primeiraPosicao = 0;
            }
            System.out.println("O processo " + primeiraPosicao + " tem " + list.get(primeiraPosicao).getTempo());
            System.out.println("Executa " + quantum);
            try {
                if (list.get(primeiraPosicao).getTempo() < quantum) {
                    Thread.sleep(list.get(primeiraPosicao).getTempo());
                } else {
                    Thread.sleep(quantum);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int novoTempo = list.get(primeiraPosicao).getTempo() - quantum;
            list.get(primeiraPosicao).setTempo(novoTempo);
            if (list.get(primeiraPosicao).getTempo() <= 0) {
                System.out.println("Saiu da fila");
                contadorFila--;
            } else {
                System.out.println(
                        "Vai para o final da fila com " + list.get(primeiraPosicao).getTempo() + " segundos restantes");
            }
            primeiraPosicao++;
            if (primeiraPosicao > contadorFila)
                primeiraPosicao = 0;
        }
    }

}
