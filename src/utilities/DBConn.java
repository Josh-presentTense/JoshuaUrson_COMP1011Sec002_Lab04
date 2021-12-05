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

    /**
     * This method pulls all the players_and_games, games, and players tables data from the DB using
     * the game or player id
     * @param id_type : String
     * @param id : int
     * @return : ArrayList
     * @throws SQLException
     */
    public static ArrayList<String> getRecordsBySpecifiedIdFromDB(String id_type, int id) throws SQLException {
        ArrayList<String> recordsList = new ArrayList<>(); // Holds the PlayerAndGame objects from the players_and_games table of the DB
        String queryString = ""; // Query that will be run on the DB
        String pulledRecord; // Holds the record pulled from the DB

        if (id_type.equals("game_id"))
            queryString = String.format(
                    "SELECT PG.player_game_id, G.game_id, G.game_title, PG.score, PG.playing_date, P.player_id, P.first_name, P.last_name " +
                            "FROM players_and_games AS PG " +
                            "JOIN players AS P " +
                            "ON PG.player_id = P.player_id " +
                            "JOIN games as G " +
                            "ON PG.game_id = G.game_id " +
                            "WHERE G.%s = %d;", id_type, id);
        else if (id_type.equals("player_id"))
            queryString = String.format(
                    "SELECT PG.player_game_id, P.player_id, P.first_name, P.last_name, G.game_id, G.game_title, PG.score, PG.playing_date " +
                            "FROM players_and_games AS PG " +
                            "JOIN players AS P " +
                            "ON PG.player_id = P.player_id " +
                            "JOIN games as G " +
                            "ON PG.game_id = G.game_id " +
                            "WHERE P.%s = %d;", id_type, id);

        try (
                Connection conn = DriverManager.getConnection(connString, user, password); // Connect to DB
                Statement statement = conn.createStatement(); // Create a statement object
                ResultSet resultSet = statement.executeQuery(queryString); // Create / execute the query
        ) {
            if (id_type.equals("game_id")) {
                // Parse through the content of the joined tables
                while (resultSet.next()) {
                    pulledRecord = String.format(
                            "|RecordID: %d| : |GameID: %d| %s - Score: %s, Achieved on: %s - By |PlayerID: %d| %s %s",
                            resultSet.getInt("PG.player_game_id"),
                            resultSet.getInt("G.game_id"),
                            resultSet.getString("G.game_title"),
                            resultSet.getString("PG.score"),
                            resultSet.getString("PG.playing_date"),
                            resultSet.getInt("P.player_id"),
                            resultSet.getString("P.first_name"),
                            resultSet.getString("P.last_name")
                    );
                    recordsList.add(pulledRecord); // Add record to the String arraylist
                }
            } else if (id_type.equals("player_id")) {
                // Parse through the content of the joined tables
                while (resultSet.next()) {
                    pulledRecord = String.format(
                            "|RecordID: %d| : |PlayerID: %d| %s %s - |GameID: %d| %s - Score: %s, Achieved on: %s",
                            resultSet.getInt("PG.player_game_id"),
                            resultSet.getInt("P.player_id"),
                            resultSet.getString("P.first_name"),
                            resultSet.getString("P.last_name"),
                            resultSet.getInt("G.game_id"),
                            resultSet.getString("G.game_title"),
                            resultSet.getString("PG.score"),
                            resultSet.getString("PG.playing_date")
                    );
                    recordsList.add(pulledRecord); // Add record to the String arraylist
                }
            }
        } catch (SQLException e) {
            System.out.println(String.format("Database access issue: %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("Exception caught: %s", e.getMessage()));
        }
        return recordsList;
    }

    /**
     * This method will insert a new row into the games table of the DB
     * @param gameName : String
     * @throws SQLException
     */
    public static void insertGameIntoDB(String gameName) throws SQLException {
        String queryString = String.format("INSERT INTO games (game_title) VALUES ('%s');", gameName);

        try (
                Connection conn = DriverManager.getConnection(connString, user, password); // Connect to DB
                PreparedStatement preparedStatement = conn.prepareStatement(queryString); // Create a statement object
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(String.format("Database access issue: %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("Exception caught: %s", e.getMessage()));
        }
    }

    /**
     * This method will insert a new row into the players table of the DB
     * @param firstName : String
     * @param lastName : String
     * @param address : String
     * @param postalCode : String
     * @param province : String
     * @param phoneNum : String
     * @throws SQLException
     */
    public static void insertPlayerIntoDB(String firstName, String lastName, String address, String postalCode, String province, String phoneNum) throws SQLException {
        String queryString = String.format("INSERT INTO players (" +
                "first_name, " +
                "last_name, " +
                "address, " +
                "postal_code, " +
                "province, " +
                "phone_number" +
                ") VALUES ('%s', '%s', '%s', '%s', '%s', %s);", firstName, lastName, address, postalCode, province, phoneNum);

        try (
                Connection conn = DriverManager.getConnection(connString, user, password); // Connect to DB
                PreparedStatement preparedStatement = conn.prepareStatement(queryString); // Create a statement object
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(String.format("Database access issue: %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("Exception caught: %s", e.getMessage()));
        }
    }

    /**
     * This method will insert a new row into the players_and_games table of the DB
     * @param gameId : int
     * @param playerId : int
     * @param playingDate : String
     * @param score : String
     * @throws SQLException
     */
    public static void insertRecordIntoDB(int gameId, int playerId, String playingDate, String score) throws SQLException {
        String queryString = String.format("INSERT INTO players_and_games (" +
                "game_id, " +
                "player_id, " +
                "playing_date, " +
                "score" +
                ") VALUES (%d, %d, '%s', '%s');", gameId, playerId, playingDate, score);

        try (
                Connection conn = DriverManager.getConnection(connString, user, password); // Connect to DB
                PreparedStatement preparedStatement = conn.prepareStatement(queryString); // Create a statement object
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(String.format("Database access issue: %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("Exception caught: %s", e.getMessage()));
        }
    }
}
