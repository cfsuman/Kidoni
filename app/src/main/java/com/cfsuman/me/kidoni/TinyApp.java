package com.cfsuman.me.kidoni;

/**
 * Created by jones on 2/21/2017.
 */

public class TinyApp {
    private String name;
    private String activity;
    private String image;
    private String description;
    private boolean enabled;


    public void setName(String name) {
        this.name = name;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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

    public String getName() {
        return name;
    }

    public String getActivity() {
        return activity;
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
                "name='" + name + '\'' +
                ", activity='" + activity + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
