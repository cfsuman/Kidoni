package com.cfsuman.me.kidoni;

/**
 * Created by jones on 2/21/2017.
 */

public class TinyApp {
    private String title;
    private String classname;
    private String image;
    private String description;
    private boolean enabled;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTitle() {
        return title;
    }

    public String getClassname() {
        return classname;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "TinyApp{" +
                "title='" + title + '\'' +
                ", classname='" + classname + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                '}';
    }

}
