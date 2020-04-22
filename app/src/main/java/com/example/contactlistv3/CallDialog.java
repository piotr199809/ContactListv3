package com.example.contactlistv3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.contactlistv3.contacts.ContactListContent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCallDialogInteractionListener} interface
 * to handle interaction events.
 */
public class CallDialog extends DialogFragment {

    private OnCallDialogInteractionListener mListener;
    int Position;

    public CallDialog() {
        // Required empty public constructor
    }

    static CallDialog newInstance(int position){
        CallDialog c = new CallDialog();

        Bundle args = new Bundle();
        args.putInt("position", position);
        c.setArguments(args);
        return c;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Position = getArguments().getInt("position");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Call " + ContactListContent.getName(Position) + "?");
        builder.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onCallDialogPositiveClick(CallDialog.this);
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onCallDialogNegativeClick(CallDialog.this);
            }
        });
        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCallDialogInteractionListener) {
            mListener = (OnCallDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDeleteDialogInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCallDialogInteractionListener {
        void onCallDialogPositiveClick(DialogFragment dialog);
        void onCallDialogNegativeClick(DialogFragment dialog);
    }
}


