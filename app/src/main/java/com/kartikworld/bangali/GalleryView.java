package com.kartikworld.bangali;

import android.os.Bundle;
import android.widget.Gallery;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GalleryView extends AppCompatActivity {

    Gallery simpleGallery;

    // CustomizedGalleryAdapter is a java/kotlin
    // class which extends BaseAdapter
    // and implement the override methods.
    CustomizedGalleryAdapter customGalleryAdapter;
    ImageView selectedImageView;

    // To show the selected language, we need this
    // array of images, here taken 10 different kind of
    // most popular programming languages
    int[] images = {
            R.drawable.img,
            R.drawable.india,
            R.drawable.bangladesh,
            R.drawable.shiv,
            R.drawable.kartik_pic,
            R.drawable.lots_candles,
            R.drawable.gita_01,
            R.drawable.teachers,
            R.drawable.temple_singapore,
            R.drawable.bangladesh,
            R.drawable.img,
            R.drawable.india,
            R.drawable.bangladesh,
            R.drawable.shiv,
            R.drawable.kartik_pic,
            R.drawable.lots_candles,
            R.drawable.gita_01,
            R.drawable.teachers,
            R.drawable.temple_singapore,
            R.drawable.bangladesh
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_view);

        // Our layout is activity_main
        // get the reference of Gallery. As we are showing
        // languages it is named as languagesGallery
        // meaningful names will be good for easier understanding
        simpleGallery = (Gallery) findViewById(R.id.languagesGallery);

        // get the reference of ImageView
        selectedImageView = (ImageView) findViewById(R.id.imageView);

        // initialize the adapter
        customGalleryAdapter = new CustomizedGalleryAdapter(getApplicationContext(), images);

        // set the adapter for gallery
        simpleGallery.setAdapter(customGalleryAdapter);

        // Let us do item click of gallery and image can be identified by its position
        simpleGallery.setOnItemClickListener((parent, view, position, id) -> {
            // Whichever image is clicked, that is set in the  selectedImageView
            // position will indicate the location of image
            selectedImageView.setImageResource(images[position]);
        });
    }
}