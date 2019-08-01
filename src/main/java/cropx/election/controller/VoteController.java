
/*
 * This project created by Eden Charcon, for Cropx company as a Test.
 *01/08/2019
 * */
package cropx.election.controller;
import cropx.election.config.Constants;
import cropx.election.db.dao.CustomUserDetailsService;
import cropx.election.db.entity.MessageResponse;
import cropx.election.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = Constants.CLIENT_URL, maxAge = 3600)
@RequestMapping(path = "/vote")
@RestController
public class VoteController {
    @Autowired
    private CustomUserDetailsService userService;

    @RequestMapping(value= "/voteto", method = RequestMethod.POST)
    public ResponseEntity Vote(@RequestBody User user) throws Exception {
        try {
            return userService.voteToUser(user.getVoteId(),user.getVoteTo());
        } catch (Exception e ){
            return new ResponseEntity(new MessageResponse(e.getMessage(),400),HttpStatus.BAD_REQUEST);

        }

    }
    @RequestMapping(value= "/top10", method = RequestMethod.GET)
    public List<User.UserVotes> getTop10(){
        return userService.getTop10();
    }
}
