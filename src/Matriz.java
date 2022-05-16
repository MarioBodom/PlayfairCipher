import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Arrays;


public class Matriz {
    
    String tablero[][];

    public Matriz(String clave){
        this.tablero = new String[5][5];
        
        rellenarMatriz(limpiarClave(clave.toUpperCase()));


    }

    private boolean estaLetra(String clave, char letra) {
        boolean existe = false;
        int longitud = clave.length();
        // int contador = 0;
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
        // String keyString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (contador < claveLimpia.length()) {
                    this.tablero[i][j] = claveLimpia.substring(contador, contador+1);
                    contador++;
                }else{
                    // if(!estaLetra(claveLimpia, caracter) && caracter != 'J'){
                    //     tablero[i][j] = String.valueOf(caracter);
                    //     caracter++;
                    // }else{
                    //     caracter++;
                    //     j--;
                    // }

                    while (estaLetra(claveLimpia, caracter)) {
                        caracter++;
                    }
                    
                        tablero[i][j] = String.valueOf(caracter);
                        caracter++;
                    
                    // for (int j2 = 0; j2 < claveLimpia.length(); j2++) {
                    //     // for (int k = 0; k < claveLimpia.length(); k++) {
                    //     //     if (!keyString.substring(j2, j2+1).equals(claveLimpia.substring(k, k+1))) {
                    //     //         this.tablero[i][j] = keyString.substring(j2);
                    //     //     }else{
                    //     //         continue;
                    //     //     }
                    //     // }
                    //     if (!claveLimpia.substring(j2, j2).equals(String.valueOf(caracter)) && caracter<='Z') {
                    //         this.tablero[i][j] = String.valueOf(caracter);
                    //         caracter++;
                    //     }else{
                    //         caracter++;
                    //     }
                    // }
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
}
