import configuration.Constants;
import controller.CoinDeskController;

import model.Currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoinDeskCheckApp {
    private final CoinDeskController controller = new CoinDeskController();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        CoinDeskCheckApp coinDeskCheckApp = new CoinDeskCheckApp();
        coinDeskCheckApp.run();

    }

    public void run() throws IOException {
        while (true){
            Currency currencyRequest = null;
            System.out.println(Constants.MEET_MESSAGE);
            try {
                currencyRequest = Currency.valueOf(reader.readLine().toUpperCase());
            }catch (IllegalArgumentException exception){
                System.out.println(Constants.INVALID_CURRENCY_ERROR);
                continue;
            }
            System.out.println(controller.getBitcoinRate(currencyRequest));
        }
    }
}
