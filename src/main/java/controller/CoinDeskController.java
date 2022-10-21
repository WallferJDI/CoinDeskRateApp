package controller;

import model.Currency;
import service.CoinDeskService;

import java.io.IOException;

public class CoinDeskController {

    private final CoinDeskService coinDeskService = new CoinDeskService();

    public String getBitcoinRate(Currency currency) throws IOException {
        return coinDeskService.getBitcoinCurrentRate(currency);
    }
}
