package com.paysera.sdk.wallet.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ListMoneyAdapter extends TypeAdapter<List<Money>> {

    private final RoundingMode roundingMode;

    public ListMoneyAdapter(RoundingMode roundingMode) {
        this.roundingMode = roundingMode;
    }

    public ListMoneyAdapter() {
        this.roundingMode = RoundingMode.HALF_UP;
    }

    @Override
    public void write(JsonWriter out, List<Money> moneyList) throws IOException {
        out.beginArray();
        for (Money money : moneyList) {
            out.beginObject();
            out.name("amount").value(money.getAmount().toPlainString());
            out.name("currency").value(money.getCurrencyUnit().getCode());
            out.endObject();
        }
        out.endArray();
    }

    @Override
    public List<Money> read(JsonReader in) throws IOException {
        ArrayList<Money> outMoneyList = new ArrayList();
        in.beginArray();
        String amount = null;
        String currency = null;
        while (in.hasNext()) {
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                if (nextName.equals("amount")) {
                    amount = in.nextString();
                } else if (nextName.equals("currency")) {
                    currency = in.nextString();
                }
            }
            outMoneyList.add(
                Money.of(CurrencyUnit.of(currency), new BigDecimal(amount), roundingMode)
            );
            in.endObject();
        }
        in.endArray();
        return outMoneyList;
    }
}

//class TestMain {
//    public static void main(String[] args) {
//        ListMoneyAdapter adapter = new ListMoneyAdapter(RoundingMode.HALF_UP);
//
//        List<Money> testMoneyList = new ArrayList<Money>();
//        testMoneyList.add(Money.of(CurrencyUnit.EUR, BigDecimal.TEN));
//        testMoneyList.add(Money.of(CurrencyUnit.GBP, BigDecimal.ZERO));
//        testMoneyList.add(Money.of(CurrencyUnit.JPY, BigDecimal.TEN));
//
//        Writer writer = new StringWriter();
//
//        JsonWriter jsonWriter = new JsonWriter(writer);
//
//        try {
//            adapter.write(jsonWriter, testMoneyList);
//
//            System.out.println(writer);
//
//            String input = writer.toString();
//
//            Reader reader = new StringReader(input);
//
//            JsonObject object = new JsonObject();
//
//            JsonReader testReader = new JsonReader(reader);
//
//            List<Money> testList = adapter.read(testReader);
//            for (int i = 0; i < testList.size(); i++) {
//                Money money = testList.get(i);
//                System.out.println(String.format("%d. %s", i + 1, money.toString()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}