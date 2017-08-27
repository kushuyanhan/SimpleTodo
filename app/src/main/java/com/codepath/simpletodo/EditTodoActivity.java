package com.codepath.simpletodo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.simpletodo.model.Todo;
import com.codepath.simpletodo.model.UIUtils;

import java.util.Calendar;

import static com.codepath.simpletodo.ListAdapter.DONE;
import static com.codepath.simpletodo.ListAdapter.TODO;
import static com.codepath.simpletodo.NewTodoActivity.KEY_TODO;

public class EditTodoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private EditText todoId;
    private EditText todoName;
    private EditText todoNote;
    private TextView todoDate;
    private TextView todoStatus;
    private TextView todoPriority;
    private ImageView todoCalendar;
    private ImageButton morePriority;
    private ImageButton moreStatus;

    private Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);

        todo = getIntent().getParcelableExtra(KEY_TODO);

        setupUI();
        setupDatePicker();
        setupPriority();
        setupStatus();
    }

    private void setupUI() {
        //todoId = (EditText) findViewById(R.id.todo_id);
        todoName = (EditText) findViewById(R.id.todo_name);
        todoNote = (EditText) findViewById(R.id.todo_notes);
        todoDate = (TextView) findViewById(R.id.todo_date);
        todoStatus = (TextView) findViewById(R.id.todo_status);
        todoPriority = (TextView) findViewById(R.id.todo_priority);

        todoCalendar = (ImageView) findViewById(R.id.todo_calendar);

        morePriority = (ImageButton) findViewById(R.id.moreMenu_priority);
        moreStatus = (ImageButton) findViewById(R.id.moreMenu_status);

        if (todo != null) {
            if (todo.status.equals(DONE)) {
                UIUtils.setTextViewStrikeThrough(todoName, true);
            }
            if (todo.status.equals(TODO)){
                UIUtils.setTextViewStrikeThrough(todoName, false);
            }

            todoName.setText(todo.name);
            todoNote.setText(todo.note);
            todoDate.setText(todo.date);
            todoStatus.setText(todo.status);
            todoPriority.setText(todo.priority);

        }

    }
    private void setupStatus() {

        moreStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(EditTodoActivity.this, v);
                popup.getMenuInflater()
                        .inflate(R.menu.status_options_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.todo:
                                todoStatus.setText(item.getTitle());
                                return true;
                            case R.id.done:
                                todoStatus.setText(item.getTitle());
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show(); //showing popup menu
            }
        });
    }

    private void setupPriority() {

        morePriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(EditTodoActivity.this, v);
                popup.getMenuInflater()
                        .inflate(R.menu.priority_options_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.high:
                                todoPriority.setText(item.getTitle());
                                return true;
                            case R.id.medium:
                                todoPriority.setText(item.getTitle());
                                return true;
                            case R.id.low:
                                todoPriority.setText(item.getTitle());
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show(); //showing popup menu
            }
        });

    }

    private void setupDatePicker() {

        todoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                Dialog dialog = new DatePickerDialog(
                        EditTodoActivity.this,
                        (DatePickerDialog.OnDateSetListener) EditTodoActivity.this,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_item_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveTodo();
                return true;
            case R.id.cancel:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // this method will be called after user has chosen date from the DatePickerDialog
        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);

        todoDate.setText(DateUtils.dateToStringDate(c.getTime()));
    }

    private void saveTodo() {
        if (todo == null) {
            todo = new Todo();
        } else {
            todo.name = todoName.getText().toString();
            todo.priority = todoPriority.getText().toString();
            todo.status = todoStatus.getText().toString();
            todo.note = todoNote.getText().toString();
            todo.date = todoDate.getText().toString();
        }

        Intent result = new Intent();
        result.putExtra(KEY_TODO, todo);
        setResult(Activity.RESULT_FIRST_USER, result);
        finish();
    }


}

