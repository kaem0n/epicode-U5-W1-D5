package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.User;
import kaem0n.u5w1d5.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO ud;

    public void save(User user) {
        if (!ud.existsByUsername(user.getUsername())) {
            ud.save(user);
            System.out.println("User " + user.getUsername() + " saved on db.");
        }
    }

    public User findById(long id) {
        return ud.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(long id) {
        User found = this.findById(id);
        ud.delete(found);
        System.out.println("User " + found.getUsername() + " deleted from db.");
    }
}
