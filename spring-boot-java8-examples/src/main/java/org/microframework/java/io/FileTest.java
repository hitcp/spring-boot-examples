package org.microframework.java.io;

import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File file = File.createTempFile(UUID.randomUUID().toString().replace("-", ""), ".jpg");

        MultipartFile multipartFile = null;

        // MultipartFile 转 File
        multipartFile.transferTo(file);
        FileCopyUtils.copy(multipartFile.getBytes(), file);
        FileUtils.copyToFile(multipartFile.getInputStream(), file);
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);

//        InputStream inputStream = new FileInputStream(file);
//        OutputStream outputStream = new FileOutputStream(file);
//        IOUtils.copy(multipartFile.getInputStream(),outputStream); // ?
//        IOUtils.copyLarge(multipartFile.getInputStream(),outputStream); // ?


//        FileCopyUtils.copy(multipartFile.getBytes(),outputStream); // ?
//        FileCopyUtils.copy(multipartFile.getInputStream(),outputStream); // ?
    }
}
