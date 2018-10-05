/*
*Class Name: MainActivity
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
import android.view.View;

/*This is the class of main activity, which is the first interface after the user launched the app.*/
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "cs.ualberta.ca.zlu_feelsbook.FEELNUMBER";

    /*
    Automatic generated method
    */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    This block check if user press any button and respond by jumping to new activity.
     */
    public void lovePressed(View view){
        Intent intent=new Intent(this, HistoryActivity.class);
        intent.putExtra(EXTRA_MESSAGE,"1");
        startActivity(intent);
    }
    public void joyPressed(View view){
        Intent intent=new Intent(this, HistoryActivity.class);
        intent.putExtra(EXTRA_MESSAGE,"2");
        startActivity(intent);
    }
    public void surprisePressed(View view){
        Intent intent=new Intent(this, HistoryActivity.class);
        intent.putExtra(EXTRA_MESSAGE,"3");
        startActivity(intent);
    }
    public void sadnessPressed(View view){
        Intent intent=new Intent(this, HistoryActivity.class);
        intent.putExtra(EXTRA_MESSAGE,"4");
        startActivity(intent);
    }
    public void angerPressed(View view){
        Intent intent=new Intent(this, HistoryActivity.class);
        intent.putExtra(EXTRA_MESSAGE,"5");
        startActivity(intent);
    }
    public void fearPressed(View view){
        Intent intent=new Intent(this, HistoryActivity.class);
        intent.putExtra(EXTRA_MESSAGE,"6");
        startActivity(intent);
    }

    public void checkHistory(View view){
        Intent intent=new Intent(this, HistoryActivity.class);
        intent.putExtra(EXTRA_MESSAGE,"7");
        startActivity(intent);
    }
}
