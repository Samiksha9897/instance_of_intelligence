package client_sharenow;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFetch implements Serializable {

    private UserFetchRequest userFetchRequest;
    public UserFetch(){}
    public List<User> fetch(){
        List<User> userList=new ArrayList<>();
        String query="Select * from user_registration;";
        User user;
        try {
            PreparedStatement preparedStatement= Main.connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            extract(userList, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private void extract(List<User> userList, ResultSet resultSet) throws SQLException {
        User user;
        while (resultSet.next()) {
            user = new User();
            user.setFullName(resultSet.getString(1));
            user.setUsername(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setContact(resultSet.getString(4));
            userList.add(user);
        }
    }

    public List<User> fetch(String search){
        List<User> userList=new ArrayList<>();
        String query="Select * from user_registration where full_name LIKE ? || username LIKE ?;";
        User user;
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,"%"+search+"%");
            preparedStatement.setString(2,"%"+search+"%");
            ResultSet resultSet=preparedStatement.executeQuery();
            extract(userList, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

}