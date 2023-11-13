package dao.impl;

import model.builder.CampeonatoBuilder;
import model.Campeonato;
import model.builder.TimeBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CampeonatoDaoTest {

    private static CampeonatoDao campeonatoDao;
    private static Campeonato campeonato, campeonatoComTime;

    @BeforeAll
    static void setUp() {
        campeonatoDao = new CampeonatoDao();


        campeonato = new CampeonatoBuilder() //instantiated
                .create();

        campeonatoComTime = new CampeonatoBuilder() //instantiated with team
                .addTime(
                        new TimeBuilder()
                                .setNome("Rodrigues")
                                .create()
                )
                .create();

        campeonatoDao.persist(new CampeonatoBuilder() //database
                .create());
        campeonatoDao.persist(new CampeonatoBuilder()
                .create());
        campeonatoDao.persist(new CampeonatoBuilder()
                .create());
    }

    @Test
    @DisplayName("saving entity into the database")
    void persist() {
        campeonatoDao.persist(campeonatoComTime);
        assertTrue(campeonatoDao.findAll().size()>0);
    }

    @Test
    @DisplayName("updating entity into the database")
    void update() {
        var campeonatoToUpdate = campeonatoDao.findAll().get(2);
        campeonatoToUpdate.setCampeao("Gabriel(Gabu) is the champion of course !");
        campeonatoDao.update(campeonatoToUpdate);
        assertEquals("Gabriel(Gabu) is the champion of course !",campeonatoDao.findById(campeonatoToUpdate.getId()).getCampeao());
    }

    @Test
    @DisplayName("getting entity from the database by id")
    void findById() {
        Campeonato campeonato1 = campeonatoDao.findAll().get(2);
        assertEquals(campeonato1.getId() , campeonatoDao.findById(campeonato1.getId()).getId());
    }

    @Test
    @DisplayName("deleting entity into the database by sending the entity itself")
    void delete() {
        var campeonaToDelete = campeonatoDao.findAll().get(3);
        System.out.println("Id: "+campeonaToDelete.getId() +",size: "+ campeonatoDao.findAll().size());
        campeonatoDao.delete(campeonaToDelete);
        assertNull(campeonatoDao.findById(campeonaToDelete.getId()));
    }

    @Test
    void findAll() {

    }


}