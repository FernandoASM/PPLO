/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Rodrigo
 */
import DAO.Prestigioprestador;
import java.util.List;

/**
 *
 * @author 
 */
public class PrestigioDaoHibernate extends AbstractDao {

    public PrestigioDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param prestigioprestador
     */
    public void save(Prestigioprestador prestigioprestador) throws DataAccessLayerException {
        super.save(prestigioprestador);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param prestigioprestador
     */
    public void update(PrestigioDaoHibernate prestigioprestador) throws DataAccessLayerException {
        super.update(prestigioprestador);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param prestigioprestador
     */
    public void delete(PrestigioDaoHibernate prestigioprestador) throws DataAccessLayerException {
        super.delete(prestigioprestador);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public PrestigioDaoHibernate find(Integer id) throws DataAccessLayerException {
        return (PrestigioDaoHibernate) super.find(PrestigioDaoHibernate.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(PrestigioDaoHibernate.class);
    }
}
