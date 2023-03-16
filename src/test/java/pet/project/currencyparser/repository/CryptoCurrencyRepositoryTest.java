package pet.project.currencyparser.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Sort;
import pet.project.currencyparser.model.CurrencyPair;

@DataMongoTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class CryptoCurrencyRepositoryTest {
    @Autowired
    private CryptoCurrencyRepository repository;
    List<CurrencyPair> currencyPairList;

    @BeforeEach
    private void setUp() {
        currencyPairList = List.of(
                new CurrencyPair("1", "BTC", "USD", BigDecimal.valueOf(0.9), new Date()),
                new CurrencyPair("2", "BTC", "USD", BigDecimal.valueOf(1.1), new Date()),
                new CurrencyPair("3", "BTC", "USD", BigDecimal.valueOf(2), new Date()),
        new CurrencyPair("4", "BTC", "USD", BigDecimal.valueOf(2.2), new Date()));
        repository.saveAll(currencyPairList);
    }

    @Test
    public void findMinPriceByCurrency_Ok() {
        CurrencyPair currencyPair = repository
                .findFirstByFirst("BTC", Sort.by(Sort.Direction.ASC, "lastPrice"))
                .get();
        Assertions.assertEquals(currencyPair, currencyPairList.get(0));
    }

    @Test
    public void findMaxPriceByCurrency_Ok() {
        CurrencyPair currencyPair = repository
                .findFirstByFirst("BTC", Sort.by(Sort.Direction.DESC, "lastPrice"))
                .get();
        Assertions.assertEquals(currencyPair, currencyPairList.get(currencyPairList.size() - 1));
    }
}