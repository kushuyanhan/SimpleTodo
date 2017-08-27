package com.codepath.simpletodo;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codepath.simpletodo.model.Todo;
import com.codepath.simpletodo.model.UIUtils;

import java.util.List;

import static com.codepath.simpletodo.NewTodoActivity.KEY_TODO;

/**
 * Created: xuemaomao
 * Date: 8/24/17
 * Package: com.codepath.simpletodo
 * File: ListAdapter
 * Description: TODO
 */

public class ListAdapter extends BaseAdapter {
    public static final String DONE = "DONE";
    public static final String TODO = "TO-DO";
    public static final String HIGH = "HIGH";
    public static final String MEDIUM = "MEDIUM";
    public static final String LOW = "LOW";
    private MainActivity activity;
    private List<Todo> data;

    public ListAdapter(MainActivity activity, List<Todo> data) {
        this.activity = activity;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.list_item, parent, false);

            vh = new ViewHolder();
            vh.todoName = (TextView) convertView.findViewById(R.id.main_list_item_name);
            vh.todoPriority = (TextView) convertView.findViewById(R.id.main_list_item_priority);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final Todo todo = (Todo) getItem(position);
        vh.todoName.setText(todo.name);
        vh.todoPriority.setText(todo.priority);

        setPriorityColor(vh, todo);
        setTodoStrikeThrough(vh, todo);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, TodoDetailActivity.class);
                intent.putExtra(KEY_TODO, todo);
                activity.startActivityForResult(intent, MainActivity.REQ_CODE_TODO_EDIT);
            }
        });

        return convertView;
    }

    private void setPriorityColor(ViewHolder vh, Todo todo) {
        switch (todo.priority) {
            case HIGH:
                vh.todoPriority.setTextColor(Color.RED);
                break;
            case MEDIUM:
                vh.todoPriority.setTextColor(Color.MAGENTA);
                break;
            case LOW:
                vh.todoPriority.setTextColor(Color.BLUE);
                break;
            default:
                break;
        }
    }

    public void setTodoStrikeThrough(ViewHolder vh, Todo todo) {
        if (todo.status.equals(DONE)) {
            UIUtils.setTextViewStrikeThrough(vh.todoName, true);
        }
        if (todo.status.equals(TODO)) {
            UIUtils.setTextViewStrikeThrough(vh.todoName, false);
        }
    }

    private static class ViewHolder {
        TextView todoName;
        TextView todoPriority;
    }
}
