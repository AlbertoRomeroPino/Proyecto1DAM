package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.connection.ConnectionMariaDB;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.model.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class PersonDAO implements IDAO<Person, String> {
    private Connection connection;

    public PersonDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String FINDID = "select Nickname, Dni, password from person where nickname = ?";
    private static final String INSERT = "insert into person (Nickname, DNI, Password) values (?,?,?)";
    private static final String UPDATE = "update person set dni =?, Password = ? where nickname = ?";
    private static final String DELETE = "delete from person where nickname = ? and password = ?";

    @Override
    public Person store(Person person) {
        if (person != null) {
            String idPersontmp = person.getNickName();
            if (idPersontmp != null) {
                Person persontmp = findID(person.getNickName());
                if (persontmp.getNickName() == null && persontmp.getPassword() == null) {
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
                        //El primero es lo que esta en el where
                        preparedStatement.setString(3, person.getNickName());
                        preparedStatement.setString(1, person.getDni());
                        preparedStatement.setString(2, person.getPassword());

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
                    persontmp.setGames(GameDAO.build().findByPerson(resultSet.getString("nickName")));

                    person = persontmp;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

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
