package com.example.contactlistv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactlistv3.contacts.ContactListContent;

import java.util.Random;

public class ContactAddActivity extends AppCompatActivity {

    //Funkcja sprawdzajaca czy podany numer telefonu jest poprawny
    //Tzn. czy sklada sie z 9 cyfr
    public boolean phoneNumberIsCorrect(String phoneNumber) {
        if (phoneNumber.length() == 9) {
            for (char c : phoneNumber.toCharArray()) {
                if (!Character.isDigit(c)) {
                    Toast.makeText(getApplicationContext(), "Wrong phone number!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Wrong phone number!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //Funkcja sprawdzajaca czy podana data urodzenia jest prawidlowa
    //Data jest formatu dd.mm.rrrr
    //Funkcja sprawdza poprawnosc formatu - czy sklada sie z 10 znakow oraz 3 i 6 znak to "."
    //Funkcja sprawdza czy rok jest wiekszy od 1000 oraz czy miesiac ma wartosc od 1 do 12
    //Poprawnosc dnia jest sprawdzana na podstawie tablicy monthArray[]
    //ktora zawiera odpowiednia liczbe dni w danym miesiacu
    public boolean birthdayIsCorrect(String birthday){
        //format dd.mm.yyyy
        if(birthday.length() == 0){
            return  true;
        }
        else if(birthday.length() == 10)
        {
            char[] birthdayCharArray = birthday.toCharArray();
            if((birthdayCharArray[2] ==  '.')  && (birthdayCharArray[5] == '.'))
            {
                if(Character.isDigit(birthdayCharArray[0])
                        && Character.isDigit(birthdayCharArray[1])
                        && Character.isDigit(birthdayCharArray[3])
                        && Character.isDigit(birthdayCharArray[4])
                        && Character.isDigit(birthdayCharArray[6])
                        && Character.isDigit(birthdayCharArray[7])
                        && Character.isDigit(birthdayCharArray[8])
                        && Character.isDigit(birthdayCharArray[9])){

                    int[] monthArray = {31,29,31,30,31,30,31,31,30,31,30,31};
                    int day;
                    int month;
                    int year;

                    day = (Integer.parseInt(String.valueOf(birthdayCharArray[0])) * 10) + (Integer.parseInt(String.valueOf(birthdayCharArray[1])));
                    month = (Integer.parseInt(String.valueOf(birthdayCharArray[3])) * 10) + (Integer.parseInt(String.valueOf(birthdayCharArray[4])));
                    year = (Integer.parseInt(String.valueOf(birthdayCharArray[6])) * 1000) + (Integer.parseInt(String.valueOf(birthdayCharArray[7])) * 100)
                            + (Integer.parseInt(String.valueOf(birthdayCharArray[8])) * 10) + (Integer.parseInt(String.valueOf(birthdayCharArray[9])));

                    if(year >= 1000) {
                        if (month >= 1 && month <= 12) {
                            if (day >= 1 && day <= monthArray[month - 1]) {
                                return true;
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong date of birthday! Wrong day!", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong date of birthday! Wrong month!", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong date of birthday! Wrong year!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Wrong date of birthday!", Toast.LENGTH_SHORT).show();
                    return false;
                }
           } else {
                Toast.makeText(getApplicationContext(), "Wrong date of birthday! Wrong format!", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else{
            Toast.makeText(getApplicationContext(), "Wrong date of birthday!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
    }

    //Funkcja ktora dodaje nowe kontaty
    //Jest uruchamiana po nacisnieciu przycisku
    //Funkcja wymaga zeby uzytkownik podal przynajmniej numer telefonu aby zapisac nowy kontakt
    //W funkcji generowany jest losowe zdjecie dla dodawanego kontaktu
    //W funkcji sprawdzana jest poprawnosc nr telefonu oraz data urodzenia
    //Po prawidlowym wprowadzeniu kontaktu uzytkownik powraca do MainActivity
    public void addNewContact(View view) {
        Random rnd = new Random();
        int picPath = rnd.nextInt(16);
        EditText nameEditTxt = findViewById(R.id.editText_name);
        EditText surnameEditTxt = findViewById(R.id.editText_surname);
        EditText birthdayEditTxt = findViewById(R.id.editText_birthday);
        EditText phoneNumberEditTxt = findViewById(R.id.editText_phoneNumber);

        String name = nameEditTxt.getText().toString();
        String surname = surnameEditTxt.getText().toString();
        String birthday = birthdayEditTxt.getText().toString();
        String phoneNumber = phoneNumberEditTxt.getText().toString();


        if (!phoneNumber.isEmpty()) {
            if(phoneNumberIsCorrect(phoneNumber)) {
                if(birthdayIsCorrect(birthday)){
                    if (name.isEmpty() && surname.isEmpty()) {
                        name = phoneNumber;
                        surname = getString(R.string.default_surname);
                    }
                    if (name.isEmpty() && !surname.isEmpty()) {
                        name = surname;
                        surname = getString(R.string.default_surname);
                    }
                    if (birthday.isEmpty()) {
                        birthday = getString(R.string.default_birthday);
                    }
                    ContactListContent.addItem(new ContactListContent.Contact("Contact." + ContactListContent.ITEMS.size() + 1,
                            name,
                            surname,
                            birthday,
                            phoneNumber,
                            picPath));

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            Toast.makeText(getApplicationContext(), "You didn't phone number enter", Toast.LENGTH_SHORT).show();
        }
    }
}


