package tacos.web.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/orders", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderController {

	private final OrderRepository orderRepo;
//
//	@Autowired
//	public OrderController(OrderRepository orderRepo) {
//		this.orderRepo = orderRepo;
//	}
//
//	@GetMapping("/current")
//	public String orderForm(Model model) {
//		model.addAttribute("order", new Order());
//		return "orderForm";
//	}

//	@Autowired
	EntityLinks entityLinks;

	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping
	public Iterable<Order> recentOrders() {
		return orderRepo.findAll();
	}

	@GetMapping("/{id}")
	public Order Taco_OrderById(@PathVariable("id") Long id) {
		Optional<Order> optOrder = orderRepo.findById(id);
		if (optOrder.isPresent()) {
			return optOrder.get();
		}
		return null;
	}

//	@PostMapping
//	public String processOrder(@Valid Order order, Errors errors) {
//		if (errors.hasErrors()) {
//			return "orderForm";
//		}
//		orderRepo.save(order);
////		log.info("Order submitted: " + order);
//		return "redirect:/";
//	}
	
	
	
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Order postOrder(@RequestBody Order order) {
		return orderRepo.save(order);
	}
	
//	@PutMapping(path = "/{orderId}") 
//	public Order putOrder(@RequestBody Order order) { 
//		return orderRepo.save(order); 
//	} 
}
