package cropx.election.controller;

import cropx.election.config.Constants;
import cropx.election.db.dao.CustomUserDetailsService;
import cropx.election.db.entity.MessageResponse;
import cropx.election.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(path = "/users")
@RestController
public class LoginController {
    @Autowired
    private CustomUserDetailsService userService;
//    @Resource(name="authenticationManager")
//    private AuthenticationManager authManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User user) { //
            try {
            User fulluser=userService.login(user.getUsername(),user.getPassword());
        if (fulluser.getId()==null)
            return new ResponseEntity(new MessageResponse("Cant find username",400),HttpStatus.BAD_REQUEST);
                else {
            return new ResponseEntity(fulluser,HttpStatus.OK);
        }

        } catch (Exception e) {
                return new ResponseEntity(new MessageResponse("Something went wrong,try again.",400),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewUser(@RequestBody User user) {
        try {
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists == null) {
            userService.saveUser(user);
            return new ResponseEntity(new MessageResponse(Constants.SIGN_UP_SUCC,200),HttpStatus.OK);
        } else {
            return new ResponseEntity( new MessageResponse(Constants.USER_EXIST,400),HttpStatus.BAD_REQUEST);

        }
        }catch (Exception e ){
            return new ResponseEntity(new MessageResponse(e.getMessage(),400),HttpStatus.BAD_REQUEST);
        }
    }


}
