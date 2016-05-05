/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import DAO.Sonido;
import java.util.List;
/**
 *
 * @author fernando
 */
public class SonidoDaoHibernate  extends AbstractDao {
    
    public SonidoDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param sonido
     */
    public void save(Sonido sonido) throws DataAccessLayerException {
        super.save(sonido);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param sonido
     */
    public void update(Sonido sonido) throws DataAccessLayerException {
        super.update(sonido);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param sonido
     */
    public void delete(Sonido sonido) throws DataAccessLayerException {
        super.delete(sonido);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Sonido find(Long id) throws DataAccessLayerException {
        return (Sonido) super.find(Sonido.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Sonido.class);
    }
    
}

