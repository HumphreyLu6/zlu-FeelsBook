package cs.ualberta.ca.zlu_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class HistoryActivity extends AppCompatActivity {

    private EditText commentText;
    private String feelNumber;
    private static final String FILENAME = "file.sav";
    private ListView historyList;
    private EditText editPastFeel;
    public ArrayList<Feel> feels = new ArrayList<Feel>();
    private ArrayAdapter<Feel> adapter;
    private Integer clickPosition = -1;
    //public static final String EXTRA_MESSAGE ="cs.ualberta.ca.zlu_feelsbook.FEELINDEX";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        historyList = (ListView) findViewById(R.id.historyList);
        Intent intent = getIntent();
        feelNumber = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Button addButton = (Button) findViewById(R.id.addButton);
        Button editButton = (Button) findViewById(R.id.editButton);
        commentText = (EditText) findViewById(R.id.commentText);
        editPastFeel= (EditText) findViewById(R.id.editFeel);

        addButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
               String comment = commentText.getText().toString();
               ImportantFeel newFeel = new ImportantFeel(comment,feelNumber);
               feels.add(newFeel);
               adapter.notifyDataSetChanged();
               saveInFile();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                String commentEditing = editPastFeel.getText().toString();
                feels.get(clickPosition).modifyFeel(commentEditing);
                String test = feels.get(0).message;
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        loadFromFile();//
        adapter = new ArrayAdapter<Feel>(this, R.layout.list_item, feels);
        historyList.setAdapter(adapter);
        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(HistoryActivity.this, "You clicked on : " + position, Toast.LENGTH_SHORT).show();
                clickPosition = position;
                edit();
            }
        });
    }

    public void edit(){
        String tempFeel=feels.get(clickPosition).toString();
        editPastFeel.setText(tempFeel);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            Type typeListFeels = new TypeToken<ArrayList<ImportantFeel>>(){}.getType();////
            feels = gson.fromJson(reader, typeListFeels);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
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
