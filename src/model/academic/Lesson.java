package model.academic;

import enums.LessonType;
import model.users.Teacher;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Objects;

public class Lesson implements Serializable {
    private LessonType lessonType;
    private Teacher teacher;
    private Course course;
    private String room;
    private LocalDateTime schedule;

    public Lesson(LessonType lessonType, Teacher teacher,
                  String room, LocalDateTime schedule) {
        this.lessonType = lessonType;
        this.teacher = teacher;
        this.room = room;
        this.schedule = schedule;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public Course getCourse(){return course;}
    public String getRoom() {
        return room;
    }
    public LocalDateTime getSchedule() {
        return schedule;
    }
    @Override
    public String toString() {
        return "Lesson{" +
                "type=" + lessonType +
                ", teacher=" + teacher +
                ", course=" + course +
                ", room='" + room + '\'' +
                ", schedule=" + schedule +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return lessonType == lesson.lessonType &&
                Objects.equals(teacher, lesson.teacher) &&
                Objects.equals(course, lesson.course) &&
                Objects.equals(room, lesson.room) &&
                Objects.equals(schedule, lesson.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonType, teacher, course, room, schedule);
    }


}