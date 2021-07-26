package app.core.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.loginManager.ClientService;
import app.core.repository.CompanyRepository;
import app.core.repository.CopounRepository;
import exception.CouponException;

@Service
@Transactional
public class CompanyService implements ClientService {

	private Integer companyId;
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private CopounRepository couponRepo;

	public boolean login(String email, String password) throws CouponException {
		try {
			Company company = companyRepo.findByEmailAndPassword(email, password);
			System.out.println(company);
			this.companyId = company.getId();
			System.out.println("Welcome");
			return true;

		} catch (Exception e) {
			throw new CouponException("Your email or password is incorrect, sorry!", e);
		}

	}

	public Coupon addCoupon(Coupon coupon) throws CouponException {
		try {
			System.out.println(coupon + "==============>>>>>>");
			List<Coupon> coupons = couponRepo.findByCompanyIdAndTitle(companyId, coupon.getTitle());

			if (coupons.isEmpty() && LocalDate.now().isBefore(coupon.getEndDate())) {
				coupon.setCompany(getCompanyDetails());
				couponRepo.save(coupon);
				System.out.println("coupon added");
				return coupon;
			} else {
				throw new CouponException("We can't added coupon, change your title or your coupon expired");
			}

		} catch (Exception e) {
			throw new CouponException("We can't added coupon, change your title or your coupon expired", e);
		}
	}

	public Coupon updateCoupon(Coupon coupon) throws CouponException {
		try {
			Optional<Coupon> coupon1 = couponRepo.findById(coupon.getId());
			if (coupon1.isEmpty()) {
				throw new CouponException("not coupon found");
			} else {
				Coupon coupon2 = coupon1.get();
				coupon2.setAmount(coupon.getAmount());
				coupon2.setCategory(coupon.getCategory());
				coupon2.setDiscription(coupon.getDiscription());
				coupon2.setEndDate(coupon.getEndDate());
				coupon2.setImage(coupon.getImage());
				coupon2.setPrice(coupon.getPrice());
				coupon2.setStartDate(coupon.getStartDate());
				coupon2.setTitle(coupon.getTitle());
				System.out.println("Coupon details updated");
				return coupon2;
			}

		} catch (Exception e) {
			throw new CouponException("The methd updateCoupon failed", e);

		}
	}

	public void deletCoupon(Integer id) throws CouponException {
		try {
			couponRepo.deleteById(id);

		} catch (Exception e) {
			throw new CouponException("the method deletCoupon failed", e);

		}
	}

	public List<Coupon> getCompanyCoupons() throws CouponException {
		try {
			List<Coupon> coupons = couponRepo.findByCompanyId(companyId);

			for (Coupon coupon : coupons) {
				System.out.println(coupon);
			}
			return coupons;

		} catch (Exception e) {
			throw new CouponException("the method getCompanyCoupons failed", e);
		}

	}

	public List<Coupon> getAllCouponsOfCompanyCaterory(Category caterory) throws CouponException {
		try {
			List<Coupon> list = couponRepo.findByCompanyIdAndCategory(companyId, caterory);
			if (list != null) {
				for (Coupon coupon : list) {
					System.out.println(coupon);
				}
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new CouponException("the method getAllCouponsOfCompanyCaterory failed", e);

		}
	}

	public List<Coupon> getCompanyCouponsMaxPrice(double maxPrice) throws CouponException {
		try {
			List<Coupon> list = couponRepo.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
			if (list != null) {
				for (Coupon coupon : list) {
					System.out.println(coupon);
					return list;
				}
				throw new CouponException("NOT FOUND!");
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new CouponException("the method getCompanyCouponsMaxPrice failed", e);
		}
	}

	public Coupon getOneCoupon(Integer id) throws CouponException {
		Optional<Coupon> opt = couponRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new CouponException("coupon not found: " + id);
	}

	public Company getCompanyDetails() throws CouponException {
		Optional<Company> opt = companyRepo.findById(companyId);
		if (opt.isPresent()) {
			System.out.println(opt.get());
			return opt.get();
		}
		throw new CouponException("company not found");
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
