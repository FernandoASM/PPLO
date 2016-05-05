/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Controlador.ArticuloDaoHibernate;
import Controlador.UsuarioDaoHibernate;
import Controlador.TelefonoDaoHibernate;
import Controlador.ContrasenaDaoHibernate;
import Controlador.InstrumentoDaoHibernate;
import DAO.Articulo;
import DAO.Usuario;
import DAO.Contrasena;
import DAO.Instrumento;
import DAO.Telefono;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        UsuarioDaoHibernate usuarioDao = new UsuarioDaoHibernate();
        TelefonoDaoHibernate telefonoDao = new TelefonoDaoHibernate();
        ContrasenaDaoHibernate contrasenaDao = new ContrasenaDaoHibernate();
        
//        List<Contrasena> listContrasena;
//        ContrasenaDaoHibernate contrasenaDao = new ContrasenaDaoHibernate();
//        listContrasena= contrasenaDao.findAll();
  
        
        
//        for (Contrasena contrasenaD : listContrasena) {
//            System.out.println(contrasenaD.toString());
  //      }
        
        
        Usuario usuario = new Usuario();
        Telefono telefono = new Telefono();
        Contrasena contrasena = new Contrasena();
        
        Instrumento instrumento = new Instrumento();
        
        usuario.setCorreo("bobs@gmail.com");
        usuario.setNombre("AntiRodrigo");
        usuario.setApepaterno("Salazar");
        usuario.setApematerno("Delgado");
        usuario.setCalle("Cda. de las Rosas");
        usuario.setNumero(4);
        usuario.setColonia("San Isidro");
        usuario.setDelegacion("Xochimilco");
        usuario.setCodigopostal(16889);
        
        
        usuarioDao.save(usuario);
        System.out.println("Todo salio bien con el primer insert");
        
        telefono.setUsuario(usuario);
        telefono.setTelefono(554128488);
        telefonoDao.save(telefono);
        System.out.println("Todo salio bien con el segundo insert");
        
        
        contrasena.setUsuario(usuario);
        contrasena.setContrasena("1234");
        contrasenaDao.save(contrasena);
        System.out.println("Todo salio bien con el tercer insert");
        
                ArticuloDaoHibernate articuloDao = new ArticuloDaoHibernate();
        
        Articulo articulo = new Articulo();
        articulo.setIdarticulo(100);
        articulo.setUsuario(usuario);
        articulo.setDisponible(true);
        articulo.setDescripcion("Mi artículo es muy bueno y se encuentra en un buen estado");
        articuloDao.save(articulo);
        
        System.out.println("Todo salio bien con el cuarto insert");
        
        
        //Instrumento
            InstrumentoDaoHibernate instrumentoDAO = new InstrumentoDaoHibernate();
            instrumento.setArticulo(articulo);
            instrumento.setIdinstrumento(1);
            instrumento.setNombreinstrumento("guitarra");
            instrumento.setMarca("fender");
            instrumento.setAno(1984);
            instrumento.setTipo("acustico");
            instrumentoDAO.save(instrumento);
        
        System.out.println("Todo salio bien con el quinto insert");
        
        
        
    }
    
}
