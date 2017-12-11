package com.example.juleo.task;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAddTask, btnSearchTask, btnDelete;
    ListView listTasks;
    EditText editTask;
    TextView txtTotalTasks;

    ArrayList<String> listData;
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uicActivityFullScreen();
        setContentView(R.layout.activity_main);

        editTask = (EditText) findViewById(R.id.editText2);
        txtTotalTasks = (TextView) findViewById(R.id.txtTotalTasks);
        listTasks = (ListView) findViewById(R.id.listTask);
        listData = new ArrayList<>();

        //listData.add("");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, listData);
        listTasks.setAdapter(adapter);


        txtTotalTasks.setText(adapter.getCount()+"");

        btnAddTask = (Button) findViewById(R.id.btnAdd);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uicHideKeyboard();
                String newTask = editTask.getText().toString();
                adapter.add(newTask);
                adapter.notifyDataSetChanged();
                editTask.setText("");
                txtTotalTasks.setText(adapter.getCount()+"");
                uicToastMessage("New task has been added!");
            }
        });

        btnSearchTask = (Button) findViewById(R.id.btnSearch);
        btnSearchTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uicHideKeyboard();
                String searchTask = editTask.getText().toString();
                adapter.getFilter().filter(searchTask.toString());
            }
        });

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uicHideKeyboard();
                String deleteTask = editTask.getText().toString();
                adapter.remove(editTask.getText().toString());
                adapter.notifyDataSetChanged();
                editTask.setText("");
                txtTotalTasks.setText(adapter.getCount() + "");
                uicToastMessage("Task has been deleted!");
            }
        });


    }





    /* ###########  UIC Helper Methods  ###########  */

    public void uicActivityFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    public void uicToastMessage(String message){
        Toast.makeText(this, message ,Toast.LENGTH_SHORT).show();
    }

    public void uicHideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /* ###########  UIC Helper Methods  ###########  */
}
