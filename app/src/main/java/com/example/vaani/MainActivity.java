package com.example.vaani;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteText, authorText;
    private Button newQuoteButton;
    private ArrayList<Quote> quoteList;
    private Random random;
    private Animation fadeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quote_text);
        authorText = findViewById(R.id.author_text);
        newQuoteButton = findViewById(R.id.new_quote_button);
        random = new Random();

        // Load fade-in animation from res/anim/fade_in.xml
        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        quoteList = QuoteDataLoader.loadQuotes(this); // local quote loader

        showRandomQuote();

        newQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRandomQuote();
            }
        });
    }

    private void showRandomQuote() {
        if (!quoteList.isEmpty()) {
            int index = random.nextInt(quoteList.size());
            Quote quote = quoteList.get(index);

            quoteText.setText("\"" + quote.getText() + "\"");
            authorText.setText("- " + quote.getAuthor());

            // Apply fade-in animation to both TextViews
            quoteText.startAnimation(fadeInAnimation);
            authorText.startAnimation(fadeInAnimation);
        }
    }
}