/*
 * This project created by Eden Charcon, for Cropx company as a Test.
 *01/08/2019
 * */

package cropx.election.db.repository;
import cropx.election.db.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findUserByUsername(String username);
    User findUserByVoteId(String username);
    User findUserById(String id);
}