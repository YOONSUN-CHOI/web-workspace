package kr.ac.kopo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HandlerMapping {
	/*
	 * 
	 * 이 기능하는것 만드는 것임
	 * 
	 * case "/list.do": control = new ListController(); break; case "/write.do":
	 * control = new WriteController(); break;
	 */

	private Map<String, Controller> mappings = null;

	public HandlerMapping(String propLoc) {
		
		mappings = new HashMap<String, Controller>();
		Properties prop = new Properties();
		
		try {
			InputStream inStream = new FileInputStream(propLoc); // 원래 여기에 절대경로로 프로퍼티 파일 있음!
			prop.load(inStream);

			Set<Object> keys = prop.keySet();
			for(Object key : keys) {
				String className = prop.getProperty(key.toString()); // kr.ac.kopo.ListController 이렇게 나올꺼임
				
				System.out.println(key+" : "+className); 
				
				// 리플랙션필요. 동적으로 className에 대한 객체 생성을 하여 mappings에 value로 넣어주고싶다.
				Class<?> clz = Class.forName(className);
				
				mappings.put( key.toString(),  (Controller)clz.newInstance() );
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public Controller getController(String uri) {
		return mappings.get(uri);
	}
	
	

}
