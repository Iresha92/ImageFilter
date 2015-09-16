package com.example.ucsc.imagefilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ishkImageView;
    Drawable ishkFace;
    Bitmap bitmapImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ishkImageView = (ImageView)findViewById(R.id.ishkImageView);


        ishkFace = getResources().getDrawable(R.drawable.iresha);
        bitmapImage = ((BitmapDrawable)ishkFace).getBitmap();
        Bitmap newPhoto = invertImage(bitmapImage);
        ishkImageView.setImageBitmap(newPhoto);

/*
        Drawable[] layers=new Drawable[2];
        layers[0]=getResources().getDrawable(R.drawable.iresha);
        layers[1]=getResources().getDrawable(R.drawable.frame);
        LayerDrawable layerDrawable=new LayerDrawable(layers);
        ishkImageView.setImageDrawable(layerDrawable);
*/

        //to save d pic in users device
        MediaStore.Images.Media.insertImage(getContentResolver(),newPhoto,"title","description");


    }

    //invert a bitmap image
    public static Bitmap invertImage(Bitmap original) {

        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(), original.getHeight(), original.getConfig());

        int A, R, G, B;
        int pixelColor;
        int height = original.getHeight();
        int width = original.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = original.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = 255-Color.red(pixelColor);
                G = 255-Color.green(pixelColor);
                B = 255-Color.blue(pixelColor);
                finalImage.setPixel(x,y,Color.argb(A,R,G,B));

            }
        }
        return finalImage;

    }


    }




