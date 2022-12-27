package com.example.bookstore;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper
{
    private static final String databaseName = "libraryDatabase";
    SQLiteDatabase libraryDatabase ;
    public DBHelper (Context context)
    {
        super(context,databaseName,null,2);
        libraryDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
       /* db.execSQL("create table User(" +
                "userid integer primary key autoincrement " +
                ", name text" +
                ",email text " +
                ", password text " +
                ", phone text " +
                ", address text " +
                ", authority binary" +
                ");" ); */
        db.execSQL("create Table User (inputUsername TEXT primary key, inputPassword TEXT )");


        db.execSQL("create table Books(" +
                "bookid integer primary key autoincrement" +
                ",name text " +
                ",publisher text " +
                ",category text " +
                ",author text " +
                ",quantity integer " +
                ",price integer" +
                ",description text " +
                ");" );


        db.execSQL("create table Cart(" +
                "orderid integer primary key autoincrement " +
                ",totalprice integer " +
                ",bookids integer" +
                ",userids integer " +
                ",foreign key (bookids) references Books (bookid)" +
                ",foreign key (userids) references User (userid)" +
                ");");

        ContentValues value= new ContentValues();
        value.put("name","Love on the Brain");
        value.put("publisher","Penguin Publishing Group");
        value.put("category","Romance");
        value.put("author","Ali Hazelwood");
        value.put("quantity",5);
        value.put("price",211);
        value.put("description","From the New York Times bestselling author of The Love Hypothesis comes a new STEMinist rom-com in which a scientist is forced to work on a project with her nemesis—with explosive results.");
        db.insert("Books",null,value);
        value.clear();

        value.put("name","Things We Never Got Over");
        value.put("publisher","Hachette UK");
        value.put("category","Romance");
        value.put("author","Lucy Score");
        value.put("quantity",2);
        value.put("price",216);
        value.put("description", "Escaping her seemingly perfect wedding, Naomi Witt arrives in rough-around-the-edges Knockemout, Virginia, running to the rescue of her estranged twin, Tina.");
        db.insert("Books",null,value);
        value.clear();

        value.put("name","Hunt the Stars");
        value.put("publisher","HarperCollins");
        value.put("category","SC-FI");
        value.put("author","Jessie Mihalik");
        value.put("quantity",10);
        value.put("price",250);
        value.put("description","Octavia Zarola would do anything to keep her tiny, close-knit bounty hunting crew together—even if it means accepting a job from Torran Fletcher, a ruthless former general and her sworn enemy. When Torran offers her enough credits to not only keep her crew afloat but also hire someone to fix her ship, Tavi knows that she can’t refuse—no matter how much she’d like to.");
        db.insert("Books",null,value);
        value.clear();

        value.put("name","Sea of Tranquility");
        value.put("publisher","Alfred A.Knopf ");
        value.put("category","SC-FI");
        value.put("author","Emily St.John Mandel");
        value.put("quantity",2);
        value.put("price",317);
        value.put("description", "Edwin St. Andrew is eighteen years old when he crosses the Atlantic by steamship, exiled from polite society following an ill-conceived diatribe at a dinner party. He enters the forest, spellbound by the beauty of the Canadian wilderness, and suddenly hears the notes of a violin echoing in an airship terminal—an experience that shocks him to his core.");
        db.insert("Books",null,value);
        value.clear();


        value.put("name","Manhunt");
        value.put("publisher","Tor Publishing Group");
        value.put("category","Horror");
        value.put("author","Gretchen Felker-Martin");
        value.put("quantity",12);
        value.put("price",220);
        value.put("description", "Beth and Fran spend their days traveling the ravaged New England coast, hunting feral men and harvesting their organs in a gruesome effort to ensure they'll never face the same fate." );
        db.insert("Books",null,value);
        value.clear();

        value.put("name","The Hollow Kind");
        value.put("publisher","Farrar, Straus and Giroux");
        value.put("category","Horror");
        value.put("author","Andy Davidson");
        value.put("quantity",8);
        value.put("price",385);
        value.put("description", "Nellie Gardner is looking for a way out of an abusive marriage when she learns that her long-lost grandfather, August Redfern, has willed her his turpentine estate.");
        db.insert("Books",null,value);
        value.clear();


        value.put("name","The Night Shift");
        value.put("publisher","Head of Zeus");
        value.put("category","Thriller");
        value.put("author","Alex Finlay");
        value.put("quantity",18);
        value.put("price",185);
        value.put("description", "It's New Year's Eve of 1999 when four teenagers working late are attacked at a Blockbuster video store in New Jersey. Only one survives. Police quickly identify a suspect, the boyfriend of one of the victims, who flees and is never seen again.");
        db.insert("Books",null,value);
        value.clear();



        value.put("name","A Flicker in the Dark");
        value.put("publisher","St. Martin's Publishing Group");
        value.put("category","Thriller");
        value.put("author","Stacy Willingham");
        value.put("quantity",18);
        value.put("price",295);
        value.put("description", "When Chloe Davis was twelve, six teenage girls went missing in her small Louisiana town. By the end of the summer, her own father had confessed to the crimes and was put away for life, leaving Chloe and the rest of her family to grapple with the truth and try to move forward while dealing with the aftermath.");
        db.insert("Books",null,value);
        value.clear();



    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion)
    {
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Books");
        db.execSQL("drop table if exists Cart");
        db=getWritableDatabase();
        onCreate(db);


    }

    public void createNewBook( String name, String publisher, String category, String author, int quantity, int price, String description)
    {
        ContentValues row = new ContentValues();

        row.put("name",name);
        row.put("publisher",publisher);
        row.put("category",category);
        row.put("author",author);
        row.put("quantity",quantity);
        row.put("price",price);
        row.put("description",description);

        libraryDatabase=getWritableDatabase();
        libraryDatabase.insert("Books",null,row);
        libraryDatabase.close();
    }

    public ArrayList<BookModal> readBooks()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorBooks = db.rawQuery("SELECT * FROM " + "Books", null);
        ArrayList<BookModal> bookModalArrayList = new ArrayList<>();
        if(cursorBooks.moveToFirst())
        {
            do
            {
                bookModalArrayList.add(new BookModal(cursorBooks.getString(1),cursorBooks.getString(2),cursorBooks.getString(3),cursorBooks.getString(4),cursorBooks.getString(5),cursorBooks.getString(6),cursorBooks.getString(7)));

            }
            while (cursorBooks.moveToNext());
        }

        cursorBooks.close();
        return bookModalArrayList;
    }

    public void deleteBook(String bookName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Books", "name=?", new String[]{bookName});
        db.close();
    }

    public void updateBook(String originalBookName, String bookName, String bookPublisher, String bookCategory, String bookAuthor , String bookQuantity , String bookPrice ,String bookDescription)
    {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("name", bookName);
        values.put("publisher", bookPublisher);
        values.put("category", bookCategory);
        values.put("author", bookAuthor);
        values.put("quantity", bookQuantity);
        values.put("price", bookPrice);
        values.put("description", bookDescription);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update("Books", values, "name=?", new String[]{originalBookName});
        db.close();
    }


/*
    public Cursor fetchBooks()
    {
        libraryDatabase=getReadableDatabase();
        String[] rowDetails={"bookid","name","publisher","category","author","quantity","price","description"};
        Cursor cursor=libraryDatabase.query("Books",rowDetails,null,null,null,null,null);
        if (cursor!=null)
        {
            cursor.moveToFirst();
        }
        libraryDatabase.close();
        return cursor;
    }*/

    public Cursor getBook(String bookName)
    {
        libraryDatabase=getReadableDatabase();
        String [] arg={bookName};
        Cursor cursor=libraryDatabase.rawQuery("select * from Books where name like ?",arg);

        if(cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            libraryDatabase.close();
            return cursor;
        }

        libraryDatabase.close();
        return null;
    }

    public Cursor getAuthor(String authorName)
    {
        libraryDatabase=getReadableDatabase();
        String [] arg={authorName};
        Cursor cursor=libraryDatabase.rawQuery("select * from Books where author like ?",arg);

        if(cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            libraryDatabase.close();
            return cursor;
        }

        libraryDatabase.close();
        return null;
    }

    public String[] getBooksname(String Category)
    {
        libraryDatabase=getReadableDatabase();
        String[]arg={Category};


        Cursor cursor=libraryDatabase.rawQuery("Select name from Books where category like?",arg);

        String[] bnames=new String[cursor.getCount()];
        int i=0;
        while(cursor.moveToNext())
        {
            String name=cursor.getString(0);
            bnames[i]=name;
            i++;

        }
        return bnames;
    }

    public String[] getBooksauthors(String Category)
    {
        libraryDatabase=getReadableDatabase();
        String[]arg={Category};


        Cursor cursor=libraryDatabase.rawQuery("Select author from Books where category like?",arg);

        String[] bauthors=new String[cursor.getCount()];
        int i=0;
        while(cursor.moveToNext())
        {
            String name=cursor.getString(0);
            bauthors[i]=name;
            i++;

        }
        return bauthors;
    }

    public boolean insertData (String inputUsername, String inputPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("inputUsername", inputUsername);
        contentValues.put("inputPassword", inputPassword);
        long result = db.insert("User", null, contentValues);
        if (result==-1) return false;
        else return true;
    }

    public boolean checkUsername (String inputUsername){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where inputUsername = ?", new String [] {inputUsername});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }

    public boolean checkUsernamePassword (String inputUsername, String inputPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where inputUsername = ? and inputPassword = ?", new String[] {inputUsername, inputPassword});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }
/*
    public boolean checkloop (String username,String password)
    {
        libraryDatabase=getReadableDatabase();
        Cursor cursor=libraryDatabase.rawQuery("SELECT inputUsername FROM " + "User", null);
        Cursor cursor1=libraryDatabase.rawQuery("SELECT inputPassword FROM " + "User", null);
        int i =0 ;
        while (cursor.moveToNext())
        {
            if (cursor.getString(i)==username)
            {
                while (cursor1.moveToNext())
                {
                    if(cursor1.getString(i)==password)
                    {
                        return true ;

                    }
                }

            }



        }
        return false ;



    }

 */



}