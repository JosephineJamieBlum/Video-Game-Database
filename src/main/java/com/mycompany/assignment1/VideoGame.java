/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment1;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Jamie
 */
public class VideoGame {
    
    @SerializedName("title")
    private String title;
    
    @SerializedName("price")
    private double price;
    
    @SerializedName("esrb")
    private String esrb;
    
    /**
     * returns a copy of the title
     * @return title of game
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * returns the price of the game
     * @return price of game 
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * returns the esrb
     * @return the esrb of the game
     */
    public String getEsrb() {
        return esrb;
    }
    
    /**
     * changes the title of the game
     * @param newTitle the game's new title
     */
    public void setTitle(String newTitle) {
        title = newTitle;
    }
    
    /**
     * changes the price of the game
     * @param newPrice the game's new price
     */
    public void setPrice(double newPrice) {
        price = newPrice;
    }
   
    /**
     * changes the game's esrb rating
     * @param newEsrb the game's new esrb rating
     */
    public void setEsrb(String newEsrb) {
        esrb = newEsrb;
    }
}
