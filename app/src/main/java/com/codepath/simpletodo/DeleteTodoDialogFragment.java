package com.codepath.simpletodo;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class DeleteTodoDialogFragment extends DialogFragment {
    public static final String TITLE = "title";
    public static final String MESSAGE = "Are you sure to delete this todo?";
    private Handler handler;
    private ConfirmDialogListener mListener;

    public interface ConfirmDialogListener {
        void onLoginInputComplete();
    }

    public static DeleteTodoDialogFragment newInstance(String title) {
        DeleteTodoDialogFragment frag = new DeleteTodoDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        frag.setArguments(args);
        return frag;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(MESSAGE);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener = (ConfirmDialogListener) getActivity();
                mListener.onLoginInputComplete();

            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", null);
        return alertDialogBuilder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof ConfirmDialogListener) {
            mListener = (ConfirmDialogListener) getActivity();
        }
    }
}

