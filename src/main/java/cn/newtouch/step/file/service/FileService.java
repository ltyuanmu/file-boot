package cn.newtouch.step.file.service;


import cn.newtouch.step.file.bean.FileBean;
import cn.newtouch.step.file.invo.FileInVo;
import org.apache.tomcat.jni.FileInfo;

public interface FileService {
    /**
     * 保存文件信息
     * @param inVo
     */
    void saveFileInfo(FileInVo inVo);

    /**
     * 删除文件
     * @param key
     */
    void deleteFile(String key,String folder);

    /**
     * 查询文件信息
     * @param key
     * @param folder
     * @return
     */
    FileBean getFileInfo(String key, String folder);
}
