package com.web.zhu.controller;

import com.web.zhu.entity.User;
import com.web.zhu.service.UserSerivce;
import com.web.zhu.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * @author : Created by zhq
 * @since : 2017/7/10
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/user")
public class HelloWord {
    private static Logger logger = LoggerFactory.getLogger(HelloWord.class);
    @Autowired
    UserSerivce userSerivce;

    @RequestMapping("/getUserCount")
    @ResponseBody
    public Integer getUserCount(){
        int count = userSerivce.getUser();
        logger.info("返回数据:"+count);
        return count;
    }

    @RequestMapping("/getInfo")
    public String getInfo(@RequestParam(value = "uid") Integer uid,Model model){
        User user = userSerivce.getUserById(uid);
        model.addAttribute("user",user);
        logger.info("返回数据:"+user);
        return "/login";
    }

    /**
     * main方法测试
     * @param args
     */
    public static void main(String[] args) {
        long time = DateUtils.getLongTimeMillisecond();
        System.out.println(time);

        Date nowDate = new Date();
        Date times = DateUtils.weeHours(nowDate,1);
        String dateStr = DateUtils.getHmsStr(times);
        System.out.println(dateStr);
        try {
         /* 写入Txt文件 */
        File writename = new File("D:\\output.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
        writename.createNewFile(); // 创建新文件
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        String info = "我会写入文件啦"+time+"\r\n";
        out.write(info); // \r\n即为换行
        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件
        }catch (Exception e){
            e.printStackTrace();
        }



        String [] b = new String[]{"1","2","7","4","3"};
        Arrays.sort(b);


        int[] ids = {1, 2, 3, 4, 5};

        // 1、测试复制到别的数组上
        // 将ids数组的索引从0开始其后5个数，复制到ids2数组的索引从0开始
        int[] ids2 = new int[5];
        System.arraycopy(ids, 0, ids2, 1, 4);
        System.out.println(Arrays.toString(ids2)); // [1, 2, 3, 4, 5]


        User user = new User();
        Map map1 = new HashMap();
        map1.put("id",1);
        map1.put("username","张三");
        map1.put("age",17);
        BeanUtils.copyProperties(map1,user);
        System.out.println(user.getUsername());
    }
}
