package com.codepath.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.codepath.simpletodo.model.Todo;
import com.codepath.simpletodo.model.UIUtils;

import static com.codepath.simpletodo.NewTodoActivity.KEY_TODO;
import static com.codepath.simpletodo.R.id.delete;

public class TodoDetailActivity extends AppCompatActivity implements DeleteTodoDialogFragment.ConfirmDialogListener {
    public static final int REQUEST_CODE = 300;
    public static final String TAG = "delete_todo";
    public static final String DONE = "DONE";
    private TextView nameTv;
    private TextView dateTv;
    private TextView noteTv;
    private TextView priorityTv;
    private TextView statusTv;

    private Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        todo = getIntent().getParcelableExtra(KEY_TODO);

        setupUI();
    }

    private void setupUI() {
        setContentView(R.layout.activity_todo_detail);

        dateTv = (TextView) findViewById(R.id.todo_detail_date);
        nameTv = (TextView) findViewById(R.id.todo_detail_name);
        statusTv = (TextView) findViewById(R.id.todo_detail_status);
        noteTv = (TextView) findViewById(R.id.todo_detail_notes);
        priorityTv = (TextView) findViewById(R.id.todo_detail_priority);

        if (todo != null) {
            nameTv.setText(todo.name);
            setStrikeThrough();
            noteTv.setText(todo.note);
            dateTv.setText(todo.date);
            statusTv.setText(todo.status);
            priorityTv.setText(todo.priority);
        }
    }

    public void setStrikeThrough() {
        if (todo.status.equals(DONE)) {
            UIUtils.setTextViewStrikeThrough(nameTv, true);
        } else {
            UIUtils.setTextViewStrikeThrough(nameTv, false);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(TodoDetailActivity.this, EditTodoActivity.class);
                intent.putExtra(KEY_TODO, todo);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case delete:
                showDeleteTodoDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void showDeleteTodoDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DeleteTodoDialogFragment editTodoDialogFragment = DeleteTodoDialogFragment.newInstance("DELETE TODO");
        editTodoDialogFragment.show(fm, TAG);

    }

    @Override
    public void onLoginInputComplete() {
        Intent intent = new Intent(TodoDetailActivity.this, MainActivity.class);
        startActivity(intent);
        MainActivity.instance.deleteTodo(String.valueOf(todo.id));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            setResult(Activity.RESULT_FIRST_USER, data);
            finish();
        }
    }
}
