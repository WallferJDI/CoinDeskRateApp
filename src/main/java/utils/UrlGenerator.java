package utils;

import configuration.Constants;
import configuration.UrlConstants;
import model.Currency;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class UrlGenerator {
    public URL getUrlRangeDate(Currency currency) throws MalformedURLException {
        return new URL(UrlConstants.HISTORY_URL + LocalDate.now().minusDays(Constants.DAYS_RANGE)+"&end="
                +LocalDate.now()+"&currency="+currency.toString().toLowerCase()) ;
    }
}
