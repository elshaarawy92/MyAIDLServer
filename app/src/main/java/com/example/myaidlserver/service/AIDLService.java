package com.example.myaidlserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.myaidlserver.bean.Talk;
import com.example.myaidlserver.bean.TalkManager;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service{

    private final static String TAG = "AIDLService";

    private List<Talk> talkList;

    private TalkManager.Stub talkManager = new TalkManager.Stub() {
        @Override
        public void sendMessage(Talk talk) throws RemoteException {
            talkList.add(talk);
        }

        @Override
        public Talk getMessage() throws RemoteException {
            return talkList.get(talkList.size() - 1);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        talkList = new ArrayList<>();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return talkManager;
    }
}
