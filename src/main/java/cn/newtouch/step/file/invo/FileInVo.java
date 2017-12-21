package cn.newtouch.step.file.invo;

import lombok.Data;

import java.io.Serializable;

/**
 * 保存文件信息入参
 */
@Data
public class FileInVo implements Serializable{
    /**文件真实名称*/
    private String fileName;
    /**保存文件名*/
    private String key;
    /**后缀名*/
    private String suffix;
    /**保存文件夹*/
    private String folder;
    /**文件大小*/
    private long fileSize;
    /**操作人*/
    private String opUser;
    /**文件私有标志位 false：公开，true：私有*/
    private boolean privateFlag;
}
