package pet.project.currencyparser.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pet.project.currencyparser.dto.ModelMapper;
import pet.project.currencyparser.dto.request.CurrencyPairRequest;
import pet.project.currencyparser.model.CurrencyPair;
import pet.project.currencyparser.repository.CryptoCurrencyRepository;
import pet.project.currencyparser.service.CryptocurrencyPuller;

@Component
public class CryptocurrencyPullerImpl implements CryptocurrencyPuller {
    @Value("${arrayOfCurrencyPairs}")
    private String[] arrayOfCurrencyPairs;
    @Value("${cexApiLastPriceUrl}")
    private String cexApiLastPriceUrl;
    private final RestTemplate restTemplate;
    private final CryptoCurrencyRepository currencyPairRepository;
    private final ModelMapper<CurrencyPair, CurrencyPairRequest> mapper;

    public CryptocurrencyPullerImpl(RestTemplate restTemplate,
                                    CryptoCurrencyRepository currencyPairRepository,
                                    ModelMapper<CurrencyPair, CurrencyPairRequest> mapper) {
        this.restTemplate = restTemplate;
        this.currencyPairRepository = currencyPairRepository;
        this.mapper = mapper;
    }

    @Override
    @Scheduled(fixedDelay = 10000)
    public void getCurrencies() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        for (String currencyPair : arrayOfCurrencyPairs) {
            CurrencyPairRequest currencyPairRequest =
                    restTemplate.getForObject(cexApiLastPriceUrl
                            + "/" + currencyPair, CurrencyPairRequest.class);
            currencyPairRepository.save(mapper.toModel(currencyPairRequest));
        }

    }
}
