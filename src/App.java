import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
// import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indica la fecha de la clave");
        Lector reader = new Lector();
        String[]clavesFichero = reader.leer().split("\n");
        String claveFichero = "";
        LocalDate fechaPedida = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println(fechaPedida);
        for (int i = 0; i < clavesFichero.length; i++) {
            LocalDate fechaFichero = LocalDate.parse(clavesFichero[i].split(" ")[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (fechaPedida.equals(fechaFichero)) {
                claveFichero = clavesFichero[i].substring(11).trim();
                break;
            }
        }

        String clave = claveFichero;
        System.out.println(clave);
        Matriz m1 = new Matriz(clave);

        m1.toString();
        m1.cifrarMensaje();
        m1.descifrar();
        

    }
}

/**
 * Lector
 */
class Lector {
    
    public String leer() {
        String frase = "";
        try {
            FileReader file = new FileReader("src/claves.txt");
            int caracter = file.read();
            while (caracter != -1) {
                char letra = (char)caracter;
                frase += letra;
                caracter = file.read();
            }
            file.close();
            
        } catch (FileNotFoundException e) {
            //TODO: handle exception
            System.out.println("El fichero no se ha encontrado");
        } catch (IOException e){
            System.out.println("IOException");
        }
        return frase;
    }
}