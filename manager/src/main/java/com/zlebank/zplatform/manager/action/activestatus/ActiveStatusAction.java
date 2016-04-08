package com.zlebank.zplatform.manager.action.activestatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.object.ParaDicModel;
import com.zlebank.zplatform.manager.dao.object.scan.MemberQueueMode;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.service.iface.IMemberQueueService;
import com.zlebank.zplatform.member.pojo.PojoMerchDeta;
import com.zlebank.zplatform.member.service.MerchService;
/**
 * 
 * @author eason
 *接收商户是否激活成功状态
 */
public class ActiveStatusAction extends BaseAction {
    /**
     * 
     */
    @Autowired
    private IMemberQueueService iMemberQueueService;
    @Autowired
    private DAOContainer daoContainer;
    @Autowired
    private ServiceContainer serviceContainer;
    @Autowired
    private MerchService merchService;
    private static final long serialVersionUID = -8759717743908763748L;
    public void saveActiveStatus(){
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context
                .get(ServletActionContext.HTTP_REQUEST);
        
        
        String activeStatus=request.getParameter("activeStatus");
        String memberId=request.getParameter("memberId");
        memberId="200000000000611";
        activeStatus="00";
        MemberQueueMode  mem = iMemberQueueService.getMemQueuebyMemId(memberId);
        PojoMerchDeta mer=merchService.getMerchBymemberId(memberId);
        if(("00").equals(activeStatus)){
            mem.setAcriveStatus("00");
            mer.setActiveStatus("00");
        }else{
            mem.setAcriveStatus("01");
            mer.setActiveStatus("01");
        }
        iMemberQueueService.update(mem);
        merchService.update(mer);
        
    }
    public String  replayEmail(){
        Map<String, Object> map = new HashMap<String, Object>();
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context
                .get(ServletActionContext.HTTP_REQUEST);
        String memberId=request.getParameter("memberId");
        MemberQueueMode mem= iMemberQueueService.getMemQueuebyMemId(memberId);
        if(mem==null){
            List<?> list  =daoContainer.getEnterpriseDetaDAO().getIdCardByMemberId(memberId);
            if(list.size()>0){
                JSONArray jsonArray = JSONArray.fromObject(list);
                JSONObject job = jsonArray.getJSONObject(0);
                MemberQueueMode member=new MemberQueueMode();
                ParaDicModel maxsend= serviceContainer.getParaDicService().get( 102L);
                ParaDicModel expirationTime= serviceContainer.getParaDicService().get( 101L);
                member.setMemberId(memberId);
                member.setEmail(job.get("EMAIL").toString());
                member.setSendTimes(0);
                member.setFlag("01");
                member.setMaxSendTimes(Integer.parseInt(maxsend.getParaCode()));
                member.setExpirationTime(expirationTime.getParaCode());
                member.setIdCard(job.get("CORP_NO").toString());
                iMemberQueueService.save(member);
            }
        }else{
            if(!("00").equals(mem.getAcriveStatus())){
                mem.setSendTimes(0);
                mem.setFlag("01");
                mem.setStatus("01");
                iMemberQueueService.update(mem);
            }else{
                map.put("messg", "正在激活中");
                json_encode(map);
                return null;
            }
            
        }
        map.put("messg", "发送邮件成功");
        json_encode(map);
        return null;
    }
    

}
