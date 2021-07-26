package app.core.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.loginManager.ClientService;
import app.core.repository.CompanyRepository;
import app.core.repository.CustomerRepository;
import exception.CouponException;

@Service
@Transactional
public class AdminService implements ClientService {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepo;

	public boolean login(String email, String password) throws CouponException {
		try {
			System.out.println("Welcome!!!!");
			if (email.equals("admin@admin.com") && password.equals("admin")) {
				System.out.println("Welcome!!");
				return true;
			}
			return false;

		} catch (Exception e) {
			throw new CouponException("The methd login failed", e);

		}
	}

	public Company addCompany(Company company) throws CouponException {
		try {
			Optional<Company> companyNameExists = companyRepository.findByName(company.getName());
			Optional<Company> companyEmailExists = companyRepository.findByEmail(company.getEmail());
			if (companyNameExists.isPresent() || companyEmailExists.isPresent()) {
				throw new CouponException("check your email or name and change them");
			} else {
				companyRepository.save(company);
				System.out.println("Company added");
				return company;
			}

		} catch (Exception e) {
			throw new CouponException("The methd addCoupon failed", e);
		}

	}

	public Company updateCompany(Company company) throws CouponException {
		try {
			Optional<Company> company1 = companyRepository.findById(company.getId());

			Company company3 = company1.get();
			if (company3.getName().equals(company.getName()) || company.getName() == null) {
				company3.setEmail(company.getEmail());
				company3.setPassword(company.getPassword());
				System.out.println("company update");
				return company3;
			} else {
				throw new CouponException("you can't change company name");
			}

		} catch (Exception e) {
			throw new CouponException("The methd updateCompany failed", e);

		}
	}

	public Company getOneCompany(Integer id) throws CouponException {

		try {
			Optional<Company> company = companyRepository.findById(id);
			if (company.isPresent()) {
				Company company2 = company.get();
				return company2;
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new CouponException("company not found: " + id);
		}

	}

	public void deleteCompny(Integer id) throws CouponException {
		try {
			companyRepository.deleteById(id);

		} catch (Exception e) {
			throw new CouponException("The methd deleteCompny failed", e);

		}
	}

	public List<Company> getAllConpany() throws CouponException {
		try {
			List<Company> companies = companyRepository.findAll();
			System.out.println(companies);
			return companies;

		} catch (Exception e) {
			throw new CouponException("The methd getAllConpany failed", e);
		}
	}

	public Customer addCustomr(Customer customer) throws CouponException {
		try {
			Customer customerEmailExists = customerRepo.findByEmail(customer.getEmail());
			if (customerEmailExists != null) {
				throw new CouponException("check your email");

			} else {
				customerRepo.save(customer);
				System.out.println("Customer added");
				return customer;
			}

		} catch (Exception e) {
			throw new CouponException("The methd addCustomr failed", e);
		}

	}

	public Customer updateCustomer(Customer customer) throws CouponException {
		try {
			Optional<Customer> customer1 = customerRepo.findById(customer.getId());

			Customer customer2 = customer1.get();
			customer2.setFirstName(customer.getFirstName());
			customer2.setLastName(customer.getLastName());
			customer2.setEmail(customer.getEmail());
			customer2.setPassword(customer.getPassword());
			return customer2;

		} catch (Exception e) {
			throw new CouponException("The methd updateCustomer failed, not customer found enter id customer", e);
		}
	}

	public Customer getOneCustomer(Integer id) throws CouponException {
		try {
			Optional<Customer> opt = customerRepo.findById(id);
			System.out.println(opt);
			if (opt.isPresent()) {
				Customer customer = opt.get();
				return customer;
			}
			throw new CouponException("customer not found: " + id);

		} catch (Exception e) {
			throw new CouponException("customer not found: " + id, e);
		}

	}

	public void deleteCustomer(Integer id) throws CouponException {
		try {
			customerRepo.deleteById(id);

		} catch (Exception e) {
			throw new CouponException("The methd deleteCustomer failed", e);
		}
	}

	public List<Customer> getAllCustomer() throws CouponException {
		try {
			List<Customer> customers = customerRepo.findAll();
			System.out.println(customers);
			return customers;

		} catch (Exception e) {
			throw new CouponException("The methd deleteCustomer failed", e);
		}
	}
}
