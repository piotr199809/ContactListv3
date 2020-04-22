package com.example.contactlistv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.contactlistv3.DeleteDialog.OnDeleteDialogInteractionListener;
import com.example.contactlistv3.contacts.ContactListContent;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity implements
        ContactFragment.OnListFragmentInteractionListener,
        CallDialog.OnCallDialogInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener{


    public static final String contactExtra = "contactExtra";
    private int currentItemPosition = -1;
    private int currentItemPositionClick = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    //Krotkie nacisniecie na element listy - wyswietlanie info o kontakcie
    //Gdy terminal jest w orientacji pionowej przechodzimy do innej aktywnosci
    //Gdy terminal jest w orientacji poziomej info jest wyswietlane w tej samej aktywnosci obok listy
    @Override
    public void onListFragmentClickInteraction(ContactListContent.Contact contact, final int position) {
        currentItemPositionClick = position;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            ImageView im = findViewById(R.id.photo_contactInfo);
            im.setVisibility(View.VISIBLE);     //im ustawiamy jako widoczny,
                                                //poniewaz gdy usuniemy element z listy, imageView ustawiamy
                                                //jako niewidoczny i w tej sytuacji nacisniecie dowolnego
                                                //elementu listy powodowaloby to ze zdjecie
                                                //tego elementu bylo by niewidoczne
            displayContactInFragment(contact);
        }else {
            startSecondActivity(contact, position);
        }
    }

    //Dlugie nacisniegie elementu listy powoduje wyswietlenie dialogu polaczenia z tym elementem
    @Override
    public void onListFragmentLongClickInteraction(int position) {
        currentItemPosition = position;
        showCallDialog();
    }

    //Nacisniecie ikony kosza powoduje wyswietlenie dialogu potwierdzajacego usuniecie danego kontaktu
    @Override
    public void onListFragmentDeleteInteraction(int position) {
        currentItemPosition = position;
        showDeleteDialog();
    }

    //Funkcja obslugujaca przycisniecie przycisku floatingActionButton,
    //ktory przekierowuje do aktywnosci pozwalajacej dodawanie nowego kontaktu
    public void addClick(View view) {
        Intent intent = new Intent(this, ContactAddActivity.class);

        startActivity(intent);
    }

    //Funkcja do przechodznia do aktywnosci wyswietlajacej info o kontakcie - orientacja pionowa
    private void startSecondActivity(ContactListContent.Contact contact, int position){
        Intent intent = new Intent(this, ContactInfoActivity.class);
        intent.putExtra(contactExtra, (Parcelable) contact);
        startActivity(intent);
    }

    //Funkcja do wyswietlania info o kontakcie - orientacja pozioma
    private void displayContactInFragment(ContactListContent.Contact contact){
        ContactInfoFragment contactInfoFragment = ((ContactInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(contactInfoFragment != null){
            contactInfoFragment.displayContact(contact);
        }
    }

    //Wyswietlanie dialogu polaczenia
    private void showCallDialog(){
        CallDialog.newInstance(currentItemPosition).show(getSupportFragmentManager(), getString(R.string.call_dialog_tag));
    }

    //Wyswietlanie dialogu usuwania kontaktu
    private void showDeleteDialog(){
        DeleteDialog.newInstance(currentItemPosition).show(getSupportFragmentManager(), getString(R.string.delete_dialog_tag));
    }

    //Potwierdzenie polaczenia z danym  kontaktem powoduje wyswietlenie Toast
    @Override
    public void onCallDialogPositiveClick(DialogFragment dialog) {
       Toast toast = Toast.makeText(this,"Call " + ContactListContent.getName(currentItemPosition) , Toast.LENGTH_LONG);
       toast.setGravity(Gravity.CENTER,0,0);
       toast.show();
    }

    //Odwolanie polaczenia z danym kontaktem powoduje wyswietlenie Snackbar, ktory jeszcze raz pyta
    //uzytkownika czy chce polaczyc sie z danym kontaktem
    @Override
    public void onCallDialogNegativeClick(DialogFragment dialog) {
        View v = findViewById(R.id.addButton);
        if(v != null){
            Snackbar.make(v,"Call " + ContactListContent.getName(currentItemPosition) + "?", Snackbar.LENGTH_LONG)
                    .setAction("YES", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast toast = Toast.makeText(getApplicationContext(),"Call " + ContactListContent.getName(currentItemPosition) , Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                    }).show();
        }
    }

    //Potwierdzenie usuniecia danego kontaktu
    //Gdy terminal znajduje sie w orientacji poziomej - wyswietlane info o usuwanym kontakcie
    //jest usuwane z ekranu
    @Override
    public void onDeleteDialogPositiveClick(DialogFragment dialog) {
        ContactListContent.removeItem(currentItemPosition);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(currentItemPositionClick == currentItemPosition){
                ContactInfoFragment contactInfoFragment = ((ContactInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
                if (contactInfoFragment != null) {
                    contactInfoFragment.displayBlankContact();
                }
            }else{
                if(currentItemPositionClick > currentItemPosition){
                    currentItemPositionClick = currentItemPositionClick - 1;
                }
            }
        }

        ((ContactFragment) getSupportFragmentManager().findFragmentById(R.id.contactFragment)).notifyDataChange();
    }

    //Odwolanie usuwania kontaktu - nic nie robi
    @Override
    public void onDeleteDialogNegativeClick(DialogFragment dialog) {
    }
}
