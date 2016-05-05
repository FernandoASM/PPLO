/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author fernando
 */
import DAO.Instrumento;
import java.util.List;
/**
 *
 * @author fernando
 */
public class InstrumentoDaoHibernate  extends AbstractDao {
    
    public InstrumentoDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param instrumento
     */
    public void save(Instrumento instrumento) throws DataAccessLayerException {
        super.save(instrumento);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param instrumento
     */
    public void update(Instrumento instrumento) throws DataAccessLayerException {
        super.update(instrumento);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param libro
     */
    public void delete(Instrumento instrumento) throws DataAccessLayerException {
        super.delete(instrumento);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Instrumento find(Long id) throws DataAccessLayerException {
        return (Instrumento) super.find(Instrumento.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Instrumento.class);
    }
    
}

