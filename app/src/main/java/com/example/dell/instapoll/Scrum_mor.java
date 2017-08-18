package com.example.dell.instapoll;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Scrum_mor extends AppCompatActivity {
    Button b1;
    RadioButton radioButton;

    int cvote1=0;
    int cvote2=0;
    int cvote3=0;
    int cvote4=0;
    private DatabaseReference mDatabase;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrum_mor);

        b1 = (Button) findViewById(R.id.button5);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        rb1= (RadioButton) findViewById(R.id.rad_ayush);
        rb2= (RadioButton) findViewById(R.id.rad_nik);
        rb3= (RadioButton) findViewById(R.id.rad_vis);
        rb4= (RadioButton) findViewById(R.id.rad_roh);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int vote1=  Integer.parseInt( rb1.getText().toString().trim() );
                int vote2=  Integer.parseInt( rb2.getText().toString().trim() );
                int vote3=  Integer.parseInt( rb3.getText().toString().trim() );
                int vote4=  Integer.parseInt( rb4.getText().toString().trim() );

                HashMap<String, Integer> dataMap= new HashMap<String, Integer>();
                dataMap.put("Ayush", vote1);
                dataMap.put("Nikhil", vote2);
                dataMap.put("Vishal", vote3);
                dataMap.put("Rohit", vote4);


                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(Scrum_mor.this,"Thank you for the vote.",Toast.LENGTH_LONG).show();
                        }
                        else
                        {

                            Toast.makeText(Scrum_mor.this,"Try again",Toast.LENGTH_LONG).show();

                        }
                    }
                });

                Intent i = new Intent(Scrum_mor.this, Scr_pie.class);
                startActivity(i);
            }
        });
    }
        public void onRadioButtonClicked(View view) {
            // Is the button now checked?



            boolean checked = ((RadioButton) view).isChecked();

            // Check which radio button was clicked
            switch(view.getId()) {
                case R.id.rad_ayush:
                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked)

                                cvote1++;

                        }
                    });

                        // Pirates are the best
                        break;
                case R.id.rad_nik:
                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked)
                                cvote2++;
                        }
                    });
                        break;
                case R.id.rad_vis:
                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked)
                                cvote3++;
                        }
                    });
                        // Pirates are the best
                        break;
                case R.id.rad_roh:
                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked)
                                cvote4++;
                        }
                    });
                        break;
            }

        }

    }



