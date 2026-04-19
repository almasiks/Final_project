package model.academic;

import enums.LessonType;
import model.users.Teacher;

import java.io.Serializable;

public class Lesson implements Serializable {
    private LessonType lessonType;
    private Teacher instructor;
    private String room;
    private String timeSlot;

    public Lesson(LessonType lessonType, Teacher instructor,
                  String room, String timeSlot) {
        this.lessonType = lessonType;
        this.instructor = instructor;
        this.room = room;
        this.timeSlot = timeSlot;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    public String getRoom() {
        return room;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return lessonType + " | Room: " + room
                + " | " + timeSlot
                + " | Teacher: " + instructor.getFirstName();
    }
}