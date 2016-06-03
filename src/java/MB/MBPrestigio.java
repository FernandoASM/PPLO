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
import DAO.Prestigioprestador;
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
//@SessionScoped 
@RequestScoped 
public class MBPrestigio implements Serializable {
   
    
    private List<Prestigioprestador> listaPrestigios;
    private Prestigioprestador Usuarioprestigio;
    
    private List<Usuario> listaPrestador;
    private Integer prestigio;
    
    
    @ManagedProperty("#{mBUsuario}")
    private MBUsuario usuario;
    
     /**
     * Creates a new instance of MBNotificacionPrestamo
     */
    public MBPrestigio() {
    }
    
    public List<Prestigioprestador> PrestigiosUsuario(){
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
            String sql = "Select * from Prestigioprestador where correo ='"+Usuarioprestigio.getUsuario().getCorreo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Prestigioprestador.class);
            listaPrestigios = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        imprimePrestigios(listaPrestigios);
        Usuarioprestigio = listaPrestigios.get(0);
        prestigio = Usuarioprestigio.getPrestigio();
return listaPrestigios;

     }
    
    
      //IMPRIME SOLICTUDES DEL USUARIO EN SESION
     public void imprimePrestigios(List<Prestigioprestador> art){
        for (Prestigioprestador temp : art) {
                System.out.println(temp.toString());
            
        }
    }
    
    
    
    public List<Usuario> UsuarioPrestigio(){
        Usuarioprestigio = listaPrestigios.get(0);
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
            String sql = "Select * from Usuario where correo ='"+Usuarioprestigio.getUsuario().getCorreo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Articulo.class);
            listaPrestador = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        imprimeUsuario(listaPrestador);
        System.out.println(Usuarioprestigio.getPrestigio());
return listaPrestador;
     }
    
    
    public void imprimeUsuario(List<Usuario> art){
        for (Usuario temp : art) {
                System.out.println(temp.toString());
            
        }
    }

    
       @PostConstruct
    public void init() {
        listaPrestigios = PrestigiosUsuario();
        listaPrestador = UsuarioPrestigio();
        
    }
    
    public String mostrarCalificacion(){
        return "notificacionPrestamoIH";
    }
    
    
    
    /**
     * @return the listaPrestigios
     */
    public List<Prestigioprestador> getListaPrestigios() {
        return listaPrestigios;
    }

    /**
     * @param listaPrestigios the listaPrestigios to set
     */
    public void setListaPrestigios(List<Prestigioprestador> listaPrestigios) {
        this.listaPrestigios = listaPrestigios;
    }

    /**
     * @return the Usuarioprestigio
     */
    public Prestigioprestador getUsuarioprestigio() {
        return Usuarioprestigio;
    }

    /**
     * @param Usuarioprestigio the Usuarioprestigio to set
     */
    public void setUsuarioprestigio(Prestigioprestador Usuarioprestigio) {
        this.Usuarioprestigio = Usuarioprestigio;
    }

    /**
     * @return the listaPrestador
     */
    public List<Usuario> getListaPrestador() {
        return listaPrestador;
    }

    /**
     * @param listaPrestador the listaPrestador to set
     */
    public void setListaPrestador(List<Usuario> listaPrestador) {
        this.listaPrestador = listaPrestador;
    }

    /**
     * @return the prestigio
     */
    public Integer getPrestigio() {
        return prestigio;
    }

    /**
     * @param prestigio the prestigio to set
     */
    public void setPrestigio(Integer prestigio) {
        this.prestigio = prestigio;
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
  
    
    
    
}
