package pet.project.currencyparser.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pet.project.currencyparser.model.CurrencyPair;

@Repository
public interface CryptoCurrencyRepository extends MongoRepository<CurrencyPair, String> {
    Optional<CurrencyPair> findFirstByFirst(String name, Sort sort);

    List<CurrencyPair> findAllByFirst(String name, Pageable paging);
}
