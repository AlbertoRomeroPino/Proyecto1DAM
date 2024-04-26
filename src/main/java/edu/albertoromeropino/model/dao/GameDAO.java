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

public class GameDAO implements IDAO<Game> {
    private static final String FINDID = "select ga.id_game, ga.Name, ga.Category, ga.NickName, ga.NameCompany " +
            "from game ga " +
            "where ga.id_Game=?";
    private static final String FINDPERSON = "select ga.id_game ,ga.Name " +
            "from game ga, person pe " +
            "where ga.NickName = pe.?";
    private static final String INSERT = "insert into game(Name, Category, NickName, NameCompany) " +
            "values (?,?,?,?)";
    private static final String DELETE = "Delete from game where id_game=?";
    private static final String UPDATE = "Update game set ga.Name=?, ga.Category=?, ";


    private Connection connection;

    public GameDAO() {
        connection = ConnectionMariaDB.getConnection();
    }


    @Override
    public Game save(Game entity) {
        Game game = entity;
        if (entity != null) {
            int idGame = entity.getIdGame();
            if (idGame > 0) {
                Game gametmp = findID(idGame);
                if (gametmp == null){
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
                        preparedStatement.setString(1, entity.getName());
                        preparedStatement.setString(2, entity.getCategory());
                        preparedStatement.setString(3, entity.getPerson().getNickName());
                        preparedStatement.setString(4, entity.getCompany().getNameCompany());
                        preparedStatement.executeUpdate();
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
                        preparedStatement.setString(1,entity.getName());
                        preparedStatement.setString(2,entity.getCategory());
                        preparedStatement.executeUpdate();
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }
        }

        return game;
    }

    /**
     * Busca un juego por su Identificador
     * @param Id Se le pasa su identificador y se utiliza para buscarse
     * @return devuelve el juego completo con este identificador
     */
    @Override
    public Game findID(int Id) {
        Game game = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, Id);    //Recordatorio esto es para intercambiar con la ? de FindID
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Game gametmp = new Game();
                    gametmp.setIdGame(resultSet.getInt("idGame"));
                    gametmp.setName(resultSet.getString("Name"));
                    gametmp.setCategory(resultSet.getString("Category"));
                    gametmp.setPerson(PersonDAO.build().findByID(resultSet.getString("NickName")));
                    gametmp.setCompany(CompanyDAO.build().findByID(resultSet.getString("NameCompany")));
                    game = gametmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return game;
    }

    public Game findByPerosn(Person person) {
        Game game = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDPERSON)) {
            preparedStatement.setString(1, person.getNickName());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Game gametmp = new Game();
                    gametmp.setIdGame(resultSet.getInt("idGame"));
                    gametmp.setName(resultSet.getString("Name"));
                    game = gametmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return game;
    }

    @Override
    public Game deleteEntity(Game entityDelete) {
        if (entityDelete != null){
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
                preparedStatement.setInt(1, entityDelete.getIdGame());
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
