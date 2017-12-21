package cn.newtouch.step.file.controller;

import cn.newtouch.step.file.bean.FileBean;
import cn.newtouch.step.file.constant.FileUrl;
import cn.newtouch.step.file.invo.FileInVo;
import cn.newtouch.step.file.service.FileService;
import cn.newtouch.step.file.util.KeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class FileController {
    @Value("${basedir}")
    private String baseDir;
    @Value("${deletedir}")
    private String deleteDir;
    @Value("${accessToken}")
    private String accessToken;
    @Value("${privatedir}")
    private String privateDir;
    @Autowired
    private FileService fileService;

    @PostMapping(value = FileUrl.FILE ,produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                String key, String folder, String accessToken,
                                                   boolean isPrivate){
        // 校验accessToken
        if(!this.accessToken.equals(accessToken)){
            return ResponseEntity.status(400).body("Bad credentials");
        }
        if (file.isEmpty()) {
            return ResponseEntity.status(400).body("file is null!!");
        }
        if(StringUtils.isEmpty(folder)){
            return ResponseEntity.status(400).body("folder is null!!");
        }
        //获取要存储的路径
        String path = isPrivate?privateDir:baseDir;
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("file name:{}",fileName);
        String suffixName = FilenameUtils.getExtension(fileName);
        // 获取文件的后缀名
        log.info("file suffixName:{}",suffixName);
        // 文件上传后的路径
        // 检测基础目录是否存在
        checkOrCreateDir(path);
        //检测文件夹是否存在
        checkOrCreateDir(path+File.separator+folder);
        //保存名称为key
        if(StringUtils.isEmpty(key)){
            key = KeyGenerator.getUuid()+"."+suffixName;
        }else{
            FileBean fileInfo = this.fileService.getFileInfo(key, folder);
            if(fileInfo!=null){
                return ResponseEntity.status(400).body("file is exist!!");
            }
        }
        String filePath = FilenameUtils.concat(path + File.separator + folder, key);
        File dest = new File(filePath);
        try {
            file.transferTo(dest);
            FileInVo inVo = new FileInVo();
            inVo.setSuffix(suffixName);
            inVo.setKey(key);
            inVo.setFileName(fileName);
            inVo.setFileSize(dest.length());
            inVo.setFolder(folder);
            inVo.setOpUser("admin");
            inVo.setPrivateFlag(isPrivate);
            fileService.saveFileInfo(inVo);
            log.info("upload file success!!");
            Map<String,String> map = new HashMap<>();
            map.put("key",key);
            return ResponseEntity.status(200).body(map);
        } catch (IOException|IllegalStateException e) {
            log.error(e.getMessage(), e);
        }
        return ResponseEntity.status(500).body("upload file fail!!");
    }

    @DeleteMapping(value = FileUrl.FILE ,produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteFile(String key, String folder, String accessToken){
        // 校验accessToken
        if(!this.accessToken.equals(accessToken)){
            return ResponseEntity.status(400).body("Bad credentials");
        }
        if(StringUtils.isEmpty(folder)){
            return ResponseEntity.status(400).body("folder is null!!");
        }
        if(StringUtils.isEmpty(key)){
            return ResponseEntity.status(400).body("key is null!!");
        }
        //查看是私有文件还是共有文件,获得文件路径
        FileBean fileInfo = this.fileService.getFileInfo(key, folder);
        if(fileInfo==null){
            return ResponseEntity.status(409).body("file is not exist!!");
        }
        String path = fileInfo.getPrivateFlag()?privateDir:baseDir;
        //获得当时路径
        String filePath = FilenameUtils.concat(path + File.separator + folder, key);
        log.info("delete filePath:{}",filePath);
        // 检测文件是否存在
        File srcFile = new File(filePath);
        //如果为文件目录则不能删除
        if(srcFile.isDirectory()){
            return ResponseEntity.status(409).body("file is directory!!");
        }
        if(!srcFile.exists()){
            return ResponseEntity.status(409).body("file is not exist!!");
        }
        File destDir =new File(deleteDir + File.separator + folder);
        try {
            FileUtils.moveFileToDirectory(srcFile,destDir,true);
            this.fileService.deleteFile(key,folder);
            return ResponseEntity.status(200).body("delete file success!!");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(500).body("delete file fail!!");
        }
    }

    @GetMapping(value = FileUrl.PRIVATE_FIlE ,produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPrivateFile(HttpServletResponse response,
                                                 String key, String folder, String accessToken){
        // 校验accessToken
        if(!this.accessToken.equals(accessToken)){
            return ResponseEntity.status(400).body("Bad credentials");
        }
        if(StringUtils.isEmpty(folder)){
            return ResponseEntity.status(400).body("folder is null!!");
        }
        //获取要存储的路径
        String path = privateDir;
        //获得当时路径
        String filePath = FilenameUtils.concat(path + File.separator + folder, key);
        log.info("download filePath:{}",filePath);
        // 检测文件是否存在
        File srcFile = new File(filePath);
        //如果为文件目录则不能下载
        if(srcFile.isDirectory()){
            return ResponseEntity.status(409).body("file is directory!!");
        }
        if(!srcFile.exists()){
            return ResponseEntity.status(409).body("file is not exist!!");
        }
        //进行下载
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            //response.setContentType("image/*");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(srcFile.getName().getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(srcFile.length()));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileUtils.copyFile(srcFile,bos);
            bos.close();
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.status(500).body("download file fail!!");
        }
    }

    /**
     * 校验有没有此目录 如果有则创建
     */
    private void checkOrCreateDir(String path){
        log.info("check path:{}",path);
        File filePath = new File(path);
        if  (!filePath.exists() && !filePath.isDirectory()){
            log.info("{} is not exists !",path);
            boolean result = filePath.mkdir();
            log.info("path create result:{}",result);
        }
    }

}
