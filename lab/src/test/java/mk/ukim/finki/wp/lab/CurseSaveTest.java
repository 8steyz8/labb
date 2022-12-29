package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.model.exceptions.NoCourseNameException;
import mk.ukim.finki.wp.lab.model.exceptions.NoDescException;
import mk.ukim.finki.wp.lab.model.exceptions.NoSuchTeacherException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.impl.CourseServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CurseSaveTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private TeacherRepository teacherRepository;

    private CourseService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Course course = new Course("name", "description");
        Optional<Teacher> optionalTeacher = Optional.of(new Teacher());
        course.setTeacher(optionalTeacher.get());
        Mockito.when(this.courseRepository.save(Mockito.any(Course.class))).thenReturn(course);
        Mockito.when(this.teacherRepository.findById(0L)).thenReturn(optionalTeacher);


        this.service = Mockito.spy(new CourseServiceImpl(this.courseRepository, this.teacherRepository, this.studentRepository));
    }

    @Test
    public void testSaveCourse() {
        Course course = this.service.saveCourse("name","description",0L);

        Mockito.verify(this.service).saveCourse("name","description",0L);


        Assert.assertNotNull("Course is null", course);
        Assert.assertEquals("name do not mach", "name", course.getName());
        Assert.assertEquals("description do not mach", "description", course.getDescription());
    }


    @Test
    public void testEmptyName() {
        String name = "";
        Assert.assertThrows("NoCourseNameException expected",
                NoCourseNameException.class,
                () -> this.service.saveCourse2(new Course(name, "description"), 0L));
        Mockito.verify(this.service).saveCourse2(new Course(name, "description"), 0L);
    }


    @Test
    public void testEmptyDescription() {
        String description = "";
        Assert.assertThrows("NoDescException expected",
                NoDescException.class,
                () -> this.service.saveCourse2(new Course("name", description), 0L));
        Mockito.verify(this.service).saveCourse2(new Course("name", description), 0L);
    }


    @Test
    public void testTeacherNotFound() {
        Long id = Long.valueOf(-1);
        Assert.assertThrows("NoSuchTeacherException expected",
                NoSuchTeacherException.class,
                () -> this.service.saveCourse2(new Course("name", "description"), id));
        Mockito.verify(this.service).saveCourse2(new Course("name", "description"), id);
    }


    @Test
    public void testUnsuccessfulSaveCourse() {
        Assert.assertThrows("Exception expected",
                Exception.class,
                () -> this.service.saveCourse2(null, 0L));
        Mockito.verify(this.service).saveCourse2(null, 0L);
    }
}
