package org.launchcode.javawebdevtechjobspersistent.models.data;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EmployerRepository extends CrudRepository<Employer, Integer>, PagingAndSortingRepository<Employer, Integer> {


   // List<Passenger> passengers = repository.findAll(Sort.by(Sort.Direction.ASC, "seatNumber"));
//    @Query("FROM Employer ORDER BY name ASC")
 //   <List>Employer findAllByOrderByName();
   // <List>Employer findByOrderByName(String employer.name);
//    User findByUsername(String username)
}