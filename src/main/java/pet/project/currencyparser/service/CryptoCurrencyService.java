package pet.project.currencyparser.service;

import java.util.List;
import pet.project.currencyparser.model.CurrencyPair;

public interface CryptoCurrencyService {
    CurrencyPair save(CurrencyPair currencyPair);

    CurrencyPair findMinPriceByCurrency(String currencyName);

    CurrencyPair findMaxPriceByCurrency(String currencyName);

    List<CurrencyPair> findAllByCurrencyName(String name, Integer page, Integer size);

    String generateCsvReport();
}
