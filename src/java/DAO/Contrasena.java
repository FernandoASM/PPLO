package DAO;
// Generated 26/04/2016 03:15:50 PM by Hibernate Tools 4.3.1



/**
 * Contrasena generated by hbm2java
 */
public class Contrasena  implements java.io.Serializable {


     private String correo;
     private Usuario usuario;
     private String contrasena;

    public Contrasena() {
    }

    public Contrasena(Usuario usuario, String contrasena) {
       this.usuario = usuario;
       this.contrasena = contrasena;
    }
   
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getContrasena() {
        return this.contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }




}


