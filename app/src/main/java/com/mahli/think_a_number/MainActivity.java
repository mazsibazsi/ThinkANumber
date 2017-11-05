package com.mahli.think_a_number;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button_plus, button_minus, button_send;
    TextView textView_szam;
    ImageView imageView_heart1, imageView_heart2, imageView_heart3, imageView_heart4, imageView_heart5;
    int szam=0, kitalalando_szam,lives=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Random rand = new Random();
        kitalalando_szam = rand.nextInt(50)+1;
        System.out.println("\n\n\n\n\n\n-------------------" + kitalalando_szam);
        button_plus = (Button) findViewById(R.id.button_plus);
        button_minus = (Button) findViewById(R.id.button_minus);
        button_send = (Button) findViewById(R.id.button_send);
        textView_szam = (TextView) findViewById(R.id.textView_szam);
        imageView_heart1 = (ImageView) findViewById(R.id.imageView_heart1);
        imageView_heart2 = (ImageView) findViewById(R.id.imageView_heart2);
        imageView_heart3 = (ImageView) findViewById(R.id.imageView_heart3);
        imageView_heart4 = (ImageView) findViewById(R.id.imageView_heart4);
        imageView_heart5 = (ImageView) findViewById(R.id.imageView_heart5);

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
                textView_szam.setText(szam+"");
            }
        });
        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract();
                textView_szam.setText(szam+"");
            }
        });
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compare();
                if (szam < kitalalando_szam){
                    Toast.makeText(MainActivity.this,"A tippelt számod nagyobb.",Toast.LENGTH_SHORT).show();
                }
                if (szam > kitalalando_szam){
                    Toast.makeText(MainActivity.this,"A tippelt számod kisebb.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void add(){
        if (szam == 50){
            //do nothing
        }else{
            szam++;
        }
    }
    public void subtract(){
        if (szam == 0){
            //do nothing
        }else{
            szam--;
        }
    }
    public void compare(){
        if (szam == kitalalando_szam){
            Toast.makeText(getApplicationContext(), "Nyerté'", Toast.LENGTH_SHORT).show();

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            Intent i = getBaseContext().getPackageManager()
                                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            finish();
                            System.exit(0);
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Nyertél, új játék?").setPositiveButton("Igen", dialogClickListener)
                    .setNegativeButton("Nem", dialogClickListener).show();

        }else{
            switch (lives){
                case 5:
                    imageView_heart5.setImageResource(R.drawable.heart1);
                    lives--;
                    break;
                case 4:
                    imageView_heart4.setImageResource(R.drawable.heart1);
                    lives--;
                    break;
                case 3:
                    imageView_heart3.setImageResource(R.drawable.heart1);
                    lives--;
                    break;
                case 2:
                    imageView_heart2.setImageResource(R.drawable.heart1);
                    lives--;
                    break;
                case 1:
                    imageView_heart1.setImageResource(R.drawable.heart1);
                    lives--;
                    break;
                default:


                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    Intent i = getBaseContext().getPackageManager()
                                            .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    finish();
                                    System.exit(0);
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Vesztettél. Új játék?").setPositiveButton("Igen", dialogClickListener)
                            .setNegativeButton("Nem", dialogClickListener).show();

                    break;
            }
        }
    }
}
