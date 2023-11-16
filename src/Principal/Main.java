package Principal;

import Controlador.Controlador;
import Modelo.*;
import Vista.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Tabla tabla = new Tabla();
        Variable variable = new Variable();
        Operaciones operaciones = new Operaciones();
        Funciones_Logicas funciones_logicas = new Funciones_Logicas();
        Muestra_tabla tabla_verdad = new Muestra_tabla();
        Controlador controlador = new Controlador(variable, tabla, operaciones, funciones_logicas, tabla_verdad);
       
        funciones_logicas.setVisible(true);
        tabla_verdad.setVisible(true);
    }

}
