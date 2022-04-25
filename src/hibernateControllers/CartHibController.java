package hibernateControllers;

import book_store.ShopingCart;
import book_store.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CartHibController {
    private EntityManagerFactory emf = null;

    public CartHibController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createCart(ShopingCart shopingCart){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(shopingCart);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void updateCart(ShopingCart cart) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(cart);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
