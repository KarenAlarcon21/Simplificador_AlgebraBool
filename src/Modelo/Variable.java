/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Variable {

    private String Variables = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    public ArrayList<ArrayList<Integer>> matriz = new ArrayList<>();
    int saber_operacion;
    int tamaño;

    public String getVariables() {
        return Variables;
    }

    public void setVariables(String Variables) {
        this.Variables = Variables;
    }

    public int getSaber_operacion() {
        return saber_operacion;
    }

    public void setSaber_operacion(int saber_operacion) {
        this.saber_operacion = saber_operacion;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int Posicion(char caracter) {
        return Variables.indexOf(caracter);
    }

}
