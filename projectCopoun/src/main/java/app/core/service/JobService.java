package app.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Coupon;
import app.core.repository.CopounRepository;

@Service
@Transactional
public class JobService {

	@Autowired
	private CopounRepository repository;

	public void deleteCoupon(int couponId) {
		repository.deleteById(couponId);
	}

	public List<Coupon> findAllCoupons() {
		return repository.findAll();
	}

}
