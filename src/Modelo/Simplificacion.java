package Modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import Modelo.Tabla;

public class Simplificacion {

    int inicio = 0;
    ArrayList<ArrayList<Integer>> sobrantes = new ArrayList<>();
    ArrayList<Integer> pos_eliminar = new ArrayList<>();
    ArrayList<ArrayList<Integer>> matrizFinal;
    Tabla objetorTabla;

    public Simplificacion() {
    }
    ArrayList<ArrayList<Integer>> matriz_simplificada = new ArrayList<>();
    ArrayList<Integer> miniterminos = new ArrayList<>();
    ArrayList<Integer> cantidad_unos = new ArrayList<>();
    int noCambio = 0;
    int tamaño = 0;
    boolean recursividad = true;

    public void llenarObjTabla(Tabla obje) {
        objetorTabla = obje;
    }

    public ArrayList<Integer> posicion_minterminos(ArrayList<ArrayList<Integer>> matriz) {
        int tamanio = matriz.get(0).size();
        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < matriz.get(0).size(); j++) {

                if (matriz.get(i).get(tamanio - 1) == 1 && j == tamanio - 1) {
                    miniterminos.add(i);
                }

            }
        }

        return miniterminos;
    }

    public ArrayList<ArrayList<Integer>> minterminos(ArrayList<ArrayList<Integer>> matriz, ArrayList posicion) {

        ArrayList<ArrayList<Integer>> min = new ArrayList<>();
        for (int i = 0; i < posicion.size(); i++) {
            min.add(matriz.get((int) posicion.get(i)));
        }
        System.out.println("miniterminos");
        System.out.println(min);
        return min;
    }

    public void quine_mccluskey(ArrayList<ArrayList<Integer>> matriz) {
        Map<Integer, ArrayList<ArrayList<Integer>>> grupos = agrupacion_1(matriz);
        comparacion(grupos);
    }

    public Map<Integer, ArrayList<ArrayList<Integer>>> agrupacion_1(ArrayList<ArrayList<Integer>> matriz) {

        Map<Integer, ArrayList<ArrayList<Integer>>> grupos = new HashMap<>();

        for (int i = 0; i < matriz.size(); i++) {
            ArrayList<Integer> fila = matriz.get(i);
            int unos = cantidadUnos(fila);

            if (!grupos.containsKey(unos)) {
                grupos.put(unos, new ArrayList<>());
            }
            grupos.get(unos).add(fila);

        }

        for (Map.Entry<Integer, ArrayList<ArrayList<Integer>>> entry : grupos.entrySet()) {
            int llave = entry.getKey();
            ArrayList<ArrayList<Integer>> value = entry.getValue();
            //   System.out.println("Grupo con " + llave + " unos:");
            for (ArrayList<Integer> fila : value) {
                System.out.println(fila);
            }
        }
        Set<Integer> unos = new HashSet<>(cantidad_unos);
        cantidad_unos = new ArrayList<>(unos);
        Collections.sort(cantidad_unos);

        return grupos;
    }

    public int cantidadUnos(ArrayList<Integer> fila) {
        int count = 0;
        for (int elemento : fila) {
            if (elemento == 1) {
                count++;
            }
        }
        cantidad_unos.add(count);
        return count;
    }

    public ArrayList<ArrayList<Integer>> comparacion(Map<Integer, ArrayList<ArrayList<Integer>>> grupos) {
        matrizFinal = new ArrayList<>(matriz_simplificada);
        matriz_simplificada.clear();
        for (int a = 0; a < cantidad_unos.size(); a++) {

            ArrayList<ArrayList<Integer>> grupoActual = grupos.get(cantidad_unos.get(a));

            if (a + 1 == cantidad_unos.size()) {
                break;
            }
            ArrayList<ArrayList<Integer>> siguienteGrupo = grupos.get(cantidad_unos.get(a + 1));

            for (int j = 0; j < grupoActual.size(); j++) {
                for (int k = 0; k < siguienteGrupo.size(); k++) {
                    tamaño++;
                    getDiferenciaBit(grupoActual.get(j), siguienteGrupo.get(k));
                }
            }

        }

        cantidad_unos.clear();
        Set<ArrayList<Integer>> uniqueRows = new HashSet<>(matriz_simplificada);

        // Convertir de nuevo a ArrayList
        matriz_simplificada = new ArrayList<>(uniqueRows);
        // System.out.println("sobrantes: " + sobrantes);

        System.out.println("\n");
      //  System.out.println("tamaño: " + matriz_simplificada.size());
       // for (int i = 0; i < matriz_simplificada.size(); i++) {
         //   System.out.println(matriz_simplificada.get(i));
       // }
        Pos_sobrantes(sobrantes, matriz_simplificada);
        pos_eliminar.clear();

        System.out.println("\n");

        //  System.out.println("tamaño:" + tamaño);
        // System.out.println("nocambio: " + noCambio);
        if (noCambio != tamaño) {
            tamaño = 0;
            noCambio = 0;
            quine_mccluskey(matriz_simplificada);
        } else {
            Set<ArrayList<Integer>> repetidos = new HashSet<>(sobrantes);

            // Convertir de nuevo a ArrayList
            sobrantes = new ArrayList<>(repetidos);
        //    System.out.println("sobrantesAAA: " + sobrantes);
            matrizFinal.addAll(sobrantes);
            Set<ArrayList<Integer>> borrarFinal = new HashSet<>(matrizFinal);

            //Convertir de nuevo a ArrayList
            matrizFinal = new ArrayList<>(borrarFinal);
            for (int i = 0; i < matrizFinal.size(); i++) {
                System.out.println(matrizFinal.get(i));
            }
            System.out.println("no hay cambios");
            convertirALetras();
        }

        return null;
    }

    public void getDiferenciaBit(ArrayList<Integer> num1, ArrayList<Integer> num2) {
        ArrayList<ArrayList<Integer>> aux_1 = new ArrayList<>();
        ArrayList<Integer> aux = new ArrayList<>(num1);
        //System.out.println("fila 1: " + num1);
        //System.out.println("fila 2: " + num2);

        int diferencias = 0; // Contador de diferencias

        for (int i = 0; i < num1.size(); i++) {
            if (!(num1.get(i).equals(num2.get(i)))) {
                aux.set(i, 2);
                diferencias++;

            }
        }
        //  System.out.println("diferencias: " + diferencias);
        if (diferencias == 1) {
            matriz_simplificada.add(aux);
        } else {
            noCambio++;
            sobrantes.add(num2);
            sobrantes.add(num1);
         //   System.out.println("vector que no entra: " + num1);

        }

    }

    public void Pos_sobrantes(ArrayList<ArrayList<Integer>> sobrantes, ArrayList<ArrayList<Integer>> matriz_simplificada) {
     //   System.out.println("entro a pos sobrantes: ");
       // System.out.println(sobrantes);
        for (int i = 0; i < matriz_simplificada.size(); i++) {
            for (int j = 0; j < sobrantes.size(); j++) {
                compararFilas(matriz_simplificada.get(i), sobrantes.get(j), j);
            }
        }

        Set<Integer> uniqueRows = new HashSet<>(pos_eliminar);
        pos_eliminar = new ArrayList<>(uniqueRows);

       //System.out.println("pos a eliminar: " + pos_eliminar);

        for (int h = pos_eliminar.size() - 1; h >= 0; h--) {
            if (pos_eliminar.get(h) < this.sobrantes.size()) {
                this.sobrantes.remove(pos_eliminar.get(h).intValue());
            }
        }

        // System.out.println("sobrantes: " + this.sobrantes);
    }

    private void compararFilas(ArrayList<Integer> fila1, ArrayList<Integer> fila2, int j) {
        int contador = 0;
        // System.out.println("fila1: " + fila1);
        //System.out.println("fila2: " + fila2);
        for (int k = 0; k < fila1.size(); k++) {
            if (!fila1.get(k).equals(fila2.get(k))) {

                //      System.out.println("Las filas son diferentes en la posición " + k);
                contador++;
            }

        }
        if (contador == 1) {
            // System.out.println("error?");
            pos_eliminar.add(j);
            

        }

        contador = 0;

    }

    public void convertirALetras() {
        System.out.println("letras: " + objetorTabla.letras);
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < matrizFinal.size(); i++) {
            boolean primerTermino = true; // Controla si es el primer término en la expresión.

            resultado.append("("); // Agrega un paréntesis al inicio de cada término.

            for (int j = 0; j < matrizFinal.get(0).size(); j++) {
                if (matrizFinal.get(i).get(j) == 1) {
                    if (!primerTermino) {
                        resultado.append("*");
                    }
                    resultado.append(objetorTabla.letras.get(j));
                    primerTermino = false;
                } else if (matrizFinal.get(i).get(j) == 0) {
                    if (!primerTermino) {
                        resultado.append("*");
                    }
                    resultado.append("~");
                    resultado.append(objetorTabla.letras.get(j));
                    primerTermino = false;
                }
            }

            resultado.append(")"); // Agrega un paréntesis al final de cada término.

            if (i != matrizFinal.size() - 1) {
                resultado.append("+"); // Agrega el símbolo '+' si no es el último término.
            }
        }

        System.out.println(resultado.toString());
    }

}
