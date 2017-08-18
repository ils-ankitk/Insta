package com.example.dell.instapoll;


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;

        import com.google.firebase.auth.FirebaseAuth;

        import java.util.ArrayList;

        import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class MainActivity extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Game> games;
    private Button mLogOutBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener; //What activity will do after sign out or sign is judged by this.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        mAuth = FirebaseAuth.getInstance();

        //This will return back
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() ==null){

                    startActivity(new Intent(MainActivity.this, Launcher.class));
                }
            }
        };

        mLogOutBtn = (Button) findViewById(R.id.logOutBtn);
        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

            }
        });
        settingDummyData();
        adapter = new CoverFlowAdapter(this, games);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO CoverFlow item clicked
                switch( position )
                {
                    case 0:  Intent i = new Intent(MainActivity.this, Scrum_mor.class);
                        startActivity(i);
                        break;
                    case 1:  Intent newActivity = new Intent(MainActivity.this, Scrum_eve.class);
                        startActivity(newActivity);
                        break;
                    case 2:  Intent k = new Intent(MainActivity.this, Saturday.class);
                        startActivity(k);
                        break;
                    case 3:  Intent j= new Intent(MainActivity.this, I_fest.class);
                        startActivity(j);
                        break;

                }

            }
        });

        coverFlow.setOnScrollPositionListener(onScrollListener());
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        games = new ArrayList<>();
        games.add(new Game(R.drawable.scr_mo, "Scrum Morning"));
        games.add(new Game(R.drawable.scr_eve, "Scrum Evening"));
        games.add(new Game(R.drawable.sat, "Saturday Sessions"));
        games.add(new Game(R.drawable.ifest, "iFest"));

    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
