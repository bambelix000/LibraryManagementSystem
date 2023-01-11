package pl.bambelix000.LibraryManagementSystem.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser(){
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public void addUser(User user){
        Optional<User>userOptional = userRepository.findBySocialSecurityNumber(user.getSocialSecurityNumber());

        if(userOptional.isPresent()){
            throw new IllegalStateException("This user is already added");
        }
        userRepository.save(user);
    }

}
