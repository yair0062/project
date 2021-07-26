package app.core.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import app.core.loginManager.ClientType;
import app.core.loginManager.LoginManager;
import app.core.service.AdminService;
import exception.CouponException;

@Component
public class Test {
	@Autowired
	ApplicationContext ap;

	@Autowired
	LoginManager loginManager;

	public void test() throws CouponException {

//		====================================START ADMIN SERVICE=======================================================
		try {
			AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin",
					ClientType.Administrator);

//			Company company1 = new Company(0, "ttttt", "ttt@admin.com", "555");
//			Company company2 = new Company(0, "google", "google@email", "22222");
//			Company company3 = new Company(0, "whatApp", "whatApp@email", "33333");
//			Company company4 = new Company(0, "FaceBook", "FaceBook@email", "44444");
//			Company company5 = new Company(0, "enstegram", "enstegram@email", "555555");
//			adminService.addCompany(company1);
//			adminService.addCompany(company2);
//			adminService.addCompany(company3);
//			adminService.addCompany(company4);
//			adminService.addCompany(company5);

//			Company company = adminService.getOneCompany(1);
//			System.out.println(company);
//			company.setEmail("666@5555");
//			company.setPassword("444444");
//			adminService.updateCompany(company);
//			adminService.deleteCompny(1);
			adminService.getAllConpany();

//			Customer customer1 = new Customer(0, "yaidr", "H5atuka", "yair@5Hatuka", "111d1111");
//			Customer customer2 = new Customer(0, "tam", "Hatuka", "tam@Hatuka", "22222222");
//			Customer customer3 = new Customer(0, "aila", "Hatuka", "aila@Hatuka", "3333333");
//
//			adminService.addCustomr(customer1);
//			adminService.addCustomr(customer2);
//			adminService.addCustomr(customer3);

//			Customer customer = adminService.getOneCustomer(1);
//			customer.setPassword("1111177711");
//			customer.setFirstName("55555");
//			adminService.updateCustomer(customer);
//			adminService.getAllCustomer();
//			adminService.deleteCustomer(4);
		} catch (Exception e) {
			throw new CouponException("Test filed", e);

		}

//		====================================START COMPANY SERVICE=======================================================
//		CompanyService companyService = (CompanyService) loginManager.login("google@email", "22222",
//				ClientType.Company);

//		Coupon coupon = new Coupon(0, companyService.getCompanyDetails(), Category.ELECTRICITY, "kjjn", "google google",
//				LocalDate.of(2020, 8, 8), LocalDate.of(2028, 8, 8), 100, 100.00, "ffff");
//		companyService.addCoupon(coupon);

//		Coupon coupon = companyService.getOneCoupon(10);
//		coupon.setAmount(500);
//		companyService.updateCoupon(coupon);

//		companyService.deletCoupon(10);

//		companyService.getCompanyCoupons();
//		companyService.getAllCompanyCoupon();
//		companyService.getAllCouponsOfCompanyCaterory(Category.FOOD);
//		companyService.getCompanyCouponsMaxPrice(100);
//		companyService.getCompanyDetails();

//		====================================START CUSTOMER SERVICE=======================================================
//		CustomerService customerService = (CustomerService) loginManager.login("tam@Hatuka", "22222222",
//				ClientType.Customer);

//		customerService.purchaseCoupon(16);
//
//		customerService.getCustomerAllCoupon();
//		customerService.getCustomerCouponCategory(Category.FOOD);
//		customerService.getCustomerCouponCategoryMaxPrice(100);
//		customerService.getCustomerDetails();

	}

}
