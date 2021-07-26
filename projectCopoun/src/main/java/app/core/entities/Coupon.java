package app.core.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "companyId")
	private Company company;
	private Category category;
	private String title;
	private String discription;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private double price;
	private String image;
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "Customer_Coupon", joinColumns = { @JoinColumn(name = "coupon_Id") }, inverseJoinColumns = {
			@JoinColumn(name = "customer_Id") })
	@JsonIgnore
	private List<Customer> customers;

	public Coupon(Integer id, Company companyId, Category category, String title, String discription,
			LocalDate startDate, LocalDate endDate, int amount, double price, String image) {
		super();
		this.id = id;
		this.company = companyId;
		this.category = category;
		this.title = title;
		this.discription = discription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void addCustomer(Customer customer) {
		if (this.customers == null) {
			this.customers = new ArrayList<Customer>();
		}
		this.customers.add(customer);
	}

	public void addCustomr2(Customer customer) {
		customers.add(customer);
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyId=" + company + ", category=" + category + ", title=" + title
				+ ", discription=" + discription + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

//	public int setCategory1Id(int categoryId) {
//		this.companyId = getCategoryId().ordinal();
//		return categoryId = getCategoryId().ordinal();
//	}

}
