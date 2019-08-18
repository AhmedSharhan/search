package com.zawamil.ahmedsharhan.search;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
TextView textView;
EditText editText;
ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.tv);
        editText=findViewById(R.id.ev);
        scrollView=findViewById(R.id.scroll);
    }
int num=0;
    int cont=0;
    ArrayList<Integer> integers=new ArrayList<>();
    public void search(View view) {

        String textToFind = editText.getText().toString().trim().toLowerCase();
        String fullTxt = textView.getText().toString();

        SpannableString spannable = new SpannableString(fullTxt);


        final int index = fullTxt.indexOf(textToFind);


        if(index == -1) {
            // text does not contain the word
            Toast.makeText(getApplicationContext(), "Text '" + textToFind + "' not found.", Toast.LENGTH_SHORT).show();

        }
        else {

            int lineNum = textView.getLayout().getLineForOffset(index);
            int lineStart = textView.getLayout().getLineEnd(lineNum -1);
            int lineEnd = textView.getLayout().getLineEnd(lineNum);
            // set style to the entire line, as your origional code
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#035525")), lineStart, lineEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), lineStart, lineEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            textView.setText(spannable);
            textView.post(new Runnable() {
                @Override
                public void run() {
                    int line = textView.getLayout().getLineForOffset(index);
                    int y = textView.getLayout().getLineTop(line);
                    scrollView.scrollTo(0, y);
                }
            });
        }
    }
}
