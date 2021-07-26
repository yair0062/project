package app.core.loginManager;

import exception.CouponException;

public interface ClientService {

	public abstract boolean login(String email, String password) throws CouponException;

}
