package com.example.wecareclient.dto;

public class RewardItem {
	private int itemId;
	private String image;
	private String discription;
	
	public RewardItem(int itemId, String image, String discription) {
		super();
		this.itemId = itemId;
		this.image = image;
		this.discription = discription;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discription == null) ? 0 : discription.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + itemId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RewardItem other = (RewardItem) obj;
		if (discription == null) {
			if (other.discription != null)
				return false;
		} else if (!discription.equals(other.discription))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (itemId != other.itemId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RewardItem [itemId=" + itemId + ", image=" + image + ", discription=" + discription + "]";
	}
	
	
}
