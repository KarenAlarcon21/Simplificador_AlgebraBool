package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {

    Variable variable;
    Tabla modelo_tabla;
    Operaciones operacion;
    Funciones_Logicas objetoVista;
    Muestra_tabla tabla_verdad;
    int fila_busqueda;
    int bandera = 0;
    Simplificacion objerSimplificacion;

    ArrayList<ArrayList<Integer>> matriz_aux = new ArrayList<>();
    ArrayList<ArrayList<Integer>> matriz_simpli;

    public Controlador(Variable variable, Tabla modelo_tabla, Operaciones operacion, Funciones_Logicas objetoVista, Muestra_tabla tabla_verdad) {

        this.variable = variable;
        this.modelo_tabla = modelo_tabla;
        this.operacion = operacion;
        this.objetoVista = objetoVista;
        this.tabla_verdad = tabla_verdad;

        objetoVista.btn_generarTabla.addActionListener(this);
        objetoVista.btn_refrescar.addActionListener(this);
        objetoVista.btn_suma.addActionListener(this);
        objetoVista.btn_producto.addActionListener(this);
        objetoVista.btn_negacion.addActionListener(this);
        objetoVista.bnt_busqueda.addActionListener(this);
        objetoVista.btn_xor.addActionListener(this);
        objetoVista.btn_xnor1.addActionListener(this);
        objetoVista.btnsimplificar.addActionListener(this);
        objetoVista.getBnt_busqueda().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(objetoVista.btn_generarTabla)) {
            Refrescar();
            matriz_aux = modelo_tabla.Combinaciones_Matriz(2, Integer.parseInt(objetoVista.spn_nVariables.getValue().toString()));
            matriz_simpli = modelo_tabla.Combinaciones_Matriz(2, Integer.parseInt(objetoVista.spn_nVariables.getValue().toString()));
            Subir_Tabla();

        }

        if (e.getSource().equals(objetoVista.btn_refrescar)) {

            objetoVista.spn_nVariables.setValue(1);
            Refrescar();

        }

        if (e.getSource().equals(objetoVista.btn_suma)) {

            if (!matriz_aux.isEmpty()) {

                Realizar_Operacion(0);

            }

        }

        if (e.getSource().equals(objetoVista.btn_producto)) {

            if (!matriz_aux.isEmpty()) {

                Realizar_Operacion(1);

            }

        }

        if (e.getSource().equals(objetoVista.btn_negacion)) {

            if (!matriz_aux.isEmpty()) {

                Realizar_Operacion(2);

            }

        }

        if (e.getSource().equals(objetoVista.btn_xnor1)) {

            if (!matriz_aux.isEmpty()) {

                Realizar_Operacion(3);

            }

        }

        if (e.getSource().equals(objetoVista.btn_xor)) {

            if (!matriz_aux.isEmpty()) {

                Realizar_Operacion(4);

            }

        }

        if (e.getSource().equals(objetoVista.getBnt_busqueda())) {

            ArrayList<ArrayList<Integer>> matriz_ax = new ArrayList<>();

            matriz_ax = modelo_tabla.Combinaciones_Matriz(2, Integer.parseInt(objetoVista.spn_nVariables.getValue().toString()));
            objetoVista.getTxtbusqueda().getText();
            String[] partes = objetoVista.getTxtbusqueda().getText().split(" ");
            ArrayList<Integer> busqueda = new ArrayList<>();

            for (String part : partes) {

                try {

                    int numero = Integer.parseInt(part);
                    busqueda.add(numero);

                } catch (NumberFormatException a) {

                }
            }

            fila_busqueda = modelo_tabla.busqueda(matriz_ax, busqueda);
            bandera = 1;
            Subir_Tabla();
        }

        if (e.getSource().equals(objetoVista.btnsimplificar)) {

            System.out.println("le di al boton simplificar");
            objerSimplificacion = new Simplificacion();
            objerSimplificacion.llenarObjTabla(modelo_tabla);
            ArrayList<Integer> posicion = objerSimplificacion.posicion_minterminos(matriz_aux);
            ArrayList<Integer> pos = new ArrayList<>();
         
            /*pos.add(7);
            pos.add(8);
            pos.add(10);
            pos.add(11);
            pos.add(13);
            pos.add(14);
            pos.add(15);
            */
            //6 variables
          
            pos.add(0);
            pos.add(1);
            pos.add(2);
            pos.add(3);
            pos.add(7);
            pos.add(9);
            pos.add(12);
            pos.add(19);
            pos.add(20);
            pos.add(22);
            pos.add(24);
            pos.add(26);
            pos.add(28);
            pos.add(30);
            pos.add(32);
            pos.add(38);
            pos.add(39);
            pos.add(41);
            pos.add(42);
            pos.add(47);
            pos.add(49);
            pos.add(51);
            pos.add(58);
            pos.add(59);
            pos.add(60);
            pos.add(61);
            pos.add(62);
           




// 5 VARIABLES

        /*  pos.add(0);
            pos.add(2);
            pos.add(6);
            pos.add(7);
            pos.add(8);
            pos.add(10);
            pos.add(14);
            pos.add(16);
            pos.add(18);
            pos.add(19);
            pos.add(20);
            pos.add(22);
            pos.add(24);
            pos.add(25);
            pos.add(27);
            pos.add(29);
            pos.add(30);
            pos.add(31);
*/
        //4 variables 
        /*
            pos.add(0);
            pos.add(3);
            pos.add(4);
            pos.add(6);
            pos.add(7);
            pos.add(8);
            pos.add(10);
            pos.add(12);
            pos.add(14);
*/
            matriz_simpli = objerSimplificacion.minterminos(matriz_simpli, pos);
            objerSimplificacion.quine_mccluskey(matriz_simpli);

        }

    }

    private void Realizar_Operacion(int valor) {

        String c = objetoVista.txt_operacion.getText();
        c = c.replaceAll("\\s+", "");

        String[] cArray = c.split(",");
        if (verificar_columna(cArray)) {

            variable.setSaber_operacion(valor);
            cArray = modelo_tabla.Letras_ANumeros(cArray, variable.getSaber_operacion());

            int[] Columnas = new int[cArray.length];

            for (int i = 0; i < cArray.length; i++) {

                try {

                    Columnas[i] = Integer.parseInt(cArray[i]);

                } catch (NumberFormatException ex) {

                    break;

                }

            }

            switch (valor) {

                case 0:

                    matriz_aux = operacion.Suma(matriz_aux, Columnas);
                    Subir_Tabla();

                    break;

                case 1:

                    matriz_aux = operacion.Producto(matriz_aux, Columnas);
                    Subir_Tabla();

                    break;

                case 2:

                    matriz_aux = operacion.Negacion(matriz_aux, Columnas[0]);
                    Subir_Tabla();

                    break;

                case 3:

                    matriz_aux = operacion.XNOR(matriz_aux, Columnas);
                    Subir_Tabla();

                    break;

                case 4:

                    matriz_aux = operacion.XOR(matriz_aux, Columnas);
                    Subir_Tabla();

                    break;

            }
        }
    }

    private void Refrescar() {

        matriz_aux.clear();
        objetoVista.txt_operacion.setText("");
        modelo_tabla.letras.clear();
        objetoVista.txtbusqueda.setText(null);

        String v[] = new String[modelo_tabla.letras.size()];
        v = modelo_tabla.letras.toArray(v);
        DefaultTableModel tabla = new DefaultTableModel(null, v);
        tabla_verdad.Tabla_Verdad.setModel(tabla);
        bandera = 0;

    }

    private void Subir_Tabla() {
        System.out.println(modelo_tabla.letras);
        StringBuilder matrizNumerica = new StringBuilder();
        variable.setTamaño(matriz_aux.size());

        matrizNumerica.append("|");

        for (String letra : modelo_tabla.letras) {

            matrizNumerica.append(letra).append("|");
        }

        matrizNumerica.append("\n");

        String v[] = new String[modelo_tabla.letras.size()];
        v = modelo_tabla.letras.toArray(v);

        int filas = matriz_aux.size();
        int columnas = matriz_aux.get(0).size();

        String[][] matriz = new String[filas][columnas];

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                matriz[i][j] = matriz_aux.get(i).get(j).toString();

            }
        }

        DefaultTableModel tabla = new DefaultTableModel(matriz, v);

        if (bandera == 1) {

            bandera = 0;
            seleccionar_fila(tabla);

        } else {

            tabla_verdad.Tabla_Verdad.setModel(tabla);

        }

        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);

        int numColumnas = tabla_verdad.Tabla_Verdad.getColumnCount();

        for (int i = 0; i < numColumnas; i++) {
            tabla_verdad.Tabla_Verdad.getColumnModel().getColumn(i).setCellRenderer(Alinear);
        }

    }

    private boolean verificar_columna(String[] cArray) {

        int bandera = 0;
        boolean a;

        for (int i = 0; i < modelo_tabla.letras.size(); i++) {

            for (int j = 0; j < cArray.length; j++) {

                if (modelo_tabla.letras.get(i).equals(cArray[j])) {

                    bandera++;

                }

            }

        }

        if (bandera == cArray.length) {

            a = true;

        } else {

            a = false;

        }

        return a;
    }

    public void seleccionar_fila(DefaultTableModel tabla) {

        if (fila_busqueda >= 0 && fila_busqueda < tabla.getRowCount()) {

            ListSelectionModel selectionModel = tabla_verdad.Tabla_Verdad.getSelectionModel();
            selectionModel.setSelectionInterval(fila_busqueda, fila_busqueda);

        }
    }
}
