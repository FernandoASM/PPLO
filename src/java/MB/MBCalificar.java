/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;

import Controlador.EstadoDaoHibernate;
import Controlador.PrestaDaoHibernate;
import Controlador.PrestigioDaoHibernate;
import DAO.Prestigioprestador;
import DAO.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
    
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
import DAO.Estado;
import DAO.Presta;
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
/**
 *
 * @author Rodrigo
 */
public class MBCalificar implements Serializable {
   
    private List<Presta> Prestamos;
    private Presta prestamo;
    
    private List<Prestigioprestador> listaPrestigios;
    private Prestigioprestador Usuarioprestigio;
     private Integer prestigio;
     
         
     private List<Estado> listaEstados;
     private Estado estado1;
     private String EstadoArticulo;
    
     private String correo;
    
    @ManagedProperty("#{mBUsuario}")
    private MBUsuario usuario;
    
    private Usuario usuarioprestador;
    private Usuario usuarioprestador2;
    
    private Usuario usuarioconsumidor;
    private Articulo articulosolicitado;
    
    
    private List<Usuario> Usuarios;
    private Usuario usuarioconsumidor2;
    
    private List<Articulo> articulos;
    private Articulo articulosolicitado2;
    
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    private List<Presta> PrestamosCP;
    private Presta prestamoCP;
    private Usuario usuarioprestador2CC;
    private List<Usuario> UsuariosCC;
    
    
    private List<Prestigioprestador> listaPrestigiosCC;
    private Prestigioprestador UsuarioprestigioCC;
     private Integer prestigioCC;
     
     private Articulo articulosolicitadoCC;
     
     private List<Estado> listaEstadosCC;
     private Estado estado1CC;
     private String EstadoArticuloCC;
     
     private List<Articulo> articulosCC;
    private Articulo articulosolicitado2CC;
     
     /**
     * Creates a new instance of MBNotificacionPrestamo.
     */
    public MBCalificar() {
    }
    
       
    @PostConstruct
    public void init() {
        Prestamos = Prestamos();
        listaEstados = EstadosArticulo();
        listaPrestigios = PrestigiosUsuario();
        Usuarios = UsuarioPrestador();
        articulos = Articulos();
        
        PrestamosCP = PrestamosCP();
        listaEstadosCC = EstadosArticuloCC();
        listaPrestigiosCC = PrestigiosUsuarioCC();
        UsuariosCC = UsuarioPrestadorCC();
        articulosCC = ArticulosCC();

        
    }
        
     /**
     * Método que busca mediante una consulta SQL la lista de Prestamos que tengan como correo el correo del usuario
     * También inicializa los variables y los métodos articulosolicitado, getArticulo
        usuarioprestador 
        listaEstados 
        listaPrestigios 
        Usuarios 
        articulos 
     * @return Una lista de elementos del tipo Presta
     */
    public List<Presta> Prestamos(){
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
            String sql = "Select * from Presta where correo ='"+usuario.getCorreo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Presta.class);
            Prestamos = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        imprimePrestamos(Prestamos);
//        prestamo = Prestamos.get(0);
//        
//        articulosolicitado= prestamo.getArticulo();
//        usuarioprestador2 = prestamo.getUsuarioByCorreoconsumidor();
//        
        
return Prestamos;

     }
    
    
    /**
     * Método que imprime una lista de elementos de tipo Presta
     * @param art Una lista de elementos de tipo Presta.
     */
    public void imprimePrestamos(List<Presta> art){
        for (Presta temp : art) {
                System.out.println(temp.toString());
            
        }
    }
    
    
    
    
    
    
    
    
   /**
     * Método que mediante una consulta SQL devuelve la lista de PrestigioPrestadores
     * y guarda el primer elemento en un Objeto PrestigioPrestadores
     * @return Una lista de elementos del tipo PrestigioPrestador
     */
    public List<Prestigioprestador> PrestigiosUsuario(){
        
        if(Prestamos.isEmpty() == false){
            
        prestamo = Prestamos.get(0);
        usuarioprestador2 = prestamo.getUsuarioByCorreoconsumidor();
        
        //Aqui acabo de cambiar
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
            String sql = "Select * from Prestigioprestador where correo ='"+usuarioprestador2.getCorreo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Prestigioprestador.class);
            setListaPrestigios((List<Prestigioprestador>) query.list());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        imprimePrestigios(getListaPrestigios());
        Usuarioprestigio = getListaPrestigios().get(0);
        setPrestigio(getUsuarioprestigio().getPrestigio());
        return  getListaPrestigios();
        }
return  getListaPrestigios();

     }
    
    
/**
     * Método que imprime una lista de Prestigioprestador
     * * @param art El parámetro art define una la lista de Prestigioprestador
     */     public void imprimePrestigios(List<Prestigioprestador> art){
        for (Prestigioprestador temp : art) {
                System.out.println(temp.toString());
            
        }
    }
    
     
          /**
     * Método que mediante una consulta SQL devuelve la lista de Usuarios, donde el correo el igual al usuarioprestador
     * y guarda el primer elemento en un Objeto Usuario
     * @return Una lista de elementos del tipo Usuario
     */
    public List<Usuario> UsuarioPrestador(){
        
        if(Prestamos.isEmpty() == false){
        prestamo = Prestamos.get(0);
        usuarioprestador2 = prestamo.getUsuarioByCorreoconsumidor();
        //Aqui acabo de cambiar
        
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
            String sql = "Select * from Usuario where correo ='"+usuarioprestador2.getCorreo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Usuario.class);
            setUsuarios((List<Usuario>) query.list());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        usuarioprestador2 = getUsuarios().get(0);
        return  getUsuarios();
        }
return  getUsuarios();

     }
    
  
     
            /**
     * Método que mediante una consulta SQL devuelve la lista de EstadosArticulo, donde el idarticulo el igual al articulosolicitado
     * y guarda el primer elemento en un Objeto Estado
     * @return Una lista de elementos del tipo Estado
     */
     
     public List<Estado> EstadosArticulo(){
         if(Prestamos.isEmpty() == false){
         
            prestamo = Prestamos.get(0);
            articulosolicitado= prestamo.getArticulo();
        
        //Aqui acabo de cambiar
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
            String sql = "Select * from Estado where idarticulo ='"+articulosolicitado.getIdarticulo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Estado.class);
            setListaEstados((List<Estado>) query.list());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        imprimeEstados(getListaEstados());
        estado1 = getListaEstados().get(0);
        EstadoArticulo = estado1.getEstado();
return  getListaEstados();
         }
return  getListaEstados();
     }
    
    
/**
     * Método que imprime una lista de Estados
     * * @param art El parámetro art define una la lista de Estados
     */     public void imprimeEstados(List<Estado> art){
        for (Estado temp : art) {
                System.out.println(temp.toString());
            
        }
    }
     
     
     
           /**
     * Método que mediante una consulta SQL devuelve la lista de Articulos, donde el idarticulo el igual al articulosolicitado
     * y guarda el primer elemento en un Objeto Articulo
     * @return Una lista de elementos del tipo Articulo
     */
     
     public List<Articulo> Articulos(){
         if(Prestamos.isEmpty() == false){
             
         
         prestamo = Prestamos.get(0);
        articulosolicitado= prestamo.getArticulo();
        //Aqui acabbo de cambiar
        
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
            String sql = "Select * from Articulo where idarticulo ='"+articulosolicitado.getIdarticulo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Articulo.class);
             articulos =query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
         articulosolicitado2= articulos.get(0);
         return  getArticulos();
         }
return  getArticulos();

     }
     
    /**
     * Método que guarda la califcacion del Prestador y el estado del articulo
     * y guarda en la base
     * @param usuariocon
     * @param solici
     * @return Redirecciona mediante una variable String a la siguiente vista
     */
     
     public String guarda(Usuario usuariocon,Articulo solici) {
        Prestigioprestador tmp = new Prestigioprestador();
        Estado tmp2 = new Estado();
        String redirecciona ="";
         System.out.println(usuariocon.getCorreo());
        try {
            tmp.setUsuario(usuariocon);
            tmp.setCorreo(usuariocon.getCorreo());
            tmp.setPrestigio(prestigio);
            PrestigioDaoHibernate pretigioDAO = new PrestigioDaoHibernate();
            pretigioDAO.update(tmp);
            
            tmp2.setArticulo(solici);
            tmp2.setIdarticulo(solici.getIdarticulo());
            tmp2.setEstado(EstadoArticulo);
            EstadoDaoHibernate estadoDado = new EstadoDaoHibernate();
            estadoDado.update(tmp2);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se ha guardado la calificacion satisfactoriamente"));
            redirecciona = "administrarCuentaIH";
            
        } catch (Exception e) {
            System.out.println("Error al intentar guardar presta" + e);
            
        }
        return redirecciona;
    }
     
     
  
   
    
        /**
     * Método que inicializa la variable Prestamos mediante el método Prestamos()
     * @return Redirecciona mediante una variable String a la siguiente vista
     */
     
    public String mostrarSolicitud(){
        Prestamos = Prestamos();
        listaEstados = EstadosArticulo();
        listaPrestigios = PrestigiosUsuario();
        Usuarios = UsuarioPrestador();
        articulos = Articulos();
        if(Prestamos == null || Prestamos.isEmpty()==true || listaEstados == null || listaEstados.isEmpty()==true || listaPrestigios == null || listaPrestigios.isEmpty()==true ||Usuarios == null || Usuarios.isEmpty()==true || articulos == null || articulos.isEmpty()==true  ){
        PrestamosCP = PrestamosCP();
        listaEstadosCC = EstadosArticuloCC();
        listaPrestigiosCC = PrestigiosUsuarioCC();
        UsuariosCC = UsuarioPrestadorCC();
        articulosCC = ArticulosCC();
        if(PrestamosCP == null || PrestamosCP.isEmpty()==true || listaEstadosCC == null || listaEstadosCC.isEmpty()==true || listaPrestigiosCC == null || listaPrestigiosCC.isEmpty()==true ||UsuariosCC == null || UsuariosCC.isEmpty()==true || articulosCC == null || articulosCC.isEmpty()==true  ){
            return "sincalificarPrestamoIH";
        }
        PrestamosCP = PrestamosCP();
        listaEstadosCC = EstadosArticuloCC();
        listaPrestigiosCC = PrestigiosUsuarioCC();
        UsuariosCC = UsuarioPrestadorCC();
        articulosCC = ArticulosCC();
        return "calificarPrestamoCCIH";
        }
        Prestamos = Prestamos();
        listaEstados = EstadosArticulo();
        listaPrestigios = PrestigiosUsuario();
        Usuarios = UsuarioPrestador();
        articulos = Articulos();
        return "calificarPrestamoIH";
    }
     
    
    
    
    
    //////////////
     //////////////
    //////////////////////7777
    ///////////////77
    //////////////////7
    //////////////////////////7
    ///////////////////////7
    ////////////////////77777
    /////////////////////777
    ////////////////////////7777
    ////////////////////////////
     //////////////
    //////////////////////7777
    ///////////////77
    //////////////////7
    //////////////////////////7
    ///////////////////////7
    ////////////////////77777
    /////////////////////777
    ////////////////////////7777
    ////////////////////////////
    //////////////////////7777
    ///////////////77
    //////////////////7
    //////////////////////////7
    ///////////////////////7
    ////////////////////77777
    /////////////////////777
    ////////////////////////7777
    ////////////////////////////
    
    
    
    /**
     * Método que busca mediante una consulta SQL la lista de Prestamos que tengan como correo el correo del usuario
     * También inicializa los variables y los métodos articulosolicitado, getArticulo
        usuarioprestador 
        listaEstados 
        listaPrestigios 
        Usuarios 
        articulos 
     * @return Una lista de elementos del tipo Presta
     */
    public List<Presta> PrestamosCP(){
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
            String sql = "Select * from Presta where correoconsumidor ='"+usuario.getCorreo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Presta.class);
            setPrestamosCP((List<Presta>) query.list());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
//        prestamo = Prestamos.get(0);
//        
//        articulosolicitado= prestamo.getArticulo();
//        usuarioprestador2 = prestamo.getUsuarioByCorreoconsumidor();
//        
        
return  getPrestamosCP();

     }
    
    
    
   /**
     * Método que mediante una consulta SQL devuelve la lista de PrestigioPrestadores
     * y guarda el primer elemento en un Objeto PrestigioPrestadores
     * @return Una lista de elementos del tipo PrestigioPrestador
     */
    public List<Prestigioprestador> PrestigiosUsuarioCC(){
        
        if(getPrestamosCP().isEmpty() == false){
            
            setPrestamoCP(getPrestamosCP().get(0));
            setUsuarioprestador2CC(getPrestamoCP().getUsuarioByCorreo());
        
        //Aqui acabo de cambiar
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
            String sql = "Select * from Prestigioprestador where correo ='"+usuarioprestador2CC.getCorreo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Prestigioprestador.class);
            setListaPrestigiosCC((List<Prestigioprestador>) query.list());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        UsuarioprestigioCC = getListaPrestigiosCC().get(0);
        setPrestigioCC(getUsuarioprestigioCC().getPrestigio());
        return  getListaPrestigiosCC();
        }
return  getListaPrestigiosCC();

     }
    
    
     
          /**
     * Método que mediante una consulta SQL devuelve la lista de Usuarios, donde el correo el igual al usuarioprestador
     * y guarda el primer elemento en un Objeto Usuario
     * @return Una lista de elementos del tipo Usuario
     */
    public List<Usuario> UsuarioPrestadorCC(){
        
        if(PrestamosCP.isEmpty() == false){
        prestamoCP = PrestamosCP.get(0);
        usuarioprestador2CC = prestamoCP.getUsuarioByCorreo();
        //Aqui acabo de cambiar
        
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
            String sql = "Select * from Usuario where correo ='"+usuarioprestador2CC.getCorreo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Usuario.class);
            setUsuariosCC((List<Usuario>) query.list());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        usuarioprestador2CC = getUsuariosCC().get(0);
        return  getUsuariosCC();
        }
return  getUsuariosCC();

     }
    
  
     
            /**
     * Método que mediante una consulta SQL devuelve la lista de EstadosArticulo, donde el idarticulo el igual al articulosolicitado
     * y guarda el primer elemento en un Objeto Estado
     * @return Una lista de elementos del tipo Estado
     */
     
     public List<Estado> EstadosArticuloCC(){
         if(PrestamosCP.isEmpty() == false){
         
            prestamoCP = PrestamosCP.get(0);
            articulosolicitadoCC = prestamoCP.getArticulo();
        
        //Aqui acabo de cambiar
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
            String sql = "Select * from Estado where idarticulo ='"+articulosolicitadoCC.getIdarticulo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Estado.class);
            setListaEstadosCC((List<Estado>) query.list());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        estado1CC = getListaEstadosCC().get(0);
        EstadoArticuloCC = estado1CC.getEstado();
return  getListaEstadosCC();
         }
return  getListaEstadosCC();
     }
    
    

     
     
           /**
     * Método que mediante una consulta SQL devuelve la lista de Articulos, donde el idarticulo el igual al articulosolicitado
     * y guarda el primer elemento en un Objeto Articulo
     * @return Una lista de elementos del tipo Articulo
     */
     
     public List<Articulo> ArticulosCC(){
         if(PrestamosCP.isEmpty() == false){
             
         
         prestamoCP = PrestamosCP.get(0);
        articulosolicitadoCC= prestamoCP.getArticulo();
        //Aqui acabbo de cambiar
        
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
            String sql = "Select * from Articulo where idarticulo ='"+articulosolicitadoCC.getIdarticulo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Articulo.class);
             articulosCC =query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
         articulosolicitado2CC= articulosCC.get(0);
         return  getArticulosCC();
         }
return  getArticulosCC();

     }
  
    
    
     /**
     * Método que guarda la califcacion del Prestador y el estado del articulo
     * y guarda en la base
     * @param usuariocon
     * @param solici
     * @return Redirecciona mediante una variable String a la siguiente vista
     */
     
     public String guardaCC(Usuario usuariocon,Articulo solici) {
        Prestigioprestador tmp = new Prestigioprestador();
        Estado tmp2 = new Estado();
        String redirecciona ="";
         System.out.println(usuariocon.getCorreo());
        try {
            tmp.setUsuario(usuariocon);
            tmp.setCorreo(usuariocon.getCorreo());
            tmp.setPrestigio(prestigioCC);
            PrestigioDaoHibernate pretigioDAO = new PrestigioDaoHibernate();
            pretigioDAO.update(tmp);
            
            tmp2.setArticulo(solici);
            tmp2.setIdarticulo(solici.getIdarticulo());
            tmp2.setEstado(EstadoArticuloCC);
            EstadoDaoHibernate estadoDado = new EstadoDaoHibernate();
            estadoDado.update(tmp2);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se ha guardado la calificacion satisfactoriamente"));
            redirecciona = "administrarCuentaIH";
            
        } catch (Exception e) {
            System.out.println("Error al intentar guardar presta" + e);
            
        }
        return redirecciona;
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
     * @return the listaEstados
     */
    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    /**
     * @param listaEstados the listaEstados to set
     */
    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    /**
     * @return the estado
     */
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
     * @return the Prestamos
     */
    public List<Presta> getPrestamos() {
        return Prestamos;
    }

    /**
     * @param Prestamos the Prestamos to set
     */
    public void setPrestamos(List<Presta> Prestamos) {
        this.Prestamos = Prestamos;
    }

    /**
     * @return the prestamo
     */
    public Presta getPrestamo() {
        return prestamo;
    }

    /**
     * @param prestamo the prestamo to set
     */
    public void setPrestamo(Presta prestamo) {
        this.prestamo = prestamo;
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
     * @return the EstadoArticulo
     */
    public String getEstadoArticulo() {
        return EstadoArticulo;
    }

    /**
     * @param EstadoArticulo the EstadoArticulo to set
     */
    public void setEstadoArticulo(String EstadoArticulo) {
        this.EstadoArticulo = EstadoArticulo;
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
     * @return the usuarioconsumidor2
     */
    public Usuario getUsuarioconsumidor2() {
        return usuarioconsumidor2;
    }

    /**
     * @param usuarioconsumidor2 the usuarioconsumidor2 to set
     */
    public void setUsuarioconsumidor2(Usuario usuarioconsumidor2) {
        this.usuarioconsumidor2 = usuarioconsumidor2;
    }

    /**
     * @return the articulos
     */
    public List<Articulo> getArticulos() {
        return articulos;
    }

    /**
     * @param articulos the articulos to set
     */
    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    /**
     * @return the articulosolicitado2
     */
    public Articulo getArticulosolicitado2() {
        return articulosolicitado2;
    }

    /**
     * @param articulosolicitado2 the articulosolicitado2 to set
     */
    public void setArticulosolicitado2(Articulo articulosolicitado2) {
        this.articulosolicitado2 = articulosolicitado2;
    }

    /**
     * @return the estado1
     */
    public Estado getEstado1() {
        return estado1;
    }

    /**
     * @param estado1 the estado1 to set
     */
    public void setEstado1(Estado estado1) {
        this.estado1 = estado1;
    }

    /**
     * @return the usuarioprestador
     */
    public Usuario getUsuarioprestador() {
        return usuarioprestador;
    }

    /**
     * @param usuarioprestador the usuarioprestador to set
     */
    public void setUsuarioprestador(Usuario usuarioprestador) {
        this.usuarioprestador = usuarioprestador;
    }

    /**
     * @return the Usuarios
     */
    public List<Usuario> getUsuarios() {
        return Usuarios;
    }

    /**
     * @param Usuarios the Usuarios to set
     */
    public void setUsuarios(List<Usuario> Usuarios) {
        this.Usuarios = Usuarios;
    }

    /**
     * @return the usuarioprestador2
     */
    public Usuario getUsuarioprestador2() {
        return usuarioprestador2;
    }

    /**
     * @param usuarioprestador2 the usuarioprestador2 to set
     */
    public void setUsuarioprestador2(Usuario usuarioprestador2) {
        this.usuarioprestador2 = usuarioprestador2;
    }

    /**
     * @return the PrestamosCP
     */
    public List<Presta> getPrestamosCP() {
        return PrestamosCP;
    }

    /**
     * @param PrestamosCP the PrestamosCP to set
     */
    public void setPrestamosCP(List<Presta> PrestamosCP) {
        this.PrestamosCP = PrestamosCP;
    }

    /**
     * @return the prestamoCP
     */
    public Presta getPrestamoCP() {
        return prestamoCP;
    }

    /**
     * @param prestamoCP the prestamoCP to set
     */
    public void setPrestamoCP(Presta prestamoCP) {
        this.prestamoCP = prestamoCP;
    }

    /**
     * @return the usuarioprestador2CC
     */
    public Usuario getUsuarioprestador2CC() {
        return usuarioprestador2CC;
    }

    /**
     * @param usuarioprestador2CC the usuarioprestador2CC to set
     */
    public void setUsuarioprestador2CC(Usuario usuarioprestador2CC) {
        this.usuarioprestador2CC = usuarioprestador2CC;
    }

    /**
     * @return the listaPrestigiosCC
     */
    public List<Prestigioprestador> getListaPrestigiosCC() {
        return listaPrestigiosCC;
    }

    /**
     * @param listaPrestigiosCC the listaPrestigiosCC to set
     */
    public void setListaPrestigiosCC(List<Prestigioprestador> listaPrestigiosCC) {
        this.listaPrestigiosCC = listaPrestigiosCC;
    }

    /**
     * @return the UsuarioprestigioCC
     */
    public Prestigioprestador getUsuarioprestigioCC() {
        return UsuarioprestigioCC;
    }

    /**
     * @param UsuarioprestigioCC the UsuarioprestigioCC to set
     */
    public void setUsuarioprestigioCC(Prestigioprestador UsuarioprestigioCC) {
        this.UsuarioprestigioCC = UsuarioprestigioCC;
    }

    /**
     * @return the prestigioCC
     */
    public Integer getPrestigioCC() {
        return prestigioCC;
    }

    /**
     * @param prestigioCC the prestigioCC to set
     */
    public void setPrestigioCC(Integer prestigioCC) {
        this.prestigioCC = prestigioCC;
    }

    /**
     * @return the UsuariosCC
     */
    public List<Usuario> getUsuariosCC() {
        return UsuariosCC;
    }

    /**
     * @param UsuariosCC the UsuariosCC to set
     */
    public void setUsuariosCC(List<Usuario> UsuariosCC) {
        this.UsuariosCC = UsuariosCC;
    }

    /**
     * @return the articulosolicitadoCC
     */
    public Articulo getArticulosolicitadoCC() {
        return articulosolicitadoCC;
    }

    /**
     * @param articulosolicitadoCC the articulosolicitadoCC to set
     */
    public void setArticulosolicitadoCC(Articulo articulosolicitadoCC) {
        this.articulosolicitadoCC = articulosolicitadoCC;
    }

    /**
     * @return the listaEstadosCC
     */
    public List<Estado> getListaEstadosCC() {
        return listaEstadosCC;
    }

    /**
     * @param listaEstadosCC the listaEstadosCC to set
     */
    public void setListaEstadosCC(List<Estado> listaEstadosCC) {
        this.listaEstadosCC = listaEstadosCC;
    }

    /**
     * @return the estado1CC
     */
    public Estado getEstado1CC() {
        return estado1CC;
    }

    /**
     * @param estado1CC the estado1CC to set
     */
    public void setEstado1CC(Estado estado1CC) {
        this.estado1CC = estado1CC;
    }

    /**
     * @return the EstadoArticuloCC
     */
    public String getEstadoArticuloCC() {
        return EstadoArticuloCC;
    }

    /**
     * @param EstadoArticuloCC the EstadoArticuloCC to set
     */
    public void setEstadoArticuloCC(String EstadoArticuloCC) {
        this.EstadoArticuloCC = EstadoArticuloCC;
    }

    /**
     * @return the articulosCC
     */
    public List<Articulo> getArticulosCC() {
        return articulosCC;
    }

    /**
     * @param articulosCC the articulosCC to set
     */
    public void setArticulosCC(List<Articulo> articulosCC) {
        this.articulosCC = articulosCC;
    }

    /**
     * @return the articulosolicitado2CC
     */
    public Articulo getArticulosolicitado2CC() {
        return articulosolicitado2CC;
    }

    /**
     * @param articulosolicitado2CC the articulosolicitado2CC to set
     */
    public void setArticulosolicitado2CC(Articulo articulosolicitado2CC) {
        this.articulosolicitado2CC = articulosolicitado2CC;
    }

    
    

    
}
