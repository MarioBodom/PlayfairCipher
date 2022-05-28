import java.util.Arrays;
import java.util.Scanner;
import java.io.*;;;;


public class Matriz {
    
    String tablero[][];

    public Matriz(String clave){
        this.tablero = new String[5][5];
        
        rellenarMatriz(limpiarClave(clave.replaceAll(" ", "").toUpperCase()));


    }

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

    public void cifrarMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Dame el mensaje que quieras cifrar");
        String mensaje = limpiarMensaje(sc.nextLine().toUpperCase());
        String mensajeCifrado = "";
        for (int i = 0; i < mensaje.length()-1; i+=2) {
            String[] letras = letrasCifrar(mensaje.charAt(i), mensaje.charAt(i+1));
            for (int j = 0; j < letras.length; j++) {
                mensajeCifrado += letras[j];
            }
        }
        System.out.println(mensajeCifrado);
    }

    public void descifrar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dame el mensaje que quieras descifrar");
        String mensaje = limpiarMensaje(sc.nextLine().toUpperCase());
        String mensajeDescifrado = "";
        for (int i = 0; i < mensaje.length()-1; i+=2) {
            String[] letras = letrasDescifrar(mensaje.charAt(i), mensaje.charAt(i+1));
            for (int j = 0; j < letras.length; j++) {
                mensajeDescifrado += letras[j];
            }
        }
        System.out.println(mensajeDescifrado);
    }

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
