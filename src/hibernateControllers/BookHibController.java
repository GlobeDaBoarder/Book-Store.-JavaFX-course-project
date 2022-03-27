package hibernateControllers;

import book_store.Book;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookHibController {
    private EntityManagerFactory emf = null;

    public BookHibController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createBook(Book book) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editBook(Book old_book, Book new_book) {
        EntityManager em = null;

        old_book.setAuthor(new_book.getAuthor());
        old_book.setQuantityAvalible(new_book.getQuantityAvalible());
        old_book.setDescription(new_book.getDescription());
        old_book.setGenre(new_book.getGenre());
        old_book.setName(new_book.getName());
        old_book.setLang(new_book.getLang());
        old_book.setPrice(new_book.getPrice());
        old_book.setPageCount(new_book.getPageCount());
        old_book.setReleaseDate(new_book.getReleaseDate());


        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(old_book);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Book getBookById(int id) {
        EntityManager em = null;
        Book book = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            book = em.getReference(Book.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No book with such ID");
        }
        return book;
    }

    public void removeBook(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book book = null;
            try {
                book = em.getReference(Book.class, id);
            } catch (Exception e) {
                System.out.println("No book with such ID");
            }
            em.remove(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List getAllBooks(boolean onlyAvailableBooks) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Book> query = cb.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            if (onlyAvailableBooks)
                query.select(root).where(cb.equal(root.get("isAvailable"), true));
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
}
