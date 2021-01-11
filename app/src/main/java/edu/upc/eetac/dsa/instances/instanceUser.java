package edu.upc.eetac.dsa.instances;

import edu.upc.eetac.dsa.models.User;

public class instanceUser {
    private static instanceUser instance;
    private User user;

    private instanceUser() {this.user = null;}

    public static instanceUser getInstance() {
        if(instance == null){
            instance = new instanceUser();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
