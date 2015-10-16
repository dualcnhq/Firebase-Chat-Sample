package com.dualcnhq.firebasetest;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ListActivity {

    private Firebase mFireBaseRef;

    private EditText mAnyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireBaseRef = new Firebase("<put firebase app url here>");

        mAnyText = (EditText) findViewById(R.id.et_anyText);
        Button mSendButton = (Button) findViewById(R.id.btn_send);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendButtonClick();
            }
        });

        FireBaseListAdapter mFireBaseListAdapter = new FireBaseListAdapter<ChatMessage>(mFireBaseRef,
                ChatMessage.class, R.layout.layout_message, this) {
            @Override
            protected void populateView(View v, ChatMessage model) {
                ((TextView)v.findViewById(R.id.t_user)).setText(model.getName());
                ((TextView)v.findViewById(R.id.t_message)).setText(model.getMessage());
            }
        };

        setListAdapter(mFireBaseListAdapter);
    }

    private void onSendButtonClick() {
        String message = mAnyText.getText().toString();
        mFireBaseRef.push().setValue(new ChatMessage("dualcnhq", message));
        mAnyText.setText("");
    }

}
