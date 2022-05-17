import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indica la palabra clave");
        String clave = sc.nextLine();
        Matriz m1 = new Matriz(clave);

        m1.toString();

    }
}
