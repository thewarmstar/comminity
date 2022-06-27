package com.wenxing.community.controller;

import com.wenxing.community.dao.AlphaDao;
import com.wenxing.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot.";
    }

    //浏览器返回方法的前提是要有注解声明和路径
    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find(); //把find结果返回给浏览器
    }

    //底层
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //1.获取请求数据
        //1.1请求第一行的数据
        System.out.println(request.getMethod());  //返回get
        System.out.println(request.getServletPath()); //返回alpha/http
        //1.2请求消息头，若干行数据
        Enumeration<String> enumeration = request.getHeaderNames(); //key value结构   迭代器对象
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));// ?code=123

        //2.返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter();) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求  用于获取某些数据

    //1.1获取批量信息
    // /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //1.2 获取某个学生信息
    // /student/123  把参数拼到路径中
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    // POST请求  浏览器向服务器提交数据  需要有表单
    // 参数有很多项的话，get请求放不下，而且在明面上  所以提交数据一般用POST
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    //POST获取参数：参数名字和表单中一致即可
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //1. 响应html数据  把数据响应到网页上
    //html不需要 @ResponseBody注解
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    //ModelAndView封装着给servlet返回的model和view两份数据
    //1.1 model view一起装
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",30);
        //设置模板的路径和名字
        mav.setViewName("/demo/view"); //不需要写.html
        return mav;
    }

    //1.2 String需要返回路径
    //返回view,model装到参数里
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age",80);
        return "/demo/view";
    }

    //2. 响应JSON数据，通常在异步请求中
    //JAVA对象-> JSON字符串-> JS对象
    //加  @ResponseBody表明返回的不是html,会自动转为json字符串
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age","33");
        emp.put("salary","2000");
        return emp;
    }

    //返回多个员工
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age","33");
        emp.put("salary","2000");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age","30");
        emp.put("salary","2100");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","王五");
        emp.put("age","23");
        emp.put("salary","12000");
        list.add(emp);

        return list;
    }
}