package demofiredb.myfirstdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyFirstFireDBActivity extends AppCompatActivity {

    TextView mTextView;
    Button mButton_sunny, mButton_foggy;
    DatabaseReference DBreference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionReference = DBreference.child("condition");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_fire_db);

        mButton_sunny = (Button) findViewById(R.id.button1);
        mButton_foggy = (Button) findViewById(R.id.button2);
        mTextView = (TextView) findViewById(R.id.textid);

        mButton_sunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conditionReference.setValue("sunny");
            }
        });

        mButton_foggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conditionReference.setValue("foggy");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        conditionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mTextView.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
