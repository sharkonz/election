
/*
 * This project created by Eden Charcon, for Cropx company as a Test.
 *01/08/2019
 * */

package cropx.election.db.entity;
import java.util.Set;


import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String voteId;
    private String username;
    private String password;
    private String fullname;
    private String voteTo;
    private int votes;

    public User() {
    }
    public User(String id, String voteId, String username, String password, String fullname, String voteTo, int votes) {
        this.id = id;
        this.voteId = voteId;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.voteTo = voteTo;
        this.votes = votes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }



    public String getVoteTo() {
        return voteTo;
    }

    public void setVoteTo(String voteTo) {
        this.voteTo = voteTo;

    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }


    @Document(collection = "user")
    public class UserVotes {
        @Id
        private String id;
        private String voteId;
        private String username;
        private String fullname;
        private int votes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVoteId() {
            return voteId;
        }

        public void setVoteId(String voteId) {
            this.voteId = voteId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }



        public UserVotes() {
        }

    }
}
