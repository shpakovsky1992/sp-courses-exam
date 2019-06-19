package by.cources.spring.exam.repository;

import by.cources.spring.exam.model.Language;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface LanguageRepository extends CrudRepository<Language, Long> {

  @Query("select b from Language b where b.description = :value")
  Optional<Language> findByName(@Param("value") String value);
}
