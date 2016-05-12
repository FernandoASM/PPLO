/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;
import Controlador.ContrasenaDaoHibernate;
import Controlador.TelefonoDaoHibernate;
import Controlador.UsuarioDaoHibernate;
import DAO.Contrasena;
import DAO.Telefono;
import DAO.Usuario;

import java.io.Serializable;
import java.util.List;
import javassist.bytecode.analysis.Util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.faces.bean.ViewScoped;



/**
 *
 * @author Rodrigo
 */

@ManagedBean
//@RequestScoped
//@ViewScoped
@SessionScoped




public class MBUsuario implements Serializable{
    private String correo;
    private String nombre;
    private String appPaterno;
    private String appMaterno;
    private String calle;
    private String colonia;
    private String delegacion;
    private Integer num;
    private Integer codigopostal;
    
    private String contrasena;
    private Integer telefono;
    
    private String msn;
    
    
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    
   private Usuario usuario;
 

  public MBUsuario (){
    
    faceContext = FacesContext.getCurrentInstance();
   httpServletRequest  = (HttpServletRequest)faceContext.getExternalContext().getRequest();
}
    
    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the appPaterno
     */
    public String getAppPaterno() {
        return appPaterno;
    }

    /**
     * @param appPaterno the appPaterno to set
     */
    public void setAppPaterno(String appPaterno) {
        this.appPaterno = appPaterno;
    }

    /**
     * @return the appMaterno
     */
    public String getAppMaterno() {
        return appMaterno;
    }

    /**
     * @param appMaterno the appMaterno to set
     */
    public void setAppMaterno(String appMaterno) {
        this.appMaterno = appMaterno;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the delegacion
     */
    public String getDelegacion() {
        return delegacion;
    }

    /**
     * @param delegacion the delegacion to set
     */
    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    /**
     * @return the num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * @return the codigopostal
     */
    public Integer getCodigopostal() {
        return codigopostal;
    }

    /**
     * @param codigopostal the codigopostal to set
     */
    public void setCodigopostal(Integer codigopostal) {
        this.codigopostal = codigopostal;
    }
    
    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the telefono
     */
    public Integer getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    

    /**
     * @return the msn
     */
    public String getMsn() {
        return msn;
    }

    /**
     * @param msn the msn to set
     */
    public void setMsn(String msn) {
        this.msn = msn;
    }
    
       
    public String guarda() {
        Usuario tmp = new Usuario();
        Telefono tmp1 = new Telefono();
        Contrasena tmp2 = new Contrasena();
        String redirecciona = "";
        try {
            tmp.setCorreo(getCorreo());
            tmp.setNombre(getNombre());
            tmp.setApepaterno(appPaterno);
            tmp.setApematerno(appMaterno);
            tmp.setCalle(calle);
            tmp.setColonia(colonia);
            tmp.setDelegacion(delegacion);
            tmp.setNumero(num);
            tmp.setCodigopostal(codigopostal);
            
            UsuarioDaoHibernate usuarioDAO = new UsuarioDaoHibernate();
            usuarioDAO.save(tmp);
            
            TelefonoDaoHibernate telefonoDao = new TelefonoDaoHibernate();
            tmp1.setUsuario(tmp);
            tmp1.setTelefono(telefono);
            telefonoDao.save(tmp1);
            
            ContrasenaDaoHibernate contrasenaDao = new ContrasenaDaoHibernate();
            tmp2.setUsuario(tmp);
            tmp2.setContrasena(contrasena);
            contrasenaDao.save(tmp2);
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La cuenta se ha creado satisfactoriamente"));
            redirecciona = "inicioSesionIH";
        } catch (Exception e) {
            System.out.println("Hubo un error al intentar crear la cuenta" + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Hubo un error al intentar crear la cuenta"));
            redirecciona = "registroIH";
        }
        
        
        return redirecciona;
    }
    
       public String actualizar() {
        Usuario tmp = new Usuario();
        Telefono tmp1 = new Telefono();
        Contrasena tmp2 = new Contrasena();
        String redirecciona ="";
        try {
            tmp.setNombre(nombre);
            tmp.setCorreo(correo);
            tmp.setApematerno(appMaterno);
            tmp.setApepaterno(appPaterno);
            tmp.setCalle(calle);
            tmp.setCodigopostal(codigopostal);
            tmp.setColonia(colonia);
            tmp.setDelegacion(delegacion);
            tmp.setNumero(num);
            
            UsuarioDaoHibernate usuarioDAO = new UsuarioDaoHibernate();
            usuarioDAO.update(tmp);
            
            TelefonoDaoHibernate telefonoDao = new TelefonoDaoHibernate();
            tmp1.setUsuario(tmp);
            tmp1.setCorreo(correo);
            tmp1.setTelefono(telefono);
            telefonoDao.update(tmp1);
            
            ContrasenaDaoHibernate contrasenaDao = new ContrasenaDaoHibernate();
            tmp2.setUsuario(tmp);
            tmp2.setCorreo(correo);
            tmp2.setContrasena(contrasena);
            contrasenaDao.update(tmp2);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El usuario se guardo correctamente"));
            redirecciona = "administrarCuentaIH";
        } catch (Exception e) {

            msn = "upss! Ocurrio un error " + e;
            System.out.println(" upss! Ocurrio un error.  " + e);
            redirecciona = "administrarCuenta2IH";
        }
        return redirecciona;
    }
 
    
    
    public String inicioSesion(){
  List<Usuario> listUsuario;
  UsuarioDaoHibernate usuarioDao = new UsuarioDaoHibernate();
  listUsuario= usuarioDao.findAll();
  String saludo = "";
  String nada ="";
        for (Usuario usuarioD : listUsuario) {
         if(this.correo.equals(usuarioD.getCorreo()) && this.contrasena.equals(usuarioD.getContrasena().getContrasena())){
             System.out.println(usuarioD.toString());
            this.nombre = usuarioD.getNombre();
            this.appMaterno = usuarioD.getApematerno();
            this.appPaterno = usuarioD.getApepaterno();
            this.calle = usuarioD.getCalle();
            this.codigopostal = usuarioD.getCodigopostal();
            this.colonia = usuarioD.getColonia();
            this.delegacion = usuarioD.getDelegacion();
            this.contrasena = usuarioD.getContrasena().getContrasena();
            this.num = usuarioD.getNumero();
            this.telefono = usuarioD.getTelefono().getTelefono();
            
            usuario = new Usuario(usuarioD.getCorreo(),usuarioD.getNombre(),usuarioD.getApematerno(),usuarioD.getApematerno(),usuarioD.getCalle(),usuarioD.getColonia(),usuarioD.getDelegacion(),usuarioD.getNumero(),usuarioD.getCodigopostal(),usuarioD.getTelefono(), usuarioD.getContrasena());
        
            //httpServletRequest.getSession().setAttribute("sessionUsuario", usuario);
            //faceContext.addMessage("sessionUsuario", new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Hola "+ usuarioD.getNombre() + " has iniciado Sesión"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Hola "+ usuarioD.getNombre() + " has iniciado Sesión"));
            saludo= "administrarCuentaIH";            
        
        break;
            }else {
             
            saludo= "inicioSesionIH";                    
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Correo o Contraseña incorrecta"));
            
          }
         
        }
        return saludo;                
    }
    
    
    
    public String cerrarSesion(){
//        httpServletRequest.getSession().removeAttribute("sessionUsuario");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sesión Cerrada correctamente"));
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();        
        return "homeIH";
    }
    
    
    public String eliminarUsuario(){
        UsuarioDaoHibernate usuarioDAO = new UsuarioDaoHibernate();
        List<Usuario> lista = usuarioDAO.findAll();
        for (Usuario temp:  lista) {
            if (this.correo.equals((temp.getCorreo()))) {
                usuarioDAO.delete(temp);
                break;
            }   
            
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            
                
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La cuenta ha sido eliminada"));
        return "homeIH";
        
    }
    
    
    
    
    

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
    

    

