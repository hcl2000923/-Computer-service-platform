package com.yc.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传工具类
 *
 * @author hp
 */
public class FileUploadUtil {
    private static final String IMAGEPATH = "D:/shopping/shopping_images/";
//    private static final String PATH = "../shopping_images/";
    private static final String CHARSET = "UTF-8";

    /**
     * 文件上传
     */
    public static String parseRequest(HttpServletRequest request) throws Exception {
        //Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        //服务器上传对象
        //解析请求对象
        //Parse the request
        List<FileItem> items = upload.parseRequest(request);
//        StringBuffer sb = new StringBuffer();
//        int i = 1;
        //循环文件项
        if (!items.isEmpty()) {
            FileItem item=items.get(0);
            if (item.isFormField()) {
                return null;
            } else {
                String fieldName = item.getFieldName();//获取表单的Name属性值
                //获取文件名称
                String name = item.getName();
                //文件存在服务器的哪个位置
//                String path = request.getServletContext().getRealPath("/");
//                System.out.println(path);
                //文件重命名问题
                UUID uuid = UUID.randomUUID();
                String fileName = uuid.toString() + "" + name;
//                System.out.println(uuid.toString() + "" + name);
                //如果把图片存到项目的目录下，但是当我重启服务器后，项目下上传的图片就消失
                //服务器webapps目录创建一个名为images的文件夹，就相当于一个文件夹项目
                //D:\\tomcat\apach-tomcat-8.0\webapps\web321\
                //D:\\tomcat\apach-tomcat-8.0\webapps\images\
                //文件如何写入到指定位置    放到项目下
                //创建文件对象
                File file = new File(IMAGEPATH, fileName);
//                System.out.println(IMAGEPATH + fileName);
                //将文件对象写到磁盘中
                item.write(file);
                //获取存储后的文件路径  如何处理  存储到image_path
                String image_path = IMAGEPATH + fileName;
//                if (i == 1) {
//                sb.append(image_path);
//                } else {
//                    sb.append(";" + image_path);
//                }
//                i++;
//                for (Method m : methods) {
//                    if (("set" + fieldName).equalsIgnoreCase(m.getName())) {
//                        m.invoke(t, sb.toString());
//                    }
//                }
                return image_path;
            }
        }else {
            return null;
        }
    }
}
