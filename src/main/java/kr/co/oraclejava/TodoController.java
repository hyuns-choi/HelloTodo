package kr.co.oraclejava;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/todo")
public class TodoController {
	
	private static final Log log = LogFactory.getLog(TodoController.class);
	
	@Autowired
	private TodoLogic logic;
	
	@ModelAttribute("todo")
	public Todo createTodo() {
		return new Todo();
	}
	
	@RequestMapping({"", "/list"})
	@Transactional(readOnly=true)
	public String index(Model model) {
		log.debug("index");
		List<Todo> list = logic.list();
		model.addAttribute("list", list);
		return "todo";
	}
	
	@RequestMapping("/update")
	@Transactional
	public String update(Todo todo, Model model) {
		log.debug("update");
		
		logic.update(todo);
		model.addAttribute("result", "success");
		
		return index(model);
	}	
	
	@RequestMapping("/select")
	@Transactional(readOnly=true)
	public String update(@RequestParam int id, Model model) {
		log.debug("select");
		
		Todo todo = logic.select(id);
		model.addAttribute(todo);		//"tode", todo
		
		return index(model);
	}	
	
	@RequestMapping("/complete")
	@Transactional
	public String complete(@RequestParam int id, Model model) {
		log.debug("update");
				
		logic.complete(id);
		model.addAttribute("result", "success");
		
		return index(model);
	}	
	
	@RequestMapping("/delete")
	@Transactional
	public String delete(@RequestParam int id, Model model) {
		log.debug("update");
				
		logic.delete(id);
		model.addAttribute("result", "success");
		
		return index(model);
	}	
}
