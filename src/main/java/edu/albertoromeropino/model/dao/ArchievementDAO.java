package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.connection.ConnectionMariaDB;
import edu.albertoromeropino.model.entity.Archievement;
import edu.albertoromeropino.model.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArchievementDAO implements IDAO<Archievement, Integer> {
    private Connection connection;

    public ArchievementDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String FINDID = "select Id_Archievement, ArchievementName, DescriptionArchievement, HelpArchievement, Id_Game" +
            " from archievement where Id_Archievement = ?";
    private static final String INSERT = "insert into archievement (ArchievementName, DescriptionArchievement, HelpArchievement, Id_Game) " +
            "values (?,?,?,?,?)";
    private static final String DELETE = "Delete from archievement where Id_archievement = ?";
    private static final String UPDATE = "Update archievement set ArchievementName=?, DescriptionArchievement=?, " +
            "HelpArchievement=?, Id_Game=?";

    @Override
    public Archievement store(Archievement entity) {
        if (entity != null) {
            int idArchievementtmp = entity.getIdArchievement();
            if (idArchievementtmp > 0) {
                Archievement archievementtmp = findID(idArchievementtmp);
                if (archievementtmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setString(1, entity.getArchievementName());
                        preparedStatement.setString(2, entity.getDescriptionArchievement());
                        preparedStatement.setString(3, entity.getHelpArchievement());
                        preparedStatement.setInt(4, entity.getGame().getIdGame());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, entity.getArchievementName());
                        preparedStatement.setString(2, entity.getDescriptionArchievement());
                        preparedStatement.setString(3, entity.getHelpArchievement());
                        preparedStatement.setInt(4, entity.getGame().getIdGame());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return entity;
    }

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
                    archievementtmp.setDescriptionArchievement(resultSet.getString("DescriptionArchievement"));
                    archievementtmp.setHelpArchievement(resultSet.getString("HelpArchievement"));
                    archievementtmp.setGame(GameDAO.build().findID(resultSet.getInt("idGame")));
                    archievement = archievementtmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return archievement;
    }

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
}
