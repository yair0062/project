package app.core.loginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import app.core.service.AdminService;
import app.core.service.CompanyService;
import app.core.service.CustomerService;
import exception.CouponException;

@Component
public class LoginManager {

	@Autowired
	private ApplicationContext applicationContext;

	public ClientService login(String email, String password, ClientType clientType) throws CouponException {

		switch (clientType) {
		case Administrator:
			if (applicationContext.getBean(AdminService.class).login(email, password)) {
				return applicationContext.getBean(AdminService.class);
			}
		case Company:
			if (applicationContext.getBean(CompanyService.class).login(email, password)) {
				return applicationContext.getBean(CompanyService.class);
			}

		case Customer:
			if (applicationContext.getBean(CustomerService.class).login(email, password)) {
				return applicationContext.getBean(CustomerService.class);
			}
		}
		throw new CouponException("The login method failed");
	}

	public LoginManager(ApplicationContext applicationContext) {
		super();
		this.applicationContext = applicationContext;
	}

}
