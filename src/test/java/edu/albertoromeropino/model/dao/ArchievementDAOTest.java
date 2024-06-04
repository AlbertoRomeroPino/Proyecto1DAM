package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.entity.Archievement;
import edu.albertoromeropino.model.entity.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArchievementDAOTest {

    @Test
    void store() {
        Game game = GameDAO.build().findID(1);
        Archievement archievement = new Archievement(5,"Caza un malvadoJho","Caza por primera vez un devilJho", "Usa armas de tipo draco o de paralisis para derrotarlo",game);
        ArchievementDAO.build().store(archievement);
        System.out.println(archievement);
    }

    @Test
    void findID() {
        Archievement archievement = ArchievementDAO.build().findID(2);
        System.out.println(archievement);
    }

    @Test
    void findByIdGame() {
        //en el momento que escribo esto no puedo hacerlo porque no tengo que los juegos se almacenen los logros.
        //Game game = new Game;
        //game.setIdGame(1);
        //game = ArchievementDAO.build().findByIdGame(1);

    }

    @Test
    void deleteEntity() {
        Archievement archievement = new Archievement();
        archievement.setIdArchievement(5);
        ArchievementDAO.build().deleteEntity(archievement);

    }
}