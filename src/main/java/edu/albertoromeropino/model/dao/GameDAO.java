package edu.albertoromeropino.model.dao;


import edu.albertoromeropino.model.connection.ConnectionMariaDB;
import edu.albertoromeropino.model.entity.Archievement;
import edu.albertoromeropino.model.entity.Company;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.model.interfaces.IDAO;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameDAO implements IDAO<Game, Integer> {
    private Connection connection;

    public GameDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String FINDID = "select id_game, Name, Category, NickName, NameCompany " +
            "from game " +
            "where id_Game=?";
    private static final String FINDBYPERSON = "select ga.id_game, ga.Name, ga.Category, ga.NameCompany " +
            "from game ga, person pe " +
            "where ga.nickname = pe.nickname and pe.nickname = ?";
    private static final String INSERT = "insert into game(Name, Category, NickName, NameCompany) " +
            "values (?,?,?,?)";
    private static final String DELETE = "Delete from game where id_game=?";
    private static final String UPDATE = "Update game set Name=?, Category=?";


    @Override
    public Game store(Game entity) {
        if (entity != null) {
            int idGametmp = entity.getIdGame();
            if (idGametmp > 0) {
                Game gametmp = findID(idGametmp);
                if (gametmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setString(1, entity.getName());
                        preparedStatement.setString(2, entity.getCategory());
                        preparedStatement.setString(3, entity.getPerson().getNickName());
                        preparedStatement.setString(4, entity.getCompany().getNameCompany());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, entity.getName());
                        preparedStatement.setString(2, entity.getCategory());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return entity;
    }

    /**
     * Busca un juego por su Identificador
     *
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

    public Set<Game> findByPerson(String nickNamePerson){
        Set<Game> games = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDBYPERSON)){
            preparedStatement.setString(1,nickNamePerson);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setNickName(nickNamePerson);

                Game game = new Game();
                game.setIdGame(resultSet.getInt("Id_Game"));
                game.setName(resultSet.getString("Name"));
                game.setCategory(resultSet.getString("Category"));

                game.setPerson(person);
                //Meto los logros
                Set<Archievement> archievements = storeArchievement(game);
                game.setArchievements(archievements);
                //Meto las companias
                game.setCompany(CompanyDAO.build().findID(resultSet.getString("nameCompany")));

                games.add(game);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return games;
    }

    @Override
    public Game deleteEntity(Game entityDelete) {
        if (entityDelete != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setInt(1, entityDelete.getIdGame());
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

    public static GameDAO build() {
        return new GameDAO();
    }

    public Set<Archievement> storeArchievement(Game game){
        Set<Archievement> archievements = new HashSet<>();
        try {
            archievements = ArchievementDAO.build().findByIdGame(game.getIdGame());
        }catch (Exception e){
            e.printStackTrace();
        }
        return archievements;
    }

}