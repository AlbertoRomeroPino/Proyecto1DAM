package edu.albertoromeropino.model.dao;


import edu.albertoromeropino.model.connection.ConnectionMariaDB;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAO implements IDAO<Game, Integer> {
    private Connection connection;

    public GameDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String FINDID = "select id_game, Name, Category, NickName, NameCompany " +
            "from game " +
            "where id_Game=?";
   /* private static final String FINDPERSON = "select ga.id_game ,ga.Name " +
            "from game ga, person pe " +
            "where ga.NickName = pe.?";*/
    private static final String INSERT = "insert into game(Name, Category, NickName, NameCompany) " +
            "values (?,?,?,?)";
    private static final String DELETE = "Delete from game where id_game=?";
    private static final String UPDATE = "Update game set Name=?, Category=?";





    @Override
    public Game store(Game entity) {
        Game game = entity;
        if (entity != null) {
            int idGametmp = entity.getIdGame();
            if (idGametmp > 0) {
                Game gametmp = findID(idGametmp);
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
     * @param entityId Se le pasa su identificador y se utiliza para buscarse
     * @return devuelve el juego completo con este identificador
     */
    @Override
    public Game findID(Integer entityId) {
        Game game = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);    //Recordatorio esto es para intercambiar con la ? de FindID
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Game gametmp = new Game();
                    gametmp.setIdGame(resultSet.getInt("idGame"));
                    gametmp.setName(resultSet.getString("Name"));
                    gametmp.setCategory(resultSet.getString("Category"));
                    gametmp.setPerson(PersonDAO.build().findID(resultSet.getString("NickName")));
                    gametmp.setCompany(CompanyDAO.build().findID(resultSet.getString("NameCompany")));
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
        return entityDelete;
    }

    @Override
    public void close() throws IOException {

    }

    public static GameDAO build(){
        return new GameDAO();
    }

}
