package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.connection.ConnectionMariaDB;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.model.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO implements IDAO<Person, String> {
    private Connection connection;

    public PersonDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String FINDID = "select NickName, DNI, Password " +
            "from person " +
            "where NickName = ?";
    /*private static final String FINDLOGIN = "select NickName, Password " +
            "from person " +
            "where nickname = ? and password = ?";*/
    private static final String INSERT = "insert into person(NickName, DNI, Password) values (?,?,?)";
    private static final String DELETE = "Delete from person where NickName = ? and password = ?";
    private static final String UPDATE = "Update game set NickName = ?, DNI = ?, password = ?";

    @Override
    public Person store(Person person) {
        if (person != null) {
            String idPersontmp = person.getNickName();
            if (idPersontmp != null) {
                Person persontmp = findID(person.getNickName());
                if (persontmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setString(1, person.getNickName());
                        preparedStatement.setString(2, person.getDni());
                        preparedStatement.setString(3, person.getPassword());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, person.getNickName());
                        preparedStatement.setString(2, person.getDni());
                        preparedStatement.setString(3, person.getPassword());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return person;
    }

    @Override
    public Person findID(String nickName) {
        Person person = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setString(1, nickName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Person persontmp = new Person();
                    persontmp.setNickName(resultSet.getString("nickName"));
                    persontmp.setDni(resultSet.getString("Dni"));
                    persontmp.setPassword(resultSet.getString("Password"));
                    persontmp.setGames(GameDAO.build().findByPerson(resultSet.getString(nickName)));
                    person = persontmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
    // Este de abajo se puede modificar porque es lo mismo que el de arriba pero sin 1 campo
   /* public Person findLogin (String nickname, String password){
        Person person = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2,password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Person persontmp = new Person();
                    persontmp.setNickName(resultSet.getString("nickName"));
                    persontmp.setDni(resultSet.getString("Dni"));
                    persontmp.setPassword(resultSet.getString("Password"));
                    persontmp.setGames(GameDAO.build().findByPerson(resultSet.getString("games")));
                    person = persontmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }*/

    @Override
    public Person deleteEntity(Person person) {
        if (person != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setString(1, person.getNickName());
                preparedStatement.setString(2, person.getPassword());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return person;
    }

    @Override
    public void close() throws IOException {

    }

    public static PersonDAO build() {
        return new PersonDAO();
    }

}
