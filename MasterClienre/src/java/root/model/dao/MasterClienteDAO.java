/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root.model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import root.model.dao.exceptions.NonexistentEntityException;
import root.model.dao.exceptions.PreexistingEntityException;
import root.model.dao.exceptions.RollbackFailureException;
import root.model.entities.MasterCliente;

/**
 *
 * @author Eliulson
 */
public class MasterClienteDAO implements Serializable {
    
    
    /* Se crea constructor vacío, el cual no recibe parámetros */
    public MasterClienteDAO() {
    }

    public MasterClienteDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MasterClienrePU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MasterCliente masterCliente) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(masterCliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                System.out.println("BAD");
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMasterCliente(masterCliente.getCliIdentificacion()) != null) {
                throw new PreexistingEntityException("MasterCliente " + masterCliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MasterCliente masterCliente) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(masterCliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                System.out.println("BAD");
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = masterCliente.getCliIdentificacion();
                if (findMasterCliente(id) == null) {
                    throw new NonexistentEntityException("The masterCliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MasterCliente masterCliente;
            try {
                masterCliente = em.getReference(MasterCliente.class, id);
                masterCliente.getCliIdentificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The masterCliente with id " + id + " no longer exists.", enfe);
            }
            em.remove(masterCliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                System.out.println("BAD");
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MasterCliente> findMasterClienteEntities() {
        return findMasterClienteEntities(true, -1, -1);
    }

    public List<MasterCliente> findMasterClienteEntities(int maxResults, int firstResult) {
        return findMasterClienteEntities(false, maxResults, firstResult);
    }

    private List<MasterCliente> findMasterClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MasterCliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MasterCliente findMasterCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MasterCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getMasterClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MasterCliente> rt = cq.from(MasterCliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
