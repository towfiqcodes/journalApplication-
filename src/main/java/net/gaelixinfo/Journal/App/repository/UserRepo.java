package net.gaelixinfo.Journal.App.repository;

import net.gaelixinfo.Journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);

    User deleteByUsername(String username);
}
