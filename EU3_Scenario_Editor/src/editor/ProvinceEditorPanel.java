/*
 * ProvinceEditorPanel.java
 *
 * Created on May 31, 2007, 2:29 PM
 */

package editor;

import eug.parser.EUGFileIO;
import eug.parser.ParserSettings;
import eug.shared.GenericObject;
import eug.shared.ObjectVariable;
import eug.specific.clausewitz.ClausewitzHistory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JCheckBox;

/**
 *
 * @author  Michael Myers
 */
public class ProvinceEditorPanel extends javax.swing.JPanel {
    
    private int provId;
    
    private GenericObject provData;
    
    private String date;
    
    private static final List<String> techGroups = initGroups();
    
    /**
     * Tech group check boxes (for discoveries) are dynamically created based
     * on data loaded from common/technology.txt . This map keeps track of them
     * so that we can set them when we load province data.
     */
    private final java.util.Map<String, JCheckBox> techGroupCheckBoxes =
            new HashMap<String, JCheckBox>();
    
    
    private static List<String> initGroups() {
        final List<String> ret = new ArrayList<String>();
        final GenericObject techFile =
                EUGFileIO.load(Main.filenameResolver.resolveFilename("common/technology.txt"));
        
        for (ObjectVariable var : techFile.getChild("groups").values) {
            ret.add(var.varname);
        }
        
        return ret;
    }
    
    /** Creates new form ProvinceEditorPanel */
    public ProvinceEditorPanel(int provId) {
        date = "1453.1.1";
        this.provId = provId;
        loadData();
        initComponents();
    }
    
    public int getProvId() {
        return provId;
    }
    
    public void setProvId(int provId) {
        this.provId = provId;
        loadData();
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
        loadData();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        javax.swing.JPanel controlledByPanel;
        javax.swing.JPanel coreOfPanel;
        javax.swing.JPanel discoveredByPanel;
        javax.swing.JComboBox jComboBox1;
        javax.swing.JComboBox jComboBox2;
        javax.swing.JTextField jTextField1;
        javax.swing.JPanel ownedByPanel;

        discoveredByPanel = new javax.swing.JPanel();
        countryDiscoveriesTextField = new javax.swing.JTextField();
        ownedByPanel = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        controlledByPanel = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        coreOfPanel = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();

        discoveredByPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Discovered by"));
        for (String group : techGroups) {
            JCheckBox checkBox = new JCheckBox(group);
            techGroupCheckBoxes.put(group, checkBox);
            discoveredByPanel.add(checkBox);
        }
        countryDiscoveriesTextField.setPreferredSize(new java.awt.Dimension(200, 20));
        discoveredByPanel.add(countryDiscoveriesTextField);

        ownedByPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Owned by"));

        javax.swing.GroupLayout ownedByPanelLayout = new javax.swing.GroupLayout(ownedByPanel);
        ownedByPanel.setLayout(ownedByPanelLayout);
        ownedByPanelLayout.setHorizontalGroup(
            ownedByPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ownedByPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, 0, 63, Short.MAX_VALUE)
                .addContainerGap())
        );
        ownedByPanelLayout.setVerticalGroup(
            ownedByPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ownedByPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        controlledByPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Controlled by"));

        javax.swing.GroupLayout controlledByPanelLayout = new javax.swing.GroupLayout(controlledByPanel);
        controlledByPanel.setLayout(controlledByPanelLayout);
        controlledByPanelLayout.setHorizontalGroup(
            controlledByPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlledByPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, 0, 80, Short.MAX_VALUE)
                .addContainerGap())
        );
        controlledByPanelLayout.setVerticalGroup(
            controlledByPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlledByPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        coreOfPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Core of"));

        javax.swing.GroupLayout coreOfPanelLayout = new javax.swing.GroupLayout(coreOfPanel);
        coreOfPanel.setLayout(coreOfPanelLayout);
        coreOfPanelLayout.setHorizontalGroup(
            coreOfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coreOfPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addContainerGap())
        );
        coreOfPanelLayout.setVerticalGroup(
            coreOfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coreOfPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(discoveredByPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ownedByPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlledByPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coreOfPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(discoveredByPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(controlledByPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coreOfPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ownedByPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(140, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField countryDiscoveriesTextField;
    // End of variables declaration//GEN-END:variables
    
    private void loadData() {
        clear();
        
        String filename = Main.filenameResolver.getProvinceHistoryFile(provId);
        
        if (filename == null) {
            // must be terra incognita.
            return;
        }
        
        provData = EUGFileIO.load(filename, ParserSettings.getDefaults().setPrintTimingInfo(false));
        
        // Discoveries
        
        List<String> discoveredBy = ClausewitzHistory.getHistStrings(provData, "discovered_by", date);
        
        for (String groupOrTag : discoveredBy) {
            if (!groupOrTag.matches("\\w{3}")) {
                // not a tag, so it must be a tech group
                JCheckBox box = techGroupCheckBoxes.get(groupOrTag);
                if (box != null) {
                    box.setSelected(true);
                } else {
                    System.out.println("Can't find checkbox for group " + groupOrTag);
                }
            } else {
                String text = countryDiscoveriesTextField.getText();
                if (text.length() == 0 || text.endsWith(" ")) {
                    countryDiscoveriesTextField.setText(text + groupOrTag);
                } else {
                    countryDiscoveriesTextField.setText(text + " " + groupOrTag);
                }
            }
        }
    }
    
    private void clear() {
        for (JCheckBox box : techGroupCheckBoxes.values()) {
            box.setSelected(false);
        }
        if (countryDiscoveriesTextField != null)
            countryDiscoveriesTextField.setText("");
    }
    
}
