package com.spring.boot.rest.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dao.FactorialRepository;
import com.spring.boot.dao.entity.FactEntity;
import com.spring.boot.rest.api.model.FactMessage;

@RestController
public class FactorialController {
	
	@Autowired
	private FactorialRepository factorialRepository;

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
	@GetMapping("/facts/{num}")
	public FactMessage computeFact(@PathVariable("num") int num) {
		int sum=1;
		if(!results.containsKey(num)) {
			for(int x=2;x<=num;x++){
				sum=sum*x;
			}
			results.put(num, sum);
		}
		
		FactEntity entity=new FactEntity();
		entity.setNum(num);
		entity.setResult(sum+"");
		entity.setVersion(1);
		entity.setDoc(new Date());
		entity.setDescription("This is auto generated message");
		factorialRepository.save(entity);
		
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
	
	
	/**
	 * @param num
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@DeleteMapping("/facts/{num}")
	public FactMessage deleteFatc(@PathVariable("num") int num) {
				results.remove(num);
			    FactMessage factMessage=new FactMessage();
				factMessage.setDoc(new Date());
				factMessage.setNum(num);
				factMessage.setResult(results.get(num)+"");
				factMessage.setStatus("success");
				factMessage.setMessage("fact is delete successfully.............");
				return factMessage;
	}
	
	
	@GetMapping("/factimage/{fid}")
	public void findImageByFid(@PathVariable("fid") int fid,HttpServletResponse response) throws IOException{
		response.setContentType("image/png");
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("math.png").getFile());
		byte[] array = Files.readAllBytes(file.toPath());
		ServletOutputStream outputStream=response.getOutputStream();
		outputStream.write(array);
		outputStream.flush();
		outputStream.close();
	}
	
}
