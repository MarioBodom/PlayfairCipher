import java.util.Arrays;

public class Matriz {
    
    String tablero[][];

    public Matriz(String clave){
        this.tablero = new String[5][5];
        
        rellenarMatriz(limpiarClave(clave));


    }

    private void rellenarMatriz(String claveLimpia){

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                this.tablero[i][j] = Character.toString(claveLimpia.charAt(j+i));
                
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
        return String.valueOf(Arrays.copyOf(strChar, index));
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                System.out.print(tablero[i][j]);
            }
        }
        return null;
        
    }
}
