package springrestapp.dao;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import springrestapp.entity.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

}
