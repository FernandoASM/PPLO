
package MB;

import Controlador.ContrasenaDaoHibernate;
import Controlador.UsuarioDaoHibernate;
import DAO.Contrasena;
import DAO.Usuario;
import DAO.Telefono;


import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Rodrigo
 */
@ManagedBean
@RequestScoped


public class MBContrasena {
private String correo;
    private String nombre;
    private String appPaterno;
    private String appMaterno;
    private String calle;
    private String colonia;
    private String delegacion;
    private int num;
    private int codigopostal;
    
    private String contrasena;
    private int telefono;
    
    private String msn;
        
//    private HttpServletRequest httpServletRequest;
//    private FacesContext faceContext;
    
    
    
    public MBContrasena(){
        
    //faceContext  = FacesContext.getCurrentInstance();
  //  httpServletRequest = (HttpServletRequest)faceContext.getExternalContext().getRequest();
    
    //if(httpServletRequest.getSession().getAttribute("SessionUsuario")!= null){
//        usuario = (Usuario) httpServletRequest.getSession().getAttribute("SessionUsuario");
    //}
}

    //public String cerrarSesion(){
//        getHttpServletRequest().getSession().removeAttribute("sessionUsuario");
//        setMsn("Sesión Cerrada correctamente" );
//        return "index";
  //  }
    
    
    public String inicioSesion(){
  List<Usuario> listUsuario;
  UsuarioDaoHibernate usuarioDao = new UsuarioDaoHibernate();
  listUsuario= usuarioDao.findAll();
  String saludo = "";
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
            
            
        setMsn("Hola "+ usuarioD.getNombre() + " has iniciado Sesión" );
        saludo= "administrarCuentaIH";            
        
        break;
            }else {
                setMsn("Correo o contraseña incorrecta");
                saludo= "index";                    
          }    
        }
        return saludo;                
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
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the codigopostal
     */
    public int getCodigopostal() {
        return codigopostal;
    }

    /**
     * @param codigopostal the codigopostal to set
     */
    public void setCodigopostal(int codigopostal) {
        this.codigopostal = codigopostal;
    }

    /**
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    
}