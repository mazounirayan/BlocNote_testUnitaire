package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;

public class Todolist {


    private int id;

    private User user;
    private ArrayList<Item> listItem;

    private EmailSenderService emailSenderService;


    public Todolist(int id, User user, ArrayList<Item> listItem, EmailSenderService emailSenderService) {
        this.id = id;
        this.user = user;
        this.listItem = listItem;
        this.emailSenderService = emailSenderService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Item> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<Item> listItem) {
        this.listItem = listItem;
    }

    public boolean isFull(Todolist todolist){
        int size = todolist.listItem.size();
        if(size==10){
            return true;
        }
        return false;
    }



    public boolean addItem(Item item){
        if(isFull(this)){
            return false;
        }

        if(this.listItem.isEmpty()){
            item.setDateCreate(LocalDateTime.now());
            this.listItem.add(item);
        }else{

            for (Item value : this.listItem) {
                long differenceEnMinutes = ChronoUnit.MINUTES.between(value.getDateCreate(), LocalDateTime.now());
                String nomTest = value.getName();

                if (differenceEnMinutes < 30) {
                    return false;
                }
                if(nomTest.equals(item.getName())){
                    return false;
                }

            }
        }

        int size = this.listItem.size();

        if(size > 7){
            emailSenderService.sendMail(this.user.getMail());
        }

        return true;
    }


    public boolean save(Todolist todolist) {throw new IllegalArgumentException();}




    @Override
    public String toString() {
        return "Todolist{" +
                "id=" + id +
                ", listItem=" + listItem +
                '}';
    }
}
