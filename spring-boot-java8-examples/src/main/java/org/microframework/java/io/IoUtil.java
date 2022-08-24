package org.microframework.java.io;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author Shaoyu Liu
 * @date 2022-08-18
 */
public class IoUtil {

    /**
     * gzip压缩，减低字符串大小和网络传输IO
     *
     * @param str
     * @param encoding
     * @return
     */
    public static byte[] tryCompress(String str, String encoding) throws Exception {
        if (str == null || str.length() == 0) {
            return new byte[0];
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(str.getBytes(encoding));
        }
        return out.toByteArray();
    }

}
