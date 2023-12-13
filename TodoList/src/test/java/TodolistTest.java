import org.example.EmailSenderService;
import org.example.Item;
import org.example.Todolist;
import org.example.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class TodolistTest {
    private Todolist todolist;
    private User user;
    private EmailSenderService emailSenderServiceMock;


    @Before
    public void setUp() {
        this.user = new User( "Jojo", "aoe", "jooj@e.com", "password", LocalDate.now());
        this.emailSenderServiceMock = mock(EmailSenderService.class);
        when(this.emailSenderServiceMock.sendMail(anyString())).thenReturn(true);


        todolist = new Todolist(1, user, new ArrayList<>(), this.emailSenderServiceMock);
    }

    @Test
    public void testIsFullWhenListIsFull() {
        for (int i = 0; i < 10; i++) {
            todolist.getListItem().add(new Item(i, "Content " + i, "ItemName" + i, LocalDateTime.now()));
        }

        assertTrue(todolist.isFull(todolist));
    }

    @Test
    public void testIsFullWhenListIsNotFull() {
        assertFalse(todolist.isFull(todolist));
    }

    @Test
    public void testAddItemWhenListIsFull() {
        for (int i = 0; i < 10; i++) {
            todolist.getListItem().add(new Item(i, "Content " + i, "ItemName"+ i, LocalDateTime.now()));
        }

        Item newItem = new Item(11, "New Content", "NewItemName", LocalDateTime.now());
        assertFalse(todolist.addItem(newItem));
        assertFalse(todolist.getListItem().contains(newItem));
    }

    @Test
    public void testAddItemWhenListIsNotFullAndRecentItem() {
        Item recentItem = new Item(1, "Recent Content", "RecentItemName", LocalDateTime.now());
        todolist.getListItem().add(recentItem);

        Item newItem = new Item(2, "New Content", "NewItemName", LocalDateTime.now());
        assertFalse(todolist.addItem(newItem));
        assertFalse(todolist.getListItem().contains(newItem));
    }

    @Test
    public void testAddItemWhenListIsNotFullAndOldItem() {
        Item oldItem = new Item(1, "Old Content", "OldItemName", LocalDateTime.now());
        todolist.getListItem().add(oldItem);

        Item newItem = new Item(2, "New Content", "NewItemName", LocalDateTime.now());
        assertTrue(todolist.addItem(newItem)); // Marche pas car implémentation mauvaise
        assertTrue(todolist.getListItem().contains(newItem));
    }

    @Test
    public void testAddItemWithEmailNotification() {
        for (int i = 0; i < 7; i++) {
            todolist.getListItem().add(new Item(i, "Content " + i, "ItemName"+ i, LocalDateTime.now()));
        }

        Item newItem = new Item(8, "New Content", "NewItemName", LocalDateTime.now());
        assertTrue(todolist.addItem(newItem)); // Marche pas car implémentation mauvaise
    }

    @Test
    public void testAddItemWithSameName() {
        Item newItem1 = new Item(1, "New Content", "NewItemName", LocalDateTime.now());
        Item newItem2 = new Item(2, "New Content", "NewItemName", LocalDateTime.now());

        assertTrue(todolist.addItem(newItem1));
        assertFalse(todolist.addItem(newItem2));

    }


    /*@Test
    public void testSaveMethodThrowsException() {
        todolist.save(todolist);
    }*/


}