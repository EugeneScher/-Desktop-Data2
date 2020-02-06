package com.example.data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.content.Intent;

import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity  {
    EditText Name;
    EditText Pass;
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn2 = (Button) findViewById(R.id.btn2);
        Name = (EditText) findViewById(R.id.edt4);
        Pass = (EditText) findViewById(R.id.edt3);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Login().execute();
            }
        });
    }



               private class Login extends  AsyncTask<String, Void, Void>{
                   String instanceName = "30.30.30.243";
                   String databaseName = "RieltorBD";
                   String userName = "wsr-1";
                   String pass = "vX5qKy3XtA";
                   String getName;
                   String getPass;


                   @Override
                   protected void onPreExecute() {

                       getName = Name.getText().toString();
                       getPass = Pass.getText().toString();
                       super.onPreExecute();
                   }

                   @Override
                   protected Void doInBackground(String... strings) {
                       try {
                           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                       } catch (ClassNotFoundException e) {
                           e.printStackTrace();
                       }
                       String connectionUrl = "jdbc:jtds:sqlserver://30.30.30.243:1433/RieltorBD;user=wsr-1;password=vX5qKy3XtA;";
                       String connectionString = String.format(connectionUrl,instanceName,databaseName,userName,pass);
                       try {
                           Connection con = DriverManager.getConnection(connectionString);
                           Statement stmn = con.createStatement();
                           ResultSet executeQuery = stmn.executeQuery("SELECT * FROM [RieltorBD].[dbo].[Autorization]");
                           Log.d("SQL", "ПОДКЛЮЧЕНО");
                           while (executeQuery.next())
                           {
                               if (getName.equals(executeQuery.getString("Login")) && getPass.equals(executeQuery.getString("Password")) )
                               {
                                   Intent intent = new Intent(MainActivity.this, Tablichka.class);
                                   startActivity(intent);
                                   finish();
                               }
                               else
                               {
                                   Log.d("НЕТЬ"," SAAAA");
                               }
                           }
                           executeQuery.close();
                           stmn.close();
                           con.close();
                       } catch (SQLException e) {
                           //e.printStackTrace();
                           Log.d("SQLEX: ", e.toString());
                       }

                       return null;
                   }

                   @Override
                   protected void onPostExecute(Void aVoid) {
                       super.onPostExecute(aVoid);
                   }
               }
}

