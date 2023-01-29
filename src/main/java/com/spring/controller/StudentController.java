package com.spring.controller;

import com.spring.domain.Student;
import com.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller//bu classın controller katmanı olduğunu springe tanıttık
@RequestMapping("/students")// /students şeklinde mapplemek için bu endpointi karşıladık
public class StudentController {

    @Autowired
    private StudentService studentService;

@GetMapping("/hi")
public ModelAndView sayHi(){// /students/hi
    ModelAndView mav=new ModelAndView();
    mav.addObject("message","Merhaba");
    mav.addObject("messagebody","Welcome to Student Management System");
    mav.setViewName("hi");//hi.jsp yi işaret ettik
    return mav;
}

@GetMapping("/furkir")//furkir is my dear lovely cat
    public ModelAndView myFurkir(){
    ModelAndView mav=new ModelAndView();
    mav.addObject("message","Merhaba");
    mav.addObject("messagebody","BENIM FURKIRIMIN KAFASI MINICIK!!!!!\n" +
            "O KADARRRR !!!!!!");
    mav.setViewName("furkir");//furkir.jsp yi işaret ettik
    return mav;
}

@GetMapping("/new")
    public String sendStudentForm(@ModelAttribute("student") Student student){
    //@ModelAttribute Modele student isminde bir attribute ekledik
    return "studentForm";
}
@PostMapping("/saveStudent")
    public String createStudent(@ModelAttribute Student student){
    studentService.saveStudent(student);
    return "redirect:/students";//tekrar getAll syfasına yönlendirdi

}
@GetMapping
    public ModelAndView getStudents(){
    List<Student> list=studentService.getAllStudents();

    ModelAndView mav=new ModelAndView();
    mav.addObject("students",list);
    mav.setViewName("students");
    return mav;
}

@GetMapping("/update")//THERE IS A PROBLEM HERE
    public String updateStudent(@RequestParam("id") Long id, Model model){
    Student student=studentService.findById(id);
    model.addAttribute(student);
    return "studentForm";// studentForm.jsp sayfasını client tarafına gönderiyoruz String olarak ismiyle
    //işaret ediyoruz
}

@GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
    studentService.deleteStudent(id);
    return "redirect:/students";
}



}
