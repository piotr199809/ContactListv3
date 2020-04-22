package com.example.contactlistv3;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactlistv3.contacts.ContactListContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactInfoFragment extends Fragment {

    public ContactInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_info, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent != null){
            ContactListContent.Contact receivedContact = intent.getParcelableExtra(MainActivity.contactExtra);
            if(receivedContact != null){
                displayContact(receivedContact);
            }
        }
    }

    public void displayContact(ContactListContent.Contact contact) {
        FragmentActivity activity = getActivity();

        TextView contactInfoNameSurname = activity.findViewById(R.id.name_contactInfo);
        TextView contactInfoPhoneNumber = activity.findViewById(R.id.phoneNumber_contactInfo);
        TextView contactInfoBirthday = activity.findViewById(R.id.birthday_contactInfo);
        ImageView contactInfoPhoto = activity.findViewById(R.id.photo_contactInfo);

        contactInfoNameSurname.setText(contact.name + " " + contact.surname);
        contactInfoPhoneNumber.setText("Phone number: " + contact.phoneNumber);
        contactInfoBirthday.setText("Birthday: " + contact.birthday);

        if (contact.picPath >= 0) {
            Drawable contactDrawable;
            switch (contact.picPath) {
                case 0:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_1);
                    break;
                case 1:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_2);
                    break;
                case 2:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_3);
                    break;
                case 3:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_4);
                    break;
                case 4:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_5);
                    break;
                case 5:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_6);
                    break;
                case 6:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_7);
                    break;
                case 7:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_8);
                    break;
                case 8:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_9);
                    break;
                case 9:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_10);
                    break;
                case 10:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_11);
                    break;
                case 11:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_12);
                    break;
                case 12:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_13);
                    break;
                case 13:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_14);
                    break;
                case 14:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_15);
                    break;
                case 15:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_16);
                    break;
                default:
                    contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_1);
                    break;
            }
            contactInfoPhoto.setImageDrawable(contactDrawable);
        } else {
            contactInfoPhoto.setImageDrawable(activity.getResources().getDrawable(R.drawable.avatar_1));
        }
    }

    //Wyswietlanie pustego fragmentu z info o kontakcie
    public void displayBlankContact() {
        FragmentActivity activity = getActivity();

        TextView contactInfoNameSurname = activity.findViewById(R.id.name_contactInfo);
        TextView contactInfoPhoneNumber = activity.findViewById(R.id.phoneNumber_contactInfo);
        TextView contactInfoBirthday = activity.findViewById(R.id.birthday_contactInfo);
        ImageView contactInfoPhoto = activity.findViewById(R.id.photo_contactInfo);

        contactInfoNameSurname.setText("");
        contactInfoPhoneNumber.setText("");
        contactInfoBirthday.setText("");
        contactInfoPhoto.setVisibility(View.INVISIBLE);
    }
}
