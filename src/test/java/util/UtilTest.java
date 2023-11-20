package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputConstraintsTest {

    @Test
    @DisplayName("Testing true email validation ")
    void emailValidationTrue(){
        assertTrue(

InputConstraints.validateEmail("email@email.com") &&

InputConstraints.validateEmail("email@email.com.br") &&

InputConstraints.validateEmail("email@aluno.ifsp.edu.br") &&

InputConstraints.validateEmail("user@example.com") &&       // Basic email

InputConstraints.validateEmail("info@sub.domain.com") &&      // Email with subdomain

InputConstraints.validateEmail("admin@localhost.com") &&          // Localhost email

InputConstraints.validateEmail("user@myemail.com")
        );
    }

    @Test
    @DisplayName("Testing false emails validation")
    void emailValidationFalse(){ //thanks chat gpt
        assertFalse(

InputConstraints.validateEmail("email@.com.br")   ||  // Missing username

InputConstraints.validateEmail("@domain.com.br") ||    // Missing username

InputConstraints.validateEmail("email@domain") ||   // Missing top-level domain

InputConstraints.validateEmail("email@.com.br.") ||  // Consecutive dots in the domain

InputConstraints.validateEmail("email@domain..com.br") ||  // Consecutive dots in the domain

InputConstraints.validateEmail("email@domain. com.br") ||  // Space in the domain

InputConstraints.validateEmail("email@domain@.com.br") ||   // Multiple '@' symbols

InputConstraints.validateEmail("email@domain,.com.br") ||  // Invalid character ',' in the domain

InputConstraints.validateEmail("email@domain.com.1br") ||  // Invalid character '1' in the top-level domain

InputConstraints.validateEmail("email@domain!.com.br") ||  // Invalid character '!' in the domain

InputConstraints.validateEmail("email@domain..com.br") ||   // Consecutive dots in the domain

InputConstraints.validateEmail("email@domain. com.br") ||   // Space in the domain

InputConstraints.validateEmail("email@domain_com.br") ||   // Underscore in the domain

InputConstraints.validateEmail("email@dom ain.com.br") ||   // Space in the local part

InputConstraints.validateEmail("email@domain..com.br") ||   // Consecutive dots in the domain

InputConstraints.validateEmail("email@domain. com.br") ||    // Space in the domain

InputConstraints.validateEmail("email@domain_com.br") ||    // Underscore in the domain

InputConstraints.validateEmail("email@domain@.com.br") ||   // Multiple '@' symbols

InputConstraints.validateEmail("email@domain,.com.br") ||  // Invalid character ',' in the domain

InputConstraints.validateEmail("email@domain.com.1br") ||  // Invalid character '1' in the top-level domain

InputConstraints.validateEmail("email@domain!.com.br") ||  // Invalid character '!' in the domain

InputConstraints.validateEmail("email@domain..com.br") ||   // Consecutive dots in the domain

InputConstraints.validateEmail("email@domain. com.br") ||   // Space in the domain

InputConstraints.validateEmail("email@dom ain.com.br")
        );
    }


    @Test
    @DisplayName("Testing Username constraints (VALID INPUTS)")
    void testUsernameValid(){
        assertTrue(

InputConstraints.validateUsername("user123") &&

InputConstraints.validateUsername("john_doe") &&

InputConstraints.validateUsername("alphaBeta123") &&

InputConstraints.validateUsername("user_name_123") &&

InputConstraints.validateUsername("username") &&

InputConstraints.validateUsername("username12345678") &&

InputConstraints.validateUsername("jane_doe_1") &&

InputConstraints.validateUsername("user123_alpha") &&

InputConstraints.validateUsername("aBc_123") &&

InputConstraints.validateUsername("validUsername_1") &&

InputConstraints.validateUsername("user_test_42")
        );
    }

    @Test
    @DisplayName("Testing Username constraints (INVALID INPUTS)")
    void testUsernameInvalid(){
        assertFalse(

InputConstraints.validateUsername("") ||

InputConstraints.validateUsername("user!123") ||

InputConstraints.validateUsername("in valid") ||

InputConstraints.validateUsername("user name") ||

InputConstraints.validateUsername("user#123") ||

InputConstraints.validateUsername("user%123") ||

InputConstraints.validateUsername("123user") ||

InputConstraints.validateUsername("user-123") ||

InputConstraints.validateUsername("user@123") ||

InputConstraints.validateUsername("_underscoreStart") ||

InputConstraints.validateUsername("endWithUnderscore_") ||

InputConstraints.validateUsername("toooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooolong") ||

InputConstraints.validateUsername("spaces at the end ") ||

InputConstraints.validateUsername("user with spaces") ||

InputConstraints.validateUsername("user_123_") ||

InputConstraints.validateUsername("user_123!!!") ||

InputConstraints.validateUsername("user_123#") ||

InputConstraints.validateUsername("user_123@") ||

InputConstraints.validateUsername("user_123%") ||

InputConstraints.validateUsername("user_123-") ||

InputConstraints.validateUsername("user_123$")
        );
    }


    @Test
    @DisplayName("Testing Password constraints (VALID INPUTS)")
    void testPasswordValid(){
        assertTrue(

InputConstraints.validatePassword("Strong-Password123") &&

InputConstraints.validatePassword("Secure@Passw0rd") &&

InputConstraints.validatePassword("8User#P@ss") &&

InputConstraints.validatePassword("1234AbCd!") &&

InputConstraints.validatePassword("SuperSecret!123") &&

InputConstraints.validatePassword("P@ssword123") &&

InputConstraints.validatePassword("SecureP@ssword3") &&

InputConstraints.validatePassword("Pass123!@#word") &&

InputConstraints.validatePassword("Complex@Password123") &&

InputConstraints.validatePassword("RandomP@ss!123")
        );
    }

    @Test
    @DisplayName("Testing Password constraints (INVALID INPUTS)")
    void testPasswordInvalid(){
        assertFalse(

InputConstraints.validatePassword("weakpassword") ||

InputConstraints.validatePassword("abcde") ||

InputConstraints.validatePassword("onlylowercase") ||

InputConstraints.validatePassword("ONLYUPPERCASE") ||

InputConstraints.validatePassword("12345678") ||

InputConstraints.validatePassword("noSpecialCharacter123") ||

InputConstraints.validatePassword("AllLowercase123") ||

InputConstraints.validatePassword("ALLUPPERCASE123") ||

InputConstraints.validatePassword("NoNumberOrSpecialCharacter") ||

InputConstraints.validatePassword("Short!1")
        );
    }

}