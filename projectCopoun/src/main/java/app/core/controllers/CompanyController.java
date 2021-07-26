package app.core.controllers;

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

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.service.CompanyService;
import app.core.session.Session;
import app.core.session.SessionContext;
import exception.CouponException;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController extends ClientController {

	@Autowired
	private SessionContext sessionContext;
	@Autowired
	private CompanyService companyService;

	@GetMapping("/login")
	@Override
	public String login(String email, String password) throws CouponException {
		try {
			boolean b = companyService.login(email, password);
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

	@PostMapping("/addCoupon")
	public ResponseEntity<?> addCoupon(@RequestHeader String token, @RequestBody Coupon coupon) {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok().body(companyService.addCoupon(coupon));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO SESSION IN ADD COUPON METHOD!");
		} catch (CouponException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestHeader String token, @RequestBody Coupon coupon) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok().body(companyService.updateCoupon(coupon));
			}
			return ResponseEntity.badRequest().body("NO SESSION IN UPDATE COUPON METHOD!");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("/deletCoupon")
	public void deletCoupon(@RequestHeader String token, @RequestParam Integer couponId) {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				companyService.deletCoupon(couponId);
			}

		} catch (CouponException e) {
			ResponseEntity.badRequest().body("the method deletCoupon failed");
		}
	}

	@GetMapping("/getCompanyCoupons")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader String token) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(companyService.getCompanyCoupons());
			}
			return ResponseEntity.badRequest().body("NO SESSION IN GET COMPANY COUPON METHOD!");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/getAllCouponsOfCompanyCaterory")
	public ResponseEntity<?> getAllCouponsOfCompanyCaterory(@RequestHeader String token, Category caterory) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(companyService.getAllCouponsOfCompanyCaterory(caterory));
			}
			return ResponseEntity.badRequest().body("NO SESSION IN getAllCouponsOfCompanyCaterory METHOD!");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/getCompanyCouponsMaxPrice")
	public ResponseEntity<?> getCompanyCouponsMaxPrice(@RequestHeader String token, double maxPrice) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(companyService.getCompanyCouponsMaxPrice(maxPrice));
			}
			return ResponseEntity.badRequest().body("NO SESSION IN getCompanyCouponsMaxPrice METHOD!");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/getOneCoupon")
	public ResponseEntity<?> getOneCoupon(@RequestHeader String token, Integer id) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(companyService.getOneCoupon(id));
			}
			return ResponseEntity.badRequest().body("NO SESSION IN GET ONE COUPON METHOD!");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/getCompanyDetails")
	public ResponseEntity<?> getCompanyDetails(@RequestHeader String token) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(companyService.getCompanyDetails());
			}
			return ResponseEntity.badRequest().body("NO SESSION IN GET COMPANY DETAILS METHOD!");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
