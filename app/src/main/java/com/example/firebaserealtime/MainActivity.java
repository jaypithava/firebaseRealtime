package com.example.firebaserealtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText inputName, inputEmail, inputMobile;
    Button btnSave, btnview, btnUpdate;

    List<User> users;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        inputName = findViewById( R.id.name );
        inputEmail = findViewById( R.id.email );
        inputMobile = findViewById( R.id.contact );
        btnUpdate = findViewById( R.id.btnUpdate );
        btnview = findViewById( R.id.btnView );
        btnSave = findViewById( R.id.button );

        users = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference( "Users" );

        btnSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        } );


        btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new  Intent(getApplicationContext(),update.class);
                startActivity( i );
            }
        } );

        btnview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), display.class );
                startActivity( i );

            }
        });

    }

    private void addUser() {
        String name = inputName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String id = inputMobile.getText().toString().trim();
        if (!TextUtils.isEmpty( name )) {


            User user = new User();
            user.setId( id );
            user.setName( name );
            user.setEmail( email );
            databaseReference.child( "user" ).push();
            databaseReference.setValue( user );
            inputName.setText( "" );
            inputEmail.setText( "" );
            inputMobile.setText( "" );

            Toast.makeText( this, "insert", Toast.LENGTH_SHORT ).show();

        } else {
            Toast.makeText( this, "error", Toast.LENGTH_SHORT ).show();
        }


    }

}