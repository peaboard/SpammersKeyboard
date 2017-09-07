package com.prabodhs.spammerskeyboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Button implementation thanks
            http://www.androidbegin.com/tutorial/android-button-click-new-activity-example/
            Stack Overflow
         */

        // Locate the button in activity_main.xml
        Button button1 = (Button)findViewById(R.id.ActivateKeyboard);
        Button button2 = (Button)findViewById(R.id.KeyboardSettings);

        // Capture button clicks
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                startActivityForResult(new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS), 0);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent i = new Intent(MainActivity.this, ImePreferences.class);
                startActivity(i);

            }
        });
    }

}
