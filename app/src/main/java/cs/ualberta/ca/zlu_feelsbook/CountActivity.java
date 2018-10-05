package cs.ualberta.ca.zlu_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CountActivity extends AppCompatActivity {

    private String count;
    private TextView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_count);
        countView = (TextView) findViewById(R.id.countTextView);
        Intent intent = getIntent();
        count = intent.getStringExtra(HistoryActivity.EXTRA_MESSAGE_1);

        countView.setText(count);
    }
}
