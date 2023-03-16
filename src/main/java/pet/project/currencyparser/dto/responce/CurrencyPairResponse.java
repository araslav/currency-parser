package pet.project.currencyparser.dto.responce;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class CurrencyPairResponse {
    private String id;
    private String first;
    private String second;
    private BigDecimal lastPrice;
    private Date createdAt;
}
