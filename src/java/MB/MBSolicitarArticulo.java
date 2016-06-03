/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;


import Controlador.ArticuloDaoHibernate;
import Controlador.InstrumentoDaoHibernate;
import Controlador.LibroDaoHibernate;
import Controlador.MusicaDaoHibernate;
import Controlador.SonidoDaoHibernate;
import Controlador.AccesorioDaoHibernate;
import Controlador.HibernateFactory;
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
import DAO.Prestigioprestador;

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
@SessionScoped 
public class MBSolicitarArticulo implements Serializable {
    
//Usuario
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
    //Instrumento
    private Integer idinstrumento;
    private Integer anoinstrumento;
    private String marcainstrumento;
    private String tipoinstrumento;
    private String nombreinstrumento;
    
    //Libro
    private Integer idlibro;
    private String editoriallibro;
    private String nombreautorlibro;
    private String nombrelibro;
    private String generolibro;
    private Integer anolibro;
    
    //Musica
    private Integer idmusica;
    private String autormusica;
    private String generomusica;
    private Integer anomusica;
    private String formatomusica;
    
    //Sonido
    private Integer idsonido;
    private String nombresonido;
    private String tiposonido;
    private Double potenciasonido;
    private String marcasonido;
    
    //Accesorio
    private Integer idaccesorio;
    private String tipoaccesorio;
    private String nombreaccesorio;
    private String marcaaccesorio;
    
    
    private Articulo articulo;
    
    private Instrumento instrumento;
    private Libro libro;
    private Musica musica;
    private Sonido sonido;
    private Accesorio accesorio;
    
    private Usuario usuario1;
    
    private Usuario usuariolista;
    
    private List<Articulo> listaTodo;
    private List<Usuario> listaUsuario;
    
    
    private List<Prestigioprestador> listaPrestigios;
    private Prestigioprestador Usuarioprestigio;
     private Integer prestigio;
     
     private List<Estado> listaEstados;
     private Estado estado;
     private String Estado;
     
    /**
     * Creates a new instance of MBSolicitarArticulo
     */
    public MBSolicitarArticulo() {
    }
    
        
    /**
     * Método que busca mediante una consulta SQL la lista de Usuarios donde 
     * el correo del Usuario que inicio sesion corresponde con quien esta prestando el articuloprestamo 
     * para ver la informacion tanto del Articulo como del prestador
     * @param articuloprestamo El párametro articuloprestamo 
     * @return Una lista de elementos del tipo Usuario
     */
    public String verInformacionArticulo(Articulo articuloprestamo){
        Articulo tmp = new Articulo();
        System.out.println("antes del set articulo correcto");
        articulo = articuloprestamo;
        System.out.println("set articulo correcto");
        //usuario1 = articulo.getUsuario();
        usuario1 = articulo.getUsuario();
        
        //Usuario
        //Solo tenia el correo del usuario en la lista de articulos
        //guardo el correo que esta guardado en la lista
        correo = usuario1.getCorreo();
        System.out.println(usuario1.getCorreo());
        System.out.println("set usuario correcto");
        
        //con este query obtengo la lista de un solo usuario que tiene ese correo
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
            String sql = "Select * from Usuario where correo = '"+correo+"'";//Aqui esta el correo
            SQLQuery query = session.createSQLQuery(sql);            
            //query.setParameter("correo",correo);
            query.addEntity(Usuario.class);
            listaUsuario = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
       
        //despues creo otro Usuario usuariolista y obtengo el elemento 0 que es el unico que la lista de usuairo y lo guardo
        setUsuariolista(listaUsuario.get(0));
        
        System.out.println(usuariolista.getNombre());
        System.out.println(listaUsuario.isEmpty());
        System.out.println(usuario1.getCorreo());
        System.out.println("set usuario correcto");

        return "VerInformacionArticuloIH";
    }
    
    

/**
     * Método que busca mediante una consulta SQL la lista de Usuarios donde 
     * el correo del Usuario corresponde con quien esta prestan el Articulo para ver la informacion
     * y poder prestar el articulo
     * @param articuloprestamo El párametro articuloprestamo 
     * @return Redirecciona a la siguiente vista mediante una cadena String
     */
    public String solicitarPrestamo(Articulo articuloprestamo){
        Articulo tmp = new Articulo();
        System.out.println("antes del set articulo correcto");
        articulo = articuloprestamo;
        System.out.println("set articulo correcto");
        usuario1 = articulo.getUsuario();
        //Usuario
        
        correo = usuario1.getCorreo();
        System.out.println(usuario1.getCorreo());
        System.out.println("set usuario correcto");
        
        
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
            String sql = "Select * from Usuario where correo = '"+correo+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            //query.setParameter("correo",correo);
            query.addEntity(Usuario.class);
            listaUsuario = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
       
        
        setUsuariolista(listaUsuario.get(0));
        
        System.out.println(usuariolista.getNombre());
        System.out.println(listaUsuario.isEmpty());
        System.out.println(usuario1.getCorreo());
        System.out.println("set usuario correcto");
        
        listaPrestigios = PrestigiosUsuario();
        listaEstados = EstadosArticulo();
        
        return "solicitarPrestamoIH";
    }
    
    
    
/**
     * Método que busca mediante una consulta SQL la lista de Prestigioprestadores donde 
     * el correo del Usuario corresponde con el usuario que presto el articulo 
     * @return Prestigioprestador Una lista de elementos de tipo Prestigioprestador
     */
    
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
            String sql = "Select * from Prestigioprestador where correo ='"+usuariolista.getCorreo()+"'";
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
    
    
/**
     * Método que imprime una lista de Prestigioprestador
     * * @param art El parámetro art define una la lista de Prestigioprestador
     */        
    
        public void imprimePrestigios(List<Prestigioprestador> art){
        for (Prestigioprestador temp : art) {
                System.out.println(temp.toString());
            
        }
    }
    
        
            
/**
     * Método que busca mediante una consulta SQL la lista de Estados donde 
     id del articulo corresponde con articulo que se esta prestando
     * @return Prestigioprestador Una lista de elementos de tipo Prestigioprestador
     */

     public List<Estado> EstadosArticulo(){
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
            String sql = "Select * from Estado where idarticulo ='"+articulo.getIdarticulo()+"'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Estado.class);
            listaEstados = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        imprimeEstados(listaEstados);
        estado = listaEstados.get(0);
        Estado = estado.getEstado();
return listaEstados;

     }
    
    
/**
     * Método que imprime una lista de Estados
     * * @param art El parámetro art define una lista de Estado
     */   
        public void imprimeEstados(List<Estado> art){
        for (Estado temp : art) {
                System.out.println(temp.toString());
            
        }
    }
     
     
    
    
    
       @PostConstruct
    public void init() {
        listaTodo = articulosDisponible();
    }
    
    
    
    	
    /**
     * Método que busca mediante una consulta SQL la lista de Articulos que estan
     * disponibles 
     * @return listaTodo Una lista de elementos de tipo Articulo
     */
    
     public List<Articulo> articulosDisponible(){
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
            String sql = "Select * from Articulo where disponible ='true'";
            SQLQuery query = session.createSQLQuery(sql);            
            query.addEntity(Articulo.class);
            listaTodo = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
return listaTodo;
     }

     
    /**
     * @return the listaTodo
     */
    public List<Articulo> getListaTodo() {
        return listaTodo;
    }

    /**
     * @param listaTodo the listaTodo to set
     */
    public void setListaTodo(List<Articulo> listaTodo) {
        this.listaTodo = listaTodo;
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
     * @return the articulo
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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

    /**
     * @return the idinstrumento
     */
    public Integer getIdinstrumento() {
        return idinstrumento;
    }

    /**
     * @param idinstrumento the idinstrumento to set
     */
    public void setIdinstrumento(Integer idinstrumento) {
        this.idinstrumento = idinstrumento;
    }

    /**
     * @return the anoinstrumento
     */
    public Integer getAnoinstrumento() {
        return anoinstrumento;
    }

    /**
     * @param anoinstrumento the anoinstrumento to set
     */
    public void setAnoinstrumento(Integer anoinstrumento) {
        this.anoinstrumento = anoinstrumento;
    }

    /**
     * @return the marcainstrumento
     */
    public String getMarcainstrumento() {
        return marcainstrumento;
    }

    /**
     * @param marcainstrumento the marcainstrumento to set
     */
    public void setMarcainstrumento(String marcainstrumento) {
        this.marcainstrumento = marcainstrumento;
    }

    /**
     * @return the tipoinstrumento
     */
    public String getTipoinstrumento() {
        return tipoinstrumento;
    }

    /**
     * @param tipoinstrumento the tipoinstrumento to set
     */
    public void setTipoinstrumento(String tipoinstrumento) {
        this.tipoinstrumento = tipoinstrumento;
    }

    /**
     * @return the nombreinstrumento
     */
    public String getNombreinstrumento() {
        return nombreinstrumento;
    }

    /**
     * @param nombreinstrumento the nombreinstrumento to set
     */
    public void setNombreinstrumento(String nombreinstrumento) {
        this.nombreinstrumento = nombreinstrumento;
    }

    /**
     * @return the idlibro
     */
    public Integer getIdlibro() {
        return idlibro;
    }

    /**
     * @param idlibro the idlibro to set
     */
    public void setIdlibro(Integer idlibro) {
        this.idlibro = idlibro;
    }

    /**
     * @return the editoriallibro
     */
    public String getEditoriallibro() {
        return editoriallibro;
    }

    /**
     * @param editoriallibro the editoriallibro to set
     */
    public void setEditoriallibro(String editoriallibro) {
        this.editoriallibro = editoriallibro;
    }

    /**
     * @return the nombreautorlibro
     */
    public String getNombreautorlibro() {
        return nombreautorlibro;
    }

    /**
     * @param nombreautorlibro the nombreautorlibro to set
     */
    public void setNombreautorlibro(String nombreautorlibro) {
        this.nombreautorlibro = nombreautorlibro;
    }

    /**
     * @return the nombrelibro
     */
    public String getNombrelibro() {
        return nombrelibro;
    }

    /**
     * @param nombrelibro the nombrelibro to set
     */
    public void setNombrelibro(String nombrelibro) {
        this.nombrelibro = nombrelibro;
    }

    /**
     * @return the generolibro
     */
    public String getGenerolibro() {
        return generolibro;
    }

    /**
     * @param generolibro the generolibro to set
     */
    public void setGenerolibro(String generolibro) {
        this.generolibro = generolibro;
    }

    /**
     * @return the anolibro
     */
    public Integer getAnolibro() {
        return anolibro;
    }

    /**
     * @param anolibro the anolibro to set
     */
    public void setAnolibro(Integer anolibro) {
        this.anolibro = anolibro;
    }

    /**
     * @return the idmusica
     */
    public Integer getIdmusica() {
        return idmusica;
    }

    /**
     * @param idmusica the idmusica to set
     */
    public void setIdmusica(Integer idmusica) {
        this.idmusica = idmusica;
    }

    /**
     * @return the autormusica
     */
    public String getAutormusica() {
        return autormusica;
    }

    /**
     * @param autormusica the autormusica to set
     */
    public void setAutormusica(String autormusica) {
        this.autormusica = autormusica;
    }

    /**
     * @return the generomusica
     */
    public String getGeneromusica() {
        return generomusica;
    }

    /**
     * @param generomusica the generomusica to set
     */
    public void setGeneromusica(String generomusica) {
        this.generomusica = generomusica;
    }

    /**
     * @return the anomusica
     */
    public Integer getAnomusica() {
        return anomusica;
    }

    /**
     * @param anomusica the anomusica to set
     */
    public void setAnomusica(Integer anomusica) {
        this.anomusica = anomusica;
    }

    /**
     * @return the formatomusica
     */
    public String getFormatomusica() {
        return formatomusica;
    }

    /**
     * @param formatomusica the formatomusica to set
     */
    public void setFormatomusica(String formatomusica) {
        this.formatomusica = formatomusica;
    }

    /**
     * @return the idsonido
     */
    public Integer getIdsonido() {
        return idsonido;
    }

    /**
     * @param idsonido the idsonido to set
     */
    public void setIdsonido(Integer idsonido) {
        this.idsonido = idsonido;
    }

    /**
     * @return the nombresonido
     */
    public String getNombresonido() {
        return nombresonido;
    }

    /**
     * @param nombresonido the nombresonido to set
     */
    public void setNombresonido(String nombresonido) {
        this.nombresonido = nombresonido;
    }

    /**
     * @return the tiposonido
     */
    public String getTiposonido() {
        return tiposonido;
    }

    /**
     * @param tiposonido the tiposonido to set
     */
    public void setTiposonido(String tiposonido) {
        this.tiposonido = tiposonido;
    }

    /**
     * @return the potenciasonido
     */
    public Double getPotenciasonido() {
        return potenciasonido;
    }

    /**
     * @param potenciasonido the potenciasonido to set
     */
    public void setPotenciasonido(Double potenciasonido) {
        this.potenciasonido = potenciasonido;
    }

    /**
     * @return the marcasonido
     */
    public String getMarcasonido() {
        return marcasonido;
    }

    /**
     * @param marcasonido the marcasonido to set
     */
    public void setMarcasonido(String marcasonido) {
        this.marcasonido = marcasonido;
    }

    /**
     * @return the idaccesorio
     */
    public Integer getIdaccesorio() {
        return idaccesorio;
    }

    /**
     * @param idaccesorio the idaccesorio to set
     */
    public void setIdaccesorio(Integer idaccesorio) {
        this.idaccesorio = idaccesorio;
    }

    /**
     * @return the tipoaccesorio
     */
    public String getTipoaccesorio() {
        return tipoaccesorio;
    }

    /**
     * @param tipoaccesorio the tipoaccesorio to set
     */
    public void setTipoaccesorio(String tipoaccesorio) {
        this.tipoaccesorio = tipoaccesorio;
    }

    /**
     * @return the nombreaccesorio
     */
    public String getNombreaccesorio() {
        return nombreaccesorio;
    }

    /**
     * @param nombreaccesorio the nombreaccesorio to set
     */
    public void setNombreaccesorio(String nombreaccesorio) {
        this.nombreaccesorio = nombreaccesorio;
    }

    /**
     * @return the marcaaccesorio
     */
    public String getMarcaaccesorio() {
        return marcaaccesorio;
    }

    /**
     * @param marcaaccesorio the marcaaccesorio to set
     */
    public void setMarcaaccesorio(String marcaaccesorio) {
        this.marcaaccesorio = marcaaccesorio;
    }

    /**
     * @return the instrumento
     */
    public Instrumento getInstrumento() {
        return instrumento;
    }

    /**
     * @param instrumento the instrumento to set
     */
    public void setInstrumento(Instrumento instrumento) {
        this.instrumento = instrumento;
    }

    /**
     * @return the libro
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * @param libro the libro to set
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * @return the musica
     */
    public Musica getMusica() {
        return musica;
    }

    /**
     * @param musica the musica to set
     */
    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    /**
     * @return the sonido
     */
    public Sonido getSonido() {
        return sonido;
    }

    /**
     * @param sonido the sonido to set
     */
    public void setSonido(Sonido sonido) {
        this.sonido = sonido;
    }

    /**
     * @return the accesorio
     */
    public Accesorio getAccesorio() {
        return accesorio;
    }

    /**
     * @param accesorio the accesorio to set
     */
    public void setAccesorio(Accesorio accesorio) {
        this.accesorio = accesorio;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario1() {
        return usuario1;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.setUsuario1(usuario);
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
     * @param usuario1 the usuario1 to set
     */
    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    /**
     * @return the usuariolista
     */
    public Usuario getUsuariolista() {
        return usuariolista;
    }

    /**
     * @param usuariolista the usuariolista to set
     */
    public void setUsuariolista(Usuario usuariolista) {
        this.usuariolista = usuariolista;
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
    
//    /**
//     * @return the enviarsolicitud
//     */
//    public MBEnviarSolicitud getEnviarsolicitud() {
//        return enviarsolicitud;
//    }
//
//    /**
//     * @param enviarsolicitud the enviarsolicitud to set
//     */
//    public void setEnviarsolicitud(MBEnviarSolicitud enviarsolicitud) {
//        this.enviarsolicitud = enviarsolicitud;
//    }

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
    public Estado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * @return the Estado
     */
    public String getEstados() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

     
     
     
}
