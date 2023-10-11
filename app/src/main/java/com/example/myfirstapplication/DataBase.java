package com.example.myfirstapplication;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private ArrayList<Book> books = new ArrayList<>();

    public DataBase(){
        books.add(new Book("Android", "Alaa", "Programing"));
        books.add(new Book("DataBase", "Ayman", "DataBase"));
        books.add(new Book("Python", "Ahmad", "Programing"));
        books.add(new Book("HTML 5", "Ali", "Web"));


    }

    public List<Book> getBooks(String cat){
        ArrayList<Book> result = new ArrayList<>();
        for(Book b:books){
            if(b.getCatagory().equals(cat)){
                result.add(b);
            }
        }
        return result;
    }
}
