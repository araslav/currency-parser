package pet.project.currencyparser.dto.mapper;

import java.util.Date;
import org.springframework.stereotype.Component;
import pet.project.currencyparser.dto.ModelMapper;
import pet.project.currencyparser.dto.request.CurrencyPairRequest;
import pet.project.currencyparser.model.CurrencyPair;

@Component
public class CurrencyPairModelMapperImpl implements ModelMapper<CurrencyPair,
        CurrencyPairRequest> {
    @Override
    public CurrencyPair toModel(CurrencyPairRequest requestDto) {
        CurrencyPair currencyPair = new CurrencyPair();
        currencyPair.setFirst(requestDto.getFirst());
        currencyPair.setSecond(requestDto.getSecond());
        currencyPair.setLastPrice(requestDto.getLastPrice());
        currencyPair.setCreatedAt(new Date());
        return currencyPair;
    }
}
