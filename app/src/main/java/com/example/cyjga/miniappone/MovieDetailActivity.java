package com.example.cyjga.miniappone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by cyjga on 2018-02-12.
 */

public class MovieDetailActivity extends AppCompatActivity{

    private ScrollView mScrollView;
    private Context mContext;
    // override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mContext=this;

        String title = this.getIntent().getExtras().getString("title");
        String url = this.getIntent().getExtras().getString("url");
        String description = this.getIntent().getExtras().getString("description");
        String poster = this.getIntent().getExtras().getString("poster");
        final int position = this.getIntent().getExtras().getInt("position");

        TextView titleView = findViewById(R.id.movie_detail_title);
        ImageView posterImageView = findViewById(R.id.movie_detail_poster);
        TextView descriptionView = findViewById(R.id.movie_detail_description);


        final RadioGroup radioGroup = findViewById(R.id.movie_detail_radioGroup);
        // set the title on the action bar
        setTitle(title);

        titleView.setText(title);
        Picasso.with(mContext).load(poster).into(posterImageView);
        descriptionView.setText(description);

        // create the webview and load the url
        mScrollView = findViewById(R.id.movie_detail_view);
        Button button = (Button) findViewById(R.id.movie_detail_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                String returnValue = null;
                switch(radioGroup.getCheckedRadioButtonId()){
                    case R.id.movie_detail_radioAlreadySeen:
                        returnValue = "Seen";
                        break;
                    case R.id.movie_detail_radioWantToSee:
                        returnValue = "Want";
                        break;
                    case R.id.movie_detail_radioDoNotLike:
                        returnValue = "NotLike";
                        break;
                    default:
                        returnValue = "";
                }
                intent.putExtra("editTextValue", returnValue);
                intent.putExtra("position", position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
/*
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.movie_detail_radioAlreadySeen:
                if (checked) {

                }

                break;
            case R.id.movie_detail_radioWantToSee:
                if (checked) {

                }

                break;
            case R.id.movie_detail_radioDoNotLike:
                if (checked){

                }
                break;
        }
    }*/
}


