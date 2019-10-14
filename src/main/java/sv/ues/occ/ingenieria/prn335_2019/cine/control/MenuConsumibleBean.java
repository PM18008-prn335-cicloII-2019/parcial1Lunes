/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.occ.ingenieria.prn335_2019.cine.control;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.ues.occ.ingenieria.prn335_2019.cine.DataAccessException;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.MenuConsumible;

/**
 *
 * @author jcpenya
 */
@Stateless
@LocalBean
public class MenuConsumibleBean implements Serializable {

    @PersistenceContext(unitName = "PrimerParcialPU")
    EntityManager em;

    public List<MenuConsumible> findByNombreLike(final String nombre, int first, int pageSize) throws DataAccessException {
        if (em != null && nombre != null) {
            try {
                return em.createNamedQuery("MenuConsumible.findByNombreLike").setParameter("nombre", "%" + nombre.trim().toUpperCase() + "%").setFirstResult(first).setMaxResults(pageSize).getResultList();
            } catch (Exception ex) {
                throw new DataAccessException(ex.getMessage(), ex);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public int countByNombreLike(final String nombre) throws DataAccessException {
        if (em != null && nombre != null) {
            try {
                return ((Long) em.createNamedQuery("MenuConsumible.findByNombreLike").setParameter("nombre", "%" + nombre.trim().toUpperCase() + "%").getSingleResult()).intValue();
            } catch (Exception ex) {
                throw new DataAccessException(ex.getMessage(), ex);
            }
        }
        return 0;
    }

}
