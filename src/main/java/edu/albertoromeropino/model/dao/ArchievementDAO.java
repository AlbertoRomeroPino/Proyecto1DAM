package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.connection.ConnectionMariaDB;
import edu.albertoromeropino.model.entity.Archievement;

import edu.albertoromeropino.model.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class ArchievementDAO implements IDAO<Archievement, Integer> {
    private Connection connection;

    public ArchievementDAO() {
        connection = ConnectionMariaDB.getConnection();
    }


    private static final String FINDID = "select Id_Archievement, ArchievementName, DescriptionArchievement, HelpArchievement, Id_Game" +
            " from archievement " +
            " where Id_Archievement = ?";
    private static final String FINDBYIDGAME = "select Id_archievement, ArchievementName, DescriptionArchievement, HelpArchievement, ar.id_game" +
            " from archievement ar, game ga " +
            " where ar.id_game = ga.id_game and ga.id_game = ?";
    private static final String INSERT = "insert into archievement (Id_Archievement, ArchievementName, DescriptionArchievement, HelpArchievement, Id_Game) " +
            "values (?,?,?,?,?)";
    private static final String DELETE = "Delete from archievement where Id_archievement = ?";
    private static final String UPDATE = "Update archievement set ArchievementName=?, DescriptionArchievement=?, " +
            "HelpArchievement=? where Id_Game=?";

    /**
     * Almacena o actualiza datos en la base de datos
     *
     * @param entity de tipo logro
     * @return El logro que a sido actualizado o insertado
     */
    @Override
    public Archievement store(Archievement entity) {
        if (entity != null) {
            int idArchievementtmp = entity.getIdArchievement();
            if (idArchievementtmp > 0) {
                Archievement archievementtmp = findID(idArchievementtmp);
                if (archievementtmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setInt(1, entity.getIdArchievement());
                        preparedStatement.setString(2, entity.getArchievementName());
                        preparedStatement.setString(3, entity.getDescriptionArchievement());
                        preparedStatement.setString(4, entity.getHelpArchievement());
                        preparedStatement.setInt(5, entity.getGame().getIdGame());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        //Se va a comparar con la id
                        preparedStatement.setInt(4, entity.getIdArchievement());
                        preparedStatement.setString(1, entity.getArchievementName());
                        preparedStatement.setString(2, entity.getDescriptionArchievement());
                        preparedStatement.setString(3, entity.getHelpArchievement());
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
     * Busca un logro por su id
     *
     * @param entityId Se le pasa una variable numerica que es el identificador del logro
     * @return devuelve un logro
     */
    @Override
    public Archievement findID(Integer entityId) {
        Archievement archievement = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Archievement archievementtmp = new Archievement();
                    archievementtmp.setIdArchievement(resultSet.getInt("Id_Archievement"));
                    archievementtmp.setArchievementName(resultSet.getString("ArchievementName"));
                    archievementtmp.setDescriptionArchievement(resultSet.getString("descriptionArchievement"));
                    archievementtmp.setHelpArchievement(resultSet.getString("HelpArchievement"));
                    archievementtmp.setGame(GameDAO.build().findID(resultSet.getInt("id_Game")));
                    archievement = archievementtmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return archievement;
    }

    /**
     * Busca un logro por el juego que lo identifica
     *
     * @param gameId Es el identificador de el juego
     * @return una lista de logros
     */
    public ArrayList<Archievement> findByIdGame(Integer gameId) {
        ArrayList<Archievement> archievements = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDBYIDGAME)) {
            preparedStatement.setInt(1, gameId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Archievement archievementtmp = new Archievement();
                    archievementtmp.setIdArchievement(resultSet.getInt("Id_Archievement"));
                    archievementtmp.setArchievementName(resultSet.getString("ArchievementName"));
                    archievementtmp.setDescriptionArchievement(resultSet.getString("descriptionArchievement"));
                    archievementtmp.setHelpArchievement(resultSet.getString("HelpArchievement"));
                    archievementtmp.setGame(GameDAO.build().findID(resultSet.getInt("id_Game")));
                    archievements.add(archievementtmp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return archievements;
    }

    /**
     * Borra un logro
     *
     * @param entityDelete es el logro que deseas borrar
     * @return el logro que se a eliminado
     */
    @Override
    public Archievement deleteEntity(Archievement entityDelete) {
        if (entityDelete != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setInt(1, entityDelete.getIdArchievement());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    @Override
    public void close() throws IOException {

    }

    public static ArchievementDAO build() {
        return new ArchievementDAO();
    }
}