package com.spring.boot.rest.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.rest.api.model.FactMessage;

@RestController
public class FactorialController {

	private Map<Integer,Integer> results=new HashMap<>();
	
	@PostConstruct
	public void mockdata(){
		results.put(2,2);
		results.put(3,6);
		results.put(4,24);
		results.put(5,120);
	}
	
	
	/**
	 * @param num
	 * @return
	 */
	@CrossOrigin(origins = "*")
	//Here we are sending data as a pert of URI
	@GetMapping("/facts/{num}")
	public FactMessage computeFact(@PathVariable("num") int num) {
		if(!results.containsKey(num)) {
			int sum=1;
			for(int x=2;x<=num;x++){
				sum=sum*x;
			}
			results.put(num, sum);
		}
		
		FactMessage factMessage=new FactMessage();
		factMessage.setDoc(new Date());
		factMessage.setNum(num);
		factMessage.setResult(results.get(num)+"");
		factMessage.setStatus("success");
		factMessage.setMessage("fact is computed successfully.............");
		factMessage.setVersion(1);
		return factMessage;
	}
	
	
	/**
	 * @param num
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@GetMapping("/facts")
	public List<FactMessage> findFacts() {
		List<FactMessage> messagesList=new ArrayList<>();
		 Set<Map.Entry<Integer, Integer>> entiries=results.entrySet();
		 for(Map.Entry<Integer, Integer> entry:entiries){
			    FactMessage factMessage=new FactMessage();
				factMessage.setDoc(new Date());
				factMessage.setNum(entry.getKey());
				factMessage.setResult(entry.getValue()+"");
				factMessage.setStatus("success");
				factMessage.setMessage("fact is computed successfully.............");
				factMessage.setVersion(1);
				messagesList.add(factMessage);
		 }
		return messagesList;
	}
	
}
