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
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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
    
    
//@ManagedProperty("#{mBEnviarSolicitud}")
//    private MBEnviarSolicitud enviarsolicitud;
 
    
    private List<Solicita> listaArticuloSolicitado;
    private List<Usuario> listaConsumidor;
    
    private Usuario usuarioconsumidor;
    private Articulo articulosolicitado;
    
    @ManagedProperty("#{mBUsuario}")
    private MBUsuario usuario;
    
//Usuario
    
    
     
    /**
     * Creates a new instance of MBNotificacionPrestamo
     */
    public MBNotificacionPrestamo() {
    }
    
    
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
return listaArticuloSolicitado;
     }
    
    
    
     public List<Usuario> listaConsumidor(){
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
            String sql = "Select * from Usuario where correo ='"+usuario.getCorreo()+"'";
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
return listaConsumidor;
     }
    
    
       @PostConstruct
    public void init() {
        listaArticuloSolicitado = articulosSolicitado();
        
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
    
    
}
