package dao.impl;

import model.builder.CampeonatoBuilder;
import model.Campeonato;
import model.builder.TimeBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CampeonatoDaoImplTest {

    private static CampeonatoDaoImpl campeonatoDao;
    private static Campeonato campeonato, campeonatoComTime;

    @BeforeAll
    static void setUp() {
        campeonatoDao = new CampeonatoDaoImpl();


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
    }

    @Test
    @DisplayName("saving entity into the database")
    void persist() {
        campeonatoDao.persist(campeonato);
        campeonatoDao.persist(campeonatoComTime);
        assertEquals(2, campeonatoDao.findAll().size());
    }

    @Test
    @DisplayName("updating entity into the database")
    void update() {
        var campeonatoToUpdate = campeonatoDao.findById(1);
        campeonatoToUpdate.setCampeao("Gabriel(Gabu) is the champion of course !");
        campeonatoDao.update(campeonatoToUpdate);
        assertEquals("Gabriel(Gabu) is the champion of course !",campeonatoDao.findById(1).getCampeao());
    }

    @Test
    @DisplayName("getting entity from the database by id")
    void findById() {
        campeonatoDao.persist(campeonato);
        assertEquals(1 , campeonatoDao.findById(1).getId());
    }

    @Test
    @DisplayName("deleting entity into the database by sending the entity itself")
    void delete() {
        var campeonaToDelete = campeonatoDao.findById(1);
        System.out.println("Id: "+campeonaToDelete.getId() +",size: "+ campeonatoDao.findAll().size());
        campeonatoDao.delete(campeonaToDelete);
        assertEquals(0, campeonatoDao.findAll().size());
    }

    @Test
    void findAll() {

    }


}