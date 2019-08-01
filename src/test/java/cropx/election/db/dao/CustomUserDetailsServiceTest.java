package cropx.election.db.dao;

import cropx.election.config.ApplicationConfig;
import cropx.election.db.repository.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(classes={ApplicationConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomUserDetailsServiceTest {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserRepository userRepository;


//    @Test
//        public void findUserById() {
//          User u =  customUserDetailsService.findUserById("5d3c6690192ee047f86b5395");
//        assertEquals("5d3c6690192ee047f86b5395",u.getId());
//    }
//    @Test
//    public void testDb() {
//        when(userRepository.findUserById("1234")).thenReturn(new User("1234","0","sharkonz","12341234","Eden Charcon","",2));
//        assertEquals("1234",customUserDetailsService.findUserById("1234").getId());
//    }
}