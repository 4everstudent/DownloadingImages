package a4everstudent.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView downloadedImg;

    public void downloadImage(View view) {

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {

            myImage = task.execute("http://www.wired.com/images_blogs/gadgetlab/2011/12/new-prof.png").get();
            downloadedImg.setImageBitmap(myImage);

        } catch (Exception e) {

            e.printStackTrace();

        }
        Log.i("Interaction:", "Button clicked!");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImg = (ImageView) findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            }
            catch (MalformedURLException e) {

                e.printStackTrace();
            }

            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
