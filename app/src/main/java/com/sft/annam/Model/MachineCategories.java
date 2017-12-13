package com.sft.annam.Model;

/**
 * Created by SFT on 26/11/2016.
 */
public class MachineCategories {
    private String name;
    private String imageURL;

    public MachineCategories(String name, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
