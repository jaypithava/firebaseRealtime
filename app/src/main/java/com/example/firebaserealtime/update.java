package com.example.firebaserealtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class update extends AppCompatActivity {

    EditText name,id,email;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update );

        name = findViewById( R.id.updatename );
        id=findViewById( R.id.updateid );
        name=findViewById( R.id.updatename );
        email=findViewById( R.id.updateemail );

        send=findViewById( R.id.btnView );
        send.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString().trim();
                String i = id.getText().toString().trim();
                String e = email.getText().toString().trim();
                /*if(!TextUtils.isEmpty( n )){
                    updateUser(i,n,e);

                }*/

                DatabaseReference dR = FirebaseDatabase.getInstance().getReference("user/name");
                Query queryRef = dR.equalTo( n );
                queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println("aaaa" + " datasnapshot is equal to " + dataSnapshot);
                        Log.d( "data",dataSnapshot.toString() );
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


             /*   Query query = dR.equalTo( n );
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                System.out.println("arpit" + " datasnapshot is equal to " + dataSnapshot);
                                                                Log.d( "data",dataSnapshot.toString() );
                                                            }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
*/
                //dR.orderByChild(n);
                //Firebase ref = FirebaseDatabase.getInstance().getReference("Users");



                //DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                //ref.child("Users").orderByChild("name").equalTo(n);



            }

        } );
    }

    private boolean updateUser(String id, String name, String email) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Users");

        //updating artist
       User user = new User(id, name, email);
        dR.setValue(user);
        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
        return true;
    }
}