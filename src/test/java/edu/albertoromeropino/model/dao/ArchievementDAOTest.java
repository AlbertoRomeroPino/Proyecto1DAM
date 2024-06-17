package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.entity.Archievement;
import edu.albertoromeropino.model.entity.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArchievementDAOTest {

    @Test
    void store() {
        Game game = GameDAO.build().findID(1);
        Archievement archievement = new Archievement(5, "Caza un malvadoJho", "Caza por primera vez un devilJho", "Usa armas de tipo draco o de paralisis para derrotarlo", game);
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
        //En gameDAOTest esta ya la prueba con el Laizy de esta funcion
    }

    @Test
    void deleteEntity() {
        Archievement archievement = new Archievement();
        archievement.setIdArchievement(5);
        ArchievementDAO.build().deleteEntity(archievement);

    }
}