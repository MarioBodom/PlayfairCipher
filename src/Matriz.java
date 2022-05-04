import java.util.Arrays;

import javax.swing.text.html.FormView;

public class Matriz {
    
    String tablero[][];

    public Matriz(String clave){
        this.tablero = new String[5][5];
        
        rellenarMatriz(clave);


    }

    private void rellenarMatriz(String claveLimpia){

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                this.tablero[i][j] = Character.toString(claveLimpia.charAt(j+i));
                
            }
        }
    }

    private String limpiarClave(String clave){
        String claveLimpia = " ";

        return claveLimpia;
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
