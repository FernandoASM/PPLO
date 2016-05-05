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
        
        
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;



/**
 *
 * @author fernando
 */
@ManagedBean 
@RequestScoped  


public class MBArticulo {
    
    private Integer idarticulo;
    private boolean disponible;
    private String descripcion;
    private String categoria;
    
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
    
    private String msn;
    
    @ManagedProperty("#{mBUsuario}")
    private MBUsuario usuario;

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
    public List<Articulo> listaArticulos() {
        ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
        lista = articuloDAO.findAll();
        Iterator<Articulo>it = lista.iterator(); 
        while(it.hasNext()){
            Articulo temp = it.next();
        if (!(this.usuario.getCorreo().equals(temp.getUsuario().getCorreo()))) {
               /// System.out.println(temp.toString());
                it.remove();
            }
         
        }
       
        imprime(lista);
        return lista;
    }
    
    public void imprime(List<Articulo> art){
        for (Articulo temp : art) {
            
                System.out.println(temp.toString());
            
        }
    }
     public String traeArticulos() {
        ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
        List<Articulo> lista = articuloDAO.findAll();
       
        for (Articulo temp : lista) {
            System.out.println("hola");
            if (this.usuario.getCorreo().equals(temp.getUsuario().getCorreo())) {
                System.out.println(temp.toString());
                this.idarticulo = temp.getIdarticulo();
                this.disponible = temp.isDisponible();
                this.descripcion = temp.getDescripcion();
               
                //break;
            }

        }
        return "administrarCuentaIH";
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

            ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
            articuloDAO.save(tmp);
            
           //Instrumento

            if (idinstrumento != null){  
            InstrumentoDaoHibernate instrumentoDAO = new InstrumentoDaoHibernate();
            tmp1.setArticulo(tmp);
            tmp1.setIdinstrumento(idinstrumento);
            tmp1.setNombreinstrumento(nombreinstrumento);
            tmp1.setMarca(marcainstrumento);
            tmp1.setAno(anoinstrumento);
            tmp1.setTipo(tipoinstrumento);
            instrumentoDAO.save(tmp1);

            setMsn("El articulo se ha guardado satisfactoriamente");
            }
            //Libro
            if (idlibro != null){  
            setMsn("El articulo se ha creado satisfactoriamente");
            
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

            setMsn("El articulo se ha guardado satisfactoriamente");
            }
            //Musica
            if (idmusica != null){  
            setMsn("El articulo se ha creado satisfactoriamente");
            //Musica

            MusicaDaoHibernate musicaDAO = new MusicaDaoHibernate();
            tmp3.setArticulo(tmp);
            tmp3.setIdmusica(idmusica);
            tmp3.setAutor(autormusica);
            tmp3.setFormato(formatomusica);
            tmp3.setGenero(generomusica);
            tmp3.setAno(anomusica);
            musicaDAO.save(tmp3);

            setMsn("El articulo se ha guardado satisfactoriamente");
            }
            //Sonido
            if (idsonido != null){  

            setMsn("El articulo se ha creado satisfactoriamente");
            //Sonido
            SonidoDaoHibernate sonidoDAO = new SonidoDaoHibernate();
            tmp4.setArticulo(tmp);
            tmp4.setIdsonido(idsonido);
            tmp4.setNombre(nombresonido);
            tmp4.setMarca(marcasonido);
            tmp4.setPotencia(potenciasonido);
            tmp4.setTipo(tiposonido);
            sonidoDAO.save(tmp4);
            setMsn("El articulo se ha guardado satisfactoriamente");}
            //Accesorio
            if (idaccesorio != null){  

            setMsn("El articulo se ha creado satisfactoriamente");
            //Accesorio

            AccesorioDaoHibernate accesorioDAO = new AccesorioDaoHibernate();
            tmp5.setArticulo(tmp);
            tmp5.setIdaccesorio(idaccesorio);
            tmp5.setNombre(nombreaccesorio);
            tmp5.setMarca(marcaaccesorio);
            tmp5.setTipo(tipoaccesorio);
            accesorioDAO.save(tmp5);

            setMsn("El articulo se ha guardado satisfactoriamente");}
            
            

            
            setMsn("El articulo se ha creado satisfactoriamente");
        } catch (Exception e) {
        e.printStackTrace();
            System.out.println("Hubo un error al intentar crear el articulo" + e);
        }
    }
    
    public String actualizar() {
        traeArticulos();
        Articulo tmp = new Articulo();
        
        String redirecciona ="";
        try {
            tmp.setIdarticulo(idarticulo);
            tmp.setDescripcion(descripcion);
            tmp.setDisponible(disponible);

                       
            ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
            articuloDAO.update(tmp);
            
           /* TelefonoDaoHibernate telefonoDao = new TelefonoDaoHibernate();
            tmp1.setUsuario(tmp);
            tmp1.setCorreo(correo);
            tmp1.setTelefono(telefono);
            telefonoDao.update(tmp1);
            
            ContrasenaDaoHibernate contrasenaDao = new ContrasenaDaoHibernate();
            tmp2.setUsuario(tmp);
            tmp2.setCorreo(correo);
            tmp2.setContrasena(contrasena);
            contrasenaDao.update(tmp2);

            */
            msn = "El árticulo se actualizó correctamente";
            redirecciona = "administrarCuentaIH";
        } catch (Exception e) {

            msn = "upss! Ocurrio un error " + e;
            System.out.println(" upss! Ocurrio un error.  " + e);
            redirecciona = "mostrarArticulosIH";
        }
        return redirecciona;
    }
    
    public String eliminarArticulo(){
        ArticuloDaoHibernate articuloDAO = new ArticuloDaoHibernate();
        List<Articulo> lista = articuloDAO.findAll();
        for (Articulo temp:  lista) {
            if (this.idarticulo.equals((temp.getIdarticulo()))) {
                articuloDAO.delete(temp);
                break;
            }
                setMsn("El artículo ha sido eliminado");    
        }
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
     * @return the imagen
     */
    
}
   
