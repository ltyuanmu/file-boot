package cn.newtouch.step.file.service.impl;

import cn.newtouch.step.file.bean.FileBean;
import cn.newtouch.step.file.bean.FileBeanExample;
import cn.newtouch.step.file.dao.FileBeanMapper;
import cn.newtouch.step.file.invo.FileInVo;
import cn.newtouch.step.file.service.FileService;
import cn.newtouch.step.file.util.KeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 文件服务实现
 */
@Service
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileBeanMapper fileBeanMapper;

    @Override
    public void saveFileInfo(FileInVo inVo) {
        FileBean fileBean = new FileBean();
        fileBean.setDeleteState(false);
        fileBean.setCreateTime(new Date());
        fileBean.setCreateUser(inVo.getOpUser());
        fileBean.setFileName(inVo.getFileName());
        fileBean.setFileSize(inVo.getFileSize()+"");
        fileBean.setFolder(inVo.getFolder());
        fileBean.setId(KeyGenerator.getUuid());
        fileBean.setKeyName(inVo.getKey());
        fileBean.setSuffix(inVo.getSuffix());
        fileBean.setPrivateFlag(inVo.isPrivateFlag());
        fileBean.setUpdateTime(fileBean.getCreateTime());
        fileBean.setUpdateUser(fileBean.getCreateUser());
        fileBeanMapper.insertSelective(fileBean);
    }

    @Override
    public void deleteFile(String key,String folder) {
        FileBean record = new FileBean();
        record.setDeleteState(true);
        record.setUpdateTime(new Date());
        FileBeanExample example = new FileBeanExample();
        example.createCriteria().andKeyNameEqualTo(key)
                .andDeleteStateEqualTo(false)
                .andFolderEqualTo(folder);
        fileBeanMapper.updateByExampleSelective(record,example);
    }

    @Override
    public FileBean getFileInfo(String key, String folder) {
        FileBeanExample example = new FileBeanExample();
        example.createCriteria().andKeyNameEqualTo(key)
                .andDeleteStateEqualTo(false)
                .andFolderEqualTo(folder);
        List<FileBean> fileBeen = fileBeanMapper.selectByExample(example);
        return fileBeen.stream().findFirst().orElse(null);
    }
}
