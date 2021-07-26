package app.core.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.loginManager.ClientService;
import app.core.repository.CopounRepository;
import app.core.repository.CustomerRepository;
import exception.CouponException;

@Service
@Transactional
public class CustomerService implements ClientService {

	private Integer custumerId;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private CopounRepository couponRepo;

	public boolean login(String email, String password) throws CouponException {

		try {
			Customer customer = customerRepo.findByEmailAndPassword(email, password);
			this.custumerId = customer.getId();
			System.out.println(customer);
			System.out.println("Welcome");
			return true;

		} catch (Exception e) {
			throw new CouponException("The methd login fieled, Your email or password is incorrect, sorry!");
		}
	}

	public Coupon purchaseCoupon(Coupon coupon) throws CouponException {
		try {

			Optional<Coupon> op = couponRepo.findById(coupon.getId());
			if (op.isPresent()) {
				List<Coupon> coupons = couponRepo.findByCustomersIdAndId(custumerId, coupon.getId());
				if (!coupons.isEmpty()) {
					throw new CouponException("You can not buy the coupon because you have such a coupon");
				}

				Coupon coupon1 = op.get();
				if (coupon1.getAmount() == 0) {
					throw new CouponException("The coupon is out of stock");
				}
				if (coupon1.getEndDate().isBefore(LocalDate.now())) {
					throw new CouponException("The coupon has expired");
				}
				System.out.println(getCustomerDetails());
				getCustomerDetails().addCoupons(coupon1);
				coupon1.setAmount(coupon1.getAmount() - 1);
				return coupon1;
			}
			throw new CouponException("we dont found coupon!");
		} catch (Exception e) {
			throw new CouponException("The purchaseCoupon method don't working", e);
		}

	}

	public List<Coupon> getCustomerAllCoupon() throws CouponException {
		try {
			List<Coupon> coupons = customerRepo.getOne(custumerId).getCoupons();
			if (coupons.isEmpty()) {
				System.out.println("The customer has no coupons!!");
				return null;
			}
			System.out.println(coupons);
			return coupons;
		} catch (Exception e) {
			throw new CouponException("The getCustomerCoupon failed", e);
		}
	}

	public List<Coupon> getCustomerCouponCategory(Category caterory) throws CouponException {
		try {
			List<Coupon> coupons = couponRepo.findByCustomersIdAndCategory(custumerId, caterory);
			if (coupons == null) {
				throw new CouponException("not found coupons");
			}
			System.out.println(coupons);
			return coupons;

		} catch (Exception e) {
			throw new CouponException("getCustomerCouponCategory methd failed", e);
		}
	}

	public List<Coupon> getCustomerCouponCategoryMaxPrice(double maxPrice) throws CouponException {
		try {
			List<Coupon> coupons = couponRepo.findByCustomersIdAndPriceLessThan(custumerId, maxPrice);
			if (coupons == null) {
				throw new CouponException("not found coupons");
			}
			System.out.println(coupons);
			return coupons;

		} catch (Exception e) {
			throw new CouponException("getCustomerCouponCategoryMaxPrice methd failed", e);
		}
	}

	public Coupon getOneCoupon(Integer id) throws CouponException {
		try {
			Optional<Coupon> op = couponRepo.findById(id);
			if (op.isPresent()) {
				Coupon coupon = op.get();
				System.out.println(coupon);
				return coupon;
			}
			throw new CouponException("We don't found coupon,sorry");

		} catch (Exception e) {
			throw new CouponException("We don't found coupon,sorry", e);
		}

	}

	public Customer getCustomerDetails() throws CouponException {
		Optional<Customer> opt = customerRepo.findById(custumerId);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new CouponException("Customer not found: " + custumerId);
	}

	public Integer getCustomerId() {
		return custumerId;
	}

	public void setCustomerId(Integer companyId) {
		this.custumerId = companyId;
	}

}
