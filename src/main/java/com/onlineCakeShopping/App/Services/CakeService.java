package com.onlineCakeShopping.App.Services;

import com.onlineCakeShopping.App.Repositories.CakeRepository;
import com.onlineCakeShopping.App.Domain.Cake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeService {

    @Autowired
    private CakeRepository repo;

    public List<Cake> listAll(){
        return repo.findAll();
    }

    public void save(Cake incident){
        repo.save(incident);
    }

    public Cake get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

}
