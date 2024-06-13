package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.connection.ConnectionMariaDB;
import edu.albertoromeropino.model.entity.Company;
import edu.albertoromeropino.model.interfaces.IDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class CompanyDAO implements IDAO<Company, String> {
    private Connection connection;

    public CompanyDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String FINDID = "select nameCompany, companyDirector, companyCreation from company where nameCompany = ?";
    private static final String FINDALL = "select nameCompany, companyDirector, companyCreation from company";
    private static final String INSERT = "insert into company (nameCompany, CompanyDirector, CompanyCreation) values (?,?,?)";
    private static final String DELETE = "Delete from company where nameCompany = ?";
    private static final String UPDATE = "Update company set CompanyDirector = ?, CompanyCreation = ? where nameCompany = ?";

    /**
     * Almacena o actualiza una compañia que a sido pasada
     *
     * @param entity una compañia que se desea insertar
     * @return la compañia insertada
     */
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
                        //dato del where
                        preparedStatement.setString(3, entity.getNameCompany());

                        preparedStatement.setString(1, entity.getCompanyDirector());
                        preparedStatement.setDate(2, Date.valueOf(entity.getCompanyCreation()));   //Puede fallar
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
     * Busca una compañia por su identificador que es el nameCompany. Se le pasa un String
     *
     * @param entityId es el nombre de la compañia que va a utilizarse para buscar a esta
     * @return la compañia buscada
     */
    @Override
    public Company findID(String entityId) {
        Company company = new Company();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setString(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Company companytmp = new Company();
                    companytmp.setNameCompany(resultSet.getString("NameCompany"));
                    companytmp.setCompanyDirector(resultSet.getString("CompanyDirector"));
                    companytmp.setCompanyCreation(resultSet.getDate("CompanyCreation").toLocalDate());
                    //companytmp.setGames(GameDAO.build().findByCompany(resultSet.getString("NameCompany")));

                    company = companytmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    public ArrayList<Company> findAll() {
        ArrayList<Company> company = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Company companytmp = new Company();
                    companytmp.setNameCompany(resultSet.getString("NameCompany"));
                    companytmp.setCompanyDirector(resultSet.getString("CompanyDirector"));
                    companytmp.setCompanyCreation(resultSet.getDate("CompanyCreation").toLocalDate());
                    //companytmp.setGames(GameDAO.build().findByCompany(resultSet.getString("NameCompany")));

                    company.add(companytmp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    /**
     * Borra una compañia.
     *
     * @param entityDelete Se le pasa la compañia que deseas borrar
     * @return la compañia que as borrado
     */
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