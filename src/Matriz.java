/**Clase Matriz para poder crear la matriz donde metemos las letras y la clave.
 * Esta clase también se encarga de cifrar, descifrar los mensajes y guardar los mensajes en el fichero resultados.txt
 * Creada y escrita por Mario de Santiago
 */

 // Hacemos los importes de las utilidades que necesitamos.
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Matriz {
    /**Comenzamos a declarar las variables de la clase Matriz.
     * Esta tiene un tablero, que es un array bidimensional para almacenar la tabla
     * Tenemos también la 'clave', el 'mensaje' y la 'fecha' como variables de la clase para poder guardarlas después
     * en el fichero ya que se manejan desde esta clase.
     */
    String tablero[][];
    String clave;
    String mensaje;
    LocalDate fecha = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String fechaFichero = fecha.format(formatter);
    /**
     * En el constructor lo primero darle tamaño de 5x5 al tablero
     * Después rellenamos la matriz, donde a su vez metemos la clave, la cual 'limpiamos' es decir, 
     * le quitamos las letras repetidas y los espacios. También la cambiamos a mayúscula.
     * Por último, esa clave ya modificada la metemos en la variable clave
     * @param clave
     */
    public Matriz(String clave){
        this.tablero = new String[5][5];        
        rellenarMatriz(limpiarClave(clave.replaceAll(" ", "").toUpperCase()));
        this.clave = clave;
    }
    /**
     * Comprueba si se encuentra la letra que pasemos por parámetro en la clave.
     * Esto nos sirve para comprobar a la hora de rellenar la matriz
     * @param clave
     * @param letra
     * @return
     */
    private boolean estaLetra(String clave, char letra) {
        boolean existe = false;
        int longitud = clave.length();
        for (int i = 0; i < longitud; i++) {
            if (clave.charAt(i) == letra) {
                existe = true;
                return existe;
            }
        }
        return existe;
    }
    /**
     * Rellena la matriz :D
     * Inicia un contador y la letra 'A'
     * Empieza a recorrer el tablero con dos bucles anidados y primero mete la clave ya limpia
     * Hace la comprobación de que si la letra es una 'J' se cambia a una 'I'
     * Cuando termina con la clave empieza a comprobar si el caracter que va a entrar está en la clave y si está lo salta
     * Si no está, se mete y se aumenta en uno el caracter, para tener el siguiente.
     * @param claveLimpia
     */
    private void rellenarMatriz(String claveLimpia){
        int contador = 0;
        char caracter = 'A';
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (contador < claveLimpia.length()) {
                    if (claveLimpia.charAt(contador) == 'J') {
                    this.tablero[i][j] = "I";
                    contador++;
                    }
                    this.tablero[i][j] = claveLimpia.substring(contador, contador+1);
                    contador++;
                }else{
                    while (estaLetra(claveLimpia, caracter ) || caracter == 'J' ) {
                        caracter++;
                    }
                    tablero[i][j] = String.valueOf(caracter);
                    caracter++;
                }
            }
        }
    }
    /**
     * Le entra la clave y elimina las letras duplicadas de ella.
     * Se mueve a través de un array de caracteres donde está la clave, y si el caracter que comprueba no existe, 
     * se guarda, si no lo salta y lo elimina.
     * @param clave
     * @return
     */
    private String limpiarClave(String clave){
        char strChar[] = clave.toCharArray();
        int index = 0;
        for (int i = 0; i < strChar.length; i++) {
            int j;
            for (j = 0; j < i; j++) {
                if (strChar[i] == strChar[j]) {
                    break;
                }
            }
            if (i == j) {
                strChar[index++] = strChar[i];
            }
        }
        String claveLimpia = String.valueOf(Arrays.copyOf(strChar, index));
        return claveLimpia;
    }
    /**
     * Imprimimos por pantalla el tablero.
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                System.out.print(tablero[i][j]+" ");
            }
            System.out.println();
        }
        return null;
        
    }
    /**
     * Comprobamos que la letra que entra por parámetro no sea una 'Ñ' o una 'J'
     * En caso de que sean se cambian por una 'N' e 'I' respectivamente.
     * Esto es para poder tener en la tabla sólamente 25 caracteres.
     * @param l
     * @return
     */
    private char letraJota(char l){
        char letra = l;
        if (letra == 'Ñ' || letra == '¤') {
            letra = 'N';
        } else {
            if (letra == 'J') {
            letra = 'I';
            }
        }
        return letra;
    }
    /**
     * Devuelve la posición de un caracter en el tablero.
     * Recive una letra y comprueba en el tablero si esa letra existe, devolviendo la posición [x] e [y] en el tablero
     * @param letra
     * @return
     */
    private int[] posicion(char letra){
        String l = String.valueOf(letraJota(letra));
        int[] posicion = new int[2];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j].equals(l)) {
                    posicion[0] = i;
                    posicion[1] = j;
                }
            }
        }
        return posicion;
    }
    /**
     * Cifra pares de letras respecto a las reglas del cifrado Playfair.
     * Recibe dos letras del mensaje y comprueba su posición en la tabla. 
     * Dependiendo de en que posición estén cifra y devueve un array con las 2 letras de tipo String
     * La operación '%5' de los cifrados es para comprobar que la posición de la nueva letra no sea mayor del
     * tamaño del tablero. Si es 5 devolverá 0, que sería el opuesto y si es mayor, devolverá la posición exacta.
     * @param l1
     * @param l2
     * @return
     */
    public String[] letrasCifrar (char l1, char l2){
        String[] letras = new String[2];
        int[] posL1;
        int[] posL2;
        posL1 = posicion(l1);
        posL2 = posicion(l2);
        // caso 1
        if (posL1[1] == posL2[1]) {
            letras[0] = tablero[(posL1[0]+1)%5][posL1[1]];
            letras[1] = tablero[(posL2[0]+1)%5][posL2[1]];
        }else{
            if (posL1[0] == posL2[0]) {
                letras[0] = tablero[posL1[0]][(posL1[1]+1)%5];
                letras[1] = tablero[posL2[0]][(posL2[1]+1)%5];
            }else{
                letras[0] = tablero[posL1[0]][posL2[1]];
                letras[1] = tablero[posL2[0]][posL1[1]];
            }
        }
        return letras;
    }
    /**
     * Descifra pares de letras respecto a las reglas del cifrado Playfair.
     * Recibe dos letras del mensaje y comprueba su posición en la tabla. 
     * Dependiendo de en que posición estén cifra y devueve un array con las 2 letras de tipo String
     * La operación '%5' de los cifrados es para comprobar que la posición de la nueva letra no sea mayor del
     * tamaño del tablero. Si es 5 devolverá 0, que sería el opuesto y si es mayor, devolverá la posición exacta.
     * @param l1
     * @param l2
     * @return
     */
    public String[] letrasDescifrar (char l1, char l2){
        String[] letras = new String[2];
        int[] posL1;
        int[] posL2;
        posL1 = posicion(l1);
        posL2 = posicion(l2);
        if (posL1[1] == posL2[1]) {
            letras[0] = tablero[(posL1[0]+4)%5][posL1[1]];
            letras[1] = tablero[(posL2[0]+4)%5][posL2[1]];
        }else{
            if (posL1[0] == posL2[0]) {
                letras[0] = tablero[posL1[0]][(posL1[1]+4)%5];
                letras[1] = tablero[posL2[0]][(posL2[1]+4)%5];
            }else{
                letras[0] = tablero[posL1[0]][posL2[1]];
                letras[1] = tablero[posL2[0]][posL1[1]];
            }
        }
        return letras;
    }
    /**
     * Este método no cifra realmente el mensaje
     * Es la operación entera de pedir el mensaje para cifrar, cifrarlo usaddo 'letrasCifrar()' y escribir el resultado
     * en el fichero 'resultados.txt'
     * Crea el escritor 'writer' y nos asocia la variable local de mensaje al mensaje que recogemos por pantalla
     * Limpia el mensaje, es decir quita los espacios y añade una 'X' al final en caso de que sus letras sean impares.
     * Por último 'writer' escribe en el fichero el resultado y la fecha y se imprime por pantalla.
     */
    public void cifrarMensaje(){
        Escritor writer = new Escritor();
        String accion = "Cifrar";
        Scanner sc = new Scanner(System.in);
        System.out.println("Dame el mensaje que quieras cifrar");
        this.mensaje = sc.nextLine();
        String mensajeLimpio = limpiarMensaje(this.mensaje.toUpperCase());
        String mensajeCifrado = "";
        for (int i = 0; i < mensajeLimpio.length()-1; i+=2) {
            String[] letras = letrasCifrar(mensajeLimpio.charAt(i), mensajeLimpio.charAt(i+1));
            for (int j = 0; j < letras.length; j++) {
                mensajeCifrado += letras[j];
            }
        }
        writer.escribir(this.mensaje, mensajeCifrado, accion, this.clave, this.fechaFichero);

        System.out.println(mensajeCifrado);
    }
    /**
     * Este método no descifra realmente el mensaje
     * Es la operación entera de pedir el mensaje para descifrar, descifrarlo usaddo 'letrasDescifrar()' y escribir 
     * el resultado en el fichero 'resultados.txt'
     * Crea el escritor 'writer',
     * Limpia el mensaje, es decir quita los espacios y añade una 'X' al final en caso de que sus letras sean impares.
     * Por último 'writer' escribe en el fichero el resultado y la fecha y se imprime por pantalla.
     */
    public void descifrar() {
        Scanner sc = new Scanner(System.in);
        Escritor writer = new Escritor();
        String accion = "Descifrar";        
        System.out.println("Dame el mensaje que quieras descifrar");
        String mensaje = limpiarMensaje(sc.nextLine().toUpperCase());
        String mensajeDescifrado = "";
        for (int i = 0; i < mensaje.length()-1; i+=2) {
            String[] letras = letrasDescifrar(mensaje.charAt(i), mensaje.charAt(i+1));
            for (int j = 0; j < letras.length; j++) {
                mensajeDescifrado += letras[j];
            }
        }
        writer.escribir(mensaje, mensajeDescifrado, accion, this.clave, this.fechaFichero);
        System.out.println(mensajeDescifrado);
    }
    /**
     * Modifica el mensaje que le entra por parámetro para quitar los espacios y en caso de que sea de longitud
     * impar añadir una 'X' al final
     * @param mensaje
     * @return
     */
    private String limpiarMensaje(String mensaje) {
        String mensajeLimpio = "";
        mensaje = mensaje.replaceAll(" ", "");
        if (mensaje.length()%2 == 0) {
            mensajeLimpio = mensaje;
        } else {
            mensajeLimpio = mensaje + "X";
        }
        return mensajeLimpio;
    }
}

/**
 * La clase escritor nos ayuda para poder escribir en el fichero de 'resultados.txt'
 */
class Escritor{
    /**
     * Escribe el resultado en el fichero de acuerdo a las reglas de la práctica.
     * Añade la acción, la clave de cifrado, el mensaje que se vaya a procesar, el resultado
     * y la fecha de la operación. Añade un salto de línea al final para poder escribir en la siguiente línea 
     * cuando se vuelva a lanzar el mensaje.
     * @param mensaje
     * @param mensajeCifrado
     * @param accion
     * @param clave
     * @param fecha
     */
    public void escribir(String mensaje, String mensajeCifrado, String accion, String clave, String fecha) {
        try {
            FileWriter f = new FileWriter("src/resultados.txt", true);
            f.append(accion+" - "+clave+" - "+mensaje+" - "+mensajeCifrado+" - "+fecha+"\n");
            f.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
