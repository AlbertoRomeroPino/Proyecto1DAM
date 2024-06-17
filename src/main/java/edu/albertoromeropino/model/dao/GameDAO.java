package edu.albertoromeropino.model.dao;



import edu.albertoromeropino.model.connection.ConnectionMariaDB;
import edu.albertoromeropino.model.entity.Archievement;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.model.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameDAO implements IDAO<Game, Integer> {
    private Connection connection;

    public GameDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String FINDID = "select id_game, Name, Category, NickName, NameCompany " +
            " from game " +
            " where id_Game=?";
    private static final String FINDBYPERSON = "select ga.id_game, ga.Name, ga.Category, ga.NameCompany, pe.nickname " +
            " from game ga, person pe " +
            " where ga.nickname = pe.nickname and pe.nickname = ?";
    private static final String FINDBYCOMPANY = "select ga.id_game, ga.Name, ga.Category, ga.NameCompany, ga.nickname " +
            " from game ga, company co " +
            " where ga.nameCompany = co.nameCompany and co.nameCompany = ?";
    private static final String FINDALL = "select id_game, Name, Category, NickName, NameCompany " +
            " from game ";
    private static final String INSERT = "insert into game(id_game, Name, Category, NickName, NameCompany) " +
            "values (?,?,?,?,?)";
    private static final String DELETE = "Delete from game where id_game=?";
    private static final String UPDATE = "Update game set Name=?, Category=? where id_game = ?";

    /**
     * Almacena o actualiza un juego
     *
     * @param entity de tipo juego
     * @return devuelve el juego almacenado
     */
    @Override
    public Game store(Game entity) {
        if (entity != null) {
            int idGametmp = entity.getIdGame();
            if (idGametmp > 0) {
                Game gametmp = findID(idGametmp);
                if (gametmp.getName() == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setInt(1, entity.getIdGame());
                        preparedStatement.setString(2, entity.getName());
                        preparedStatement.setString(3, entity.getCategory());
                        preparedStatement.setString(4, entity.getPerson().getNickName());
                        preparedStatement.setString(5, entity.getCompany().getNameCompany());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, entity.getName());
                        preparedStatement.setString(2, entity.getCategory());

                        // Caracter que va a ser el que se va a comparar
                        preparedStatement.setInt(3, entity.getIdGame());

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
        GameLazy game = new GameLazy();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);    //Recordatorio esto es para intercambiar con la ? de FindID
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    GameLazy gametmp = new GameLazy();
                    gametmp.setIdGame(resultSet.getInt("id_Game"));
                    gametmp.setName(resultSet.getString("Name"));
                    gametmp.setCategory(resultSet.getString("Category"));
                    gametmp.setPerson(PersonDAO.build().findID(resultSet.getString("NickName")));
                    gametmp.setCompany(CompanyDAO.build().findID(resultSet.getString("NameCompany")));

                    //gametmp.getArchievement();
                    // gametmp.setArchievements(storeArchievement(gametmp));

                    game = gametmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return game;
    }

    public ArrayList<Game> findAll() {
        ArrayList<Game> game = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    GameLazy gametmp = new GameLazy();
                    gametmp.setIdGame(resultSet.getInt("id_Game"));
                    gametmp.setName(resultSet.getString("Name"));
                    gametmp.setCategory(resultSet.getString("Category"));
                    gametmp.setPerson(PersonDAO.build().findID(resultSet.getString("NickName")));
                    gametmp.setCompany(CompanyDAO.build().findID(resultSet.getString("NameCompany")));

                    //gametmp.getArchievement();
                    // gametmp.setArchievements(storeArchievement(gametmp));

                    game.add(gametmp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return game;
    }

    /**
     * Busca un juego por el id de la persona
     *
     * @param nickNamePerson el identificador de la persona
     * @return Una lista de juegos
     */
    public ArrayList<Game> findByPerson(String nickNamePerson) {
        ArrayList<Game> games = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDBYPERSON)) {
            preparedStatement.setString(1, nickNamePerson);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    GameLazy game = new GameLazy();
                    game.setIdGame(resultSet.getInt("Id_Game"));
                    game.setName(resultSet.getString("Name"));
                    game.setCategory(resultSet.getString("Category"));
                    game.setCompany(CompanyDAO.build().findID(resultSet.getString("nameCompany")));
                    game.setPerson(Person.getPerson());

                    // Esto ya no esta en la tabla game
                    //game.setArchievements(ArchievementDAO.build().findByIdGame(game.getIdGame()));
                    games.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }

    /**
     * Busca un juego por la compañia que lo a creado
     *
     * @param nameCompany El identificador de la compañia
     * @return una lista de juegos
     */
    public ArrayList<Game> findByCompany(String nameCompany) {
        ArrayList<Game> games = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDBYCOMPANY)) {
            preparedStatement.setString(1, nameCompany);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    GameLazy game = new GameLazy();
                    game.setIdGame(resultSet.getInt("Id_Game"));
                    game.setName(resultSet.getString("Name"));
                    game.setCategory(resultSet.getString("Category"));
                    game.setCompany(CompanyDAO.build().findID(resultSet.getString("nameCompany")));
                    game.setPerson(PersonDAO.build().findID(resultSet.getString("nickname")));

                    // Esto ya no esta en la tabla game
                    //game.setArchievements(ArchievementDAO.build().findByIdGame(game.getIdGame()));
                    games.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }

    /**
     * Borra un juego en la base de datos
     *
     * @param entityDelete el juego que se va a borrar
     * @return el juego que se a borrado
     */
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

}

class GameLazy extends Game {
    @Override
    public ArrayList<Archievement> getArchievements() {
        if (super.getArchievements() == null) {
            setArchievements(ArchievementDAO.build().findByIdGame(this.getIdGame()));
        }
        return super.getArchievements();
    }
}