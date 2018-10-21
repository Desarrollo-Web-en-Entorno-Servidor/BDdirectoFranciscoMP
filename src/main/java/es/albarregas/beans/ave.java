/*
 
 */
package es.albarregas.beans;

import java.io.Serializable;

/**
 * Clase Serilizable, con solo los set y get de las variables.
 * @author FranciscoAntonio
 */
public class ave implements Serializable{
    private String anilla;
    private String especie;
    private String lugar;
    private String fecha;

    public String getAnilla() {
        return anilla;
    }

    public void setAnilla(String anilla) {
        this.anilla = anilla;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
