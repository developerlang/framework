package com.framework.network;

import java.io.File;
import java.util.Map;

/**
 * 添加文件参数的请求接口
 * @author heiyl
 *
 */
public interface MultiPartRequest {

    public void addFileUpload(String param,File file); 
    
    public void addStringUpload(String param,String content); 
    
    public Map<String,File> getFileUploads();
    
    public Map<String,String> getStringUploads(); 
}