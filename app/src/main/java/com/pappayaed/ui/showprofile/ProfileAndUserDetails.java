package com.pappayaed.ui.showprofile;

import java.util.List;
import java.util.Map;

/**
 * Created by yasar on 26/3/18.
 */

public class ProfileAndUserDetails {

    private Profile profile;

    private List<UserDetails> list;

    private Map<Object, Object> map;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<UserDetails> getList() {
        return list;
    }

    public void setList(List<UserDetails> list) {
        this.list = list;
    }


    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }
}
