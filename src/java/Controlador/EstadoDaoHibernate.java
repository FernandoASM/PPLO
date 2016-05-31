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
import DAO.Estado;
import java.util.List;


public class EstadoDaoHibernate extends AbstractDao {

    public EstadoDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param estado
     */
    public void save(Estado estado) throws DataAccessLayerException {
        super.save(estado);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param estado
     */
    public void update(Estado estado) throws DataAccessLayerException {
        super.update(estado);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param estado
     */
    public void delete(Estado estado) throws DataAccessLayerException {
        super.delete(estado);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Estado find(Integer id) throws DataAccessLayerException {
        return (Estado) super.find(Estado.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Estado.class);
    }
}
