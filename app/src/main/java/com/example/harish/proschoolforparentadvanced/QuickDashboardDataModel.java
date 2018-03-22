package com.example.harish.proschoolforparentadvanced;

public class QuickDashboardDataModel {

    public String text;
    public int drawable;
    public int image;

    public QuickDashboardDataModel(String t, int d, int b)
    {
        text=t;
        drawable=d;
        image=b;
    }

    public String getText() {
        return text;
    }

    public void setText(String iconText) {
        this.text = iconText;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int image) {
        this.drawable = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int imageMain) {
        this.image = imageMain;
    }
}
