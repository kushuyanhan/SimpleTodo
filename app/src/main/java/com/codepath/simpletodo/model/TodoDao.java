package com.codepath.simpletodo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

/**
 * Created: xuemaomao
 * Date: 8/22/17
 * Package: com.codepath.simpletodo.model
 * File: TodoDao
 * Description: TODO
 */

public class TodoDao {
    private SQLiteDatabase db;
    private TodoDatabaseHelper helper;


    //Table Names
    private static final String TABLE_TODOS = "todo";

    //Todo Table Columns
    private static final String KEY_TODO_ID = "id";
    private static final String KEY_TODO_NAME = "name";
    private static final String KEY_TODO_DATE = "due";
    private static final String KEY_TODO_NOTE = "note";
    private static final String KEY_TODO_PRIORITY = "priority";
    private static final String KEY_TODO_STATUS = "status";

    public TodoDao(Context context) {
        helper = new TodoDatabaseHelper(context);
    }

//    public void insertData() {
//        SQLiteDatabase db = helper.getWritableDatabase();
//        String sql = "INSERT INTO todos (id, name, priority) VALUES (?, ?, ?)";
//        try {
//            SQLiteStatement insStmt = db.compileStatement(sql);
//            db.beginTransaction();
//            for (int i = 0; i < 20; i++) {
//                insStmt.bindLong(1, i);
//                insStmt.bindString(2, "todo" + String.valueOf(i));
//                insStmt.bindString(3, "todo" + String.valueOf(i));
//                insStmt.executeInsert();    //  should really check value here!
//            }
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            Log.d(TAG, "error while trying to add todo to dabase");
//        } finally {
//            db.endTransaction();
//    }


    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<>();

        db = helper.getReadableDatabase();
        //String TODO_SELECT_QUERY = "SELECT * FROM todo";
        String sql = "select id, name, due, note, priority, status from todo";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Todo todo = new Todo();
            todo.setId(cursor.getInt(cursor.getColumnIndex(KEY_TODO_ID)));
            todo.setName(cursor.getString(cursor.getColumnIndex(KEY_TODO_NAME)));
            todo.setDate(cursor.getString(cursor.getColumnIndex(KEY_TODO_DATE)));
            todo.setNote(cursor.getString(cursor.getColumnIndex(KEY_TODO_NOTE)));
            todo.setPriority(cursor.getString(cursor.getColumnIndex(KEY_TODO_PRIORITY)));
            todo.setStatus(cursor.getString(cursor.getColumnIndex(KEY_TODO_STATUS)));

            list.add(todo);
        }
        db.close();
        return list;
    }

    public long saveData(Todo todo) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put("id",todo.id);
        values.put(KEY_TODO_NAME, todo.name);
        values.put(KEY_TODO_PRIORITY, todo.priority);
        values.put(KEY_TODO_STATUS, todo.status);
        values.put(KEY_TODO_NOTE, todo.note);
        values.put(KEY_TODO_DATE, todo.date);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_TODOS, null, values);
        db.close();
        return newRowId;

    }

    public long updateData(Todo todo) {
        SQLiteDatabase db = helper.getWritableDatabase();// 取得数据库操作

        ContentValues values = new ContentValues();
        values.put(KEY_TODO_ID, todo.id);
        values.put(KEY_TODO_NAME, todo.name);
        values.put(KEY_TODO_PRIORITY, todo.priority);
        values.put(KEY_TODO_STATUS, todo.status);
        values.put(KEY_TODO_NOTE, todo.note);
        values.put(KEY_TODO_DATE, todo.date);

        String[] args = {String.valueOf(todo.id)};

        long updateRowId = db.update(TABLE_TODOS, values, " id = ?", args);

        db.close();
        return updateRowId;

    }

    public boolean deleteTodo(String todoId) {
        db = helper.getReadableDatabase();
        return db.delete(TABLE_TODOS, "id = ? ", new String[]{todoId}) > 0;


    }
}
