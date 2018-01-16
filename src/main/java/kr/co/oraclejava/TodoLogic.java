package kr.co.oraclejava;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//dao

@Component
public class TodoLogic {
	private static final Log log = LogFactory.getLog(TodoLogic.class);
	
	@Autowired
	private SqlSession session;
	
	public List<Todo> list(){
		log.debug("list called");
		return session.selectList("kr.co.oraclejava.todo.list");		
	}
	
	public Todo select(int id) {
		log.debug("select called " + id);
		return session.selectOne("kr.co.oraclejava.todo.select", id);
	}
	
	public void delete(int id) {
		log.debug("delete called " + id);
		session.delete("kr.co.oraclejava.todo.delete", id);
	}
	
	public void update(Todo todo) {
		log.debug("update called "+todo);
		if(todo.getId() > 0) {
			session.update("kr.co.oraclejava.todo.update", todo);
		}
		else{
			session.insert("kr.co.oraclejava.todo.insert", todo);
		}
	}
	
	public void complete(int id) {
		log.debug("complete called " + id);
		session.delete("kr.co.oraclejava.todo.complete", id);
	}
}
