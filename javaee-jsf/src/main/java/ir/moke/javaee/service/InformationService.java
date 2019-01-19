package ir.moke.javaee.service;

import ir.moke.javaee.model.Information;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class InformationService {

    private static final List<Information> db = new ArrayList<>();

    @Inject
    @Push(channel = "infoChannel")
    PushContext push;

    @PostConstruct
    public void init() {
        Information userA = new Information("John", "Vankate");
        Information userB = new Information("Julius", "Sampao");
        db.add(userA);
        db.add(userB);
    }

    public void remove(Information info) {
        db.remove(info);
        push.send("deleteInfo");
    }

    public List<Information> findAll() {
        return db;
    }
}
