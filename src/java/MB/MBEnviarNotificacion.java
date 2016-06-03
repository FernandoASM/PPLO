/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;

import Controlador.ArticuloDaoHibernate;
import Controlador.PrestaDaoHibernate;
import Controlador.SolicitarArticuloDaoHibernate;
import DAO.Articulo;
import DAO.Presta;
import DAO.Solicita;
import DAO.Usuario;
import java.io.Serializable;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Rodrigo
 */

@ManagedBean
@RequestScoped 
/**
 *
 * @author Rodrigo
 */
public class MBEnviarNotificacion  implements Serializable {
        
    
    	private final Properties properties = new Properties();

        String destination = "C:\\Users\\Rodrigo\\Desktop\\PrestaPalaOrquesta\\web\\resources\\";
        
	private String password;
 
	private Session session;
        
           @ManagedProperty("#{mBSolicitarArticulo}")
            private MBSolicitarArticulo solicitarArticulo;
    
           
           @ManagedProperty("#{mBUsuario}")
            private MBUsuario usuario;

           /**
     * Método que envia un correo de notificacion de prestamo al usuario consumidor de que el prestador esta de acuerdo en prestar el articulo
     * @param destinatario El parámetro destinatario define el correo del consumidor 
     * @param nombreConsumidor El parámetro nombreConsumidor define el nombre del consumidor 
     * @param appPaternoConsumidor El parámetro appPaternoConsumidor define el apellido paterno del consumidor 
     * @param appMaternoConsumidor El parámetro appMaternoConsumidor define el apellido materno del consumidor 
     * @param articuloDescripcion El parámetro articuloDescripcion define la descripcion del articulo solicitado
     * @param rutaimagen El parámetro rutaimagen define el la rutaimagen de la imagen del articulo solicitado
     * @param usuarioconsumidor El parámetro usuarioconsumidor define al usuario consumidor 
     * @param articulosolicitado El parámetro articulosolicitado define el articulo solicitado
     * @return Redirecciona mediante una variable String a la siguiente vista
     */
           
	public String sendEmail(String destinatario, String nombreConsumidor, String appPaternoConsumidor, String appMaternoConsumidor,
                String articuloDescripcion, String rutaimagen,Usuario usuarioconsumidor,Articulo articulosolicitado){
            
            destination = "C:/Users/Rodrigo/Desktop/PrestaPalaOrquesta/web/resources/";
            System.out.println(destinatario);
            
            //System.out.println(solicitarArticulo.getCorreo());
            boolean enviado = false;
            String redirecciona = "";
            try{
            destination = "C:/Users/Rodrigo/Desktop/PrestaPalaOrquesta/web/resources/";
            String host= "smtp.gmail.com";
            
            Properties prop = System.getProperties();

                prop.put("mail.smtp.starttls.enable","true");
                prop.put("mail.smtp.host",host);
                prop.put("mail.smtp.user","Prestapa'laorquesta@gmail.com");
                prop.put("mail.smtp.password","tjonesassociation");
                prop.put("mail.smtp.port","587");
                prop.put("mail.smtp.auth","true");

                Session session = Session.getDefaultInstance(prop,null);
                MimeMessage message= new MimeMessage(session);

                message.setFrom(new InternetAddress("Prestapa'laorquesta@gmail.com"));

                message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
                message.setSubject("Presta Pa'la Orquesta  -  Has recibido una nueva notificación de prestamo.");
                message.setText("\n"+"Hola "+ nombreConsumidor +" "+ appPaternoConsumidor+" "+appMaternoConsumidor+", has recbido una nueva  el siguiente articulo: " +"\n"+ "\n" + articuloDescripcion +"\n" +"\n"+ "El usuario "+usuario.getNombre()+" " +usuario.getAppPaterno()+" "+usuario.getAppMaterno()+  " desea pedir prestado el artículo. ¿Se los prestas?" );
                
                String direccion  = "http://localhost:8080/PrestaPalaOrquesta";
                
                
                // Create your new message part
                BodyPart messageBodyPart = new MimeBodyPart();      
                String htmlText = "<p align=left>Hola "+ nombreConsumidor +" "+ appPaternoConsumidor+" "+appMaternoConsumidor+ ", has recibido una notificacion de prestamo del siguiente artículo:<br>";
                htmlText+="<p align=left><img src=\"cid:image\"><br><B>" + articuloDescripcion +"</B></p><br>";
                htmlText+="El usuario "+usuario.getNombre()+" "+usuario.getAppPaterno()+" "+usuario.getAppMaterno()+" esta deacuerdo de prestarte el artículo<br>"
                        + "A continuación puedes ver la información del Prestador para que puedas ponerte en contacto con él. <br><br><br>";
                htmlText+="Datos del Prestador<br><br>";
                htmlText+="Nombre: "+usuario.getNombre()+" "+usuario.getAppPaterno()+" "+usuario.getAppMaterno()+"<br>";
                htmlText+="Correo: "+usuario.getCorreo()+"<br>";
                htmlText+="Teléfono: "+usuario.getTelefono()+"<br><br>";
                htmlText+="Dirección:<br><br>";
                htmlText+="Calle: "+usuario.getCalle()+"<br>";
                htmlText+="Número: "+usuario.getNum()+"<br>";
                htmlText+="Colonia: "+usuario.getColonia()+"<br>";
                htmlText+="Delegación: "+usuario.getDelegacion()+"<br>";
                htmlText+="Código Postal: "+usuario.getCodigopostal()+"<br><br><br>";
                htmlText+="Después de que se realice el arreglo del prestamo, te invitamos a que califiques como te fue con el Prestador y el Artículo.<br><br>Gracias.<br><br><br><br>";
                htmlText+="<p align=center> <a href="+direccion+"> <img src=\"cid:presta\"></a>";
                htmlText+= "<h2><p align=center> <font color=#0033FF> <a href="+direccion+">Presta Pa'la Orquesta</a> </font> </p></h2><br><br><br><br><br>";
                htmlText+="<p align=center><img src=\"cid:tjones\" height=110 width=170>";
                htmlText+="<h6><p align=center>Powered by T-Jones Technologies.</h6>";                
                
                messageBodyPart.setContent(htmlText, "text/html");

                // Create a related multi-part to combine the parts
                MimeMultipart multipart = new MimeMultipart("related");
                multipart.addBodyPart(messageBodyPart);

                
                messageBodyPart = new MimeBodyPart();
                DataSource tjs = new FileDataSource
                (destination+"tJones.jpg");
                messageBodyPart.setDataHandler(new DataHandler(tjs));
                messageBodyPart.addHeader("Content-ID","<tjones>");
                // add it
                multipart.addBodyPart(messageBodyPart);
                
                
                messageBodyPart = new MimeBodyPart();
                DataSource fds = new FileDataSource
                (destination+"prestapalaorquesta.png");
                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.addHeader("Content-ID","<presta>");
                // add it
                multipart.addBodyPart(messageBodyPart);

                 // Create part for the image
                messageBodyPart = new MimeBodyPart();

                // Fetch the image and associate to part
                //DataSource fds = new FileDataSource(destinatario+rutaimagen);
                DataSource source = new FileDataSource(destination+rutaimagen);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setHeader("Content-ID","<image>");
                // Add part to multi-part
                multipart.addBodyPart(messageBodyPart);
                
                // Associate multi-part with message
                message.setContent(multipart);
 
                System.out.print(message);

                Transport transport=session.getTransport("smtp");
                transport.connect(host,"prestapaalaorquesta@gmail.com","tjonesassociation");

                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                enviado = true;
                //System.out.println(solicitarArticulo.getUsuariolista().getCorreo());
                System.out.print(enviado);                
                guarda(articulosolicitado,usuarioconsumidor);
                System.out.println("Prestamo Guardada");
                
                articulosolicitado.setDisponible(false);
                ArticuloDaoHibernate articuloDao = new ArticuloDaoHibernate();
                articuloDao.update(articulosolicitado);
                System.out.println("articulo actualizado");
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La notificación de prestamo ha sido enviada"));
                redirecciona = "inicioHomeIH";
                

                }catch (Exception e){
                    e.printStackTrace();

            }
            return redirecciona;

    }

/**
     * Método que guarda un objeto del tipo Presta(Prestamo) en la base de Datos 
     * @param articulo El parámetro articulo define el Articulo solicitado
     * @param usuarioconsumidor El parámetro usuarioconsumidor define el Usuario consumidor
     */        
        public void guarda(Articulo articulo,Usuario usuarioconsumidor) {
        Presta tmp = new Presta();
        try {
            tmp.setArticulo(articulo);
            tmp.setUsuarioByCorreo(usuario.getUsuario());
            tmp.setUsuarioByCorreoconsumidor(usuarioconsumidor);
            
            
            PrestaDaoHibernate prestaDAO = new PrestaDaoHibernate();
            prestaDAO.save(tmp); 
            
        } catch (Exception e) {
            System.out.println("Error al intentar guardar presta" + e);
            
        }
    }
        
        
        
    /**
     * @return the solicitarArticulo
     */
    public MBSolicitarArticulo getSolicitarArticulo() {
        return solicitarArticulo;
    }

    /**
     * @param solicitarArticulo the solicitarArticulo to set
     */
    public void setSolicitarArticulo(MBSolicitarArticulo solicitarArticulo) {
        this.solicitarArticulo = solicitarArticulo;
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