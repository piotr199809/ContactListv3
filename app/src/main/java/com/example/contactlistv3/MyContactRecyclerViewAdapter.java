package com.example.contactlistv3;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactlistv3.ContactFragment.OnListFragmentInteractionListener;
import com.example.contactlistv3.contacts.ContactListContent.Contact;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Contact} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mValues;
    private final OnListFragmentInteractionListener mListener;
    private ViewHolder holder;
    private int position;

    public MyContactRecyclerViewAdapter(List<Contact> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Contact contact = mValues.get(position);
        holder.mItem = contact;
        holder.mContentView.setText(contact.name);


        final int picPath = contact.picPath;
        Context context = holder.mView.getContext();

        if( picPath >= 0){
            Drawable contactDrawable;
            switch (picPath){
                case 0:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                    break;
                case 1:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_2);
                    break;
                case 2:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_3);
                    break;
                case 3:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_4);
                    break;
                case 4:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_5);
                    break;
                case 5:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_6);
                    break;
                case 6:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_7);
                    break;
                case 7:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_8);
                    break;
                case 8:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_9);
                    break;
                case 9:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_10);
                    break;
                case 10:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_11);
                    break;
                case 11:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_12);
                    break;
                case 12:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_13);
                    break;
                case 13:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_14);
                    break;
                case 14:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_15);
                    break;
                case 15:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_16);
                    break;
                default:
                    contactDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                    break;
            }
            holder.mItemImageView.setImageDrawable(contactDrawable);

        }else {
            holder.mItemImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_1));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mListener){
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.onListFragmentLongClickInteraction(position);
                return false;
            }
        });

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mListener){
                    mListener.onListFragmentDeleteInteraction(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public final ImageButton mDeleteButton;
        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.name_contact);
            mItemImageView = (ImageView) view.findViewById(R.id.img_contact);
            mDeleteButton = (ImageButton) view.findViewById(R.id.delete_button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
