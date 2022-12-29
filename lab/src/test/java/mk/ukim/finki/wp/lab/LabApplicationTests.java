package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


@ActiveProfiles("test") // ke se aktivira profilot test aka h2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // dali contextot uspesno ke se vcita -> dali app ke startne
// na koja porta ke slusame
class LabApplicationTests {

    @Test
    void contextLoads() {
    }

    MockMvc mockMvc;

    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @BeforeEach // pred sekoj test ovoj metod
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        //initData();
    }

    private static boolean dataInitialized = false;

    @Test
    public void testGetCourses() throws Exception {
        MockHttpServletRequestBuilder courseRequest = MockMvcRequestBuilders.get("/courses");
        this.mockMvc.perform(courseRequest)
                .andDo(MockMvcResultHandlers.print()) // pecati status na odgovor
                .andExpect(MockMvcResultMatchers.status().isOk()) //sto sakame da ocekuvaeme
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "listCourses"))
                .andExpect(MockMvcResultMatchers.view().name("master-template"));
    }

//    @Test
//    public void testAddCourse() throws Exception {
//        MockHttpServletRequestBuilder courseRequest = MockMvcRequestBuilders.get("/courses");
//        List<Teacher> teachers = this.teacherService.findAll();
//        this.mockMvc.perform(courseRequest)
//                .andDo(MockMvcResultHandlers.print()) // pecati status na odgovor
//                .andExpect(MockMvcResultMatchers.status().isOk()) //sto sakame da ocekuvaeme
//                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"))
//                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "add-course"))
//                .andExpect(MockMvcResultMatchers.view().name("master-template"));
//    }

}