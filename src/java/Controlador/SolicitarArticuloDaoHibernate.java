/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import DAO.Solicita;
import java.util.List;
/**
 *
 * @author fernando
 */
public class SolicitarArticuloDaoHibernate  extends AbstractDao {
    
    public SolicitarArticuloDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param solicita
     */
    public void save(Solicita solicita) throws DataAccessLayerException {
        super.save(solicita);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param solicita
     */
    public void update(Solicita solicita) throws DataAccessLayerException {
        super.update(solicita);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param solicita
     */
    public void delete(Solicita solicita) throws DataAccessLayerException {
        super.delete(solicita);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Solicita find(Integer id) throws DataAccessLayerException {
        return (Solicita) super.find(Solicita.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Solicita.class);
    }
    
}

