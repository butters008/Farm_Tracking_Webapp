package com.butterfield.farmtracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Slf4j
@Controller
public class UploadController {
    @RequestMapping(value = "/herd/uploadPicture", method = RequestMethod.POST)
    public ModelAndView uploadPost(@RequestParam("file") MultipartFile file, @RequestParam("idForPic") String id) throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("Upload file name: " + file.getOriginalFilename() + " size " + file.getSize());

        File targetFile = new File("c:/temp/" + file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);

        response.setViewName("/herd/herdinfo");
        return response;
    }
}