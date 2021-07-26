package app.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.service.AdminService;
import app.core.session.Session;
import app.core.session.SessionContext;
import exception.CouponException;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController extends ClientController {
//	private Map<Integer, Company> companyMap = new HashMap<Integer, Company>();
	@Autowired
	private SessionContext sessionContext;

	@Autowired
	private AdminService adminService;

	@GetMapping("/login")
	@Override
	public String login(String email, String password) throws CouponException {
		try {
			boolean b = adminService.login(email, password);
			if (b) {
				Session session = sessionContext.createSession();
				session.setAttribute("email", email);
				return session.token;
			}

			throw new CouponException("login failed");

		} catch (CouponException e) {
			throw new CouponException(e.getMessage());
		}
	}

	@PostMapping("/addCompany")
	public ResponseEntity<?> addCompany(@RequestHeader String token, @RequestBody Company company)
			throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				Company c = adminService.addCompany(company);
				System.out.println(c);
				return ResponseEntity.ok().body(c);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("check your email or name and change them");

		} catch (CouponException e) {
			throw e;
		}
	}

	@GetMapping("/getCompany")
	public ResponseEntity<?> getCompany(@RequestHeader String token, @RequestParam Integer companyId)
			throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {

				Company company = adminService.getOneCompany(companyId);
				if (company != null) {
					return ResponseEntity.ok(company);
				}
			}
			return ResponseEntity.badRequest().body("company not found! change companyId");

		} catch (CouponException e) {
			throw e;
		}
	}

	@PutMapping("/updateCompany")
	public ResponseEntity<?> updateCompany(@RequestHeader String token, @RequestBody Company company)
			throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				Company company1 = adminService.updateCompany(company);
				return ResponseEntity.ok(company1);
			}
			return ResponseEntity.badRequest().body("company not update!");

		} catch (CouponException e) {
			throw new CouponException("you can't change company name");
		}
	}

	@DeleteMapping("/deleteCompany")
	public void deleteCompany(@RequestHeader String token, @RequestParam Integer companyId) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				adminService.deleteCompny(companyId);

			}
		} catch (CouponException e) {
			throw new CouponException("your company not delete!");
		}
	}

	@GetMapping("/getAllCompany")
	public List<Company> getAllConpany(@RequestHeader String token) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				List<Company> companies = adminService.getAllConpany();
				return companies;
			}
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestHeader String token, @RequestBody Customer customer)
			throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				Customer customer2 = adminService.addCustomr(customer);
				return ResponseEntity.ok().body(customer2);
			}
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);

		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestHeader String token, @RequestBody Customer customer)
			throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(adminService.updateCustomer(customer));
			}
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());

		}
	}

	@GetMapping("/getCustomer")
	public ResponseEntity<?> getCustomer(@RequestHeader String token, @RequestParam Integer idCustomer)
			throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(adminService.getOneCustomer(idCustomer));
			}
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
	}

	@DeleteMapping("/deleteCustomer")
	public void deleteCustomer(@RequestHeader String token, @RequestParam Integer customerId) throws CouponException {
		try {
			System.out.println("================");
			Session session = sessionContext.getSession(token);
			if (session != null) {
				adminService.deleteCustomer(customerId);
			}
		} catch (CouponException e) {
			throw new CouponException("your customer not delete!" + e.getMessage());
		}
	}

	@GetMapping("/getAllCustomer")
	public List<Customer> getAllCustomer(@RequestHeader String token) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				List<Customer> customers = adminService.getAllCustomer();
				return customers;
			}
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

}
