/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author fernando
 * 
 */
import DAO.Accesorio;
import java.util.List;
/**
 *
 * @author fernando
 */
public class AccesorioDaoHibernate  extends AbstractDao {
    
    public AccesorioDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param accesorio
     */
    public void save(Accesorio accesorio) throws DataAccessLayerException {
        super.save(accesorio);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param accesorio
     */
    public void update(Accesorio accesorio) throws DataAccessLayerException {
        super.update(accesorio);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param accesorio
     */
    public void delete(Accesorio accesorio) throws DataAccessLayerException {
        super.delete(accesorio);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Accesorio find(Long id) throws DataAccessLayerException {
        return (Accesorio) super.find(Accesorio.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Accesorio.class);
    }
    
}

