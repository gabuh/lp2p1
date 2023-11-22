package dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TecnicoDaoTest {

    @Test
    @DisplayName("Testing check username method")
    void checkUsername(){
        TecnicoDao tecnicoDao = new TecnicoDao();
        assertTrue( tecnicoDao.checkIfUsernameExist("gabunogueira") );
        tecnicoDao.close();
    }

}