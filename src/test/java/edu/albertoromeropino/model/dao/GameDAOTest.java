package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.entity.Archievement;
import edu.albertoromeropino.model.entity.Company;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameDAOTest {

    @Test
    void store() {
        Person person1 = new Person("Alberto1", "31022430F", "@123abcd");
        Company company = new Company("nose", "nose", LocalDate.of(2222, 2, 2));

        Game game = new Game(1, "Monster hunter", "Caceria", person1, company);
        Game game1 = new Game(2, "mario", "plataformas", person1, company);
        Game game2 = new Game(3, "sonic", "plataformas", person1, company);

        GameDAO.build().store(game);
        GameDAO.build().store(game1);
        GameDAO.build().store(game2);

        assertEquals(game, GameDAO.build().findID(game.getIdGame()));

        System.out.println(GameDAO.build().findID(game.getIdGame()));
    }

    @Test
    void findID() {
        Person person1 = new Person("Alberto1", "31022430F", "@123abcd");
        Company company = new Company("nose", "nose", LocalDate.of(2222, 2, 2));

        Game game = GameDAO.build().findID(1);



        System.out.println(game);
    }

    @Test
    void findByPerson() {
    }

    @Test
    void findByCompany() {
    }

    @Test
    void deleteEntity() {
    }
}