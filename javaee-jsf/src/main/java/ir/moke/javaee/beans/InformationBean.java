package ir.moke.javaee.beans;

import ir.moke.javaee.model.Information;
import ir.moke.javaee.service.InformationService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class InformationBean implements Serializable {

    private Information info ;
    private List<Information> informationList ;

    @EJB
    private InformationService informationService ;


    @PostConstruct
    public void init() {
        informationList = informationService.findAll();
        info = new Information() ;
    }

    public void deleteInformation() {
        informationService.remove(info);
    }

    public Information getInfo() {
        return info;
    }

    public void setInfo(Information info) {
        this.info = info;
    }

    public List<Information> getInformationList() {
        return informationList;
    }

    public void setInformationList(List<Information> informationList) {
        this.informationList = informationList;
    }
}
