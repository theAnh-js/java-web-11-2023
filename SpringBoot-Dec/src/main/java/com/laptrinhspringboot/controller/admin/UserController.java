package com.laptrinhspringboot.controller.admin;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laptrinhspringboot.dto.UserDTO;
import com.laptrinhspringboot.service.IUserService;
import com.laptrinhspringboot.utils.FileUploadUtil;
import com.laptrinhspringboot.utils.HashUtil;

@Controller
@RequestMapping("/admin/users")
public class UserController {
	
	@Autowired
	FileUploadUtil fileUploadUtil;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ServletContext servletContext;
			
	@Autowired
	IUserService userService;
	
	@RequestMapping
	public String index(Model model) {// IoC container giúp ta khởi tạo Model và tiêm vào tham số hàm.
		
		List<UserDTO> list = userService.findAll();
		
		
		// paging
		String pageNumberStr = request.getParameter("page");
		if(pageNumberStr == null) pageNumberStr = "1";
		int pageNumber = Integer.parseInt(pageNumberStr);
		int limit = 2;
		int totalPage = list.size() % limit == 0 ? list.size() / limit : (list.size() / limit) + 1;
		
		// sort by, order by
		String sortByStr = request.getParameter("sort_by");
		String orderBy = request.getParameter("sort_direction");
		
		if(orderBy == null) orderBy = "asc";
		
		if(sortByStr != null) {
			// vì tham số tại pageNumber nhận vào từ 0, 
			// nên nếu trên url page=1, thì sẽ ra trang thứ 2, nên ta chủ động trừ -1
			// do đó, nếu page=1 trên url thì ở đây sẽ hiểu là page = 1-1 = 0 để lấy cho ta trang đầu tiên.
			// Nếu không, để lấy trang đầu tiên ta phải page = 0 trên url thì không đẹp lắm.
			list = userService.findALLBySort(pageNumber - 1, limit, sortByStr, orderBy);
		}	
		
		model.addAttribute("sortBy", sortByStr);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", pageNumber);
		
		model.addAttribute("allUsers", list);
				
		return "admin/user/index";
	}
	
	@GetMapping(params="id") // có id thì vào hàm show
	public String show(@RequestParam(name="id",defaultValue = "", required = true) Integer id,
			Model model) { // bóc id từ url ra http://localhost:8080/admin/users?id=1
		
		UserDTO userDTO = userService.findOne(id);
		model.addAttribute("user", userDTO);
		return "admin/user/show";
	}
	
	//========= tạo mới và lưu trữ============
	
	@GetMapping(value="/create")
	public String create(Model model) {
		
		// Nếu không add new UserDTO() vào thì ModelAttribute("user") không được binding, khởi tạo
		// ==> error: Neither BindingResult nor plain target object for bean name 'user' available as request attribute
		model.addAttribute("user", new UserDTO());
		
		return "admin/user/create";
	}
		
	@PostMapping(value="/store")
	public String store(@ModelAttribute("user") UserDTO user) {
		
		String hashedPass = HashUtil.hash(user.getPassword());
		user.setPassword(hashedPass);
		
		userService.insertUser(user);
		return "redirect:/admin/users";	
	}
	//========= tạo mới và lưu trữ============

	
	//========= sửa và cập nhập ============
	@GetMapping(value="/edit", params="id")
	public String edit(Model model, @RequestParam("id") int id) {
		
		UserDTO userDTO = userService.findOne(id);
		
		model.addAttribute("user", userDTO);
		
		return "admin/user/edit";
	}
	
	// bên jsp dùng form:form với ModelAttribute nó tự binding cho mình vào biến user trong tham số của hàm update
	// nên không cần lấy tham số id để lấy đối tượng nữa.
	@PostMapping(value="/update") 
	public String update(@ModelAttribute("user") UserDTO user) {
		userService.saveUser(user);
		return "redirect:/admin/users";
	}

	// Hàm update này cần lấy id từ request, dùng ModelAttribute như trên tiện hơn.
	//@PostMapping(value="/update", params = "id")
	public String updateV0(
			Model model,
			@Validated UserDTO user,
			BindingResult result
			) {  
		if(result.hasErrors()) {
			return "redirect:/admin/users/edit?id="+user.getId();
		}else {
			userService.saveUser(user);
			return "redirect:/admin/users";
		}
	}
	//========= sửa và cập nhập ============
	
	
	//========= Xóa ============
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		UserDTO user = userService.findOne(id);
		userService.deleteUser(user);
		
		return "redirect:/admin/users";
	}
	
	/*
	 * @PostMapping(value="/update", params="id") public String
	 * update(@RequestParam(name="id") Optional<Integer> id) { // Optional<> để báo
	 * cho spring biết, tham số id có thể có hoặc không.
	 * 
	 * //cũng có thể dùng HttpServletRequest để lấy value của param //String id =
	 * request.getParameter("id");
	 * 
	 * return "redirect:/admin/users"; }
	 */
	
	/*
	 * @GetMapping(value = "/hi") public String hello() { return "file-upload"; }
	 */
	
	//@PostMapping(value = "/upload-file")
	//@RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    @PostMapping("/upload-file")
	public String uploadFileHello(@RequestParam("file") MultipartFile file) {

		//System.out.println(file.getOriginalFilename());
		fileUploadUtil.handleUploadFile(file);
		
		return "redirect:/admin/users";
	}
}
