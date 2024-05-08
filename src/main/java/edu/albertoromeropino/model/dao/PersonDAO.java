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
    private static final String INSERT = "insert into person(NickName, DNI, Password) values (?,?,?)";
    private static final String DELETE = "Delete from person where NickName = ? and password = ?";
    private static final String UPDATE = "Update game set NickName = ?, DNI = ?, password = ?";

    @Override
    public Person store(Person entity) {
        if (entity != null) {
            String idPersontmp = entity.getNickName();
            if (idPersontmp != null) {
                Person persontmp = findID(entity.getNickName());
                if (persontmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setString(1, entity.getNickName());
                        preparedStatement.setString(2, entity.getDni());
                        preparedStatement.setString(3, entity.getPassword());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, entity.getNickName());
                        preparedStatement.setString(2, entity.getDni());
                        preparedStatement.setString(3, entity.getPassword());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return entity;
    }

    @Override
    public Person findID(String entityId) {
        Person person = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setString(1, entityId);
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
    }

    @Override
    public Person deleteEntity(Person entityDelete) {
        if (entityDelete != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setString(1, entityDelete.getNickName());
                preparedStatement.setString(2, entityDelete.getPassword());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entityDelete;
    }

    @Override
    public void close() throws IOException {

    }

    public static PersonDAO build() {
        return new PersonDAO();
    }

}
