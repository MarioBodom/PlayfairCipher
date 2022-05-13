import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Arrays;


public class Matriz {
    
    char tablero[][];

    public Matriz(String clave){
        this.tablero = new char[5][5];
        
        rellenarMatriz(limpiarClave(clave.toUpperCase()));


    }

    private void rellenarMatriz(String claveLimpia){
        int contador = 0;
        char caracter = 'A';
        String keyString = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (contador < claveLimpia.length()) {
                    this.tablero[i][j] = claveLimpia.charAt(contador);
                    contador++;
                }else{
                    for (int j2 = 0; j2 < keyString.length(); j2++) {
                        for (int k = 0; k < claveLimpia.length(); k++) {
                            if (claveLimpia.charAt(k) == keyString.charAt(j2)) {
                                continue;
                            }else{
                                tablero[i][j] = keyString.charAt(j2);
                            }
                        }
                    }
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
