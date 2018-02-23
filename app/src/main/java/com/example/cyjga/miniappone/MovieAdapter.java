package com.example.cyjga.miniappone;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

/**
 * Created by cyjga on 2018-02-12.
 */

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    public MovieAdapter (Context mContext, ArrayList<Movie> mMovieList) {

        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    // methods
    // a list of methods we need to override

    // gives you the number of recipes in the data source
    @Override
    public int getCount(){
        return mMovieList.size();
    }

    // returns the item at specific position in the data source

    @Override
    public Object getItem(int position){
        return mMovieList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position){
        return position;
    }

    public void updateMovieSeenStatus() { notifyDataSetChanged(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyID again
        if (convertView == null){
            // inflate
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);
            // add the views to the holder
            holder = new ViewHolder();
            // views
            holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            holder.descriptionTextView = convertView.findViewById(R.id.movie_list_description);
            holder.mainCharacterTextView = convertView.findViewById(R.id.movie_list_maincharacters);
            holder.hasSeenTextView = convertView.findViewById(R.id.movie_list_hasseen);
            holder.posterImageView = convertView.findViewById(R.id.movie_list_poster);
            // add the holder to the view
            // for future use
            convertView.setTag(holder);
        }
        else{
            // get the view holder from converview
            holder = (ViewHolder)convertView.getTag();
        }
        // get relavate subview of the row view
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView mainCharacterTextView = holder.mainCharacterTextView;
        TextView hasSeenTextView = holder.hasSeenTextView;
        ImageView posterImageView = holder.posterImageView;

        // get corresonpinding recipe for each row
        Movie movie = (Movie)getItem(position);


        // update the row view's textviews and imageview to display the information

        // titleTextView
        titleTextView.setText(movie.title);
        titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        titleTextView.setTextSize(20);

        // descriptionTextView
        descriptionTextView.setText(movie.description);
        descriptionTextView.setTextSize(9);
        descriptionTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

        // mainCharacterTextView
/*        int mainCharNos = movie.main_characters.size();
        String mainCharText = "";
        if(movie.main_characters.size()>3)
            mainCharNos = 3;
        for(int i = 0; i < mainCharNos; i++) {
            mainCharText = mainCharText + movie.main_characters.get(i);
            if(i < mainCharNos-1)
                mainCharText = mainCharText + ", ";
        }*/
//        mainCharacterTextView.setText(mainCharText);
        mainCharacterTextView.setText(movie.main_characters);
        mainCharacterTextView.setTextSize(13);
        mainCharacterTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

        // hasSeenTextView
        String hasseen = "Has Seen?";
        int colorCode = ContextCompat.getColor(mContext, R.color.colorPrimaryDark);
        switch(movie.has_seen) {
            case 1:
                hasseen = "Already seen";
                colorCode = ContextCompat.getColor(mContext, R.color.colorAccent3);
            break;
            case 2:
                hasseen = "Want to see";
                colorCode = ContextCompat.getColor(mContext, R.color.colorAccent);
            break;
            case 3:
                hasseen = "Do not like";
                colorCode = ContextCompat.getColor(mContext, R.color.colorAccent2);
            break;
        }
        hasSeenTextView.setText(hasseen);
        hasSeenTextView.setTextSize(11);
        hasSeenTextView.setTextColor(colorCode);

        // imageView
        // use Picasso library to load image from the image url
        Picasso.with(mContext).load(movie.poster).into(posterImageView);

        return convertView;
    }

    // viewHolder
    // is used to customize what you want to put into the view
    // it depends on the layout design of your row
    // this will be a private static class you have to define
    private static class ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView mainCharacterTextView;
        public TextView hasSeenTextView;
        public ImageView posterImageView;
    }




    // intent is used to pass information between activities
    // intent -> pacakge
    // sender, receiver

}
