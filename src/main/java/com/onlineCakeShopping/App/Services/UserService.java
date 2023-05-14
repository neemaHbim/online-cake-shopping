package com.onlineCakeShopping.App.Services;

import java.util.List;
import java.util.Optional;

import com.onlineCakeShopping.App.Repositories.UserRepository;
import com.onlineCakeShopping.App.Domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
  
    
    @Autowired
    private UserRepository repo;

    public List<User> listAll(){
        return repo.findAll();
    }

    public void save(User user){
        repo.save(user);
    }

    public User get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
    @Transactional
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }
    public boolean appUserEmailExists(String email){
        return findByEmail(email).isPresent();
    }


}
