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




import DAO.Articulo;
import DAO.Instrumento;
import DAO.Libro;
import DAO.Usuario;
import DAO.Musica;
import DAO.Sonido;
import DAO.Accesorio;
import static com.sun.xml.internal.ws.api.pipe.Fiber.current;
import static com.sun.xml.ws.api.pipe.Fiber.current;
import java.io.ByteArrayInputStream;
        
        
import java.io.File;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Iterator;
import static java.util.concurrent.ThreadLocalRandom.current;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import static org.hibernate.internal.util.io.StreamCopier.BUFFER_SIZE;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.event.RowEditEvent;



import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author fernando
 */
@ManagedBean 
@RequestScoped  


public class MBArticulo implements Serializable{
    
    private Integer idarticulo;
    private boolean disponible;
    private String descripcion;
    private String categoria;
    
    private byte[] imagen;
    
    private String rutaimagen;
    
    //Istrumento
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
    
    private List<Articulo> lista;
    private List<Articulo> lista2;
    
    private final String destination="home/fernando/Descargas/2016-2/PPLO3";
    
    
    private String msn;
    
    private Articulo articulo;
    
    @ManagedProperty("#{mBUsuario}")
    private MBUsuario usuario;
    
    @ManagedProperty("#{mBImagen}")
    private MBImagen miImagen;
    
        
    /**
     * Creates a new instance of MBArticulo
     */
    public MBArticulo() {
    }

    /**
     * @return the idarticulo
     */
    public Integer getIdArticulo() {
        return idarticulo;
    }

    /**
     * @param idarticulo the idArticulo to set
     */
    public void setIdArticulo(Integer idarticulo) {
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
     * @return the descripción
     */
    public String getDescripcion() {
        return descripcion;
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
     * @param descripcion the descripción to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    
    
    
    
  
    
    //Subir Imagen
    
    
 
    public void upload(FileUploadEvent event) {  
        //FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
                //FacesContext.getCurrentInstance().addMessage(null, msg)
        // Do what you want with the file      
        
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext(); 
		String txtField = ec.getRequestParameterMap().get("myform:txtField");
                System.out.println(txtField);
                this.rutaimagen = destination + event.getFile().getFileName(); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());        
            this.rutaimagen = destination + event.getFile().getFileName(); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg"
            System.out.println("la ruta es " + rutaimagen);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La imagen " + event.getFile().getFileName() + " se ha guardado satisfactoriamente"));
            this.rutaimagen = destination + event.getFile().getFileName(); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg"
        } catch (IOException e) {
          //   FacesMessage message = new FacesMessage("Is NOT Succesful", event.getFile().getFileName() + " is not uploaded.");
           // FacesContext.getCurrentInstance().addMessage(null, msg);
        }
             this.rutaimagen = destination + event.getFile().getFileName(); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg"

    }  
 
    public void copyFile(String fileName, InputStream in) {
           try {
               OutputStream out = new FileOutputStream(new File(destination + usuario.getCorreo()+ fileName ));
              
               int read = 0;
                byte[] bytes = new byte[1024];
              
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                in.close();
                out.flush();
                out.close();
                System.out.println("New file created!");
                
                /////////////////////////
                
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
            this.rutaimagen = destination + fileName; //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg" 
            tx = session.beginTransaction();
            String sql = "UPDATE Articulo set rutaimagen = :rutaimagen where idarticulo = :idarticulo";
            Query query = session.createQuery(sql);
            query.setParameter("rutaimagen", rutaimagen);
            query.setParameter("idarticulo", idarticulo);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
                
                
                
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
    
    
    
     public void imprime(List<Articulo> art){
        for (Articulo temp : art) {
            
                System.out.println(temp.toString());
            
        }
    }
    
     /*
     public String guardaImagen(String fileName, byte[] bytes){
         File f = null;
         InputStream in = null;
         try{
             f= new File(destination + usuario.getCorreo()+ fileName);
             //in = new ByteArrayInputStream(bytes);
             ByteArrayInputStream inn = new ByteArrayInputStream(bytes);
             System.out.println("Hasta aqui vas mejor");
             FileOutputStream out = new FileOutputStream(f.getAbsolutePath());
             int c = 0;
             while ((c = inn.read()) >= 0){
                 out.write(c);
             }
             out.flush();
             out.close();
             rutaimagen = destination + usuario.getCorreo()+ fileName;
             
                     SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }             
             
         }catch(Exception e){
             System.out.println("No se pudo cargar la imagen");
         }
             return rutaimagen;
     }
     
     */
     public void subiImagen (FileUploadEvent event){
         try{
             
             System.out.println("Hasta aqui vas bien");
     //        this.rutaimagen = guardaImagen(event.getFile().getFileName(),imagen);
             copyFile(event.getFile().getFileName(),event.getFile().getInputstream());
             this.imagen= event.getFile().getContents();

             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La imagen " + event.getFile().getFileName() + " se ha guardado satisfactoriamente"));
         }catch(Exception e){
             FacesMessage message = new FacesMessage("Is NOT Succesful", event.getFile().getFileName() + " is not uploaded.");
             FacesContext.getCurrentInstance().addMessage(null, message);
         }
         
         
     }

      public List<Articulo> listaArticulos() {
        ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
        setLista((List<Articulo>) articuloDAO.findAll());
        Iterator<Articulo>it = getLista().iterator(); 
        while(it.hasNext()){
            Articulo temp = it.next();
        if (!(this.usuario.getCorreo().equals(temp.getUsuario().getCorreo()))) {
               /// System.out.println(temp.toString());
                it.remove();
            }
         
        }
       
        imprime(getLista());
        return getLista();
    }
      
      public List<Articulo> listaArticulos2() {
          
        ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
        
        setLista2((List<Articulo>) articuloDAO.findAll());
        imprime(getLista2());
        return getLista2();
    }
      
      public String mostrarArticulos2(){
            String redirecciona = "";
            listaArticulos2();
            redirecciona = "homeIH2";
      return redirecciona;
      
      }
      
      public String mostrarArticulos(){
            String redirecciona = "";
            listaArticulos();
            redirecciona = "administrarArticuloIH";
      return redirecciona;
      }
      
      
      
      public String actualizar() {
          Articulo tmp = new Articulo();
          String redirecciona= "";
          try {
            //Articulo
            tmp.setIdarticulo(idarticulo);
            tmp.setDisponible(disponible);
            tmp.setDescripcion(descripcion);
            //tmp.setUsuario(usuario.getUsuario());
            //tmp.setImagen(getMiImagen().getImagen());
            //System.out.println("la ruta es "+ getMiImagen().getRutaimagen());
            //rutaimagen = getMiImagen().getRutaimagen();
            tmp.setRutaimagen(rutaimagen);
            //guardar string

            ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
            articuloDAO.update(tmp);
            
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El articulo se guardo correctamente"));
            redirecciona = "administrarCuentaIH";
        } catch (Exception e) {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El articulo se ha guardado satisfactoriamente"));
            redirecciona = "administrarArticuloIH";
        }
        return redirecciona;
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelar", ((Articulo) event.getObject()).getIdarticulo().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
      

    public void guarda() {
        Articulo tmp = new Articulo();
        Instrumento tmp1 = new Instrumento();
        Libro tmp2 = new Libro();
        Musica tmp3 = new Musica();
        Sonido tmp4 = new Sonido();
        Accesorio tmp5  = new Accesorio();
        
        try {
            //Articulo
            tmp.setIdarticulo(idarticulo);
            tmp.setDisponible(disponible);
            tmp.setDescripcion(descripcion);
            tmp.setUsuario(usuario.getUsuario());
            tmp.setImagen(getMiImagen().getImagen());
            System.out.println("la ruta es "+ getMiImagen().getRutaimagen());
            rutaimagen = getMiImagen().getRutaimagen();
            tmp.setRutaimagen(rutaimagen);
            //guardar string

            ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
            articuloDAO.save(tmp);
            
           //Instrumento

            if (getIdinstrumento() != null){  
            InstrumentoDaoHibernate instrumentoDAO = new InstrumentoDaoHibernate();
            tmp1.setArticulo(tmp);
            tmp1.setIdinstrumento(getIdinstrumento());
            tmp1.setNombreinstrumento(nombreinstrumento);
            tmp1.setMarca(marcainstrumento);
            tmp1.setAno(anoinstrumento);
            tmp1.setTipo(tipoinstrumento);
            instrumentoDAO.save(tmp1);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El articulo se ha guardado satisfactoriamente"));
            }
            //Libro
            if (idlibro != null){  
            
            //Libro
            LibroDaoHibernate libroDAO = new LibroDaoHibernate();
            tmp2.setArticulo(tmp);
            tmp2.setIdlibro(idlibro);
            tmp2.setEditorial(editoriallibro);
            tmp2.setNombreautor(nombreautorlibro);
            tmp2.setNombre(nombrelibro);
            tmp2.setGenero(generolibro);
            tmp2.setAno(anolibro);
            libroDAO.save(tmp2);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El articulo se ha guardado satisfactoriamente"));            }
            //Musica
            if (idmusica != null){  
            //Musica

            MusicaDaoHibernate musicaDAO = new MusicaDaoHibernate();
            tmp3.setArticulo(tmp);
            tmp3.setIdmusica(idmusica);
            tmp3.setAutor(autormusica);
            tmp3.setFormato(formatomusica);
            tmp3.setGenero(generomusica);
            tmp3.setAno(anomusica);
            musicaDAO.save(tmp3);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El articulo se ha guardado satisfactoriamente"));
            }
            //Sonido
            if (idsonido != null){  
            //Sonido
            SonidoDaoHibernate sonidoDAO = new SonidoDaoHibernate();
            tmp4.setArticulo(tmp);
            tmp4.setIdsonido(idsonido);
            tmp4.setNombre(nombresonido);
            tmp4.setMarca(marcasonido);
            tmp4.setPotencia(potenciasonido);
            tmp4.setTipo(tiposonido);
            sonidoDAO.save(tmp4);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El articulo se ha guardado satisfactoriamente"));
            }
            //Accesorio
            if (idaccesorio != null){  
            AccesorioDaoHibernate accesorioDAO = new AccesorioDaoHibernate();
            tmp5.setArticulo(tmp);
            tmp5.setIdaccesorio(idaccesorio);
            tmp5.setNombre(nombreaccesorio);
            tmp5.setMarca(marcaaccesorio);
            tmp5.setTipo(tipoaccesorio);
            accesorioDAO.save(tmp5);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El articulo se ha guardado satisfactoriamente"));
            }
            
            

            
        } catch (Exception e) {
        e.printStackTrace();
            System.out.println("Hubo un error al intentar crear el articulo" + e);
        }
    }
    
    
  /**  public String inicioSesion(){

  List<Articulo> listUsuario;
  UsuarioDaoHibernate usuarioDao = new UsuarioDaoHibernate();
  listUsuario= usuarioDao.findAll();
  String saludo = "";
        for (Articulo usuario : listUsuario) {
            System.out.println("AAA"+usuario.toString());
         if(this.correo.equals(usuario.getCorreo()) && this.contrasena.equals(usuario.getContrasena().getContrasena())){
             System.out.println(usuario.toString());
        setMsn("Hola "+ usuario.getNombre() + " Bienvenido has iniciado Sesión" );
        saludo= "administrarCuentaIH";            
        break;
            }else {
                setMsn("Correo o contraseña incorrecta");
                saludo= "index";                    
          }    
        }
        return saludo;                
    }
     * @param articulo
     * @return 
    */
    
    
    public String deletearticulo(MBArticulo articulo) {
		ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
                //List<Articulo> lista2 = articuloDAO.findAll();
        lista = listaArticulos();
        for (Articulo temp:  lista) {
            if (articulo.idarticulo.equals((temp.getIdarticulo()))) {
                articuloDAO.delete(temp);
                break;
            }
            
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El artículo ha sido eliminado"));    
        return "administrarCuentaIH";
	}
    
    public String eliminarArticulo(){
        ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
        List<Articulo> lista2 = articuloDAO.findAll();
        //lista = listaArticulos();
        for (Articulo temp:  lista2) {
            if (this.idarticulo.equals((temp.getIdarticulo()))) {
                articuloDAO.delete(temp);
                break;
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El artículo ha sido eliminado"));    
        return "administrarCuentaIH";
    }

    public String eliminarArticuloDos(int idarticulodos){
        ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
        //List<Articulo> lista = articuloDAO.findAll();
        lista = listaArticulos();
        for (Articulo temp:  lista) {
            if (idarticulodos == (temp.getIdarticulo())) {
                articuloDAO.delete(temp);
                break;
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El artículo ha sido eliminado"));    
        return "administrarCuentaIH";
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
     * @return the lista
     */
    public List<Articulo> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Articulo> lista) {
        this.lista = lista;
    }

    /**
     * @return the imagen
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the miImagen
     */
    public MBImagen getMiImagen() {
        return miImagen;
    }

    /**
     * @param miImagen the miImagen to set
     */
    public void setMiImagen(MBImagen miImagen) {
        this.miImagen = miImagen;
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
     * @return the lista2
     */
    public List<Articulo> getLista2() {
        return lista2;
    }

    /**
     * @param lista2 the lista2 to set
     */
    public void setLista2(List<Articulo> lista2) {
        this.lista2 = lista2;
    }


    
    
}
   
