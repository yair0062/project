package app.core.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Category;
import app.core.entities.Coupon;
import exception.CouponException;

public interface CopounRepository extends JpaRepository<Coupon, Integer> {

	List<Coupon> findByCompanyId(Integer companyId);

	List<Coupon> findByCompanyIdAndTitle(Integer companyId, String title);

	List<Coupon> findByCompanyIdAndCategory(Integer companyId, Category caterory);

	List<Coupon> findByCompanyIdAndPriceLessThan(Integer companyId, double price);

	List<Coupon> findByCustomersIdAndCategory(Integer id, Category category) throws CouponException;

	List<Coupon> findByCustomersIdAndPriceLessThan(Integer id, double price);

	List<Coupon> findByEndDateAfter(LocalDate localDate);

	List<Coupon> findByCustomersIdAndId(Integer customerId, Integer couponId);

}
