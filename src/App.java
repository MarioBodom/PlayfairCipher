/**
 * Aplicación principal del proyecto de cifrado Playfair.
 * Creada y escrita por Mario de Santiago
 */

// Hacemos los importes de las utilidades que necesitamos.
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        /**Creamos un pequeño menú para poder manejar las encriptaciones y decriptaciones.
         * Lo primero se pide una fecha, ya que el fichero donde se almacenan están guardadas con el formato 'fecha'
         * y despues la clave. Según el día que se indique al inicio se usará una clave diferente.
         */
        boolean juego = true;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println(
                    "Dime la fecha de la clave que quieres utilizar (el formato es dd/mm/yyyy):\n3 o salir para cerrar");
            // Creamos el objeto 'reader' de la clase lector, definida al final del documento para poder leer del 
            // archivo de claves
            Lector reader = new Lector();
            // Nos guarda cada línea en un array de strings
            String[] clavesFichero = reader.leer().split("\n");
            String clave = "";
            // Guardamos la primera entrada por teclado, para poder salir del programa o avanzar
            String choice1 = sc.nextLine();
            if (choice1.toLowerCase().equals("salir") || choice1.equals("3")) {
                System.out.println("Gracias por usar el programa");
                juego = false;
                break;
            } else {
                /**Parseamos la fecha que nos meten por teclado, para convertirlo en un objeto tipo fecha
                 * Después comparamos cada una de las fechas que hay en el array 'clavesFichero' donde de nuevo
                 * parseamos la entrada para convertirla en tipo fecha y compararla.
                 * Si la fecha coincide todo lo demás que haya en la posición del array que estemos comprobando
                 * se convierte en la clave.
                 * Hacemos un substring desde la posición 11, que es después de la fecha y el espacio que hay
                 */
                LocalDate fechaPedida = LocalDate.parse(choice1, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                System.out.println(fechaPedida);
                for (int i = 0; i < clavesFichero.length; i++) {
                    LocalDate fechaFichero = LocalDate.parse(clavesFichero[i].split(" ")[0],
                            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    if (fechaPedida.equals(fechaFichero)) {
                        clave = clavesFichero[i].substring(11).trim();
                        break;
                    }
                }
            }
            // Imprimimos la clave para comprobar que es la que queremos utilizar
            System.out.println("La clave es: " + clave);
            // Creamos la matriz a partir de la clave y la imprimimos.
            Matriz m1 = new Matriz(clave);
            System.out.println("La matriz es:");
            m1.toString();
            /**Preguntamos la siguiente opción, cifrar, descifrar o cerrar el programa
             * En caso de que se introduzca otra cosa diferente vuelve al principio, donde nos pide la fecha.
             */
            Scanner scMensaje = new Scanner(System.in);
            System.out.println("Que quieres hacer:\n1. Cifrar\n2. Descifrar\n3. Salir");
            String opcion = scMensaje.next().toLowerCase();
            switch (opcion) {
                case "1", "cifrar":
                    m1.cifrarMensaje();
                    break;
                case "2", "descifrar":
                    m1.descifrar();
                    break;
                case "3", "salir":
                    juego = false;
                    break;
                default:
                    System.out.println("La elección no es correcta");
                    break;
            }
        } while (juego);
    }
}

/**
 * Lector
 * Esta clase nos sirve para poder leer del fichero de 'claves.txt'
 * Es una clase abstracta, que solo tiene el método leer que devuelve un string.
 */
class Lector {

    public String leer() {
        String frase = "";
        try {
            FileReader file = new FileReader("src/claves.txt");
            int caracter = file.read();
            while (caracter != -1) {
                char letra = (char) caracter;
                frase += letra;
                caracter = file.read();
            }
            file.close();

        } catch (FileNotFoundException e) {
            // TODO: handle exception
            System.out.println("El fichero no se ha encontrado");
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return frase;
    }
}