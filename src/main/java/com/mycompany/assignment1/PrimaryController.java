package com.mycompany.assignment1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PrimaryController {
    
    @FXML
    private TextField numGames;

    @FXML
    private ListView listViewGames;

    private ObservableList<String> games;

    /**
     * sets up a connection to the access database
     *
     * @return a connection to the access database
     * @throws SQLException
     */
    private Connection connectToAccess() throws SQLException {
        String databaseURL = "";
        Connection conn;

        databaseURL = "jdbc:ucanaccess://.//VideoGames.accdb";
        conn = DriverManager.getConnection(databaseURL);
        return conn;
    }

    /**
     * inserts data from videogame array vg into access database
     *
     * @param vg array of videogame objects
     */
    private void loadIntoAccess(VideoGame[] vg) {
        try {
            Connection conn = connectToAccess();

            //clears previous data from database
            String sql = "DELETE FROM VideoGames";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql);
            preparedStatement1.executeUpdate();

            //loop to insert each videogame object into the access database
            for (VideoGame vg1 : vg) {
                sql = "INSERT INTO VideoGames (Title, Price, esrb) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, vg1.getTitle());
                preparedStatement.setDouble(2, vg1.getPrice());
                preparedStatement.setString(3, vg1.getEsrb());
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    System.out.println("Row inserted");
                }
            }
        } catch (SQLException e) {
        }
    }

    /**
     * closes the program
     */
    @FXML
    protected void close() {
        Platform.exit();
    }

    /**
     * loadJSON loads data from json file into access database all previous data
     * from access database is removed
     */
    @FXML
    protected void loadJSON() {
        //creates needed gson objects
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        
        //reads in data from json file into videogame array e
        try {
            FileReader fr = new FileReader("VideoGames.json");
            VideoGame[] e = gson.fromJson(fr, VideoGame[].class
            );
            loadIntoAccess(e);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * prints games to the list view and prints number of games
     * @param e 
     */
    private void addGamesToListView(ArrayList<VideoGame> e) {
        games = listViewGames.getItems();
        games.clear();
        for (int i = 0; i < e.size(); i++) {
            games.add(e.get(i).getTitle() + ", " + e.get(i).getPrice() + ", " + e.get(i).getEsrb());
        }
        numGames.setPromptText(Integer.toString(e.size()));
    }

    /**
     * loads games from the access database into a videogame arraylist
     */
    @FXML
    protected void loadGamesFromAccess() {
        try {
            String tableName = "VideoGames";
            Connection conn = connectToAccess();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select * from " + tableName);
            ArrayList<VideoGame> vg = new ArrayList<>();
            VideoGame temp;
            
            //loads in each videogame object from resultset
            while (result.next()) {
                temp = new VideoGame();
                temp.setTitle(result.getString("Title"));
                temp.setPrice(result.getDouble("Price"));
                temp.setEsrb(result.getString("esrb"));
                vg.add(temp);
            }
            
            addGamesToListView(vg);
            
            
        } catch (SQLException except) {
            except.printStackTrace();
        }
    }
}
