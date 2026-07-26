package com.contactbook.contactbook.dao;

import com.contactbook.contactbook.database.DatabaseConnection;
import com.contactbook.contactbook.exceptions.DatabaseException;
import com.contactbook.contactbook.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private DatabaseConnection dbConnection;
    public UserDao(){
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public User addUser(User user){
        String query = "INSERT INTO users(fullname, cellno, email) VALUES (?, ?, ?)";

        try(Connection connection = dbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, user.getFullname());
            stmt.setString(2, user.getCellno());
            stmt.setString(3, user.getEmail());

            int rowsInserted = stmt.executeUpdate();

            if(rowsInserted > 0){
                try(ResultSet genderatedKeys = stmt.getGeneratedKeys()){
                    if(genderatedKeys.next()){
                        int genderatedId = genderatedKeys.getInt(1);
                        user.setId(genderatedId);
                        return user;
                    }
                }
            }

        }catch (SQLException e){
            if(e.getMessage().contains("Duplicate entry")){
                throw new DatabaseException("Username or email already exists");
            }throw new DatabaseException("Error registering user: " + e.getMessage());
        }
        return null;
    }

    public User findByName(String fullname){
        String query = "SELECT * FROM users WHERE fullname = ?";

        try(Connection connection = dbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, fullname);
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFullname(resultSet.getString("fullname"));
                user.setCellno(resultSet.getString("cellno"));
                user.setEmail(resultSet.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while getting user by full name. " + e.getMessage());
        }
        return null;
    }

    public User findByCellNo(String cellno){
        String query = "SELECT * FROM users WHERE cellno = ?";

        try(Connection connection = dbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, cellno);
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFullname(resultSet.getString("fullname"));
                user.setCellno(resultSet.getString("cellno"));
                user.setEmail(resultSet.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while getting user by full name. " + e.getMessage());
        }
        return null;
    }

    public List<User> findAll(){
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try(Connection connection = dbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFullname(resultSet.getString("fullname"));
                user.setCellno(resultSet.getString("cellno"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        }catch(SQLException e){
            throw new DatabaseException("Error while getting all users. " + e.getMessage());
        }
        return null;
    }

    public boolean update(User user){
        String query = "UPDATE users SET fullname = ?, cellno = ?, email = ? WHERE id = ?";

        try(Connection connection = dbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, user.getFullname());
            stmt.setString(2, user.getCellno());
            stmt.setString(3, user.getEmail());

            int rowsUpdated = stmt.executeUpdate();
            return (rowsUpdated > 0);
        }catch(SQLException e){
            throw new DatabaseException("Error while updating user. " + e.getMessage());
        }
    }

    public boolean delete(int id){
        String query = "DELETE FROM users WHERE id = ?";

        try(Connection connection = dbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return (rowsDeleted > 0);
        }catch(SQLException e){
            throw new DatabaseException("Error while deleting user. " + e.getMessage());
        }
    }

    public boolean deleteAll(){
        String query = "TRUNCATE TABLE users";

        try(Connection connection = dbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.executeUpdate();
            return true;
        }catch(SQLException e){
            throw new DatabaseException("Error while deleting all users. " + e.getMessage());
        }
    }
}
