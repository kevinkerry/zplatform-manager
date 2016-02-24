package com.zlebank.zplatform.manager.action.merch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.dao.object.UploadLogModel;
import com.zlebank.zplatform.manager.dao.object.UserModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

public class UploadAction extends BaseAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3963094287299215112L;
    private File[] upload;
    private String[] uploadFileName;
    private String instiid;
    private String startDate;
    private String endDate;
    private String filestartid;
    private String memberId;
    private String memberName;
    private String acctCode;
    private String accNo;
    private ServiceContainer serviceContainer;
    private String falg;
    
    
    
    
  
    public String getFalg() {
        return falg;
    }
    public void setFalg(String falg) {
        this.falg = falg;
    }
    public String show() {
        return "upload_file";
    }
    public String showACC() {
        return "member_acc";
    }
    public String showStartCheck() {
        return "StartCheck_file";
    }
    public String showMemberQuery() {
        return "member_manager";
    }
    // 会员分页查询
    public String queryMerchByPage() {

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("memberId", memberId);
        variables.put("memberName", memberName);
        if("person".equals(falg)){
        //个人会员查询
        variables.put("membertype", BusinessActorType.INDIVIDUAL.getCode());
        }else  if("merch".equals(falg)){
            variables.put("membertype", BusinessActorType.ENTERPRISE.getCode());
        }
        Map<String, Object> memberList = serviceContainer.getUploadlogService()
                .findMemberByPage(variables, getPage(), getRows());
        json_encode(memberList);
        return null;
    }
    //会员账户查询
    public String queryACCByPage() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("memberId", memberId);
        variables.put("accNo", accNo);
        Map<String, Object> memberList = serviceContainer.getUploadlogService()
                .findMemberACCByPage(variables, getPage(), getRows());
        json_encode(memberList);
        return null;
    }
 //---------------------------------------------------------------对账-----------------------------------------------------------------------------   
    // 开始对账
    public String StartCheckFile() {
        List<?> list = serviceContainer.getUploadlogService().StartCheckFile(
                filestartid);
        try {
            json_encode(list);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    // 保存任务
    public String saveProcess() {
        List<?> list = serviceContainer.getUploadlogService().saveProcess(
                instiid);
            json_encode(list.get(0));

        return null;
    }
    /**
     * 任务分页查询（新增任务，开始对账）
     * 
     * @return
     */
    public String queryProcess() {

        Map<String, Object> variables = new HashMap<String, Object>();
        if (startDate != null && !startDate.equals("")) {
            variables.put("startDate", startDate.replace("-", ""));
        }
        if (endDate != null && !endDate.equals("")) {
            variables.put("endDate", endDate.replace("-", ""));
        }
        Map<String, Object> processList = serviceContainer
                .getUploadlogService().findProcessByPage(variables, getPage(),
                        getRows());
        json_encode(processList);
        return null;
    }

    // 上传对账文件
    public String upload() throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        BufferedReader brfile = new BufferedReader(new FileReader(upload[0]));
        String newline = "";// 读取一行
        Object[] fileNameob;// 文件名称按照_拆分
        List<Map<String, Object>> resultMark = null;// 保存对账数据后，返回的标记
        try {
            // 判断文件的类型（证联or中信）
            if (uploadFileName[0] != null) {

                // 判断机构与对账文件是否一致，（98000001：证联）（ 97000001：中信）（96000001：融宝）
                if (uploadFileName[0].contains("Reconciliation")&& instiid.equals("98000001")
                		|| uploadFileName[0].contains("MTTXN")&& instiid.equals("97000001")||uploadFileName[0].contains("Excel")&& instiid.equals("96000001")) {
                } else {
                    result.put("info", "上传文件类型与机构不符！");
                    json_encode(result);
                    return null;
                }
            } else {
                result.put("info", "请上传对账文件！");
                json_encode(result);
                return null;
            }

            // 判断是否重复上传文件
            List<?> bnktxnlist = serviceContainer.getBnktxnService()
                    .queryByHQL("from UploadLogModel where filename=? and recode='00' ",
                            new Object[]{uploadFileName[0]});
            if (bnktxnlist.size() > 0) {
                result.put("info", "此对账文件已经上传过！");
                json_encode(result);
                return null;
            } else {
                UploadLogModel ulm = new UploadLogModel();
                ulm.setLogid(1l);
                ulm.setFilename(uploadFileName[0]);
                ulm.setUploaderid(getCurrentUser().getUserId());
                ulm.setUploadername(getCurrentUser().getUserName());
                serviceContainer.getUploadlogService().save(ulm); //保存任务
            }
            // 解析对账文件内容
            if (instiid.equals("98000001")) {
                int i = 0;
                BnkTxnModel bnk_zl = new BnkTxnModel();
                while ((newline = brfile.readLine()) != null
                        && !newline.equals("")) {
                    if (i == 0) {
                        i = i + 1;
                        continue;
                    } else {
                        fileNameob = uploadFileName[0].split("_");
                        bnk_zl = MackBnkTxn_zhenglian(newline,
                                fileNameob[1].toString());
                        resultMark = serviceContainer.getBnktxnService()
                                .saveBnkTxn(bnk_zl);
                    }
                }
                // 等对账数据保存成功后，更新UPload表的上传数据状态
                serviceContainer.getBnktxnService().updateByHQL(
                        "update UploadLogModel set recode='00' where filename=? ", new Object[]{uploadFileName[0]});
                result.put("info", "对账文件上传成功！");
                json_encode(result);
            } else if((instiid.equals("97000001"))) {
                int y = 0;
                BnkTxnModel bnk_zx = new BnkTxnModel();
                while ((newline = brfile.readLine()) != null
                        && !newline.equals("")) {
                    if (y <= 6) {
                        y = y + 1;
                        continue;
                    } else {
                        bnk_zx = MackBnkTxn_zhongxin(newline);
                        resultMark = serviceContainer.getBnktxnService()
                                .saveBnkTxn(bnk_zx);
                    }
                }
                // 等对账数据保存成功后，更新UPload表的上传数据状态
                serviceContainer.getBnktxnService().updateByHQL(
                        "update UploadLogModel set recode='00' where filename=? ",new Object[]{uploadFileName[0]});
                result.put("info", "对账文件上传成功！");
                json_encode(result);
            }else if((instiid.equals("96000001"))) {//融宝
            	String infoRuslt=batchUpload();
            	result.put("info", infoRuslt);
                json_encode(result);
                if(infoRuslt.equals("")){
                	serviceContainer.getBnktxnService().updateByHQL(
                            "update UploadLogModel set recode='00' where filename=? ",new Object[]{uploadFileName[0]});
                }
                
            }
        } finally {
            if (brfile != null) {
                brfile.close();
            }
        }
        return null;
    }
    // 证联对账文件：读取一行对账文件数据
    public BnkTxnModel MackBnkTxn_zhenglian(String newline, String merchNo) {
        BnkTxnModel bnk = new BnkTxnModel();
        Object[] obzl = newline.replace(" ", "").split("\\|");
        bnk.setPayordno(obzl[0].toString());
        bnk.setSystrcno(obzl[1].toString());
        bnk.setPan(obzl[7].toString());
        bnk.setAcqsettledate(obzl[9].toString());
        bnk.setMerchno(merchNo);
        bnk.setAmount(Long.valueOf(obzl[8].toString()));
        bnk.setRetcode("00");
        bnk.setInstiid(instiid);
        return bnk;
    }
    // 中信对账文件：读取一行对账文件数据
    public BnkTxnModel MackBnkTxn_zhongxin(String newline) {
        BnkTxnModel bnk = new BnkTxnModel();
        Object[] obzl = newline.replace(" ", "").split("\\|");
        bnk.setPayordno(obzl[17].toString());
        // bnk.setSystrcno(obzl[1].toString());
        bnk.setPan(obzl[2].toString());
        bnk.setAcqsettledate(obzl[0].toString());
        bnk.setMerchno(obzl[10].toString());
        bnk.setAmount(Long.valueOf(obzl[6].toString()));
        bnk.setRetcode("00");
        bnk.setInstiid(instiid);
        return bnk;
    }
//--------------------------------------------融宝对账文件excel---------------------------------
	/*private File headImage; // 上传的文件
	private String headImageFileName; // 文件名称
	private String headImageContentType; // 文件类型
*/    /**
	 * 解析融宝对账文件，并保存交易数据
	 * @return
	 */
	public String batchUpload(){
		String errorString="";
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		try {
			//String realpath = ServletActionContext.getServletContext().getRealPath("/merchant");
			if (upload[0] != null) {
				//String fileNameString = generateFileName(headImageFileName);
				InputStream is = new FileInputStream(upload[0]);
	    		Workbook wbk = Workbook.getWorkbook(is);
	    		System.out.println(wbk);
	    		List<Map<String, Object>> tempList=null;
	    		for (int k = 0; k < wbk.getNumberOfSheets(); k++) {
	    				Sheet rs = wbk.getSheet(k);
	    				 errorString = this.saveExcelToElement(rs);
	    		}
	    		if(returnList.size()==0){
	    			Map<String, Object> valueMap= new HashMap<String, Object>();
	    			valueMap.put("RET", "succ");
	    			valueMap.put("INFO", "操作成功");
	    			returnList.add(valueMap);
	    		}else{
	    			json_encode(returnList);
	    		}
	    		
			}
		} catch (IOException e) {
			Map<String, Object> valueMap= new HashMap<String, Object>();
			valueMap.put("RET", "error");
			valueMap.put("INFO", "上传文件发生错误，请重新上传！");
			returnList.add(valueMap);
			e.printStackTrace();
		} catch (BiffException e) {
			Map<String, Object> valueMap= new HashMap<String, Object>();
			valueMap.put("RET", "error");
			valueMap.put("INFO", "导入失败！");
			returnList.add(valueMap);
			e.printStackTrace();
		}

		return errorString;
	}
    /**
	 * 保存表格中的元素
	 * @param rs
	 * @return
	 */
	public String saveExcelToElement(Sheet rs) {
    	int mark=0;
		String errorinfo = "";
		String acqsettledate="";
		int row = rs.getRows();
		Cell c0 = null,c1 = null,c2 = null,c3 = null,c4 = null,c5 = null,c6 = null,
		c7 = null,c8 = null,c9 = null,c10 = null;
		List<Map<String, Object>> returnInfo = new ArrayList<Map<String, Object>>(); 
		if(row==0){
			return null;
		}
		for (int k = 1; k < row; k++) {
		 if(k==2){
			 c2 = rs.getCell(1, k);// 商户订单号
			 acqsettledate=c2.getContents().trim();
		 }	
		 if(k>=7){
			int rownum = k + 1;
			Cell[] cells = rs.getRow(k);
			int len = cells.length;
			c0 = rs.getCell(0, k);// 序列
			c1 = rs.getCell(1, k);// 流水号
			c2 = rs.getCell(2, k);// 商户订单号
			c3 = rs.getCell(3, k);// 支付时间
			c4 = rs.getCell(4, k);// 交易类型			
			c5 = rs.getCell(5, k);// 交易金额
			c6 = rs.getCell(6, k);// 手续费
			UserModel user = getCurrentUser();
		    BnkTxnModel bnk = new BnkTxnModel();
		    bnk.setAcqsettledate(c1.getContents().trim());
		    bnk.setPaytrcno(c1.getContents().trim());
		    bnk.setPayordno(c2.getContents().trim());
		    bnk.setInstiid(instiid);
		    String txndatetime=c3.getContents().trim().toString();
		    bnk.setTxndatetime(txndatetime.replace(".", "").replace(":", "").replace(" ", ""));
		    bnk.setBusicode(c4.getContents().trim());
		    bnk.setAcqsettledate(acqsettledate.replace("-", ""));
		    if(c5.getContents()!=null&&!c5.getContents().trim().equals("")){
		    	 bnk.setAmount(new BigDecimal(c5.getContents()).multiply(new BigDecimal(100)).longValue());
		    } 
		    bnk.setRetcode("00");
		    bnk.setSystrcno(c1.getContents().trim());
			if(isNull(bnk)){
				Map<String, Object> errorMap = new HashMap<String, Object>();
				errorMap.put("RET", "error");
				errorMap.put("INFO", "信息错误");
				returnInfo.add(errorMap);
			}else {
				List<Map<String, Object>> returnList = (List<Map<String, Object>>) serviceContainer.getBnktxnService()
                        .saveBnkTxn(bnk);
				System.out.println(returnList);
				if("succ".equals(returnList.get(0).get("ERR"))){
					//returnInfo.addAll(returnList);
				}else{
					errorinfo=errorinfo+"订单号"+bnk.getPayordno()+"保存失败; ";
				}
			}
		 }
		}
		return errorinfo;     
    }
	public String generateFileName(String fileName) {
		String formatDate = new SimpleDateFormat("yyMMddHHmmss")
				.format(new Date(1));
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}
    public File[] getUpload() {
        return upload;
    }

    public void setUpload(File[] upload) {
        this.upload = upload;
    }

    public String[] getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getInstiid() {
        return instiid;
    }

    public void setInstiid(String instiid) {
        this.instiid = instiid;
    }

    public ServiceContainer getServiceContainer() {
        return serviceContainer;
    }
    public void setServiceContainer(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getFilestartid() {
        return filestartid;
    }
    public void setFilestartid(String filestartid) {
        this.filestartid = filestartid;
    }
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getAcctCode() {
		return acctCode;
	}
	public void setAcctCode(String acctCode) {
		this.acctCode = acctCode;
	}
	
	
	public String getMerch(){
	    return "merch";
	}

}
