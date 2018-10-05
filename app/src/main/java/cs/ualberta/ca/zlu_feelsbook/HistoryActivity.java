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
import java.util.Collections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HistoryActivity extends AppCompatActivity {

    private EditText commentText;
    private String feelNumber;
    public static final String EXTRA_MESSAGE_1 = "cs.ualberta.ca.zlu_feelsbook.COUNTSTRING";
    private static final String FILENAME = "file.sav";
    private ListView historyList;
    private EditText editPastFeel;
    public ArrayList<Feel> feels = new ArrayList<Feel>();
    private ArrayAdapter<Feel> adapter;
    private int clickPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        historyList = (ListView) findViewById(R.id.historyList);
        Intent intent = getIntent();
        feelNumber = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Button addButton = (Button) findViewById(R.id.addButton);
        Button editButton = (Button) findViewById(R.id.editButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        Button countButton = (Button) findViewById(R.id.countButton);
        commentText = (EditText) findViewById(R.id.commentText);
        editPastFeel= (EditText) findViewById(R.id.editFeel);

        loadFromFile();//
        adapter = new ArrayAdapter<Feel>(this, R.layout.list_item, feels);
        historyList.setAdapter(adapter);

        if (!feelNumber.equals("7")){
            ImportantFeel newFeel = new ImportantFeel("",feelNumber);
            feels.add(0,newFeel);
            Collections.sort(feels);
            Toast.makeText(HistoryActivity.this, "You've added a feel !", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
            saveInFile();
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String comment = commentText.getText().toString();
               if (!comment.equals("")){
                   commentText.getText().clear();
                   feels.get(0).modifyFeel(feels.get(0).toString()+comment);
                   adapter.notifyDataSetChanged();
                   saveInFile();
               }
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentEditing = editPastFeel.getText().toString();
                editPastFeel.getText().clear();
                feels.get(clickPosition).modifyFeel(commentEditing);
                Collections.sort(feels);
                Toast.makeText(HistoryActivity.this, "You've edited a feel !", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPastFeel.getText().clear();
                feels.remove(clickPosition);
                //feels.clear();
                adapter.notifyDataSetChanged();
                saveInFile();
                Toast.makeText(HistoryActivity.this, "You've deleted a feel !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                clickPosition = position;
                edit();
            }
        });
    }

    public void edit(){
        String tempFeel=feels.get(clickPosition).toString();
        editPastFeel.setText(tempFeel);
    }

    public void count(View view) {
        int countList[] = {0,0,0,0,0,0};
        for(int x=0; x<feels.size();x++){
            String temp= feels.get(x).toString().split(" \\| ")[1];
            if (temp.equals("Love")){
                countList[0]++;
            }
            else if (temp.equals("Joy")){
                countList[1]++;
            }
            else if (temp.equals("Surprise")){
                countList[2]++;
            }
            else if (temp.equals("Anger")){
                countList[3]++;
            }
            else if (temp.equals("Sadness")){
                countList[4]++;
            }
            else if (temp.equals("Fear")){
                countList[5]++;
            }
        }
        String countString = "Love: " + String.valueOf(countList[0])+"\n"
                +"Joy: " + String.valueOf(countList[1])+"\n"
                +"Surprise: " + String.valueOf(countList[2])+"\n"
                +"Anger: " + String.valueOf(countList[3])+"\n"
                +"Sadness: " + String.valueOf(countList[4])+"\n"
                +"Fear: " + String.valueOf(countList[5]);

        Intent intent=new Intent(this, CountActivity.class);
        intent.putExtra(EXTRA_MESSAGE_1,countString);
        startActivity(intent);
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
