import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Queue_String {
    private static class Node {
        public String data;
        public Node next;
    }

    private static Node start;
    private static Node end;

    private static class  Return {
        public String item;
        public boolean ok;
    }

    public void init() {
        start = null;
        end=null;
    }

    public boolean isEmpty() {
        return (start == null && end ==null);
    }

    public Return first() {
        Return output = new Return();
        if (!isEmpty()) {
            output.item = start.data;
            output.ok = true;
        } else {
            output.ok = false;
        }

        return output;
    }

    public void enqueue(String item) {
        Node newNode = new Node();
        newNode.data = item;
        newNode.next = null;
        if (isEmpty()) {
            start = newNode;
        } else {
            end.next = newNode;
        }
        end = newNode;
    }

    public Return dequeue() {
        Return output = new Return();

        if (!isEmpty()) {
            output.item = start.data;
            start = start.next;
            if (start == null) end = null;
            output.ok = true;
        } else {
            output.ok = false;
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        Queue_String queue = new Queue_String();
        Return res = new Return();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        int option;

        queue.init();

        do {
            System.out.println("1 - Insere cliente na fila de espera");
            System.out.println("2 - Chama cliente para atendimento");
            System.out.println("3 - Lista o primeiro paciente da fila");
            System.out.println("4 - Sair (apenas se não houver mais cliente na fila)");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Digite o nome do paciente ");
                    String item = in.readLine();
                    queue.enqueue(item);
                    break;
                case 2:
                    res = queue.dequeue();
                    if (res.ok) {
                        System.out.println("Cliente chamado: " + res.item);
                    }
                    break;
                case 3:
                    res = queue.first();
                    if (res.ok) {
                        System.out.println("Primeiro da fila: " + res.item);
                    } else  {
                        System.out.println("Não há mais clientes na fila");
                    }
                    break;
                case 4:
                    if (!queue.isEmpty()) {
                        option = 5;
                        System.out.println("Não pode encerrar, pois há clientes na fila");
                    }
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (option != 4);
        sc.close();
    }
}
