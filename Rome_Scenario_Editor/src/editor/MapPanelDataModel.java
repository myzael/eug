/*
 * MapPanelDataModel.java
 *
 * Created on June 11, 2007, 1:49 PM
 */

package editor;

import eug.specific.eu3.EU3DataSource;
import eug.specific.eu3.EU3History;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Michael Myers
 */
public class MapPanelDataModel implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // TODO: Perhaps an abstract superclass should be extracted?
    
    private final ProvinceData provinceData;
    
    private final MapData mapData;
    
    private EU3DataSource dataSource;
    
    
    private String date;
    
    /** Creates a new instance of MapPanelDataModel */
    public MapPanelDataModel(final MapData data) {
        this(data, null);
    }
    
    public MapPanelDataModel(final MapData data, final EU3DataSource source) {
        provinceData = Main.provinceData;
        mapData = data;
        dataSource = source;
    }
    
    
    public ProvinceData getProvinceData() {
        return provinceData;
    }
    
    public MapData getMapData() {
        return mapData;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setDataSource(EU3DataSource source) {
        this.dataSource = source;
    }
    
    public EU3DataSource getDataSource() {
        return dataSource;
    }
    
    public void preloadProvs() {
        dataSource.preloadProvinces(Integer.parseInt(Main.map.getString("sea_starts")));
    }
    
    public void preloadCountries() {
        dataSource.preloadCountries();
    }
    
    public List<String> isCoreOf(int provId, String date) {
        return EU3History.isCoreOf(dataSource.getProvinceHistory(provId), date);
    }
    
    public List<String> isCoreOf(int provId) {
        return isCoreOf(provId, date);
    }
    
    
    public String getHistString(int provId, String name) {
        return EU3History.getHistString(dataSource.getProvinceHistory(provId), name, date);
    }
    
    public List<String> getHistStrings(int provId, String name) {
        return EU3History.getHistStrings(dataSource.getProvinceHistory(provId), name, date);
    }
    
    public String getHistString(String tag, String name) {
        return EU3History.getHistString(dataSource.getCountryHistory(tag), name, date);
    }
    
    public List<String> getHistStrings(String tag, String name) {
        return EU3History.getHistStrings(dataSource.getCountryHistory(tag), name, date);
    }
    
    
    public List<String> getCountries() {
        return getCountries(date);
    }
    
    public List<String> getCountries(String date) {
        final java.util.Map<String, Object> ret = new HashMap<String, Object>();
        final int seaStart = Integer.parseInt(Main.map.getString("sea_starts"));
        
        for (ProvinceData.Province p : provinceData.getAllProvs()) {
            if (p.getId() == 0)
                continue;
            if (p.getId() >= seaStart)
                break;
            
            String owner = EU3History.getHistString(dataSource.getProvince(p.getId()), "owner", date);
            
            if (owner == null || owner.length() == 0 || owner.equals("none") || owner.equals("XXX")) {
                continue;
            } else {
                String text = Text.getText(owner);
                if (text == null) {
                    System.out.println("Text for " + owner + " is null!");
                    continue;
                }
                ret.put(text, null);
            }
        }
        
        final List<String> list = new ArrayList<String>(ret.keySet());
        Collections.sort(list);
        return list;
    }
    
    public List<String> getTags() {
        return getTags(date);
    }
    
    public List<String> getTags(String date) {
        final java.util.Map<String, Object> ret = new HashMap<String, Object>();
        final int seaStart = Integer.parseInt(Main.map.getString("sea_starts"));
        
        for (ProvinceData.Province p : provinceData.getAllProvs()) {
            if (p.getId() == 0)
                continue;
            if (p.getId() >= seaStart)
                break;
            
            String owner = EU3History.getHistString(dataSource.getProvince(p.getId()), "owner", date);
            
            if (owner == null || owner.length() == 0 || owner.equals("none") || owner.equals("XXX")) {
                continue;
            } else {
                ret.put(owner, null);
            }
        }
        
        final List<String> list = new ArrayList<String>(ret.keySet());
        Collections.sort(list);
        return list;
    }
    
    public List<Integer[]> getLinesInProv(int provId) {
        return mapData.getLinesInProv(provinceData.getProvByID(provId).getColor());
    }
    
}
