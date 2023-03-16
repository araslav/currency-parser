package pet.project.currencyparser.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "currencyPairs")
public class CurrencyPair {
    @Id
    private String id;
    private String first;
    private String second;
    private BigDecimal lastPrice;
    private Date createdAt;
}
