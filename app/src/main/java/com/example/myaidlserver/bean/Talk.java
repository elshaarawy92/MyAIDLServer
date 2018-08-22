package com.example.myaidlserver.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Talk implements Parcelable{

    //发送方名称
    private String name;

    //发送的信息
    private String message;

    public Talk() {
    }

    protected Talk(Parcel in) {
        name = in.readString();
        message = in.readString();
    }

    public static final Creator<Talk> CREATOR = new Creator<Talk>() {
        @Override
        public Talk createFromParcel(Parcel in) {
            return new Talk(in);
        }

        @Override
        public Talk[] newArray(int size) {
            return new Talk[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(message);
    }

    //为了实现out和inout，要自己书写此方法
    public void readFromParcel(Parcel parcel){
        name = parcel.readString();
        message = parcel.readString();
    }

    @Override
    public String toString() {
        return "Talk{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
