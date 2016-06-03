/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;
import Controlador.UsuarioDaoHibernate;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;




import DAO.Articulo;
import DAO.Instrumento;
import DAO.Libro;
import DAO.Usuario;
import DAO.Musica;
import DAO.Sonido;
import DAO.Accesorio;
import DAO.Solicita;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Rodrigo
 */
@ManagedBean
@RequestScoped 
public class MBNotificacionPrestamo implements Serializable {

    
    
    private String correo;
    private String nombre;
    private String appPaterno;
    private String appMaterno;
    private String calle;
    private String colonia;
    private String delegacion;
    private Integer num;
    private Integer codigopostal;
    
    private Integer telefono;
    
    
    //Articulo
    private Integer idarticulo;
    private boolean disponible;
    private String descripcion;
    private String categoria;
    private String rutaimagen;
    
    private List<Solicita> listaArticuloSolicitado;
    
    private List<Usuario> listaConsumidor;
    private List<Articulo> listaArticulo;
    
    private Solicita solicitud;
    
    private Usuario usuarioconsumidor;
    private Articulo articulosolicitado;
    
    @ManagedProperty("#{mBUsuario}")
    private MBUsuario usuario;
    
     
    /**
     * Creates a new instance of MBNotificacionPrestamo
     */
    public MBNotificacionPrestamo() {
    }
    
     /**
     * Método que busca mediante una consulta SQL la lista de Solicitudes donde 
     * el correo del Usuario que inicio sesion corresponde con quien solicito el Articulo 
     * @return Una lista de elementos del tipo Usuario
     */
    public List<Solicita> articulosSolicitado(){
                    SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "Select * from Solicita where correo ='"+usuario.getCorreo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Solicita.class);
            listaArticuloSolicitado = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        imprime(listaArticuloSolicitado);
return listaArticuloSolicitado;

     }
    
    
/**
     * Método que imprime una lista de Solicita
     * * @param art El parámetro art define una la lista de Solicita
     */      
        public void imprime(List<Solicita> art){
        for (Solicita temp : art) {
                System.out.println(temp.toString());
            
        }
    }
    
    /**
     * Método que busca mediante una consulta SQL la lista de Usuario donde la solicitud de prestamo corresponde con 
     * el correo del Usuario que solicito el Articulo y guarda el primer elemento de la lista en un Objeto del tipo Usuario.
     * @return Una lista de elementos del tipo Usuario
     */
     public List<Usuario> listaConsumidor(){
         
          solicitud = listaArticuloSolicitado.get(0);
                    SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "Select * from Usuario where correo ='"+solicitud.getUsuarioByCorreosolicita().getCorreo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Usuario.class);
            listaConsumidor = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        imprimeUsuario(listaConsumidor);
        usuarioconsumidor = listaConsumidor.get(0);
        correo = usuarioconsumidor.getCorreo();
return listaConsumidor;
     }
    
/**
     * Método que imprime una lista de Usuario
     * * @param art El parámetro art define una la lista de Usuario
     */      
        public void imprimeUsuario(List<Usuario> art){
        for (Usuario temp : art) {
                System.out.println(temp.toString());
            
        }
    }
     
/**
     * Método que busca mediante una consulta SQL la lista de articulos donde la solicitud de prestamo corresponde con 
     * el id del articulo y guarda el primer elemento de la lista en un Objeto del tipo Articulo.
     * @return Una lista de elementos del tipo Articulo
     */
     public List<Articulo> listaArticulo(){
         
          solicitud = listaArticuloSolicitado.get(0);
         
                    SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "Select * from Articulo where idarticulo ='"+solicitud.getArticulo().getIdarticulo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Articulo.class);
            listaArticulo = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        imprimeArticulo(listaArticulo);
        articulosolicitado = listaArticulo.get(0);
return listaArticulo;
     }
     
/**
     * Método que imprime una lista de Articulos
     * * @param art El parámetro art define una la lista de Articulos
     */          
     public void imprimeArticulo(List<Articulo> art){
        for (Articulo temp : art) {
                System.out.println(temp.toString());
            
        }
    }
     
     
     
    
       @PostConstruct
    public void init() {
        listaArticuloSolicitado = articulosSolicitado();
//        listaConsumidor = listaConsumidor();
//        listaArticulo = listaArticulo();
        
    }

    
    /**
     * Método que inicializa las variables listaArticuloSolicitado,listaConsumidor y listaArticulo mediante los métodos articulosSolicitado,listaConsumidor y listaArticulo,
     * y redirecciona a la siguiente página
     * @return Redirecciona a la siguiente vista mediante una cadena String
     */
    public String mostrarSolicitud(){
        listaArticuloSolicitado = articulosSolicitado();
        if(listaArticuloSolicitado == null || listaArticuloSolicitado.isEmpty()==true){
            return "sinnotificacionPrestamoIH";
        }
        listaArticuloSolicitado = articulosSolicitado();
        listaConsumidor = listaConsumidor();
        listaArticulo = listaArticulo();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "¡Has recibido una nueva solictud de Prestamo!"));
        return "notificacionPrestamoIH";
    }
    
    
    /**
     * @return the listaArticuloSolicitado
     */
    public List<Solicita> getListaArticuloSolicitado() {
        return listaArticuloSolicitado;
    }

    /**
     * @param listaArticuloSolicitado the listaArticuloSolicitado to set
     */
    public void setListaArticuloSolicitado(List<Solicita> listaArticuloSolicitado) {
        this.listaArticuloSolicitado = listaArticuloSolicitado;
    }

    /**
     * @return the listaConsumidor
     */
    public List<Usuario> getListaConsumidor() {
        return listaConsumidor;
    }

    /**
     * @param listaConsumidor the listaConsumidor to set
     */
    public void setListaConsumidor(List<Usuario> listaConsumidor) {
        this.listaConsumidor = listaConsumidor;
    }

    /**
     * @return the usuarioconsumidor
     */
    public Usuario getUsuarioconsumidor() {
        return usuarioconsumidor;
    }

    /**
     * @param usuarioconsumidor the usuarioconsumidor to set
     */
    public void setUsuarioconsumidor(Usuario usuarioconsumidor) {
        this.usuarioconsumidor = usuarioconsumidor;
    }

    /**
     * @return the articulosolicitado
     */
    public Articulo getArticulosolicitado() {
        return articulosolicitado;
    }

    /**
     * @param articulosolicitado the articulosolicitado to set
     */
    public void setArticulosolicitado(Articulo articulosolicitado) {
        this.articulosolicitado = articulosolicitado;
    }

    /**
     * @return the usuario
     */
    public MBUsuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(MBUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the solicitud
     */
    public Solicita getSolicitud() {
        return solicitud;
    }

    /**
     * @param solicitud the solicitud to set
     */
    public void setSolicitud(Solicita solicitud) {
        this.solicitud = solicitud;
    }

    /**
     * @return the listaArticulo
     */
    public List<Articulo> getListaArticulo() {
        return listaArticulo;
    }

    /**
     * @param listaArticulo the listaArticulo to set
     */
    public void setListaArticulo(List<Articulo> listaArticulo) {
        this.listaArticulo = listaArticulo;
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
     * @return the idarticulo
     */
    public Integer getIdarticulo() {
        return idarticulo;
    }

    /**
     * @param idarticulo the idarticulo to set
     */
    public void setIdarticulo(Integer idarticulo) {
        this.idarticulo = idarticulo;
    }

    /**
     * @return the disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the rutaimagen
     */
    public String getRutaimagen() {
        return rutaimagen;
    }

    /**
     * @param rutaimagen the rutaimagen to set
     */
    public void setRutaimagen(String rutaimagen) {
        this.rutaimagen = rutaimagen;
    }
    
    
}
