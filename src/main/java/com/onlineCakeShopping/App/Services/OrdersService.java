package com.onlineCakeShopping.App.Services;

import com.onlineCakeShopping.App.Domain.Orders;
import com.onlineCakeShopping.App.Repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository repo;

    public List<Orders> listAll(){
        return repo.findAll();
    }

    public void save(Orders orders){
        repo.save(orders);
    }

    public Orders get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

}
