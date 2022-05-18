package tacos.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;
import tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

//	private RestTemplate rest = new RestTemplate();
//
//	@ModelAttribute(name = "order")
//	public Order order() {
//		return new Order();
//	}
//
//	@ModelAttribute(name = "design")
//	public Taco design() {
//		return new Taco();
//	}
//
//	@GetMapping("/current")
//	public String orderForm(Model model) {
//		model.addAttribute("order", new Order());
//		return "orderForm";
//	}
	private Order oderCurrent = new Order();
	private RestTemplate rest = new RestTemplate();

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "design")
	public Taco design() {
		return new Taco();
	}

//	@GetMapping
//	public String showOrderForm(Model model) {
//		Order order = new Order();
//		
//		
//		model.addAttribute(order);
//		return "orderForm";
//	}
	
	@GetMapping
	public String  tacoById( Model model) {
		Taco taco = rest.getForObject("http://localhost:8080/tacos/current", Taco.class);
		Order order = new Order();
		order.addDesign(taco);
		this.oderCurrent = order;
		model.addAttribute(order);
		return "orderForm";
	}

	@PostMapping
	public String processOrder(
			@RequestParam("deliveryName") String deliveryName,
			@RequestParam("deliveryStreet") String deliveryStreet,
			@RequestParam("deliveryCity") String deliveryCity,
			@RequestParam("deliveryState") String deliveryState,
			@RequestParam("deliveryZip") String deliveryZip,
			@RequestParam("ccNumber") String ccNumber,
			@RequestParam("ccExpiration") String ccExpiration,
			@RequestParam("ccCVV") String ccCVV
			
			) {
		oderCurrent.setDeliveryName(deliveryName);
		oderCurrent.setDeliveryStreet(deliveryStreet);
		oderCurrent.setDeliveryCity(deliveryCity);
		oderCurrent.setDeliveryState(deliveryState);
		oderCurrent.setDeliveryZip(deliveryZip);
		oderCurrent.setCcNumber(ccNumber);
		oderCurrent.setCcExpiration(ccExpiration);
		oderCurrent.setCcCVV(ccCVV);
		
		System.out.println(oderCurrent);
		rest.postForObject("http://localhost:8080/orders", this.oderCurrent, Order.class);

		return "design";
	}

}
