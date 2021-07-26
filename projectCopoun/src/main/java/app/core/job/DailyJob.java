package app.core.job;

import java.time.LocalDate;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.core.entities.Coupon;
import app.core.service.JobService;

//@Component
public class DailyJob {

	private Timer timer = new Timer();
	@Autowired
	private JobService jobService;

	private boolean isCouponExpired(Coupon coupon) {
		return LocalDate.now().isAfter(coupon.getEndDate());
	}

	@PostConstruct
	public void init() {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				System.out.println("DailyJob");
				List<Coupon> coupons = jobService.findAllCoupons();
				for (Coupon coupon : coupons) {
					if (isCouponExpired(coupon)) {
						jobService.deleteCoupon(coupon.getId());
					}
				}

			}
		};
		timer.scheduleAtFixedRate(task, 30, TimeUnit.DAYS.toMillis(1));
	}

//	@PreDestroy
	public void destroy() {
		timer.cancel();
		System.out.println("timer for expired coupon removal canceled");
	}

}
