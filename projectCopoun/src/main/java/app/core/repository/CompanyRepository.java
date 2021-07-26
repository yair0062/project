package app.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Optional<Company> findByName(String name);

	Company findByEmailAndPassword(String email, String password);

	Optional<Company> findByEmail(String email);

}
