package org.wjc.maven.service;

import org.jetbrains.annotations.NotNull;
import org.wjc.maven.model.Lender;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import static org.wjc.maven.constant.CommonConstants.ENTITY_MANAGER_FACTORY;

public final class LenderService {

    private LenderService() {
        // Prevent instantiation of this class
    }

    /**
     * Create a new Lender.
     */
    public static void create(long id, @NotNull String email, @NotNull String name, @NotNull String username,
                              @NotNull String password) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Lender lender = new Lender(id, email, name, username, password);
            manager.persist(lender);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
    }

    /**
     * Find a lender by username and password.
     *
     * @return a List of Lenders
     */
    public static Lender find(@NotNull String username, @NotNull String password) {

        Lender lender = null;

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Query query = manager
                    .createQuery("SELECT b FROM Lender b WHERE b.mUsername = :username AND b.mPassword = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            lender = (Lender) query.getSingleResult();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (!(ex instanceof NoResultException)) {
                ex.printStackTrace();
            }
        } finally {
            manager.close();
        }
        return lender;
    }

    /**
     * Delete the existing Lender.
     *
     * @param id
     */
    public static void delete(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Lender lender = manager.find(Lender.class, id);
            manager.remove(lender);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
    }

    /**
     * Update the existing Lender.
     *
     * @param id
     * @param name
     */
    public static void update(int id, @NotNull String name) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Lender lender = manager.find(Lender.class, id);
            lender.mName = name;
            manager.persist(lender);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
    }
}
