package Modelo;

import java.util.ArrayList;

public class Operaciones {

    public ArrayList<ArrayList<Integer>> Suma(ArrayList<ArrayList<Integer>> matriz, int[] Columnas) {

        ArrayList<ArrayList<Integer>> matriz_aux = new ArrayList<>();
        ArrayList<Integer> resultados = new ArrayList<>();

        for (ArrayList<Integer> fila_aux : matriz) {

            ArrayList<Integer> nuevaFila = new ArrayList<>();

            for (int columna : Columnas) {

                nuevaFila.add(fila_aux.get(columna));

            }

            matriz_aux.add(nuevaFila);
        }

        for (ArrayList<Integer> fila : matriz_aux) {

            boolean Valor = false;

            for (int valor_aux : fila) {

                if (valor_aux == 1) {

                    Valor = true;
                    break;
                }

            }

            if (Valor) {

                resultados.add(1);

            } else {

                resultados.add(0);

            }
        }

        for (int i = 0; i < matriz.size(); i++) {

            matriz.get(i).add(resultados.get(i));

        }
      
        return matriz;

    }

    public ArrayList<ArrayList<Integer>> Producto(ArrayList<ArrayList<Integer>> matriz, int[] Columnas) {

        ArrayList<ArrayList<Integer>> matriz_aux = new ArrayList<>();
        ArrayList<Integer> resultados = new ArrayList<>();

        for (ArrayList<Integer> fila_aux : matriz) {

            ArrayList<Integer> nuevaFila = new ArrayList<>();

            for (int columna : Columnas) {

                nuevaFila.add(fila_aux.get(columna));

            }

            matriz_aux.add(nuevaFila);
        }

        for (ArrayList<Integer> fila : matriz_aux) {

            boolean Valor = false;

            for (int valor_aux : fila) {

                if (valor_aux == 0) {

                    Valor = true;
                    break;
                }

            }

            if (Valor) {

                resultados.add(0);

            } else {

                resultados.add(1);

            }
        }

        for (int i = 0; i < matriz.size(); i++) {

            matriz.get(i).add(resultados.get(i));

        }

        
        return matriz;

    }

    public ArrayList<ArrayList<Integer>> Negacion(ArrayList<ArrayList<Integer>> matriz, int Columna) {

        ArrayList<Integer> resultado = new ArrayList<>();

        for (int i = 0; i < matriz.size(); i++) {

            int Valor = matriz.get(i).get(Columna);

            if (Valor == 0) {

                resultado.add(1);

            } else {

                resultado.add(0);

            }

        }

        for (int i = 0; i < matriz.size(); i++) {

            matriz.get(i).add(resultado.get(i));

        }

        return matriz;

    }

    public ArrayList<ArrayList<Integer>> XOR(ArrayList<ArrayList<Integer>> matriz, int[] Columnas) {

        ArrayList<ArrayList<Integer>> matriz_aux = new ArrayList<>();
        ArrayList<Integer> resultados = new ArrayList<>();
        int valor;

        for (ArrayList<Integer> fila_aux : matriz) {

            ArrayList<Integer> nuevaFila = new ArrayList<>();

            for (int columna : Columnas) {

                nuevaFila.add(fila_aux.get(columna));

            }

            matriz_aux.add(nuevaFila);
        }

        for (ArrayList<Integer> fila : matriz_aux) {

            int primerValor = fila.get(0);
            boolean iguales = true;

            for (int i = 1; i < fila.size(); i++) {

                if (fila.get(i) != primerValor) {

                    iguales = false;
                    break;

                }

            }

            if (iguales) {

                valor = 0;

            } else {

                valor = 1;

            }

            resultados.add(valor);

        }

        for (int i = 0; i < matriz.size(); i++) {

            matriz.get(i).add(resultados.get(i));

        }

        return matriz;

    }

    public ArrayList<ArrayList<Integer>> XNOR(ArrayList<ArrayList<Integer>> matriz, int[] Columnas) {

        ArrayList<ArrayList<Integer>> matriz_aux = new ArrayList<>();
        ArrayList<Integer> resultados = new ArrayList<>();
        int valor;

        for (ArrayList<Integer> fila_aux : matriz) {

            ArrayList<Integer> nuevaFila = new ArrayList<>();

            for (int columna : Columnas) {

                nuevaFila.add(fila_aux.get(columna));

            }

            matriz_aux.add(nuevaFila);
        }

        for (ArrayList<Integer> fila : matriz_aux) {

            int primerValor = fila.get(0);
            boolean iguales = true;

            for (int i = 1; i < fila.size(); i++) {

                if (fila.get(i) != primerValor) {

                    iguales = false;
                    break;

                }

            }

            if (iguales) {

                valor = 1;

            } else {

                valor = 0;

            }

            resultados.add(valor);

        }

        for (int i = 0; i < matriz.size(); i++) {

            matriz.get(i).add(resultados.get(i));

        }

        return matriz;

    }

}
