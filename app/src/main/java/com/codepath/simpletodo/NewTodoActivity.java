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

import java.util.Calendar;

public class NewTodoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String KEY_TODO = "todo";
    private Todo todo;

    private EditText todoId;
    private EditText todoName;
    private EditText todoNote;
    private TextView todoDate;
    private TextView todoStatus;
    private TextView todoPriority;


    private ImageButton imageButton;
    private ImageView todoCalendar;
    private ImageButton morePriority;
    private ImageButton moreStatus;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);

        setupUI();

        setupDatePicker();
        setupPriority();
        setupStatus();
    }


    private void setupStatus() {

        moreStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(NewTodoActivity.this, v);
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
                PopupMenu popup = new PopupMenu(NewTodoActivity.this, v);
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
                        NewTodoActivity.this,
                        (DatePickerDialog.OnDateSetListener) NewTodoActivity.this,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }

    private void setupUI() {
        todoName = (EditText) findViewById(R.id.todo_name);
        todoNote = (EditText) findViewById(R.id.todo_notes);
        todoDate = (TextView) findViewById(R.id.todo_date);
        todoStatus = (TextView) findViewById(R.id.todo_status);
        todoPriority = (TextView) findViewById(R.id.todo_priority);

        todoCalendar = (ImageView) findViewById(R.id.todo_calendar);

        morePriority = (ImageButton) findViewById(R.id.moreMenu_priority);
        moreStatus = (ImageButton) findViewById(R.id.moreMenu_status);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_item_menu, menu);
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

    private void saveTodo() {
        todo = new Todo();
        todo.name = todoName.getText().toString();
        todo.priority = todoPriority.getText().toString();
        todo.status = todoStatus.getText().toString();
        todo.note = todoNote.getText().toString();
        todo.date = todoDate.getText().toString();

        Intent result = new Intent();
        result.putExtra(KEY_TODO, todo);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // this method will be called after user has chosen date from the DatePickerDialog
        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);

        todoDate.setText(DateUtils.dateToStringDate(c.getTime()));
    }
}
