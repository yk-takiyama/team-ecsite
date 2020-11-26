package jp.co.internous.pancake.model.domain.dto;

public class PurchaseHistoryDto {
	
	/* 出力したい情報
	 * purchased_at　購入日時
	 * product_name　商品名
	 * price　価格
	 * product_count　個数
	 * family_name,first_name　宛名
	 * address　住所  */
	
	//　以下フィールド
	
	// tbl_purchase_history.purchased_at
	private String purchasedAt;
	// mst_product.product_name
	private  String productName;
	// mst_product.price
	private int price;
	// tbl_purchase_history.product_count
	private int productCount;
	// mst_destination.family_name,mst_destination.first_name
	private String familyName;
	private String firstName;
	// mst_destinaion.address
	private String address;
	
	
	// 以下getter,setter 
	
	public String getPurchasedAt() {
		return purchasedAt;
	}
	public void setPurchasedAt(String purchasedAt) {
		this.purchasedAt = purchasedAt;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}