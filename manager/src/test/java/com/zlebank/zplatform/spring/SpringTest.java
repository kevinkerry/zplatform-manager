package com.zlebank.zplatform.spring;

import java.text.ParseException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.manager.service.iface.IMerchDetaService;

public class SpringTest {
	private ApplicationContext context;
	
	
	public void test(){
		context = new ClassPathXmlApplicationContext("/spring/*");
		IMerchDetaService merchDetaService =(IMerchDetaService) context.getBean("merchDetaService");
		try {
			merchDetaService.merchAudit(null, "3","1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");  
	  
 public static void main(String[] args) throws ParseException {
     
    try {
         Integer  i=null;
         i=1;
        System.out.println(i/0);
   } 
    catch (ArithmeticException e) {
       System.out.println("除零错误");
    }
   
    catch (Exception e){
        System.out.println("空指针");
    }
     
     
     
/*     DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date d1 = df.parse("2004-03-26 13:31:00");

     Date d2 = df.parse("2004-03-26 13:55:00");
     
     System.out.println((d2.getTime()-d1.getTime())/60/1000);*/
   /*  String strs = "1+2-2";  
 
         try {
            System.out.println(jse.eval(strs));
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
     */
/*     String a="a-b+c";
     String s=a.replaceAll("a", "本金").replaceAll("b", "佣金")
             .replaceAll("c", "手续费").replaceAll( "\\+","样");
     
     System.out.println(s);*/
     
     String a="T001";
     String b="Y101";
//     if ("T".equals(a.charAt(0)) {
//         
//     }
    
   //  System.out.println(SpringTest.IsLetter(b));
}

 
 
 

}