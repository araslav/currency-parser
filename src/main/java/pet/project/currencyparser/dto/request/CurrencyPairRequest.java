package pet.project.currencyparser.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyPairRequest {
    @JsonProperty("curr1")
    private String first;
    @JsonProperty("curr2")
    private String second;
    @JsonProperty("lprice")
    private BigDecimal lastPrice;
}
