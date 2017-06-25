
package com.mycompany.OnlineLibraryForStudent.model.dao;

import com.mycompany.OnlineLibraryForStudent.model.Article;
import com.mycompany.OnlineLibraryForStudent.model.Users;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author YARUS
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRopositoryTest {
    
    @Autowired
    private UsersRepository userRepository;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    public UserRopositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of findByEmail method, of class UserRopositoryImpl.
     */
    @Test
    public void testFindByEmail() {
        System.out.println("findByEmail");
        String email = "yarusprog@mail.ru";                
        List<Users> result = (List<Users>) userRepository.findByEmail(email);
        assertEquals(result.get(0).getLogin(), "yarus");        
        List<Article> articles = (List<Article>) articleRepository.findAll();
        assertNotEquals(articles.size(), 0);
    }
}
