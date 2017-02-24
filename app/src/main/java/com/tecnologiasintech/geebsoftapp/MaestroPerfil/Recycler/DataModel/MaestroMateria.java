package com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler.DataModel;

/**
 * Created by Sergio on 1/17/2017.
 */

public class MaestroMateria {
    MaestroMateria(){}

    private String materia_Nombre;
    private String materia_Carrera;

    public MaestroMateria(String materia_Nombre, String materia_Carrera) {
        this.materia_Nombre = materia_Nombre;
        this.materia_Carrera = materia_Carrera;
    }

    public String getMateria_Nombre() {
        return materia_Nombre;
    }

    public void setMateria_Nombre(String materia_Nombre) {
        this.materia_Nombre = materia_Nombre;
    }

    public String getMateria_Carrera() {
        return materia_Carrera;
    }

    public void setMateria_Carrera(String materia_Carrera) {
        this.materia_Carrera = materia_Carrera;
    }
}
