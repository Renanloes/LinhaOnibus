/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Renan
 */
 public class Onibus {
    private String placa;
    private int quantMax;
     
    public Onibus(String placa, int quantMax){
        this.placa = placa;
        this.quantMax = quantMax;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getQuantMax() {
        return quantMax;
    }

    public void setQuantMax(int quantMax) {
        this.quantMax = quantMax;
    }
    
    public String toString(){
        return "" + placa + ", " + quantMax; 
    }
}
