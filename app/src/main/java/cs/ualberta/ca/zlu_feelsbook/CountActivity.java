/*
 *Class Name: CountActivity
 *
 * Author: Zhongaho Lu
 *
 * Version 1.0
 *
 */
package cs.ualberta.ca.zlu_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/*This is the activity when count button in history activity was pressed.*/
public class CountActivity extends AppCompatActivity {

    private String count;
    private TextView countView;

    /*
    Automatically generated method.
     */
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
