/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Presta;
import java.util.List;

/**
 *
 * @author Rodrigo
 */

public class PrestaDaoHibernate  extends AbstractDao {
    
    public PrestaDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param presta
     */
    public void save(Presta presta) throws DataAccessLayerException {
        super.save(presta);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param presta
     */
    public void update(Presta presta) throws DataAccessLayerException {
        super.update(presta);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param presta
     */
    public void delete(Presta presta) throws DataAccessLayerException {
        super.delete(presta);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Presta find(Integer id) throws DataAccessLayerException {
        return (Presta) super.find(Presta.class, id);
    }

    /**
     * Lista todos los Presta de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Presta.class);
    }
    
}


