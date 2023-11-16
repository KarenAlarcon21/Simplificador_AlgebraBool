package Modelo;

import java.util.ArrayList;

public class Tabla {

    public Operaciones operacion;
    public Variable variable;
    public ArrayList<String> letras = new ArrayList<>();
    public int filtro = 0;

    public ArrayList<String> getLetras() {
        return letras;
    }

    public void setLetras(ArrayList<String> letras) {
        this.letras = letras;
    }

    public Tabla() {
        this.variable = new Variable();
        this.operacion = new Operaciones();
    }

    public ArrayList<ArrayList<Integer>> Combinaciones_Matriz(int baseFinal, int variables) {

        ArrayList<ArrayList<Integer>> matriz = new ArrayList<>();

        int combinaciones = (int) Math.pow(baseFinal, variables);
        if (filtro == 0) {
            for (int i = 0; i < variables; i++) {

                char letra = (char) ('A' + i);
                letras.add(String.valueOf(letra));

            }
        }
        for (int i = 0; i < combinaciones; i++) {

            ArrayList<Integer> valorbi = new ArrayList<>();
            int valor = i;

            for (int j = 0; j < variables; j++) {

                int residuo = valor % baseFinal;
                valor /= baseFinal;
                valorbi.add(0, residuo);

            }
            filtro++;
            matriz.add(valorbi);
        }

        return matriz;
    }

    public String[] Letras_ANumeros(String[] variables, int operacion) {

        String[] numeros = new String[variables.length];
        String[] variables_coincidentes = new String[variables.length];

        for (int i = 0; i < letras.size(); i++) {

            for (int j = 0; j < variables.length; j++) {

                if (letras.get(i).equals(variables[j])) {

                    String cadena = String.valueOf(i);
                    variables_coincidentes[j] = letras.get(i);
                    numeros[j] = String.valueOf(cadena);

                }
            }
        }

        Añadir_operaciones(variables_coincidentes, operacion);

        return numeros;
    }

    public void Añadir_operaciones(String[] numeros, int operacion_) {

        StringBuilder resultado = new StringBuilder();

        switch (operacion_) {

            case 0:

                resultado.append("(");

                for (int i = 0; i < numeros.length; i++) {

                    resultado.append(numeros[i]);

                    if (i < numeros.length - 1) {

                        resultado.append("+");

                    }

                }

                resultado.append(")");
                break;

            case 1:

                resultado.append("(");

                for (int i = 0; i < numeros.length; i++) {

                    resultado.append(numeros[i]);

                    if (i < numeros.length - 1) {

                        resultado.append("*");

                    }
                }

                resultado.append(")");
                break;

            case 2:

                resultado.append("~").append(numeros[0]);
                break;

            case 3:

                resultado.append("(");

                for (int i = 0; i < numeros.length; i++) {

                    resultado.append(numeros[i]);

                    if (i < numeros.length - 1) {

                        resultado.append("⊙");

                    }
                }

                resultado.append(")");
                break;

            case 4:

                resultado.append("(");

                for (int i = 0; i < numeros.length; i++) {

                    resultado.append(numeros[i]);

                    if (i < numeros.length - 1) {

                        resultado.append("⊕");

                    }
                }

                resultado.append(")");
                break;

        }

        letras.add(resultado.toString());
    }

    public int busqueda(ArrayList<ArrayList<Integer>> matriz_aux, ArrayList<Integer> valores) {

        int filaCoincidente = -1;

        for (int i = 0; i < matriz_aux.size(); i++) {

            ArrayList<Integer> filaActual = matriz_aux.get(i);

            if (filaActual.equals(valores)) {

                filaCoincidente = i;
                break;

            }
        }

        return filaCoincidente;
    }
}
