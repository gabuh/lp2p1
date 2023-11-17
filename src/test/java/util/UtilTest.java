package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    @DisplayName("Testing true email validation ,Util:")
    void emailValidationTrue(){

        assertTrue(
                Util.validateEmail("email@email.com")
//                        Util.validateEmail("email@email.com.br") &&
//                        Util.validateEmail("email@aluno.ifsp.edu.br")
        );
    }


}