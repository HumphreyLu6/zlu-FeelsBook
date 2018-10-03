package cs.ualberta.ca.zlu_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private EditText commentText;
    private ListView oldFeelsList;
    private ArrayList<Feel> feels = new ArrayList<Feel>();
    public int feelNumber=0;
    private ArrayAdapter<Feel> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        commentText = (EditText) findViewById(R.id.comment);
        Button addButton = (Button) findViewById(R.id.add);
        oldFeelsList = (ListView) findViewById(R.id.oldFeels);
        Button loveButton = (Button) findViewById(R.id.love_button);
        Button joyButton = (Button) findViewById(R.id.joy_button);
        Button angerButton = (Button) findViewById(R.id.anger_button);
        Button fearButton = (Button) findViewById(R.id.fear_button);
        Button sadnessButton = (Button) findViewById(R.id.sadness_button);
        Button surpriseButton = (Button) findViewById(R.id.suprise_button);

        loveButton.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                feelNumber=1;
            }
        });
        joyButton.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                feelNumber=2;
            }
        });
        angerButton.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                feelNumber=3;
            }
        });
        fearButton.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                feelNumber=4;
            }
        });
        sadnessButton.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                feelNumber=5;
            }
        });
        surpriseButton.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                feelNumber=6;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                String text = commentText.getText().toString();

                if (feelNumber==1){
                    Love newfeel = new Love(text);
                    feels.add(newfeel);
                    adapter.notifyDataSetChanged();
                }
                else if(feelNumber==2){
                    Joy newfeel = new Joy(text);
                    feels.add(newfeel);
                    adapter.notifyDataSetChanged();
                }
                else if(feelNumber==3){
                    Anger newfeel = new Anger(text);
                    feels.add(newfeel);
                    adapter.notifyDataSetChanged();
                }
                else if(feelNumber==4){
                    Fear newfeel = new Fear(text);
                    feels.add(newfeel);
                    adapter.notifyDataSetChanged();
                }
                else if(feelNumber==5){
                    Sadness newfeel = new Sadness(text);
                    feels.add(newfeel);
                    adapter.notifyDataSetChanged();
                }
                else if(feelNumber==6){
                    Surprise newfeel = new Surprise(text);
                    feels.add(newfeel);
                    adapter.notifyDataSetChanged();
                }
                saveInFile();
            }

        });

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        loadFromFile();//
        adapter = new ArrayAdapter<Feel>(this,
                R.layout.history, feels);
        oldFeelsList.setAdapter(adapter);//oldTweetsLists are the listview, we need to refresh them.
    }

    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            Type typeListFeels = new TypeToken<ArrayList<Feel>>(){}.getType();////
            feels = gson.fromJson(reader, typeListFeels);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void saveInFile(){
        try {
            //Log.d("shaiful","I am inside savefile");//logcat consol
            FileOutputStream fos =openFileOutput(FILENAME, 0);
            OutputStreamWriter osw =new OutputStreamWriter(fos);
            BufferedWriter writer= new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(feels,writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
