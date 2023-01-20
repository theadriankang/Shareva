package com.example.shareva.Model;

public class ForYouItems {

    private int id;
    private int image;
    private String itemName;
    private String itemDesc;
    private Boolean availability;

    public ForYouItems(int id, int image, String itemName, String itemDesc, Boolean availability) {
        this.id = id;
        this.image = image;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.availability = availability;
    }

    public ForYouItems() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
