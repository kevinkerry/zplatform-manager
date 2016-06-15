package com.zlebank.zplatform.manger.recon;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.zlebank.zplatform.manager.action.chanpay.ChanPayFileContent;
import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.exception.ResolveReconFileContentException;

public class ChanPayContentHandlerTest {
    @Test
    public void test() {
        AbstractFileContentHandler contentHandler = new ChanPayFileContent();
        File[] files = new File[]{new File("/Excel-20160615144143-t.xls")};
        try {
            List<BnkTxnModel> list = contentHandler.readFile(files, "12321321",
                    new String[]{"Excel-20160615144143-t.xls"});
            Assert.assertNotNull(list);
            Assert.assertTrue(list.size() > 0);
        } catch (ResolveReconFileContentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
