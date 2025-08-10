package com.example.vaani;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class QuoteDataLoader {

    public static ArrayList<Quote> loadQuotes(Context context) {
        ArrayList<Quote> quoteList = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("quotes.json");
            Scanner scanner = new Scanner(is);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }

            JSONArray jsonArray = new JSONArray(builder.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject quoteObj = jsonArray.getJSONObject(i);
                String text = quoteObj.getString("text");
                String author = quoteObj.getString("author");
                quoteList.add(new Quote(text, author));
            }

            scanner.close();
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return quoteList;
    }
}

