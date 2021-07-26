package app.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByEmailAndPassword(String email, String password);

	Customer findByEmail(String email);

}
