package com.example.shareva.Model;

public class ForYouItems {

    private int id;
    private int image;
    private String itemName;
    private String itemDesc;
    private String availability;
    private String moreDesc;

    public ForYouItems(int id, int image, String itemName, String itemDesc, String availability, String moreDesc) {
        this.id = id;
        this.image = image;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.availability = availability;
        this.moreDesc = moreDesc;
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

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getMoreDesc() {
        return moreDesc;
    }

    public void setMoreDesc(String moreDesc) {
        this.moreDesc = moreDesc;
    }
}
