package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.entity.Company;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompanyDAOTest {

    @Test
    void store() {
        Company company = new Company("nose","nose", LocalDate.of(2222,2,2));
        Company company1 = new Company("empresados","nos5", LocalDate.of(2004,2,6));
        Company company2 = new Company("compania3","nose2", LocalDate.of(1992,11,6));
        Company company23 = new Company("compania34","nose2", LocalDate.of(1992,11,6));
        assertEquals(company, CompanyDAO.build().store(company));
        assertEquals(company1, CompanyDAO.build().store(company1));
        assertEquals(company2, CompanyDAO.build().store(company2));
        assertEquals(company23, CompanyDAO.build().store(company23));
    }

    @Test
    void findID() {
        Company company = new Company("nose","nose", LocalDate.of(2222,2,2));
        assertEquals(company, CompanyDAO.build().findID(company.getNameCompany()));
        System.out.println(CompanyDAO.build().findID(company.getNameCompany()));
        System.out.println(CompanyDAO.build().findID("compania3"));
    }

    @Test
    void deleteEntity() {
        Company company23 = new Company("compania34","nose2", LocalDate.of(1992,11,10));
        assertEquals(company23, CompanyDAO.build().deleteEntity(company23));
    }
}