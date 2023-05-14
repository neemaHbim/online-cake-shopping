package com.onlineCakeShopping.App.Domain;

import com.onlineCakeShopping.App.Config.SecurityConfiguration;
import com.onlineCakeShopping.App.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

    @Component
    public class Startup {
        private final Logger Log = LoggerFactory.getLogger(Startup.class);

        @Autowired
        private UserRepository userRepository;


        @Autowired
        private SecurityConfiguration bCryptPasswordEncoder;




        @PostConstruct
        public void init() {
            System.out.println("-----------> Initializing variables");
            initAdminCreation();
            System.out.println("-----------> Completion of Admin Creation Initialization");
        }


        private void initAdminCreation() {
            User user = new User(0,true,bCryptPasswordEncoder.getPasswordEncoder().encode("admin"),"ROLE_ADMIN","admin@cake.com","Admin","",null,null);

            try {

                    userRepository.save(user);
            } catch (Exception e) {
//                Log.info(e.getMessage());
//                e.printStackTrace();
            }

        }




}
