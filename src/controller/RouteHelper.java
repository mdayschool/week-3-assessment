package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Route;

public class RouteHelper {
    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ClimbingRoutes");

    public void insertRoute(Route r) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
        em.close();
    }
    
    public List<Route> getAllRoutes() {
        EntityManager em = emf.createEntityManager();
        List<Route> r = em.createQuery("SELECT i FROM Route i").getResultList();
        return r;
    }
 
    public void deleteRoute(Route toDelete) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Route> typedQuery = em.createQuery("select li from "
                + "Route li where li.name = :selectedName", Route.class);
        typedQuery.setParameter("selectedName", toDelete.getName());
        typedQuery.setMaxResults(1);
        Route result = typedQuery.getSingleResult();
        em.remove(result);
        em.getTransaction().commit();
        em.close();
    }

    public Route searchForItemById(int idToEdit) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Route found = em.find(Route.class, idToEdit);
        em.close();
        return found;
    }

    public void updateItem(Route toEdit) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(toEdit);
        em.getTransaction().commit();
        em.close();
    }

    public List<Route> searchRouteByName(String name) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Route> typedQuery = em.createQuery(
                "select li from Route li where li.name = :selectedName",
                Route.class);
        typedQuery.setParameter("selectedName", name);
        List<Route> foundRoutes = typedQuery.getResultList();
        em.close();
        return foundRoutes;
    }

    public List<Route> searchRouteByDifficulty(String difficulty) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Route> typedQuery = em.createQuery(
                "select li from Route li where li.difficulty = :selectedDifficulty",
                Route.class);
        typedQuery.setParameter("selectedItem", difficulty);
        List<Route> foundRoutes = typedQuery.getResultList();
        em.close();
        return foundRoutes;
    }

    public void cleanUp() {
        emf.close();
    }   
    
}
