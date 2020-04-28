/**
 * 
 */
package org.bestbuy.aggregator.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.bestbuy.aggregator.mdels.Order;
import org.bestbuy.aggregator.mdels.OrderDetail;
import org.bestbuy.aggregator.mdels.User;
import org.bestbuy.aggregator.service.AggregatorDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author nimitjohri
 *
 */
@Service
public class AggregatorDetailServiceImpl implements AggregatorDetailService {

	@Resource(name = "restTemp")
	private RestTemplate restTemplate;

	@Value("${ORDER_API_URL:http://localhost:8050}")
	private String ordersUrl;

	@Value("${USER_API_URL:http://localhost:8060}")
	private String usersUrl;

	@Override
	public OrderDetail getUserOrderDetails(Integer userId) {
		System.out.println("ORDER HOST URL " + ordersUrl);
		System.out.println("USER HOST URL " + usersUrl);
		OrderDetail orderDetail = new OrderDetail();
		List<Order> ordersDetails = getUserOrders(userId);
		User userDetails = getUserDetail(userId);

		if (userDetails != null) {
			orderDetail.setUser(userDetails);
			if (ordersDetails != null) {
				orderDetail.setOrders(ordersDetails);
			}
			return orderDetail;
		}
		return null;
	}

	public List<Order> getUserOrders(Integer userId) {
		String url = ordersUrl + "/api/v1/orders/" + userId;

		ResponseEntity<Order[]> result = restTemplate.getForEntity(url, Order[].class);
		if (result != null) {
			List<Order> orders = Arrays.asList(result.getBody());
			return orders;
		}
		return null;
	}

	public User getUserDetail(Integer userId) {
		String url = usersUrl + "/api/v1/users/" + userId;
		ResponseEntity<User> resultUser = restTemplate.getForEntity(url, User.class);

		if (resultUser != null) {
			return resultUser.getBody();
		}
		return null;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
