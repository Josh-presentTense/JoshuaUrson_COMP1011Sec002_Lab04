package utilities;

import models.Game;
import models.Player;
import models.PlayerAndGame;

import java.sql.*;
import java.util.ArrayList;

public class DBConn {
    // ADD CONNECTION INFO TO RUN
    private static String user = "root";
    private static String password = "Josh@9891";
    private static String connString = "jdbc:mysql://localhost:3306/comp1011lab04";

    /**
     * This method pulls all the games from the games table in the DB
     * All pulled data is placed in a Game object, and stored on an Arraylist
     * @return : ArrayList
     * @throws SQLException
     */
    public static ArrayList<Game> getGamesFromDB() throws SQLException {
        ArrayList<Game> listOfGames = new ArrayList<>(); // Holds the Game objects from the games table of the DB
        String queryString = "SELECT * FROM games"; // Query that will be run on the DB

        try (
                Connection conn = DriverManager.getConnection(connString, user, password); // Connect to DB
                Statement statement = conn.createStatement(); // Create a statement object
                ResultSet resultSet = statement.executeQuery(queryString); // Create / execute the query
        ) {
            // Parse through the content of the games table
            while (resultSet.next()) {
                // Add content from the games table to create Game objects
                Game pulledGame = new Game(
                        resultSet.getInt("game_id"),
                        resultSet.getString("game_title")
                );
                listOfGames.add(pulledGame); // add the newly created Game object to the arraylist
            }
        } catch (SQLException e) {
            System.out.println(String.format("Database access issue: %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("Exception caught: %s", e.getMessage()));
        }
        return listOfGames;
    }

    /**
     * This method pulls all the games from the players table in the DB
     * All pulled data is placed in a Player object, and stored on an Arraylist
     * @return : ArrayList
     * @throws SQLException
     */
    public static ArrayList<Player> getPlayersFromDB() throws SQLException {
        ArrayList<Player> listOfPlayers = new ArrayList<>(); // Holds the Player objects from the players table of the DB
        String queryString = "SELECT * FROM players"; // Query that will be run on the DB

        try (
                Connection conn = DriverManager.getConnection(connString, user, password); // Connect to DB
                Statement statement = conn.createStatement(); // Create a statement object
                ResultSet resultSet = statement.executeQuery(queryString); // Create / execute the query
        ) {
            // Parse through the content of the players table
            while (resultSet.next()) {
                // Add content from the players table to create Player objects
                Player pulledPlayer = new Player(
                        resultSet.getInt("player_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("province"),
                        resultSet.getLong("phone_number")
                );
                listOfPlayers.add(pulledPlayer); // add the newly created Player object to the arraylist
            }
        } catch (SQLException e) {
            System.out.println(String.format("Database access issue: %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("Exception caught: %s", e.getMessage()));
        }
        return listOfPlayers;
    }

    /**
     * This method pulls all the players_and_games data from the players_and_games table in the DB
     * All pulled data is placed in a PlayerAndGame object, and stored on an Arraylist
     * @return : ArrayList
     * @throws SQLException
     */
    public static ArrayList<PlayerAndGame> getPlayersAndGamesFromDB() throws SQLException {
        ArrayList<PlayerAndGame> listOfPlayersAndGames = new ArrayList<>(); // Holds the PlayerAndGame objects from the players_and_games table of the DB
        String queryString = "SELECT * FROM players_and_games"; // Query that will be run on the DB

        try (
                Connection conn = DriverManager.getConnection(connString, user, password); // Connect to DB
                Statement statement = conn.createStatement(); // Create a statement object
                ResultSet resultSet = statement.executeQuery(queryString); // Create / execute the query
        ) {
            // Parse through the content of the players_and_games table
            while (resultSet.next()) {
                // Add content from the players_and_games table to create PlayerAndGame objects
                PlayerAndGame pulledPlayerAndGame = new PlayerAndGame(
                        resultSet.getInt("player_game_id"),
                        resultSet.getInt("game_id"),
                        resultSet.getInt("player_id"),
                        resultSet.getString("playing_date"),
                        resultSet.getString("score")
                );
                listOfPlayersAndGames.add(pulledPlayerAndGame); // add the newly created PlayerAndGame object to the arraylist
            }
        } catch (SQLException e) {
            System.out.println(String.format("Database access issue: %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("Exception caught: %s", e.getMessage()));
        }
        return listOfPlayersAndGames;
    }
}
