package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Connector {
    private static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private static String nama_db = "upnvyk";
    private static String url_db = "jdbc:mysql://localhost:3306/" + nama_db;
    private static String username_db = "rahadityaputra";
    private static String password_db = "rahadityaputra123";

    static Connection conn;
    
    // Mencoba menghubungkan program kita dengan ke database MySQL.
    public static Connection Connect() {
        try {
            // 1. Register driver yang akan dipakai
            Class.forName(jdbc_driver);
            
            // 2. Buat koneksi ke database
            conn = DriverManager.getConnection(url_db, username_db, password_db);

            // 3. Menampilkan pesan "Connection Success" jika berhasil terhubung ke database.
            System.out.println("MySQL Connected");
        } catch (ClassNotFoundException | SQLException exception) {
            // Menampilkan pesan error ketika MySQL gagal terhubung.
            System.out.println("Connection Failed: " + exception.getLocalizedMessage());
        }
        return conn;
    }
    
    public static int executeUpdate(String query, Object ...params) throws SQLException {
        try (PreparedStatement stmt = Connect().prepareStatement(query)) {
            for(int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            return stmt.executeUpdate();
        } 
    }


    public static <T extends Identitas> List<T> executeQuery(String query,Supplier<T> constructor) throws SQLException {
        List<T> results = new ArrayList<>();

        try (PreparedStatement stmt = Connect().prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                T data = constructor.get();
                data.setId(resultSet.getInt("id"));
                data.setNama(resultSet.getString("nama"));
                data.setNIMorNIDN(resultSet.getString(data.getNIMorNIDNColumnName()));
                results.add(data);
            }
            return results;
        }
    }

}
