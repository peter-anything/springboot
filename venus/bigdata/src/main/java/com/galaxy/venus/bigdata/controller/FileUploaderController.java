package com.galaxy.venus.bigdata.controller;

import com.galaxy.venus.bigdata.IHBaseOperator;
import com.galaxy.venus.bigdata.resp.beans.ResponseBean;
import com.google.common.primitives.Longs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/data/unstruct")
public class FileUploaderController {
    private final int BUFFER_SIZE = 2 * 1024 * 1024;
    @Value("UnstructuredTable")
    private String hbaseName;
    @Value("UnstructureData")
    private String hbaseColumnFamily;
    @Autowired
    private IHBaseOperator handler;
    @RequestMapping(value = "/data", method = RequestMethod.POST)
//    @PreAuthorize("hasPermission('GRAPH','MODIFY',#requestBean.whichGraph)")
    public ResponseBean<String> upload(@RequestPart MultipartFile file) throws Exception {
            byte[] bytes = Longs.toByteArray(System.currentTimeMillis());
            bytes[0] = (byte) (Math.abs(Math.random() * 37) % 256);
            if (file.getOriginalFilename() != null) {
                bytes[1] = file.getOriginalFilename().getBytes()[0];
            handler.upload(hbaseName, hbaseColumnFamily, bytes, file, BUFFER_SIZE);
        }
        return new ResponseBean<>("2000", "success", "all");
    }

}
