package com.tecnologiasintech.geebsoftapp.MaestroPerfilInformacion;

import com.google.firebase.database.Exclude;

/**
 * Created by Sergio on 1/22/2017.
 */

public class MaestroComentario {
    private String usuarioComentario;
    private String usuarioFechaPublicacion;
    private String usuarioNombre;
    private String key;
    private String usuarioFotoPerfil;
//    private long usuarioLike;

    public MaestroComentario(){}

    @Exclude
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public MaestroComentario(String usuarioNombre,String usuarioComentario,String usuarioFechaPublicacion,String usuarioFotoPerfil){
        this.usuarioNombre=usuarioNombre;
        this.usuarioComentario=usuarioComentario;
        this.usuarioFechaPublicacion=usuarioFechaPublicacion;
        this.usuarioFotoPerfil = usuarioFotoPerfil;
    }

    public String getUsuarioComentario() {
        return usuarioComentario;
    }

    public void setUsuarioComentario(String usuarioComentario) {
        this.usuarioComentario = usuarioComentario;
    }

    public String getUsuarioFechaPublicacion() {
        return usuarioFechaPublicacion;
    }

    public void setUsuarioFechaPublicacion(String usuarioFechaPublicacion) {
        this.usuarioFechaPublicacion = usuarioFechaPublicacion;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }


    public String getUsuarioFotoPerfil() {
        return usuarioFotoPerfil;
    }

    public void setUsuarioFotoPerfil(String usuarioFotoPerfil) {
        this.usuarioFotoPerfil = usuarioFotoPerfil;
    }
}
