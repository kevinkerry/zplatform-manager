/* 
 * ReanExcle.java  
 * 
 * version TODO
 *
 * 2015年12月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 读取excle
 *
 * @author yangpeng
 * @version
 * @date 2015年12月11日 下午3:35:47
 * @since 
 */
public class ReadExcle {
    
    
    /**
     * 读取excel
     * @param sheets ecxle的sheet名
     * @param file 
     * @return
     * @throws BiffException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static List<String[]> readExcle(    int number,File file) throws BiffException, IOException{
      //创建一个list 用来存储读取的内容
        @SuppressWarnings("rawtypes")
        List list = new ArrayList();
        Workbook rwb = null;
        Cell cell = null;
        
        //创建输入流
        InputStream stream = new FileInputStream(file);
        
        //获取Excel文件对象
        rwb = Workbook.getWorkbook(stream);
    
  
        //获取文件的指定工作表 默认的第一个
        Sheet sheet = rwb.getSheet(number);  
        
  
        //行数(表头的目录不需要，从1开始)
        for(int i=1; i<sheet.getRows(); i++){
         
         //创建一个数组 用来存储每一列的值
         String[] str = new String[sheet.getColumns()];
   
         //列数
         for(int j=0; j<sheet.getColumns(); j++){
        
          //获取第i行，第j列的值
          cell = sheet.getCell(j,i);    
          
          str[j] = cell.getContents();
          
         }
         //把刚获取的列存入list
         list.add(str);
        
        
        }
        stream.close();
        rwb.close();
        return list;
        }    

    

}
