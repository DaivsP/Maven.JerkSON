package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;
import io.zipcoder.utils.match.Match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    public List<Item> parseItemList(String valueToParse) {
        String[] itemArr = valueToParse.split("##");
        List<Item> answer = new ArrayList<>();

        for (String string : itemArr) {
            try {
                answer.add(parseSingleItem(string + "##"));

            }
            catch(ItemParseException e){
            }
        }
        return answer;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        Item item = new Item(parseName(singleItem), parsePrice(singleItem), parseType(singleItem), parseExpiration(singleItem));
        return item;
    }

    public String parseName(String singleItem) throws ItemParseException {
        String stringName;
        String lowerCase = singleItem.toLowerCase();
        Pattern pattern = Pattern.compile("name([:|@|^|*|%])(\\w+).+##");
        return throwException(lowerCase, pattern);
    }

    public Double parsePrice(String singleItem) throws ItemParseException {
        Double doublePrice;
        Pattern pattern = Pattern.compile(".*price([:|@|^|*|%])(\\d+\\.\\d{1,2}).+##");
        Matcher matcher = pattern.matcher(singleItem);
        if (matcher.matches()) {
            doublePrice = Double.parseDouble(matcher.group(2));
        } else {
            throw new ItemParseException();
        }
        return doublePrice;
    }

    public String parseType(String singleItem) throws ItemParseException {
        String stringType;
        String lowerCase = singleItem.toLowerCase();
        Pattern pattern = Pattern.compile(".*type([:|@|^|*|%])(\\w+).+##");
        return throwException(lowerCase, pattern);
    }

    public String parseExpiration(String singleItem) throws ItemParseException {
        String stringExpiration;
        Pattern pattern = Pattern.compile(".*expiration([:|@|^|*|%])(\\d{1,2}/\\d{1,2}/\\d{4})##");
        return throwException2(singleItem, pattern);
    }

    private String throwException(String lowerCase, Pattern pattern) throws ItemParseException {
        String stringType;
        return throwException2(lowerCase, pattern);
    }

    private String throwException2(String singleItem, Pattern pattern) throws ItemParseException {
        String stringExpiration;
        Matcher matcher = pattern.matcher(singleItem);
        if (matcher.matches()) {
            stringExpiration = matcher.group(2);
        } else {
            throw new ItemParseException();
        }
        return stringExpiration;
    }
}
