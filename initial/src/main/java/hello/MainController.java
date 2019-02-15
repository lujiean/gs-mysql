package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.User;
import hello.UserRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	@Autowired
	private LjjtestRepository ljjtestRepository;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/update") // Map ONLY GET Requests
	public @ResponseBody String updateUser (@RequestParam Integer id, @RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		if (userRepository.existsById(id)) {
			User n = new User();
			n.setId(id);
			n.setName(name);
			n.setEmail(email);
			userRepository.save(n);
			return "Updated";
		} else {
			return "ID not exists";
		}
	}

	@GetMapping(path="/delete") // Map ONLY GET Requests
	public @ResponseBody String delUser (@RequestParam Integer id) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "Deleted";
		} else {
			return "ID not exists";
		}
	}

	@GetMapping(path="/findByID") // Map ONLY GET Requests
	public @ResponseBody Optional<User> finduserbyid (@RequestParam Integer id) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		return userRepository.findById(id);
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping(path="/findByName")
	public @ResponseBody Iterable<User> findByName(@RequestParam String name){
		return userRepository.findByName(name);
	}

	@GetMapping(path="/findBy2Param")
	public @ResponseBody Iterable<User> findBy2Param(@RequestParam String name, @RequestParam String name2){
		return userRepository.findBy2Param(name, name2);
	}

	@GetMapping(path="/updateBySql")
	public @ResponseBody String updateBySql(@RequestParam Integer id, @RequestParam String name2){
		// userRepository.updateBySql(id, name2);
		userRepository.updateBySql(name2, id);
		return "success";
	}

	@GetMapping(path="/file")
	@ResponseBody
	public String FileProcess(@RequestParam String type, @RequestParam String filename){
		String s = null, outstr = "";
		// File file;
		File file = new File(filename);
		switch (type) {
			case "r":
				// file = new File("C:\\Users\\jiean.a.lu\\Documents\\test\\r.txt");
				// file = new File(filename);
				try {
					BufferedReader bf = new BufferedReader(new FileReader(file));
					while ((s = bf.readLine()) != null) {
						// System.out.println(s);
						outstr = outstr + s + System.getProperty("line.separator");
					}
					bf.close();
				} catch (Exception e) {
					//TODO: handle exception
					return e.getMessage();
				}
				// break;
				return outstr;
			case "w":
				// file = new File("C:\\Users\\jiean.a.lu\\Documents\\test\\w.txt");
				// file = new File(filename);
				try {
					if (!file.exists()) {
						file.createNewFile();
					}
					BufferedWriter bw = new BufferedWriter(new FileWriter(file));
					bw.newLine();
					bw.write("testString");
					bw.close();
				} catch (Exception e) {
					//TODO: handle exception
					return e.getMessage();
				}
				// break;
				outstr="success";
				return outstr;
			default:
				return type + " not support";
		}
		// return "success";
	}

	@GetMapping(path="/ljjall")
	@ResponseBody
	public Iterable<Ljjtest> ljjAll() {
		return ljjtestRepository.findAll();
	}
}