package com.codepath.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.simpletodo.model.Todo;
import com.codepath.simpletodo.model.TodoDao;
import com.codepath.simpletodo.model.TodoDatabaseHelper;

import java.util.List;

import static com.codepath.simpletodo.NewTodoActivity.KEY_TODO;


public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE_TODO_NEW = 100;
    public static final int REQ_CODE_TODO_EDIT = 200;

    private ListAdapter adapter;
    private List<Todo> todos;
    private TodoDao todoDao;
    private Todo todo;

    public static MainActivity instance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;

        TodoDatabaseHelper helper = TodoDatabaseHelper.getInstance(MainActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        //load data
        todoDao = new TodoDao(this);
        todos = todoDao.findAll();

        adapter = new ListAdapter(this, todos);
        ((ListView) findViewById(R.id.main_list_view)).setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, NewTodoActivity.class);
                startActivityForResult(intent, REQ_CODE_TODO_NEW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_TODO_NEW && resultCode == Activity.RESULT_OK) {
            todo = data.getParcelableExtra(KEY_TODO);
            insertTodo(todo);
        } else if (requestCode == REQ_CODE_TODO_EDIT && resultCode == Activity.RESULT_FIRST_USER) {
            todo = data.getParcelableExtra(KEY_TODO);
            updateTodo(todo);
        }
    }

    public void deleteTodo(String todoId) {
        for (int i = 0; i < todos.size(); i++) {
            if (String.valueOf(todos.get(i).id).equals(todoId)) {
                todos.remove(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
        todoDao.deleteTodo(todoId);
    }

    private void updateTodo(Todo todo) {
        for (int i = 0; i < todos.size(); ++i) {
            Todo item = todos.get(i);
            if (String.valueOf(item.id).equals(String.valueOf(todo.id))) {
                todos.set(i, todo);
                break;
            }
        }

        adapter.notifyDataSetChanged();
        todoDao.updateData(todo);
    }

    private void insertTodo(Todo todo) {
        todos.add(todo);
        todoDao.saveData(todo);

        todos = todoDao.findAll();

        adapter = new ListAdapter(this, todos);
        ((ListView) findViewById(R.id.main_list_view)).setAdapter(adapter);
    }
}
