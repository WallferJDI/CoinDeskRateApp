package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.Constants;
import configuration.UrlConstants;
import model.Bitcoin;
import model.Currency;
import utils.UrlGenerator;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CoinDeskService {

    private final UrlGenerator urlGenerator = new UrlGenerator();
    private final ObjectMapper mapper  = new ObjectMapper();

    public String getBitcoinCurrentRate(Currency currency) throws IOException {
        StringBuilder bitcoinInfo = new StringBuilder();
        List<Bitcoin> bitcoinRateList = getBitcoinRate((new URL(UrlConstants.COIN_JSON_URL+ UrlConstants.REALTIME_ENDPOINT)));
        for (Bitcoin bitcoinRate:bitcoinRateList) {
            if(bitcoinRate.getCode().equals(currency)){
                bitcoinInfo.append("Current rate: " + bitcoinRate.getRate()+" " + bitcoinRate.getCode() + "\n");
            }
        }
        bitcoinInfo.append(getYearRateStatistic(urlGenerator.getUrlRangeDate(currency)));
        return bitcoinInfo.toString();
    }

    public List<Bitcoin> getBitcoinRate(URL url) throws IOException {
        JsonNode jsonNode =  mapper.readTree(url);
        List<Bitcoin> bitcoinRateList = new ArrayList<>();
        jsonNode.get(UrlConstants.TARGET_NODE).elements().forEachRemaining((e)-> {
            try {
                bitcoinRateList.add(mapper.treeToValue(e,Bitcoin.class));
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        });
        return bitcoinRateList;
    }


    public StringBuilder getYearRateStatistic(URL url) throws IOException {
        JsonNode jsonNode =  mapper.readTree(url);
        HashMap<String,Number> hashMap = mapper.treeToValue( jsonNode.get(UrlConstants.TARGET_NODE), HashMap.class);
        List<Number> numberList = hashMap.values().stream().toList();
        Number max = numberList.stream().mapToDouble(Number::doubleValue).max().getAsDouble();
        Number min = numberList.stream().mapToDouble(Number::doubleValue).min().getAsDouble();
        return new StringBuilder().append(Constants.DAYS_RANGE +" days statistic:")
                .append("\n\tLowest rate: "+min)
                .append("\n\tHighest rate: "+max);
    }

}
