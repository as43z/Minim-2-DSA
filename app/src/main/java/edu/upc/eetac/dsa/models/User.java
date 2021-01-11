package edu.upc.eetac.dsa.models;

import java.util.List;

public class User {
    //User Properties
    private String login;
    private String name;
    private int id;
    private String avatar_url;
    private String repos_url;
    private String location;
    private List<Repo> lRepo;
    private int following;
    private int followers;

    //create constructor
    public User(String login, String name, int id, String avatar_url, String repos_url, String location, int followers, int following) {
        this.login = login;
        this.name = name;
        this.id = id;
        this.avatar_url = avatar_url;
        this.repos_url = repos_url;
        this.location = location;
        this.followers = followers;
        this.following = following;
    }

    // #############################
    // #    GETTERS AND SETTERS    #
    // #############################
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Repo> getlRepo() {
        return lRepo;
    }

    public void setlRepo(List<Repo> lRepo) {
        this.lRepo = lRepo;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }
}
