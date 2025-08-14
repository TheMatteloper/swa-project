package swa.semproject.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import swa.semproject.userservice.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>,
        CrudRepository<User, Integer> {
}
