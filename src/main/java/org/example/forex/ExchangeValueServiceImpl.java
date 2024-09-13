//package org.example.forex;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Service
//public class ExchangeValueServiceImpl implements ExchangeValueService {
//    private final ExchangeValueRepository exchangeValueRepository;
//    public ExchangeValueServiceImpl(ExchangeValueRepository exchangeValueRepository) {this.exchangeValueRepository = exchangeValueRepository;}
//
//    @Override
//    public List<ExchangeValue> readAll() {
//        return exchangeValueRepository.findAll();
//    }
//    @Override
//    public ExchangeValue read (@PathVariable String from, @PathVariable String to) {
//        return exchangeValueRepository.findByFromAndTo(from, to);
//    }
//}
