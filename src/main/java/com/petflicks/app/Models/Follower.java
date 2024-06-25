package com.petflicks.app.Models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Follower {

	@Id
	@Column(name="follower_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int followerId;//userid?
	@Column
	private int userId;// who was followed nullable
	@Column
	private int petId;// or pet followed  nullable
	
	
	
	public Follower() {
		super();
	}
	public Follower(int followerId, int userId, int petId) {
		super();
		this.followerId = followerId;
		this.userId = userId;
		this.petId = petId;
	}
	public int getFollowerId() {
		return followerId;
	}
	public void setFollowerId(int followId) {
		this.followerId = followId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(followerId, petId, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Follower other = (Follower) obj;
		return followerId == other.followerId && petId == other.petId && userId == other.userId;
	}
	@Override
	public String toString() {
		return "Follower [followerId=" + followerId + ", userId=" + userId + ", petId=" + petId + "]";
	}



}
