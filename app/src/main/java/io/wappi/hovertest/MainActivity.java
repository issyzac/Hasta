package io.wappi.hovertest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Innitialize Hover
        Hover.initialize(this);

        button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HOVER_TEST", "Clicked");
                Intent i = new HoverParameters.Builder(MainActivity.this)
//                        .request("04cf61c4")
                        .request("5c087725")
                        //.extra(“action_step_variable_name”, variable_value)
                        //.setEnvironment(HoverParameters.DEBUG_ENV)
                        .buildIntent();

                startActivityForResult(i, 0);
            }
        });

    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        Log.d("HOVER_TEST", "Result Code is "+resultCode);

        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {

            Log.d("HOVER_TEST", "Result is OK");

            String[] sessionTextArr = data.getStringArrayExtra("ussd_messages");
            String uuid = data.getStringExtra("uuid");
        } else if (requestCode == 0 && resultCode == Activity.RESULT_CANCELED) {

            Log.d("HOVER_TEST", "Result Error");

            Toast.makeText(this, "Error Found: " + data.getStringExtra("error"), Toast.LENGTH_LONG).show();
        }
    }

}
