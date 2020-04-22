package com.example.contactlistv3.contacts;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContactListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Contact> ITEMS = new ArrayList<Contact>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Contact> ITEM_MAP = new HashMap<String, Contact>();
/*
    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }
*/
    //Otrzymywanie imienia kontaktu o pozycji(position) z listy kontaktow
    public static String getName(int position)
    {
        Contact contact = ITEMS.get(position);
        String name = contact.name;
        return name;
    }

    //Dodawnie elemntow do listy
    public static void addItem(Contact item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    //Usuwanie elementow z listy
    public static void removeItem(int position){
        String itemId = ITEMS.get(position).id;
        ITEMS.remove(position);
        ITEM_MAP.remove(itemId);
    }
/*
    private static Contact createDummyItem(int position) {
        return new Contact(String.valueOf(position), "Item " + position, makeDetails(position),"","", 0);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
*/
    /**
     * A dummy item representing a piece of content.
     */
    public static class Contact implements Parcelable {
        public final String id;
        public final String name;
        public final String surname;
        public final String birthday;
        public final String phoneNumber;
        public final int picPath;

        public Contact(String id, String name, String surname, String birthday, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.birthday = birthday;
            this.phoneNumber = phoneNumber;
            this.picPath = 0;
        }

        public Contact(String id, String name, String surname, String birthday, String phoneNumber, int picPath) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.birthday = birthday;
            this.phoneNumber = phoneNumber;
            this.picPath = picPath;
        }

        protected Contact(Parcel in) {
            id = in.readString();
            name = in.readString();
            surname = in.readString();
            birthday = in.readString();
            phoneNumber = in.readString();
            picPath = in.readInt();
        }

        public static final Creator<Contact> CREATOR = new Creator<Contact>() {
            @Override
            public Contact createFromParcel(Parcel in) {
                return new Contact(in);
            }

            @Override
            public Contact[] newArray(int size) {
                return new Contact[size];
            }
        };

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(surname);
            dest.writeString(birthday);
            dest.writeString(phoneNumber);
            dest.writeInt(picPath);
        }
    }
}
