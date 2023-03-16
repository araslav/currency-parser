package pet.project.currencyparser.dto.mapper;

import org.springframework.stereotype.Component;
import pet.project.currencyparser.dto.ResponseMapper;
import pet.project.currencyparser.dto.responce.CurrencyPairResponse;
import pet.project.currencyparser.model.CurrencyPair;

@Component
public class CurrencyPairResponseMapperImpl implements ResponseMapper<CurrencyPair,
        CurrencyPairResponse> {
    @Override
    public CurrencyPairResponse toResponseDto(CurrencyPair model) {
        CurrencyPairResponse currencyPairResponse = new CurrencyPairResponse();
        currencyPairResponse.setId(model.getId());
        currencyPairResponse.setFirst(model.getFirst());
        currencyPairResponse.setSecond(model.getSecond());
        currencyPairResponse.setLastPrice(model.getLastPrice());
        currencyPairResponse.setCreatedAt(model.getCreatedAt());
        return currencyPairResponse;
    }
}
