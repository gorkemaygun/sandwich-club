package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView mIngredientsDisplay;
    private ImageView mIngredientsImage;
    private TextView mDescriptionDisplay;
    private TextView mAlsoKnownAsDisplay;
    private TextView mOriginDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(mIngredientsImage);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        mIngredientsImage= findViewById(R.id.image_iv);
        mIngredientsDisplay=findViewById(R.id.ingredients_tv);
        mDescriptionDisplay=findViewById(R.id.description_tv);
        mAlsoKnownAsDisplay=findViewById(R.id.also_known_tv);
        mOriginDisplay=findViewById(R.id.origin_tv);

        mOriginDisplay.setText(sandwich.getPlaceOfOrigin());
        mAlsoKnownAsDisplay.setText(TextUtils.join(",",sandwich.getAlsoKnownAs()));
        mIngredientsDisplay.setText(TextUtils.join(",",sandwich.getIngredients()));
        mDescriptionDisplay.setText((sandwich.getDescription()));
        List<String> akas= sandwich.getAlsoKnownAs();
        String out="";
        for (String s :akas){
            out+=s+",";
        }
        if (out.length()>0){
            out=out.substring(0,out.length()-2);
        }
        mAlsoKnownAsDisplay.setText(handleMissing(out));
        out="";
        List<String> igList =sandwich.getIngredients();
        for (String s: igList){
            out+=s+"\n";
        }
        mIngredientsDisplay.setText(handleMissing(out));
    }
    private String handleMissing(String s ){
        if(s.equals("")){

            return getString(R.string.data_missing);
        }else{

            return s;
        }

    }
}
