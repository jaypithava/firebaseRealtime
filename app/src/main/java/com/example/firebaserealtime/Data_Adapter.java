package com.example.firebaserealtime;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Data_Adapter extends FirebaseRecyclerAdapter<User2, Data_Adapter.userViewholder> {

    private List<User2> user2;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Data_Adapter(@NonNull FirebaseRecyclerOptions<User2> options) {
        super( options );
    }

    @Override
    protected void onBindViewHolder(@NonNull userViewholder holder, int position, @NonNull User2 model) {

        holder.name.setText( model.getName() );
        holder.id.setText( model.getId() );

    }

    @NonNull
    @Override
    public userViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.recyclerview_layout, parent, false );
        return new Data_Adapter.userViewholder( view );
    }


    public class userViewholder extends RecyclerView.ViewHolder {

        TextView name, id;
        Button delete;

        public userViewholder(@NonNull View itemView) {
            super( itemView );

            name = itemView.findViewById( R.id.list_name );
            id = itemView.findViewById( R.id.list_phone );
            delete = itemView.findViewById( R.id.btnDelete );

            delete.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child();
                    //reference.removeValue();


                     database = FirebaseDatabase.getInstance();
                    databaseReference = database.getReference( "Users" );

                   // String n = name.getText().toString().trim();
                    User user = new User();
                    user.getName();
                    databaseReference.child( "user" );
                    databaseReference.removeValue();

                }
            });

        }
    }
}
