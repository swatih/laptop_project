package demoapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import demoapp.model.Address;
import demoapp.model.Item;
import demoapp.model.Product;
import demoapp.model.Cart;
import demoapp.service.ProductService;

@Controller
@Component
public class CartController {

	@Autowired
	private ProductService service;
	Cart cart;


	@RequestMapping("/Order/{pid}")
	public String ordernow(@PathVariable(value = "pid") int pid, ModelMap mm, HttpSession session) {

		if (session.getAttribute("cart") == null) {
			List<Item> listcart = new ArrayList();
			cart = new Cart();

			listcart.add(new Item(this.service.getProductById(pid), 1));
			cart.setListitem(listcart);
			session.setAttribute("cart", cart);
		} else {
			cart = (Cart) session.getAttribute("cart");
			List<Item> listcart = cart.getListitem();
			// using method isExisting here
			int index = isExisting(pid, listcart);
			if (index == -1)
				listcart.add(new Item(this.service.getProductById(pid), 1));
			else {
				int quantity = listcart.get(index).getQuantity() + 1;
				listcart.get(index).setQuantity(quantity);
			}
			cart.setListitem(listcart);
			session.setAttribute("cart", cart);
		}
System.out.println("redirect");
		return "redirect:/memberShip"; // page name
	}

	public Cart initFlow() {
		// System.out.println(product.getPname());
		System.out.println("Data sply to flow");
		return cart;

	}

	public Address getaddress() {
		// System.out.println(product.getPname());
		System.out.println("Data sply to flow");
		return new Address();

	}

	
	@RequestMapping(value = "/delete/{pid}")
	public String delete(@PathVariable(value = "pid") int pid, HttpSession session,Model m) {
		cart = (Cart) session.getAttribute("cart");

		List<Item> listcart = (List<Item>) cart.getListitem();

		int index = isExisting(pid, listcart);
		listcart.remove(index);
		cart.setListitem(listcart);

		session.setAttribute("cart", cart);
		System.out.println("delete");
	
		
	return "redirect:http://localhost:8080/autobazaar/memberShip";
	
	}
	
	
	
	@SuppressWarnings("unchecked")
	private int isExisting(int id, List<Item> pcart) {

		for (int i = 0; i < pcart.size(); i++)

			if (pcart.get(i).getP().getId() == id)
				return i;
		return -1;
	}

}
