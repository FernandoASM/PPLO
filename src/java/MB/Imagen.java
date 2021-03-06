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
public class Imagen implements Serializable{
    private byte[] imagen;
    private String destination="C:\\Users\\Rodrigo\\Desktop\\";
    private String rutaimagen;
    
 public void upload(FileUploadEvent event) {  
        //FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
                //FacesContext.getCurrentInstance().addMessage(null, msg)
        // Do what you want with the file      
        
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext(); 
		String txtField = ec.getRequestParameterMap().get("myform:txtField");
                System.out.println(txtField);
                this.setRutaimagen(getDestination() + event.getFile().getFileName()); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());        
            this.setRutaimagen(getDestination() + event.getFile().getFileName()); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg"
            System.out.println("la ruta es " + getRutaimagen());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La imagen " + event.getFile().getFileName() + " se ha guardado satisfactoriamente"));
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La imagen " + event.getFile().getFileName() + " se ha guardado satisfactoriamente"));
            this.setRutaimagen(getDestination() + event.getFile().getFileName()); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg"
        } catch (IOException e) {
          //   FacesMessage message = new FacesMessage("Is NOT Succesful", event.getFile().getFileName() + " is not uploaded.");
           // FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        this.setRutaimagen(getDestination() + event.getFile().getFileName()); //Iniciar variable global destination + fileName + usuario.getCorreo() + ".jpg"

    }  
 
    public void copyFile(String fileName, InputStream in) {
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
                
                /////////////////////////
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La imagen " +fileName+ " se ha guardado satisfactoriamente"));
                SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
/*
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
                
  */              
                
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
             this.setImagen(event.getFile().getContents());
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La imagen " + event.getFile().getFileName() + " se ha guardado satisfactoriamente"));
         }catch(Exception e){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Is NOT Succesful", event.getFile().getFileName() + " is not uploaded."));
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