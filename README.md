# Currency Parser

Technologies:
- Java 11
- String Boot, Spring Data.
- MongoDB
- Lombok.

Description:
- Cron job timer runs every 10 seconds and pulls cryptocurrency (for the following pairs: BTC/USD, ETH/USD XRP/USD)
 last prices from CEX.IO. This data save in MongoBd.
- Rest controller return information from BD about min and max price by currency or list.
- Rest controller generate a CSV report saved into file. Report contains the following fields: 
Cryptocurrency Name, Min Price, Max Price.

Rest Endpoints:
- GET /cryptocurrencies/minprice?name=[currency_name]
- GET /cryptocurrencies/maxprice?name=[currency_name]
- GET /cryptocurrencies?name=[currency_name]&page=[page_number]&size=[page_size]
- GET /cryptocurrencies/csv
