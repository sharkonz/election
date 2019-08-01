
/*
 * This project created by Eden Charcon, for Cropx company as a Test.
 *01/08/2019
 * */

package cropx.election.db.dao;
import cropx.election.config.Constants;
import cropx.election.db.entity.MessageResponse;
import cropx.election.db.entity.User;
import cropx.election.db.repository.RoleRepository;
import cropx.election.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    MongoTemplate mongoTemplate;


    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
    public User findUserByvoteId(String voteId) {
        return userRepository.findUserByVoteId(voteId);
    }
    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }
public List<User.UserVotes> getTop10() {
    Query query = new Query();
    query.with(new Sort(Sort.Direction.DESC, "votes"));
    query.limit(Constants.NUM_TOP_VOTED_USER);
    List<User.UserVotes> users = mongoTemplate.find(query,User.UserVotes.class);
    return users;
}
//      this Function handling all votes, increase/decrease votes.
    public ResponseEntity voteToUser(String userVoteId, String voteIdOfUserToVoteTo) throws Exception
    {
        //search if votetoId user Exist
        User userToVoteFor=new User();
        User userThatVote=new User();
        userThatVote=userRepository.findUserByVoteId(userVoteId);
       userToVoteFor=findUserByvoteId(voteIdOfUserToVoteTo);
        if (userToVoteFor==null)// if user didnt found
            return new ResponseEntity(new MessageResponse(Constants.CANT_FIND_USER_TO_VOTE_FOR,400),HttpStatus.BAD_REQUEST);

        if ((userThatVote.getVoteTo()!=null))
        {
            //if he vote allready
            //first decrease one from oldVotedUser
            User oldVotedUser=new User();
            oldVotedUser = findUserByvoteId(userThatVote.getVoteTo());
            updateVotesForUser(oldVotedUser.getVoteId(),(oldVotedUser.getVotes()-1));
        }
        updateVoteToUser(userThatVote.getVoteId(),voteIdOfUserToVoteTo);
        updateVotesForUser(userToVoteFor.getVoteId(),(userToVoteFor.getVotes()+1));;
        return new ResponseEntity(new MessageResponse(Constants.VOTE_SUCC,200),HttpStatus.OK);
    }
    public void saveUser(User user) throws Exception {
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        long id=getNewestVoteId();
        if (id> Constants.MAX_REGISTERED)
            throw  new Exception("MAXIMUM USERS ALLREADY REGISTERED("+Constants.MAX_REGISTERED+")");
           user.setVoteId(""+getNewestVoteId());

        userRepository.save(user);
    }
    public void updateVotesForUser(String voteId,int numberOfVotes) {
        Query query = new Query(new Criteria("voteId").is(voteId));
        Update update = new Update().set("votes",numberOfVotes);
        mongoTemplate.updateFirst(query, update, "user");
    }
    public void updateVoteToUser(String voteId,String voteToId) {
        Query query = new Query(new Criteria("voteId").is(voteId));
        Update update = new Update().set("voteTo",voteToId);
        mongoTemplate.updateFirst(query, update, "user");
    }
    public User login(String username,String password) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user != null) {
//            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
//            return buildUserForAuthentication(user, authorities);
            if (bCryptPasswordEncoder.matches(password,user.getPassword())) {
                return user;
            }
            else {
               return null;
            }
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
@Override
public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return null;
}
    private long getNewestVoteId() {
        Query query = new Query();
        long  users = mongoTemplate.count(query,"user");
        return  ++users;
    }


}
