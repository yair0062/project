package app.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.service.CustomerService;
import app.core.session.Session;
import app.core.session.SessionContext;
import exception.CouponException;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController extends ClientController {

	@Autowired
	private SessionContext sessionContext;
	@Autowired
	private CustomerService customerService;

	@GetMapping("/login")
	@Override
	public String login(String email, String password) throws CouponException {
		try {
			boolean b = customerService.login(email, password);
			if (b) {
				Session session = sessionContext.createSession();
				session.setAttribute("email", email);
				System.out.println("==========>>>>>>>>" + session.token);
				return session.token;
			}
			throw new CouponException("login failed");

		} catch (CouponException e) {
			throw new CouponException(e.getMessage());

		}
	}

	@PostMapping("/purchaseCoupon")
	public ResponseEntity<?> purchaseCoupon(@RequestHeader String token, @RequestParam Integer couponId)
			throws Exception {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				Coupon coupon2 = customerService.purchaseCoupon(customerService.getOneCoupon(couponId));
				if (coupon2 != null) {
					return ResponseEntity.ok().body(coupon2);
				}
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("NO SESSION!! The coupon expired. purchaseCoupon Method - failed");

		} catch (CouponException e) {
			throw e;
		}
	}

	@GetMapping("/getCustomerCoupons")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				List<Coupon> coupons = customerService.getCustomerAllCoupon();
				return ResponseEntity.ok().body(coupons);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("NO SESSION!! getCustomerCoupons Method - failed");

		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getCustomerCoupons Method - failed");
		}

	}

	@GetMapping("/get_Customer_Coupon_Category")
	public ResponseEntity<?> getCustomerCouponCategory(@RequestHeader String token, @RequestParam Category caterory) {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				List<Coupon> coupons = customerService.getCustomerCouponCategory(caterory);
				return ResponseEntity.ok(coupons);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("NO SESSION!! getCustomerCouponCategory Method - failed");

		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getCustomerCouponCategory Method - failed");
		}

	}

	@GetMapping("/get_Customer_Coupon_Max_Price")
	public ResponseEntity<?> getCustomerCouponCategoryMaxPrice(@RequestHeader String token, Integer maxPrice) {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				List<Coupon> coupons = customerService.getCustomerCouponCategoryMaxPrice(maxPrice);
				return ResponseEntity.ok(coupons);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("NO SESSION!! getCustomerCouponCategoryMaxPrice Method - failed");

		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"getCustomerCouponCategoryMaxPrice Method - failed");
		}

	}

	@GetMapping("/getCustomerDetails")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader String token) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(customerService.getCustomerDetails());
			}
			return ResponseEntity.badRequest().body("NO SESSION IN GET Customer DETAILS METHOD!");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e);
		}
	}

	@GetMapping("/get_One_Coupon")
	public ResponseEntity<?> getOneCoupon(@RequestHeader String token, @RequestParam Integer id) {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(customerService.getOneCoupon(id));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO SESSION!! getOneCoupon Method - failed");

		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getOneCoupon Method - failed" + e.getMessage());
		}

	}

}