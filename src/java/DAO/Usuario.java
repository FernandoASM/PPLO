package DAO;
// Generated 26/04/2016 03:15:50 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {

     private String correo;
     private String nombre;
     private String apepaterno;
     private String apematerno;
     private String calle;
     private String colonia;
     private String delegacion;
     private int numero;
     private int codigopostal;
     private Set articulos = new HashSet(0);
     private Presta presta;
     private Solicita solicita;
     private Prestigioconsumidor prestigioconsumidor;
     private Contrasena contrasena;
     private Telefono telefono;
     private Prestigioprestador prestigioprestador;

    public Usuario() {
    }

	
    public Usuario(String correo, String nombre, String appaterno, String appematerno, String calle, String colonia, String delegacion, int numero, int codigopostal, Telefono telefono, Contrasena contrasena) {
        this.correo = correo;
        this.nombre = nombre;
        this.apepaterno = apepaterno;
        this.apematerno = apematerno;
        this.calle = calle;
        this.colonia = colonia;
        this.delegacion = delegacion;
        this.numero = numero;
        this.codigopostal = codigopostal;
        this.telefono = telefono;
        this.contrasena = contrasena;
        
    }
    public Usuario(String correo, String nombre, String apepaterno, String apematerno, String calle, String colonia, String delegacion, Integer numero, Integer codigopostal, Set articulos, Presta presta, Solicita solicita, Prestigioconsumidor prestigioconsumidor, Contrasena contrasena, Telefono telefono, Prestigioprestador prestigioprestador) {
       this.correo = correo;
       this.nombre = nombre;
       this.apepaterno = apepaterno;
       this.apematerno = apematerno;
       this.calle = calle;
       this.colonia = colonia;
       this.delegacion = delegacion;
       this.numero = numero;
       this.codigopostal = codigopostal;
       this.articulos = articulos;
       this.presta = presta;
       this.solicita = solicita;
       this.prestigioconsumidor = prestigioconsumidor;
       this.contrasena = contrasena;
       this.telefono = telefono;
       this.prestigioprestador = prestigioprestador;
    }
   
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApepaterno() {
        return this.apepaterno;
    }
    
    public void setApepaterno(String apepaterno) {
        this.apepaterno = apepaterno;
    }
    public String getApematerno() {
        return this.apematerno;
    }
    
    public void setApematerno(String apematerno) {
        this.apematerno = apematerno;
    }
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getColonia() {
        return this.colonia;
    }
    
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    public String getDelegacion() {
        return this.delegacion;
    }
    
    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }
    public Integer getNumero() {
        return this.numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public Integer getCodigopostal() {
        return this.codigopostal;
    }
    
    public void setCodigopostal(Integer codigopostal) {
        this.codigopostal = codigopostal;
    }
    public Set getArticulos() {
        return this.articulos;
    }
    
    public void setArticulos(Set articulos) {
        this.articulos = articulos;
    }
    public Presta getPresta() {
        return this.presta;
    }
    
    public void setPresta(Presta presta) {
        this.presta = presta;
    }
    public Solicita getSolicita() {
        return this.solicita;
    }
    
    public void setSolicita(Solicita solicita) {
        this.solicita = solicita;
    }
    public Prestigioconsumidor getPrestigioconsumidor() {
        return this.prestigioconsumidor;
    }
    
    public void setPrestigioconsumidor(Prestigioconsumidor prestigioconsumidor) {
        this.prestigioconsumidor = prestigioconsumidor;
    }
    public Contrasena getContrasena() {
        return this.contrasena;
    }
    
    public void setContrasena(Contrasena contrasena) {
        this.contrasena = contrasena;
    }
    public Telefono getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }
    public Prestigioprestador getPrestigioprestador() {
        return this.prestigioprestador;
    }
    
    public void setPrestigioprestador(Prestigioprestador prestigioprestador) {
        this.prestigioprestador = prestigioprestador;
    }

    public String toString(){
        return "Nombre " + this.nombre + " Correo " +this.correo;
    }



}


