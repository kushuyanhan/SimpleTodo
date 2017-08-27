package com.codepath.simpletodo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created: xuemaomao
 * Date: 8/21/17
 * Package: com.codepath.simpletodo.models
 * File: Todo
 * Description: TODO
 */

public class Todo implements Parcelable {
    public int id;
    public String name;
    public String priority;
    public String date;
    public String note;
    public String status;


    public Todo() {

    }

    protected Todo(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.priority = in.readString();
        this.date = in.readString();
        this.note = in.readString();
        this.status = in.readString();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.priority);
        dest.writeString(this.date);
        dest.writeString(this.note);
        dest.writeString(this.status);
    }


    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel source) {
            return new Todo(source);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };
}
