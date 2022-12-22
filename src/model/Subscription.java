package model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subscription {
    private final StringProperty firstname = new SimpleStringProperty();
    private final StringProperty lastname = new SimpleStringProperty();
    private final BooleanProperty javaFx = new SimpleBooleanProperty();
    private final BooleanProperty javaConcurency = new SimpleBooleanProperty();
    private final BooleanProperty javaMaster = new SimpleBooleanProperty();

    private final BooleanBinding valid;

    public Subscription() {
        // Enable/Disable Submit-Button
        BooleanBinding nameEntered =
                firstname.length().greaterThanOrEqualTo(3)
                        .and(lastname.length().greaterThanOrEqualTo(3));

        BooleanBinding courseSelected = javaConcurency.or(javaFx.or(javaMaster));

        valid = nameEntered.and(courseSelected);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public boolean isJavaFx() {
        return javaFx.get();
    }

    public BooleanProperty javaFxProperty() {
        return javaFx;
    }

    public void setJavaFx(boolean javaFx) {
        this.javaFx.set(javaFx);
    }

    public boolean isJavaConcurency() {
        return javaConcurency.get();
    }

    public BooleanProperty javaConcurencyProperty() {
        return javaConcurency;
    }

    public void setJavaConcurency(boolean javaConcurency) {
        this.javaConcurency.set(javaConcurency);
    }

    public boolean isJavaMaster() {
        return javaMaster.get();
    }

    public BooleanProperty javaMasterProperty() {
        return javaMaster;
    }

    public void setJavaMaster(boolean javaMaster) {
        this.javaMaster.set(javaMaster);
    }

    public Boolean getValid() {
        return valid.get();
    }

    public BooleanBinding validProperty() {
        return valid;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "firstname=" + firstname +
                ", lastname=" + lastname +
                ", javaFx=" + javaFx +
                ", javaConcurency=" + javaConcurency +
                ", javaMaster=" + javaMaster +
                '}';
    }

    public String getCoursesString() {
        String coursesString =
                (this.javaFx.get() ? "Java FX, " : "") +
                        (this.javaConcurency.get() ? "Java Concurrency, " : "") +
                        (this.javaMaster.get() ? "Java Master, " : "");
        if (coursesString.length() > 0) {
            coursesString = coursesString.substring(0, coursesString.length() - 2);
        }
        return coursesString;
    }

    public void save() {
        // nicht recht persistent
        System.out.println(this);
    }
}