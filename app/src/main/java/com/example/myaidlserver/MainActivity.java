package com.example.myaidlserver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myaidlserver.bean.Talk;
import com.example.myaidlserver.bean.TalkManager;
import com.example.myaidlserver.service.AIDLService;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private Button button;

    private EditText editText;

    private String message;

    private TalkManager talkManager = null;

    //发送的信息
    private Talk talk;

    //接收到的信息
    private Talk talk2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        contact();
        if(talkManager != null){
            try {
                talk2 = talkManager.getMessage();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if(talk2 != null){
            textView.append(talk2.toString() + "\n");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                talk = new Talk();
                message = editText.getText().toString();
                talk.setMessage(message);
                talk.setName("服务器");
                sendMessage(talk);
                textView.append(talk.toString() + "\n");
                editText.getText().clear();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(talkManager != null){
            try {
                talk2 = talkManager.getMessage();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if(talk2 != null){
            textView.append(talk2.toString() + "\n");
        }
    }

    public void initView(){
        textView = findViewById(R.id.textview);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.edittext);
    }

    public void contact(){
        Intent intent = new Intent();
        intent.setAction("com.example.myaidlserver");
        intent.setPackage("com.example.myaidlserver");
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        Toast.makeText(this,"已连接",Toast.LENGTH_SHORT).show();
    }

    public void sendMessage(Talk talk){
        try {
            talkManager.sendMessage(talk);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            talkManager = TalkManager.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
