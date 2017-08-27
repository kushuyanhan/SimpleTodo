package com.codepath.simpletodo.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created: xuemaomao
 * Date: 8/22/17
 * Package: com.codepath.simpletodo.model
 * File: TodoDatabaseHelper
 * Description: TODO
 */

public class TodoDatabaseHelper extends SQLiteOpenHelper {

    private static TodoDatabaseHelper sInstance;

    //Database info
    private static final String DATABASE_NAME = "todoDB";
    private static final int DATABASE_VERSION = 1;

    //Table Names
    private static final String TABLE_TODOS = "todos";

    //Todo Table Columns
    private static final String KEY_TODO_ID = "id";
    private static final String KEY_TODO_NAME = "name";
    private static final String KEY_TODO_DATE = "date";
    private static final String KEY_TODO_NOTE = "note";
    private static final String KEY_TODO_PRIORITY = "priority";
    private static final String KEY_TODO_STATUS = "status";


    public static synchronized TodoDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new TodoDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    public TodoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_TODOS +
            "(" +
            KEY_TODO_ID + " INTEGER PRIMARY KEY," + // Define a primary key
            KEY_TODO_NAME + " TEXT" +
            KEY_TODO_NOTE + " TEXT" +
            KEY_TODO_PRIORITY + " TEXT" +
            KEY_TODO_STATUS + " TEXT" +
            KEY_TODO_DATE + " TEXT" +
            ")";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODOS +
                "(" +
                KEY_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // Define a primary key
                KEY_TODO_NAME + " TEXT" +
                KEY_TODO_NOTE + " TEXT" +
                KEY_TODO_PRIORITY + " TEXT" +
                KEY_TODO_STATUS + " TEXT" +
                KEY_TODO_DATE + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(CREATE_TODO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }



//    public void addTodo(Todo todo) {
//        //Create and /or open the database for writing
//        SQLiteDatabase db = getWritableDatabase();
//
//        db.beginTransaction();
//        try {
//            long todoId = addOrUpdate(todo);
//            ContentValues values = new ContentValues();
//            values.put(KEY_TODO_ID, todoId);
//            values.put(KEY_TODO_NAME, todo.name);
//
//            db.insertOrThrow(TABLE_TODOS, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            Log.d(TAG, "error while trying to add todo to dabase");
//        } finally {
//            db.endTransaction();
//        }
//
//    }
//
//    private long addOrUpdate(Todo todo) {
//        SQLiteDatabase db = getWritableDatabase();
//        long todoId = -1;
//
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_TODO_NAME, todo.name);
//
//            int rows = db.update(TABLE_TODOS, values, KEY_TODO_NAME + "= ?",
//                    new String[]{todo.name});
//
//            if (rows == 1) {
//                String todoSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
//                        KEY_TODO_ID, TABLE_TODOS, KEY_TODO_NAME);
//
//                Cursor cursor = db.rawQuery(todoSelectQuery, new String[]{String.valueOf(todo.name)});
//                try {
//                    if (cursor.moveToFirst()) {
//                        todoId = cursor.getInt(0);
//                        db.setTransactionSuccessful();
//                    }
//                } finally {
//                    if (cursor != null && !cursor.isClosed()) {
//                        cursor.close();
//                    }
//                }
//            } else {
//                todoId = db.insertOrThrow(TABLE_TODOS, null, values);
//                db.setTransactionSuccessful();
//            }
//        } catch (Exception e) {
//            Log.d(TAG, "error while trying to add or update todo");
//        } finally {
//            db.endTransaction();
//        }
//        return todoId;
//    }
//
//
//
//    public List<Todo> getAllTodos() {
//        List<Todo> todos = new ArrayList<>();
//
//        //SELECT * FROM TODOS
//        String TODOS_SELECT_QUERY =
//                String.format("SELECT * FROM %s",
//                        TABLE_TODOS);
//
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery(TODOS_SELECT_QUERY, null);
//        try {
//            if (cursor.moveToFirst()){
//                do {
//                    Todo todo = new Todo();
//                    todo.name = cursor.getString(cursor.getColumnIndex(KEY_TODO_NAME));
//
//                } while(cursor.moveToNext());
//
//            }
//        } catch (Exception e) {
//            Log.d(TAG, "error while trying to get todos from database");
//        } finally {
//            if (cursor != null && !cursor.isClosed()) {
//                cursor.close();
//            }
//        }
//        return todos;
//    }
}
