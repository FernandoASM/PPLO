package Controlador;

import DAO.Usuario;
import java.util.List;

/**
 *
 * @author 
 */
public class UsuarioDaoHibernate extends AbstractDao {

    public UsuarioDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param usuario
     */
    public void save(Usuario usuario) throws DataAccessLayerException {
        super.save(usuario);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param usuario
     */
    public void update(Usuario usuario) throws DataAccessLayerException {
        super.update(usuario);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param usuario
     */
    public void delete(Usuario usuario) throws DataAccessLayerException {
        super.delete(usuario);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Usuario find(Long id) throws DataAccessLayerException {
        return (Usuario) super.find(Usuario.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Usuario.class);
    }
}
