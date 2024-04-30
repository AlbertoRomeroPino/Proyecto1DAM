package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.connection.ConnectionMariaDB;
import edu.albertoromeropino.model.entity.Archievement;
import edu.albertoromeropino.model.interfaces.IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArchievementDAO implements IDAO<Archievement, Integer> {
    private Connection connection;

    public ArchievementDAO(){
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String FINDID = "select Id_Archievement, ArchievementName, DescriptionArchievement, HelpArchievement, Id_Game" +
            " from archievement where Id_Archievement = ?";
    private static final String INSERT = "insert into archievement (Id_Archievement, ArchievementName, DescriptionArchievement, HelpArchievement, Id_Game) " +
            "values (?,?,?,?,?)";
    private static final String DELETE = "Delete from archievement where Id_archievement = ?";
    private static final String UPDATE = "Update archievement set Id_Archievement=?, ArchievementName=?, DescriptionArchievement=?, " +
            "HelpArchievement=?, Id_Game=?";

    @Override
    public Archievement store(Archievement entity) {

        return null;
    }

    @Override
    public Archievement findID(Integer entityId) {

        return null;
    }

    @Override
    public Archievement deleteEntity(Archievement entityDelete) {
        if (entityDelete != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
                preparedStatement.setInt(1,entityDelete.getIdArchievement());
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
