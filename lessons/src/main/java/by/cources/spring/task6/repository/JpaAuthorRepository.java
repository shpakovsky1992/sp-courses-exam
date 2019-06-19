package by.cources.spring.task6.repository;

import by.cources.spring.task6.model.Author;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaAuthorRepository implements AuthorRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional
  public List<Author> findWithBookOlderThan(Long value) {
    String hql = "select a from Author a join a.books b where b.publishedIn >= :value";
    TypedQuery<Author> query = em.createQuery(hql, Author.class);
    query.setParameter("value", value);
    return query.getResultList();
  }

  @Override
  @Transactional
  public Optional<Author> findById(Long id) {
    return Optional.ofNullable(em.find(Author.class, id));
  }

  @Override
  @Transactional
  public List<Author> findAll() {
    return em.createQuery("select a from Author a", Author.class).getResultList();
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Author save(Author author) {
    if (author.getId() == null) {
      em.persist(author);
      return author;
    } else {
      return em.merge(author);
    }
  }
}
