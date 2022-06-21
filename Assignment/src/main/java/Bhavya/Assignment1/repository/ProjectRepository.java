package Bhavya.Assignment1.repository;

import Bhavya.Assignment1.document.Projects;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProjectRepository extends MongoRepository<Projects,String> {
    public List<Projects> findProjectByEmail(String email);

    public void deleteByEmail(String email);

}
