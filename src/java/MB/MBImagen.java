/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;

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
import javax.servlet.ServletContext;
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
 * @author Rodrigo
 */
@ManagedBean 
@SessionScoped
public class MBImagen implements Serializable{
    private byte[] imagen;
    private String destination="";
    private String rutaimagen;


    
// public void upload(FileUploadEvent event) {  
//                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext(); 
//		String txtField = ec.getRequestParameterMap().get("myform:txtField");
//                System.out.println(txtField);
//                this.setRutaimagen(getDestination() + event.getFile().getFileName()); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg
//        try {
//            imagen = event.getFile().getContents();
//            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());        
//            rutaimagen = getDestination() + event.getFile().getFileName(); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg"
//            System.out.println("la ruta es " + getRutaimagen());
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La imagen " + event.getFile().getFileName() + " se ha guardado satisfactoriamente"));
//        } catch (IOException e) {
//          //   FacesMessage message = new FacesMessage("Is NOT Succesful", event.getFile().getFileName() + " is not uploaded.");
//           // FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//        this.setRutaimagen(getDestination() + event.getFile().getFileName()); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg"
//
//    }  
// 
 
 
 /**
     * Método que recibe un flujo de informacion del archivo que se quiere guardar en la carpeta del proyecto 
     * @param fileName El párametro fileName define el nombre del archivo que se va a guardar
     * @param in El párametro in define el flujo de informacion del archivo que se quiere guardar
     */
    public void copyFile(String fileName, InputStream in) {
                 //ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                 destination = "C:\\Users\\Rodrigo\\Desktop\\PrestaPalaOrquesta\\web\\resources\\";//(String) servletContext.getRealPath("/resources"); // Sustituye "/" por el directorio ej: "/upload
           try {
               OutputStream out = new FileOutputStream(new File(getDestination() + fileName ));
               int read = 0;
                byte[] bytes = new byte[1024];
              
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                in.close();
                out.flush();
                out.close();
                System.out.println("New file created!");
                
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
    
    
   
     /**
     * Método que imprime una lista de Articulos
     * * @param art El parámetro art define el la lista de Articulos
     */
     public void imprime(List<Articulo> art){
        for (Articulo temp : art) {
            
                System.out.println(temp.toString());
            
        }
    }
    
      /**
     * Método que carga la imagen que recibe del Usuario y la guarda en una carpeta del proyecto
     * @param event El parámetro event define un objeto de tipo FileUploadEvent
     */
     public void subiImagen (FileUploadEvent event){
         try{
             copyFile(event.getFile().getFileName(), event.getFile().getInputstream());        
             //rutaimagen = getDestination()+  event.getFile().getFileName(); 
             //rutaimagen = event.getFile().getFileName(); 
             rutaimagen = event.getFile().getFileName(); 
             imagen = event.getFile().getContents();
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La imagen " + event.getFile().getFileName() + " se ha guardado satisfactoriamente"));
         }catch(Exception e){
             FacesMessage message = new FacesMessage("Is NOT Succesful", event.getFile().getFileName() + " is not uploaded.");
             FacesContext.getCurrentInstance().addMessage(null, message);
         }
         
         
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
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
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