package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Item {


    private int id;
    private static final int MAX_CONTENT_LENGTH = 1000;
    private String content;
    private String name;
    private LocalDateTime dateCreate;

    public Item(int id, String content, String name, LocalDateTime dateCreate) {
        this.id = id;
        setContent(content);
        this.name = name;
        this.dateCreate = dateCreate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content.length() > MAX_CONTENT_LENGTH) {
            this.content = content.substring(0, MAX_CONTENT_LENGTH);
        } else {
            this.content = content;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static boolean isStringContentValid(Item item) {
        if (item != null) {
            if (item.getContent().length() <= MAX_CONTENT_LENGTH) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                ", dateCreate=" + dateCreate +
                '}';
    }
}
