package com.nju.mystore.Log;

import com.nju.mystore.util.OssUtil;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.util.Date;

public class Log {

    private static OssUtil ossUtil = null;

    private static String log_path = "src\\main\\java\\com\\seecoder\\BlueWhale\\Log\\log.txt";

    private static BufferedWriter writer = null;

    static {
        ossUtil = new OssUtil();
        init_writer(true);
    }

    public static void record_log(String string){
        string = new Date() +" "+ string;
        if(!string.endsWith(System.lineSeparator())){
            string += System.lineSeparator();
        }
        try {
            writer.write(string);
            writer.flush();
        }catch (IOException ignored){

        }

    }

    /**
     * 初始化writer
     * @param append
     */
    private static void init_writer(boolean append){
        try {
            Log.writer = new BufferedWriter(new FileWriter(Log.log_path, append));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 上传log.txt，每隔30min上传并清空一次
     */
    @Scheduled(cron = "0 0/30 * * * *")
    private static void upload(){
        try {
            InputStream inputStream = new FileInputStream(Log.log_path);
            ossUtil.upload("log.txt",inputStream);
            clear_log();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 清空log.txt
     */
    private static void clear_log(){
        try {
            writer.close();
            init_writer(false);
            writer.write("");
            writer.flush();
            writer.close();
            init_writer(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}
