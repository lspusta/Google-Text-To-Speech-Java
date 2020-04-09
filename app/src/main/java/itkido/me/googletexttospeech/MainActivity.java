package itkido.me.googletexttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextToSpeech textToSpeech;
    EditText editText;
    Button btnSpeak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        btnSpeak = findViewById(R.id.btnSpeak);


        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    @Override
    protected void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();
       textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
           @Override
           public void onInit(int status) {
               if (status == TextToSpeech.SUCCESS) {
                   textToSpeech.setLanguage(Locale.US);
               } else {
                   textToSpeech = null;
               }
           }
       });
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "onStop");
        super.onStop();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }


    private void speak(){
        if (textToSpeech != null) {
            textToSpeech.speak(editText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, editText.getText().toString());
        }
    }

}
