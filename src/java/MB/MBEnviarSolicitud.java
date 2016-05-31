/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;

import Controlador.SolicitarArticuloDaoHibernate;
import DAO.Articulo;
import DAO.Solicita;
import DAO.Usuario;
import java.io.Serializable;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import javax.mail.MessagingException;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
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
public class MBEnviarSolicitud  implements Serializable {
        
    
    	private final Properties properties = new Properties();

        String destination = "C:\\Users\\Rodrigo\\Desktop\\PrestaPalaOrquesta\\web\\resources\\";
        
	private String password;
 
	private Session session;
        
           @ManagedProperty("#{mBSolicitarArticulo}")
            private MBSolicitarArticulo solicitarArticulo;
    
           
           @ManagedProperty("#{mBUsuario}")
            private MBUsuario usuario;
// 
//	private void init() {
// 
//		properties.put("mail.smtp.host", "smtp.gmail.com");
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.port","587");
//		properties.put("mail.smtp.mail.sender","saderjack@gmail.com");
//		properties.put("mail.smtp.user", "usuario");
//		properties.put("mail.smtp.auth", "true");
// 
//		session = Session.getDefaultInstance(properties);
//	}
 
	public String sendEmail(String destinatario, String nombrePrestador, String appPaternoPrestador, String appMaternoPRestador,
                String articuloDescripcion, String rutaimagen,Integer idarticulo,Usuario usuariolista,Articulo articulo){
            
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
                message.setSubject("Presta Pa'la Orquesta  -  Has recibido una nueva solicitud de prestamo.");
                message.setText("\n"+"Hola "+ nombrePrestador +" "+ appPaternoPrestador+" "+appMaternoPRestador+", has el siguiente articulo: " +"\n"+ "\n" + articuloDescripcion +"\n" +"\n"+ "El usuario "+usuario.getNombre()+" " +usuario.getAppPaterno()+" "+usuario.getAppMaterno()+  " desea pedir prestado el artículo. ¿Se los prestas?" );
                
                String direccion  = "http://localhost:8080/PrestaPalaOrquesta";
                
                
                // Create your new message part
                BodyPart messageBodyPart = new MimeBodyPart();      
                String htmlText = "<p align=left>Hola "+ nombrePrestador +" "+ appPaternoPrestador+" "+appMaternoPRestador+ ", has recibido una nueva solicitud de prestamo del siguiente artículo:<br>";
                htmlText+="<p align=left><img src=\"cid:image\"><br><B>" + articuloDescripcion +"</B></p><br>";
                htmlText+="El usuario "+usuario.getNombre()+" "+usuario.getAppPaterno()+" "+usuario.getAppMaterno()+" desea pedir prestado el artículo. ¿Se lo prestas?<br>"
                        + "Puedes ingresar a tu cuenta para ver los detalles. <br><br><br><br><br><br><br>";
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
                System.out.println(solicitarArticulo.getUsuariolista().getCorreo());
                System.out.print(enviado);                
                guarda(articulo,usuariolista);
                System.out.println("Solicitud Guardada");
                
                
                
                
                
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La solitud de prestamo ha sido enviada"));
                redirecciona = "inicioHomeIH";
                

                }catch (Exception e){
                    e.printStackTrace();

            }
            return redirecciona;

    }
//
        
        
        
        public void guarda(Articulo articulo,Usuario usuariolista) {
        Solicita tmp = new Solicita();
        try {
            tmp.setArticulo(articulo);
            tmp.setUsuarioByCorreo(usuariolista);
            tmp.setUsuarioByCorreosolicita(usuario.getUsuario());
            
            
            SolicitarArticuloDaoHibernate solicitaDAO = new SolicitarArticuloDaoHibernate();
            solicitaDAO.save(tmp); 
            
        } catch (Exception e) {
            System.out.println("Error al intentar guardar solicitud" + e);
            
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
//                String redirecciona = "";
//		init();
//		try{
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress("saderjack@gmail.com"));
//			message.setSubject("Prueba");
//			message.setText("Texto");
//			Transport t = session.getTransport("smtp");
//			t.connect((String)properties.get("mail.smtp.user"), "9Jfs+tc7Ggm");
//			t.sendMessage(message, message.getAllRecipients());
//			t.close();
//                        redirecciona = "inicioHomeIH";
//		}catch (MessagingException me){
//                        System.out.println("Error al enviar correo "+ me);//Aqui se deberia o mostrar un mensaje de error o en lugar
//                        //de no hacer nada con la excepcion, lanzarla para que el modulo
//                        //superior la capture y avise al usuario con un popup, por ejemplo.
//			
//		}
//		return redirecciona;
//	}
    

