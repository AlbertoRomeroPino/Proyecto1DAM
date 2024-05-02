package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.connection.ConnectionMariaDB;
import edu.albertoromeropino.model.entity.Company;
import edu.albertoromeropino.model.interfaces.IDAO;
import org.mariadb.jdbc.export.Prepare;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class CompanyDAO implements IDAO<Company, String> {
    private Connection connection;

    public CompanyDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String FINDID = "select nameCompany, companyDirector, companyCreation";
    private static final String INSERT = "insert into company (nameCompany, CompanyDirector, CompanyCreation) values (?,?,?)";
    private static final String DELETE = "Delete from company where nameCompany = ?";
    private static final String UPDATE = "Update game set nameCompany = ?, CompanyDirector = ?, CompanyCreation = ?";

    @Override
    public Company store(Company entity) {
        if (entity != null) {
            String idCompanytmp = entity.getNameCompany();
            if (idCompanytmp != null) {
                Company companytmp = findID(entity.getNameCompany());
                if (companytmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setString(1, entity.getNameCompany());
                        preparedStatement.setString(2, entity.getCompanyDirector());
                        preparedStatement.setDate(3, Date.valueOf(entity.getCompanyCreation()));    //Puede fallar
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, entity.getNameCompany());
                        preparedStatement.setString(2, entity.getCompanyDirector());
                        preparedStatement.setDate(3, Date.valueOf(entity.getCompanyCreation()));   //Puede fallar
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
    public Company findID(String entityId) {
        Company company = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setString(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Company companytmp = new Company();
                    companytmp.setNameCompany(resultSet.getString("NameCompany"));
                    companytmp.setCompanyDirector(resultSet.getString("CompanyDirector"));
                    companytmp.setCompanyCreation(LocalDate.parse(("CompanyCreation")));
                    company = companytmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    @Override
    public Company deleteEntity(Company entityDelete) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setString(1, entityDelete.getNameCompany());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entityDelete;
    }

    @Override
    public void close() throws IOException {

    }

    public static CompanyDAO build() {
        return new CompanyDAO();
    }
}
