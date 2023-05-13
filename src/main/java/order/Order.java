package order;

public class Order {
	private int orderno;//회원번호
	private String name;//회원이름
	private String tel;//전화번호
	private String price;//주소
	private String item;//주소
	private String orderdate;//가입일
	private String gender;//등급
	private String type;//등급
	private String tbnum;//도시코드
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTbnum() {
		return tbnum;
	}
	public void setTbnum(String tbnum) {
		this.tbnum = tbnum;
	}
}
