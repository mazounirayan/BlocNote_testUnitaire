import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.example.User;
import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    User U;
    @Test


    public void setup(){
        this.U=new User("mazounirayan@gmail.com","aa","aa","azerty123",LocalDate.now().minusYears(5));
        boolean a=U.isvalid("mazounirayan@gmail.com","aa","aa","azerty123",LocalDate.now().minusYears(5));
        assertTrue(a);
    }


    @Test
    public void testUniqueIdIncremental() {

        User user1 = new User("Nom1", "Prenom1", "email1@example.com", "motdepasse1", LocalDate.now().minusYears(5));
        User user2 = new User("Nom2", "Prenom2", "email2@example.com", "motdepasse2", LocalDate.now().minusYears(5));


        assertEquals(1, user1.getId());
        assertEquals(2, user2.getId());
    }
    @Test
    public void testdateInvalid() {
        User user = new User("Nom", "Prenom", "invalid@email.com", "motdepasse" ,LocalDate.now().minusYears(20));
        boolean a=user.isvalid("invali@demail", "a", "", "motdepasse" ,LocalDate.now().minusYears(20));
        assertFalse(a);
    }

    @Test
    public void testprenomInvalid() {
        User U = new User( "a", "", "invali@demail", "motdepasse" ,LocalDate.now().minusYears(9));
        boolean a=U.isvalid("invali@demail", "a", "", "motdepasse" ,LocalDate.now().minusYears(9));
        assertFalse(a);
    }


    @Test
    public void testnomInvalid() {
        User U = new User( "", "aa", "invali@demail", "motdepasse" ,LocalDate.now().minusYears(9));
        boolean a=U.isvalid("invali@demail", "", "a", "motdepasse" ,LocalDate.now().minusYears(9));
        assertFalse(a);
    }



    @Test
    void test() {
        // Arrange
        String email = "a@a.com";
        String nom ="aaa";
        String prenom = "bb";
        String mdp="azerty123";
        LocalDate date = LocalDate.now().minusYears(5);
        User user = new User(email,nom,prenom,mdp,date);

        // Act
        boolean so = user.isvalid(email,nom,prenom,mdp,date);

        // Assert

        assertTrue(so);
    }
}