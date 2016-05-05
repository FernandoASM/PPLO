package Controlador;

import DAO.Telefono;
import java.util.List;

/**
 *
 * @author 
 */
public class TelefonoDaoHibernate extends AbstractDao {

    public TelefonoDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param usuario
     */
    public void save(Telefono telefono) throws DataAccessLayerException {
        super.save(telefono);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param usuario
     */
    public void update(Telefono telefono) throws DataAccessLayerException {
        super.update(telefono);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param usuario
     */
    public void delete(Telefono telefono) throws DataAccessLayerException {
        super.delete(telefono);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Telefono find(Long id) throws DataAccessLayerException {
        return (Telefono) super.find(Telefono.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Telefono.class);
    }
}
