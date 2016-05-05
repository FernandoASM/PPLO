package Controlador;

import DAO.Contrasena;
import java.util.List;

/**
 *
 * @author 
 */
public class ContrasenaDaoHibernate extends AbstractDao {

    public ContrasenaDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param contrasena
     */
    public void save(Contrasena contrasena) throws DataAccessLayerException {
        super.save(contrasena);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param contrasena
     */
    public void update(Contrasena contrasena) throws DataAccessLayerException {
        super.update(contrasena);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param contrasena
     */
    public void delete(Contrasena contrasena) throws DataAccessLayerException {
        super.delete(contrasena);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Contrasena find(Long id) throws DataAccessLayerException {
        return (Contrasena) super.find(Contrasena.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Contrasena.class);
    }
}
