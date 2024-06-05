package edu.albertoromeropino.model.dao;

import com.sun.javafx.collections.ObservableSequentialListWrapper;
import edu.albertoromeropino.model.entity.Archievement;
import edu.albertoromeropino.model.entity.Company;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameDAOTest {

    @Test
    void store() {
        Person person1 = new Person("Alberto1", "31022430F", "@123abcd");
        Company company = new Company("nose", "nose", LocalDate.of(2222, 2, 2));


        Game game2 = new Game(4, "Crash", "plataformas", person1, company);
        Game game1 = new Game(5, "pokemon", "plataformas", person1, company);


        GameDAO.build().store(game1);
        GameDAO.build().store(game2);

        assertEquals(game1, GameDAO.build().findID(game1.getIdGame()));
        System.out.println(GameDAO.build().findID(game1.getIdGame()));


        assertEquals(game2, GameDAO.build().findID(game2.getIdGame()));
        System.out.println(GameDAO.build().findID(game2.getIdGame()));
    }

    @Test
    void findID() {

        Game game = GameDAO.build().findID(1);

        System.out.println(game);
        game.getArchievements();
        System.out.println(game);
    }

    @Test
    void findByPerson() {

        Person person = Person.getPerson();
        person.setNickName("Alberto1");
        person.setDni("31022430F");
        person.setPassword("@123abcd");
        person.setGames(GameDAO.build().findByPerson(person.getNickName()));
        System.out.println(person);
    }

    @Test
    void findByCompany() {
        Company company = new Company();
        company.setNameCompany("nose");
        company = CompanyDAO.build().findID(company.getNameCompany());
        System.out.println(company);
        company.setGames(GameDAO.build().findByCompany(company.getNameCompany()));
        System.out.println(company);
    }

    @Test
    void deleteEntity() {
        Game game = GameDAO.build().findID(5);
        GameDAO.build().deleteEntity(game);
    }
}