package cropx.election;

import cropx.election.db.dao.CustomUserDetailsService;
import cropx.election.db.entity.User;
import cropx.election.db.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration("/application.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElectionApplicationTests {

	private static final Logger log = LogManager.getLogger();

//	@Autowired
//	private ApplicationContext context;
//	@Autowired
//	private RoleRepository roleRepository;
//	@Autowired
//	private RoleDao roleDao;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@MockBean
	private UserRepository userRepository;
	@Test
	public void testDb() {
		when(userRepository.findUserById("1234")).thenReturn(new User("1234","0","sharkonz","12341234","Eden Charcon","",2));
		assertEquals("1234",customUserDetailsService.findUserById("1234").getId());

	}
}
