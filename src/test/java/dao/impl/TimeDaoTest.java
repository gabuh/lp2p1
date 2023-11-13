package dao.impl;


import model.Time;
import model.builder.CampeonatoBuilder;
import model.builder.TimeBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeDaoTest {

    private static TimeDao timeDao;

    private static Time timeComCampeonato;


    @BeforeAll
    static void setUp(){
        timeDao = new TimeDao();

        timeComCampeonato = new TimeBuilder()
                .setNome("Sao Paulo")
                .addCampeonato(
                        new CampeonatoBuilder()
                                .create()
                )
                .create();

        timeDao.persist(
                new TimeBuilder()
                        .setNome("Time 1")
                        .create()
        );
        timeDao.persist(
                new TimeBuilder()
                        .setNome("Time 2")
                        .create()
        );
        timeDao.persist(
                new TimeBuilder()
                        .setNome("Time 3")
                        .create()
        );


    }


    @Test
    @DisplayName("saving entity into the database")
    void persist() {
        timeDao.persist(timeComCampeonato);
        assertTrue(timeDao.findAll().size()>0);
    }

    @Test
    @DisplayName("updating entity into the database")
    void update() {
        var timeToUpdate = timeDao.findAll().get(2);
        timeToUpdate.setNome("Time Doido");
        timeDao.update(timeToUpdate);
        assertEquals("Time Doido",timeDao.findById(timeToUpdate.getId()).getNome());
    }

    @Test
    @DisplayName("getting entity from the database by id")
    void findById() {
        Time time1 = timeDao.findAll().get(2);
        assertEquals(time1.getId() , timeDao.findById(time1.getId()).getId());
    }


    @Test
    @DisplayName("deleting entity into the database by sending the entity itself")
    void delete() {
        var timeToDelete = timeDao.findAll().get(3);
        System.out.println("Id: "+timeToDelete.getId() +",size: "+ timeDao.findAll().size());
        timeDao.delete(timeToDelete);
        assertNull(timeDao.findById(timeToDelete.getId()));
    }

    @Test
    void findAll() {

    }



}