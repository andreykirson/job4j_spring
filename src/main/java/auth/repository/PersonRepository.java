package auth.repository;

import org.springframework.data.repository.CrudRepository;
import auth.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findAll();
}
