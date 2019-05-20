// Template learnt from ProgrammingKnowledge Youtube Channel

package com.example.desel.sqlitetemplate;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    // VARIABLES

    // Database Related
    DatabaseHelper myDb;

    // Text Views
    TextView tvID;

    // Edit Texts
    EditText etName;
    EditText etSurname;
    EditText etMarks;
    EditText etID;

    // Buttons
    Button btnAdd;
    Button btnView;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INITIALISING

        // Database Related
        myDb = new DatabaseHelper(this);

        // Text Views
        tvID = findViewById(R.id.tvID);

        // Edit Text
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etMarks = findViewById(R.id.etMarks);
        etID = findViewById(R.id.etID);

        // Buttons
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

//        tvID.setVisibility(View.INVISIBLE);
//        etID.setVisibility(View.INVISIBLE);

        addData();
        viewData();
        updateData();
        deleteData();
    }

    public void addData()
    {
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isInserted = myDb.insertData(etName.getText().toString(),
                        etSurname.getText().toString(),
                        etMarks.getText().toString());
                if (isInserted = true)
                {
                    Toast.makeText
                            (MainActivity.this, "Data Inserted",
                                    Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText
                            (MainActivity.this, "Failed To Insert Data",
                                    Toast.LENGTH_SHORT).show();
                }

                etName.setText(null);
                etSurname.setText(null);
                etMarks.setText(null);
            }
        });
    }

    public  void viewData()
    {
        btnView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor result = myDb.getAllData();
                if (result.getCount() == 0)
                {
                    // Show message
                    showMesage("Error", "Nothing Found");
                    return;
                }
                else
                {
                    StringBuffer buffer = new StringBuffer();
                    while (result.moveToNext())
                    {
                        // Telling it how to organise the data
                        buffer.append("ID : " + result.getString(0) + "\n");
                        buffer.append("Name : " + result.getString(1) + "\n");
                        buffer.append("Surname : " + result.getString(2) + "\n");;
                        buffer.append("Marks : " + result.getString(3) + "\n");
                        buffer.append("\n------------------------------------------------" +
                                "---------------\n\n");
                    }

                    // Show all data
                    showMesage("Data", buffer.toString());
                }
            }
        });
    }

    public void updateData()
    {
        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (tvID.getVisibility() == View.INVISIBLE &&
                        etID.getVisibility() == View.INVISIBLE)
                {
                    tvID.setVisibility(View.VISIBLE);
                    etID.setVisibility(View.VISIBLE);
                }
                else
                {
                    Log.i("MainActivity", "Update Should be successful");

                    boolean isUpdated = myDb.updateData(etID.getText().toString(),
                            etName.getText().toString(), etSurname.getText().toString(),
                            etMarks.getText().toString());

                    if (isUpdated)
                    {
                        Toast.makeText
                                (MainActivity.this, "Data is updated",
                                        Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText
                                (MainActivity.this, "Failed to update",
                                        Toast.LENGTH_SHORT).show();
                    }

                    etID.setText(null);
                    etName.setText(null);
                    etSurname.setText(null);
                    etMarks.setText(null);
                }
            }
        });
    }

    public void deleteData()
    {
        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (tvID.getVisibility() == View.INVISIBLE &&
                        etID.getVisibility() == View.INVISIBLE)
                {
                    tvID.setVisibility(View.VISIBLE);
                    etID.setVisibility(View.VISIBLE);
                }
                else
                {
                    Log.i("MainActivity", "Delete Should be successful");

//                    boolean isUpdated = myDb.updateData(etID.getText().toString(),
//                            etName.getText().toString(), etSurname.getText().toString(),
//                            etMarks.getText().toString());
//
//                    if (isUpdated)
//                    {
//                        Toast.makeText
//                                (MainActivity.this, "Data is updated",
//                                        Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        Toast.makeText
//                                (MainActivity.this, "Failed to update",
//                                        Toast.LENGTH_SHORT).show();
//                    }

                    etID.setText(null);
                    etName.setText(null);
                    etSurname.setText(null);
                    etMarks.setText(null);
                }
            }
        });
    }

    public void showMesage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
