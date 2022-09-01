package studentRegisterExample.model;

public class CourseReview {

    private CourseRate courseRate;
    private String comments;
    private StudentCourseRecord studentCourseRecord;

    public enum CourseRate {
        ONE, TWO, THREE, FOUR, FIVE
    }
}
