package com.example.wecareclient.dto;

import java.util.Date;
import java.util.Set;

/**
 * @author shgu
 *
 */
public class Item {
	

	private static int autoId = 1;
	private int itemId;
	private String name;//product name 
	private double price;//product price which is equivalent to "points" in the task system  
	private int inventory;//product inventory state
	private String description;//product description
	private Set<String> imageSet;//product image set whose image should be processed into the same format
	private static int totalSalesNumber;//how many such item was sold
	private double rate;//user rating
	private String thumbnail64_64;//small thumbnail
	private String thumbnail128_128;//big thumbnail
	private Date publishedDate;//the date when this item was published, this is used to indicate the freshness of item
	private Set<Topic> belongingTopics;//belonging topics like <Baby food> <vegetarian>
	private Set<Tag> ownedTags;//tags include "new" "trending" "on sales"
	
	
	public int getId(){
		return itemId;
	}
	
	public void setId(int id){
		this.itemId = id;
	}
	
	
	public String getName() {
		return name;
	}

	public void setItmeName(String itmeName) {
		this.name = itmeName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getImageSet() {
		return imageSet;
	}

	public void setImageSet(Set<String> imageSet) {
		this.imageSet = imageSet;
	}

	public static int getTotalSalesNumber() {
		return totalSalesNumber;
	}

	public static void setTotalSalesNumber(int totalSalesNumber) {
		Item.totalSalesNumber = totalSalesNumber;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getThumbnail64_64() {
		return thumbnail64_64;
	}

	public void setThumbnail64_64(String thumbnail64_64) {
		this.thumbnail64_64 = thumbnail64_64;
	}

	public String getThumbnail128_128() {
		return thumbnail128_128;
	}

	public void setThumbnail128_128(String thumbnail128_128) {
		this.thumbnail128_128 = thumbnail128_128;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Set<Topic> getBelongingTopics() {
		return belongingTopics;
	}

	public void setBelongingTopics(Set<Topic> belongingTopics) {
		this.belongingTopics = belongingTopics;
	}

	public Set<Tag> getOwnedTags() {
		return ownedTags;
	}

	public void setOwnedTags(Set<Tag> ownedTags) {
		this.ownedTags = ownedTags;
	}
	
	
	
	public Item() {
		super();
	}

	public Item(String itmeName, double price, int inventory, String description, Set<String> imageSet, double rate,
			String thumbnail64_64, String thumbnail128_128, Date publishedDate, Set<Topic> belongingTopics) {
		super();
		this.itemId = autoId++;
		this.name = itmeName;
		this.price = price;
		this.inventory = inventory;
		this.description = description;
		this.imageSet = imageSet;
		this.rate = rate;
		this.thumbnail64_64 = thumbnail64_64;
		this.thumbnail128_128 = thumbnail128_128;
		this.publishedDate = publishedDate;
		this.belongingTopics = belongingTopics;
	}
	
	public Item(String name,double price){
		this.itemId = autoId++;
		this.name = name;
		this.price = price;
	}
	
	
	public Boolean checkInvebtory(){
		return true;
	}
	
	public void checkUpdate(){
		
	}
	
	
	public void computeHash(){
		
	}
	
	
	private class Tag{
		
	}
	
	
}
