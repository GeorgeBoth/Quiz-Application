package repository;

import org.sqlite.SQLiteDataSource;
import java.sql.*;
import Domain.Meal;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    List<Meal> storage=new ArrayList<>();
    protected String filename;
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public Repository(String filename) {
        this.filename = filename;
        getData();
    }
    public void getData(){
        try{
            openConnection();
            this.storage=new ArrayList<>();
            String selectString = "SELECT * FROM " + this.filename + ";";
            try(PreparedStatement ps=conn.prepareStatement(selectString)){
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    String name=rs.getString("name");
                    Integer time=rs.getInt("time");
                    String ingredients =rs.getString("ingredients");
                    Meal m=new Meal(name , time , ingredients);
                    storage.add(m);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void add(Meal item){
        try{
            openConnection();
            String insertString = "INSERT INTO " + filename + " VALUES(?,?,?);";
            try(PreparedStatement ps=conn.prepareStatement(insertString)){
                ps.setString(1,item.getName());
                ps.setInt(2,item.getTime());
                ps.setString(3,item.getIngredients());
                ps.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Meal> getAll(){
        return storage;
    }
}
