package pet.project.currencyparser.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.project.currencyparser.dto.ResponseMapper;
import pet.project.currencyparser.dto.responce.CurrencyPairResponse;
import pet.project.currencyparser.model.CurrencyPair;
import pet.project.currencyparser.service.CryptoCurrencyService;

@RestController
@RequestMapping("/cryptocurrencies")
public class CryptoCurrencyController {
    private final CryptoCurrencyService cryptoCurrencyService;
    private final ResponseMapper<CurrencyPair, CurrencyPairResponse> responseMapper;

    public CryptoCurrencyController(CryptoCurrencyService cryptoCurrencyService,
                                    ResponseMapper<CurrencyPair,
                                            CurrencyPairResponse> responseMapper) {
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.responseMapper = responseMapper;
    }

    @GetMapping("/minprice")
    public CurrencyPairResponse getMinPrice(@RequestParam String name) {
        return responseMapper.toResponseDto(cryptoCurrencyService.findMinPriceByCurrency(name));
    }

    @GetMapping("/maxprice")
    public CurrencyPairResponse getMaxPrice(@RequestParam String name) {
        return responseMapper.toResponseDto(cryptoCurrencyService.findMaxPriceByCurrency(name));
    }

    @GetMapping
    public List<CurrencyPairResponse> findAllByCurrencyName(@RequestParam String name,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "10") Integer size) {
        return cryptoCurrencyService.findAllByCurrencyName(name, page, size)
                .stream()
                .map(responseMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/csv")
    public String generateCsvReport() {
        return cryptoCurrencyService.generateCsvReport();
    }
}
