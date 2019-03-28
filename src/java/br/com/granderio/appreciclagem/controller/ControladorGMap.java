/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean(name="controleMap")
@SessionScoped
public class ControladorGMap {
    
    private MapModel geoModel;
    private String centerGeoMap = "41.850033, -87.6500523";
    
    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
    }
     
//    public void onGeocode(GeocodeEvent event) {
//        List<GeocodeResult> results = event.getResults();
//         
//        if (results != null && !results.isEmpty()) {
//            LatLng center = results.get(0).getLatLng();
//            centerGeoMap = center.getLat() + "," + center.getLng();
//             
//            for (int i = 0; i < results.size(); i++) {
//                GeocodeResult result = results.get(i);
//                getGeoModel().addOverlay(new Marker(result.getLatLng(), result.getAddress()));
//            }
//        }
//    }

    /**
     * @return the centerGeoMap
     */
    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    /**
     * @param centerGeoMap the centerGeoMap to set
     */
    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    /**
     * @return the geoModel
     */
    public MapModel getGeoModel() {
        return geoModel;
    }

    /**
     * @param geoModel the geoModel to set
     */
    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
    }

}
