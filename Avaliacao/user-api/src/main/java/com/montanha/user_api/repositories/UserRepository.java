package com.montanha.user_api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.montanha.user_api.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    User findByCpf(String cpf);

    List<User>	queryByNomeLike(String nome);

}

