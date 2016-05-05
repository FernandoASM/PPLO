/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import DAO.Articulo;
import java.util.List;
/**
 *
 * @author fernando
 */
public class ArticuloDaoHibernate extends AbstractDao {
   
    public ArticuloDaoHibernate() {
        super();
    }
    
    /**
     * Insert a new Articulo into the database.
     *
     * @param articulo
     */
    public void save(Articulo articulo) throws DataAccessLayerException {
        super.save(articulo);
    }

    /**
     * Updates a new Articulo into the database.
     *
     * @param articulo
     */
    public void update(Articulo articulo) throws DataAccessLayerException {
        super.update(articulo);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param articulo
     */
    public void delete(Articulo articulo) throws DataAccessLayerException {
        super.delete(articulo);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Articulo find(Long id) throws DataAccessLayerException {
        return (Articulo) super.find(Articulo.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Articulo.class);
    }
}
