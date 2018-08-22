// TalkManager.aidl
package com.example.myaidlserver.bean;

//一定要import,并写出完整路径
import com.example.myaidlserver.bean.Talk;

interface TalkManager {

    //发送消息，为了使双方能够通信，所以使用inout
    void sendMessage(inout Talk talk);

    //接收消息
    Talk getMessage();
}
