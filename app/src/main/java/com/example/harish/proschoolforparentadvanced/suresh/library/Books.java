package com.example.harish.proschoolforparentadvanced.suresh.library;

/**
 * Created by JANI on 03-06-2017.
 */

public class Books {

    private String id, book_id, book_title, author_name, book_price, qty, rack_number, inward_date, book_description, subject;

    public Books(String id, String book_id, String book_title, String author_name, String book_price, String qty, String rack_number,
                 String inward_date, String book_description, String subject) {
        this.id = id;
        this.book_id = book_id;
        this.book_title = book_title;
        this.author_name = author_name;
        this.book_price = book_price;
        this.qty = qty;
        this.rack_number = rack_number;
        this.inward_date = inward_date;
        this.book_description = book_description;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getBook_price() {
        return book_price;
    }

    public void setBook_price(String book_price) {
        this.book_price = book_price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getRack_number() {
        return rack_number;
    }

    public void setRack_number(String rack_number) {
        this.rack_number = rack_number;
    }

    public String getInward_date() {
        return inward_date;
    }

    public void setInward_date(String inward_date) {
        this.inward_date = inward_date;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
