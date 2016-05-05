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
import DAO.Musica;
import java.util.List;
/**
 *
 * @author fernando
 */
public class MusicaDaoHibernate  extends AbstractDao {
    
    public MusicaDaoHibernate() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param musica
     */
    public void save(Musica musica) throws DataAccessLayerException {
        super.save(musica);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param libro
     */
    public void update(Musica musica) throws DataAccessLayerException {
        super.update(musica);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param musica
     */
    public void delete(Musica musica) throws DataAccessLayerException {
        super.delete(musica);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Musica find(Long id) throws DataAccessLayerException {
        return (Musica) super.find(Musica.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Musica.class);
    }
    
}

