package com.zlebank.zplatform.manager.action.merch;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.trade.chanpay.bean.cj.G40003Bean;
import com.zlebank.zplatform.trade.chanpay.service.ChanPayService;
import com.zlebank.zplatform.trade.chanpay.service.impl.CjMsgSendService.sendAndGetBytes_Response;
import com.zlebank.zplatform.trade.chanpay.utils.U;
import com.zlebank.zplatform.trade.utils.ConsUtil;
import com.zlebank.zplatform.trade.utils.TimeUtil;

public class DownloadAction extends BaseAction  implements ServletResponseAware{
    private static final long serialVersionUID = -3963094287299215112L;
    private static final Log log = LogFactory.getLog(DownloadAction.class);
    @Autowired
    private ChanPayService chanPayService;
    private HttpServletResponse response;
    private String billDate;

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		 this.response = response;
	}

	public void downChanBil(){
    	log.info("下载对账单开始:");
    	sendAndGetBytes_Response  resData = null;
    	OutputStream out=null;
    	byte[] array= {};
    	try {
    		if(StringUtil.isEmpty(billDate)){
        		resData=null;
        	}else{
        		G40003Bean data= new G40003Bean();
        		data.setMertid(ConsUtil.getInstance().cons.getChanpay_cj_merchant_id());
    			//对账文件类--普通
    			data.setBillType(G40003Bean.BILL_TYPE);
    			 SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.SIMPLE_DATE_FROMAT);
                 String date = sdf.format(DateUtil.convertToDate(
                         billDate, "yyyy-MM-dd"));
    			 //“按日”下载对账文件
    			data.setBillDay(date);
    			data.setReqSn(U.createUUID());
    			//调对账接口
    			resData=this.chanPayService.getRecAccFile(data);
    			log.info("获取对账文件内容"+resData);
        	}
    		if(resData!=null){
    			 //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
                response.setContentType(null==resData.contentType?"multipart/form-data":resData.contentType);  
                //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
                response.setHeader("Content-Disposition", null==resData.contentDisposition? "attachment;fileName="+"error.txt":resData.contentDisposition); 
                if(resData.data.length>0){
                	array=resData.data;
                }
    		}
    		out = response.getOutputStream(); 
        	out.write(array);
        	out.flush();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}finally{
			log.info("下载对账单结束");
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		}
    
    
    }


   

}
