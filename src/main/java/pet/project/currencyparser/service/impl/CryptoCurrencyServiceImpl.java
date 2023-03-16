package pet.project.currencyparser.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pet.project.currencyparser.model.CurrencyPair;
import pet.project.currencyparser.repository.CryptoCurrencyRepository;
import pet.project.currencyparser.service.CryptoCurrencyService;
import pet.project.currencyparser.service.report.ReportService;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    @Value("${arrayOfCurrencyPairs}")
    private String[] arrayOfCurrencyPairs;
    private final CryptoCurrencyRepository currencyPairRepository;
    private final ReportService reportService;

    public CryptoCurrencyServiceImpl(CryptoCurrencyRepository currencyPairRepository,
                                     ReportService reportService) {
        this.currencyPairRepository = currencyPairRepository;
        this.reportService = reportService;
    }

    @Override
    public CurrencyPair save(CurrencyPair currencyPair) {
        return currencyPairRepository.save(currencyPair);
    }

    @Override
    public CurrencyPair findMinPriceByCurrency(String currencyName) {
        Sort sort = Sort.by(Sort.Direction.ASC, "lastPrice");
        return currencyPairRepository.findFirstByFirst(currencyName, sort)
                .orElseThrow(() ->
                        new RuntimeException("Didn't find currency pair by name: " + currencyName));
    }

    @Override
    public CurrencyPair findMaxPriceByCurrency(String currencyName) {
        Sort sort = Sort.by(Sort.Direction.DESC, "lastPrice");
        return currencyPairRepository.findFirstByFirst(currencyName, sort)
                .orElseThrow(() ->
                        new RuntimeException("Didn't find currency pair by name: " + currencyName));
    }

    @Override
    public List<CurrencyPair> findAllByCurrencyName(String name, Integer page, Integer size) {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "lastPrice");
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return currencyPairRepository.findAllByFirst(name, pageable);
    }

    @Override
    public String generateCsvReport() {
        List<String[]> stringList = new ArrayList<>();

        for (String arrayOfCurrencyPair : arrayOfCurrencyPairs) {
            String[] split = arrayOfCurrencyPair.split("/");
            CurrencyPair currencyPairMin = findMinPriceByCurrency(split[0]);
            CurrencyPair currencyPairMax = findMaxPriceByCurrency(split[0]);
            stringList.add(new String[]{split[0], currencyPairMin.getLastPrice().toString(),
                    currencyPairMax.getLastPrice().toString()});
        }
        return reportService.generate(stringList);
    }
}
