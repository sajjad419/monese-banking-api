package com.monese.api.banking.pojo;

import java.io.Serializable;
import java.util.List;

import com.monese.api.banking.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author C.SajjadHussain
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ResponseList implements Serializable {

	private static final long serialVersionUID = 1329485527182305935L;
   
	private User user;
	private List<?> items;

	private Integer total;

	public List<?> getItems() {
		return items;
	}

	
	
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public void setItems(List<?> items) {
		this.items = items;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	
}