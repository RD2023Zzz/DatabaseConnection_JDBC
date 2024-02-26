import java.sql.*;

/**
 * Class which connects to database.
 * Contains methods to Create a table, insert data, read data from database and delete a record
 * @author Ryan Dunne
 */
public class Lab3Part4 {
    static String urlDB = "jdbc:mysql://localhost:3306/hobbies";
    static String uNameDB = "root";
    static String passDB = "123" ;

    static String dbName = "hobbies";
    static String createTableQ  = "CREATE TABLE HOBBIES"
            + "(id INT not NULL, " + " fName VARCHAR(255), "
            + " lName VARCHAR(255), " + " hobby VARCHAR(255), "
            + " PRIMARY KEY ( id ))";;


    /**
     * Each method is called in the main method
     * @param args
     * @throws SQLException Throws an exception relating to database access
     */
    public static void main(String[] args) throws SQLException {
//         createDBTable();
//         fillDBTable(1, "Ryan", "Dunne", "Programming");
//         fillDBTable(2, "John", "Doe", "Gaming");
//         readDBTable(dbName);
         deleteDBRecord(1);
    }

    /**
     *
     * @param url Database URL
     * @param uName Database Name
     * @param pass Password for database access
     * @return Returns connection to database
     */
    private static Connection connectToDB(String url, String uName, String pass) {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, uName, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * Method to create a database table
     * Query for creating a table, in this case "hobbies"
     */
    private static void createDBTable(){
        try(Connection con = connectToDB(urlDB, uNameDB, passDB)){
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement statement = con.createStatement();

            statement.executeUpdate(createTableQ);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *Fills a new record in the hobbies table by adding an ID, first name, last name and favourite hobby
     * @param ID Primary Key for a new record
     * @param fName String parameter for first name
     * @param lName String parameter for last name
     * @param hobby String parameter for hobby
     */
    private static void fillDBTable(int ID, String fName, String lName, String hobby){

        try(Connection con = connectToDB(urlDB,uNameDB,passDB)){
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement statement = con.createStatement();

            String fillTableQ = "INSERT INTO " + dbName + "(id, fName, lName, hobby) VALUES "
                    + "(" + ID + ",'" + fName +"','" + lName + "','" + hobby +"')";

            System.out.println("Attempt Insert: " + fillTableQ);

            statement.executeUpdate(fillTableQ);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method reads data from the hobbies table using a select query
     * @param tableName String for table name
     */
    private static void readDBTable(String tableName){
        try(Connection con = connectToDB(urlDB,uNameDB,passDB)){
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement statement = con.createStatement();
            String readQ = "SELECT * FROM " + tableName;

            ResultSet rs = statement.executeQuery(readQ);
            System.out.println("Attempt Read: " + readQ);

            while (rs.next()){
                System.out.println("Data from Hobbies: "
                        + rs.getInt(1)
                        + ", " + rs.getString(2) +
                        ", " + rs.getString(3) +
                        ", " + rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a record from the table using the primary key of the record
     * @param id Integer id (the primary key)
     *
     */
    private static void deleteDBRecord(int id){
        try(Connection con = connectToDB(urlDB, uNameDB, passDB)) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement statement = con.createStatement();

            String deleteRecordQ = "DELETE FROM " + dbName + " id WHERE id = " + id;
            System.out.println("Attempt delete: " + deleteRecordQ);

            statement.executeUpdate(deleteRecordQ);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }





}
