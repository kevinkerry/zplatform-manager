package com.zlebank.zplatform.manager.action.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.exception.ResolveReconFileContentException;

public abstract class AbstractFileContentHandler {
    public abstract List<BnkTxnModel> readFile(File[] upload,
            String instiid,
            String[] uploadFileName) throws NumberFormatException, IOException,
            ResolveReconFileContentException;

}
