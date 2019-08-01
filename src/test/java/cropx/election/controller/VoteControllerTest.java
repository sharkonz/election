package cropx.election.controller;

import cropx.election.config.ApplicationConfig;
import cropx.election.db.dao.CustomUserDetailsService;
import cropx.election.db.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ContextConfiguration(classes={ApplicationConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(VoteController.class)
public class VoteControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CustomUserDetailsService customUserDetailsServicel;
   @Autowired
    private UserRepository userRepository;


    @Test
    public void vote() {

    }

    @Test
    public void getTop10() {
        try {
            mvc.perform(get("v1/vote/top10").contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}