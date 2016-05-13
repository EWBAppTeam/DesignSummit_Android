package test.com.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button info, locations, schedule, feedback;
    TextView emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (Button) findViewById(R.id.info);
        locations = (Button) findViewById(R.id.locations);
        schedule = (Button) findViewById(R.id.schedule);
        feedback = (Button) findViewById(R.id.feedback);
        emergency = (TextView) findViewById(R.id.emergency_contacts);

        info.setOnClickListener(this);
        schedule.setOnClickListener(this);
        feedback.setOnClickListener(this);
        locations.setOnClickListener(this);
        emergency.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.info:
                startActivity(new Intent(this, InfoActivity.class));
                break;
            case R.id.feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.schedule:
                startActivity(new Intent(this, ScheduleActivity.class));
                break;
            case R.id.locations:
                startActivity(new Intent(this, LocationActivity.class));
                break;
            case R.id.emergency_contacts:
                startActivity(new Intent(this, ContactsActivity.class));
                break;
        }
    }
}
