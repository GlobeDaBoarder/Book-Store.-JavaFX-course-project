package hibernateControllers;

import book_store.Book;
import book_store.ShopingCart;
import book_store.User;
import book_store.eCartStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<ShopingCart> getAllCarts() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(ShopingCart.class));
            Query q = em.createQuery(query);

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public List<ShopingCart> getAllVerifiedCarts() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ShopingCart> query = cb.createQuery(ShopingCart.class);
            Root<ShopingCart> root = query.from(ShopingCart.class);
            query.select(root).where(cb.equal(root.get("cartStatus"), eCartStatus.SUBMITTED));
            Query q = em.createQuery(query);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public ShopingCart getCartById(int id){
        EntityManager em = null;
        ShopingCart shopingCart = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            shopingCart = em.find(ShopingCart.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No user with such ID");
        }
        return shopingCart;
    }

    public void removeCart(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ShopingCart shopingCart = null;
            try {
                shopingCart = em.getReference(ShopingCart.class, id);
            } catch (Exception e) {
                System.out.println("No cart with such ID");
            }

            for(Book b:shopingCart.getBooks()){
                b.getInCarts().remove(shopingCart);
                em.merge(b);
            }

            User user = shopingCart.getBuyer();
            user.getMyOwnOrders().remove(shopingCart);
            em.merge(user);

            shopingCart.getBooks().clear();
            em.merge(shopingCart);

            em.remove(shopingCart);
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
