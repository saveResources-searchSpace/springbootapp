package springrestapp.Controller;

//import java.util.Arrays;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springrestapp.dao.CustomerRepository;
import springrestapp.entity.Customer;

@RestController
@RequestMapping("/api/customers")

public class CustomerRestController {
	@Autowired
	private CustomerRepository repo;
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
		
		try {
		Customer c1 =  repo.findById(id).get();
		    return ResponseEntity.ok(c1);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
		
	}
	
	@PostMapping()
	public ResponseEntity<?> addCustomerById(@RequestBody Customer  customer) {
           repo.save(customer);
           return ResponseEntity.ok(customer);
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateCustomerById(@PathVariable("id") Integer id,@RequestBody Customer  customer) {
		   customer.setId(id);
           repo.save(customer);
           return ResponseEntity.ok(customer);
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("id") Integer id) {
		try {
			Customer c1 =  repo.findById(id).get();
			repo.delete(c1);
			return ResponseEntity.ok(c1);
			} catch (Exception e) {
				return ResponseEntity.status(404).body(null);
			}
	}
	
	@GetMapping
	public Iterable<Customer> getAllCustomers(@RequestParam(name="_page", defaultValue = "1") Integer pageNum ,@RequestParam(name="_limit", defaultValue = "20") Integer pageSize) {
		Pageable p = PageRequest.of(pageNum-1, pageSize);
		return repo.findAll(p).getContent();
		/*
		 * 	public List<Customer> getAllCustomers() {
        Customer c1 = new Customer();
		c1.setId(1);
		c1.setName("aa");
		c1.setEmail("a@gmail.com");
		c1.setCity("newark");
		c1.setPhone("1234567899");
		c1.setState("NJ");
		c1.setCountry("USA");
		return Arrays.asList(c1);
		
		/this below code works but what if the ID doesn't exist ?
	    public Customer getCustomerById(@PathVariable Integer id) {
		return repo.findById(id).get();
	}
	
	
		*/
	}

}
