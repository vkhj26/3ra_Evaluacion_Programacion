/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aparcamientos;

/**
 *
 * @author DAW
 */
public abstract class Aparcamientos {
    
    String PARcodigo;
    String PARNombrePar;
    String PARtipo;
    String distrito;

    public String getPARcodigo() {
        return PARcodigo;
    }

    public void setPARcodigo(String PARcodigo) {
        this.PARcodigo = PARcodigo;
    }

    public Aparcamientos(String PARcodigo, String PARNombrePar, String PARtipo) {
        this.PARcodigo = PARcodigo;
        this.PARNombrePar = PARNombrePar;
        this.PARtipo = PARtipo;
    }

    public String getPARNombrePar() {
        return PARNombrePar;
    }

    public void setPARNombrePar(String PARNombrePar) {
        this.PARNombrePar = PARNombrePar;
    }

    public String getPARtipo() {
        return PARtipo;
    }

    public void setPARtipo(String PARtipo) {
        this.PARtipo = PARtipo;
    }
    
    
    
}
