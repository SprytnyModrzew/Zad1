package com.example.zad1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public int current_contact = 0;
    public int current_sound = 0;
    private MediaPlayer musicPlayer;
    private boolean playing = false;

    private HashMap<Integer, Integer> sounds = new HashMap<>();
    private HashMap<String, Integer> contact_images = new HashMap<>();

    public static final String extra_info =  "id";
    public static final class Request{
        static final int contact = 1;
        static final int sound = 2;
    }
    private static final String TAG = "UserLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //artificial sum of resources
        String[] res = getResources().getStringArray(R.array.contacts);
        contact_images.put(res[0], R.drawable.kor1);
        contact_images.put(res[1], R.drawable.kor2);
        contact_images.put(res[2], R.drawable.kor3);
        contact_images.put(res[3], R.drawable.kor4);

        sounds.put(0, R.raw.dzw1);
        sounds.put(1, R.raw.dzw2);
        sounds.put(2, R.raw.dzw3);
        sounds.put(3, R.raw.dzw4);

        int max = res.length;
        TextView text = findViewById(R.id.contactId);
        if(max != 0) {
            int rand = (int) (max * Math.random());
            Log.i(TAG, ""+rand);
            text.setText(res[rand]);
            current_contact = rand;
            ImageView im = findViewById(R.id.imageView);
            im.setImageResource(contact_images.get(res[rand]));
        }
        else text.setText("No contacts");
        max = sounds.size();
        if(max != 0){
            int rand = (int) (max * Math.random());
            current_sound = rand;
        }

        Log.i(TAG, "Main Created "+Math.random());
    }

    public void onClickContact(View arg0) {
        Log.i(TAG, "Contact Clicked");
        Intent contact = new Intent(getApplicationContext(),
                ContactActivity.class);
        contact.putExtra(extra_info, current_contact);
        startActivityForResult(contact, Request.contact);
    }

    public void onClickSound(View view){
        Log.i(TAG, "Sound Clicked");
        Intent sound = new Intent(getApplicationContext(), SoundActivity.class);
        sound.putExtra(extra_info, current_sound);
        startActivityForResult(sound, Request.sound);
    }

    public void onClickPlayButton(View view){
        final FloatingActionButton button = findViewById(R.id.play);
        if(!playing) {
            button.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_pause));
            musicPlayer = MediaPlayer.create(this, sounds.get(current_sound));
            musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    button.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_play));
                    musicPlayer.release();
                    playing = false;
                }
            });
            musicPlayer.start();
            playing = true;
            Log.i(TAG, "Should have played something...");
        }
        else{
            musicPlayer.pause();
            playing = false;
            button.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_play));
            Log.i(TAG, "Should have stopped...");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Request.contact) {
                current_contact = data.getIntExtra(extra_info, 0);
                Log.i(TAG, "Activity Main, current_contact = " + current_contact);
                TextView text = findViewById(R.id.contactId);
                String[] contactsArray = getResources().getStringArray(R.array.contacts);
                text.setText(contactsArray[current_contact]);
                ImageView im = findViewById(R.id.imageView);
                im.setImageResource(contact_images.get(contactsArray[current_contact]));
                return;
            }
            if(requestCode == Request.sound){
                current_sound = data.getIntExtra(extra_info,0);
                Log.i(TAG, "Activity Main, current_sound = " + current_sound);
            }
        }
    }
}
