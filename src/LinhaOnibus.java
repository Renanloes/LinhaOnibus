/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Renan
 */
public class LinhaOnibus {
    private String inicioFim;
    private int paradas;

    public LinhaOnibus(String inicioFim, int paradas){
        this.inicioFim = inicioFim;
        this.paradas = paradas;
    }
    
    public String getInicioFim() {
        return inicioFim;
    }

    public void setInicioFim(String inicioFim) {
        this.inicioFim = inicioFim;
    }

    public int getParadas() {
        return paradas;
    }

    public void setParadas(int paradas) {
        this.paradas = paradas;
    }
    
    public String toString(){
        return " " + inicioFim + ", " + paradas + " paradas";
    }
}
