package flux.test.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactivePersonRepository extends ReactiveCrudRepository<Person, String> {

}