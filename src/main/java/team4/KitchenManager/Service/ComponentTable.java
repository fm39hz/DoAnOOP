package team4.KitchenManager.Service;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ComponentTable <T> extends AbstractTableModel{
    private final List<T> ComponentList;
    private String[] PropertyNames;
    private Class<?>[] PropertyTypes;

    public ComponentTable(Class<?> componentType, List<T> componentList){
        this.ComponentList = componentList;
        this.PropertyNames = PropertyInfoExtractor.GetObjectPropertyNames(componentType);
        this.PropertyTypes = PropertyInfoExtractor.GetObjectPropertyTypes(componentType);
        }

    @Override
    public String getColumnName(int column){
        return this.PropertyNames[column];
        }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        return this.PropertyTypes[columnIndex];
        }
    
    @Override
    public int getRowCount() {
        return this.ComponentList.size();
        }

    @Override
    public int getColumnCount() {
        return this.PropertyNames.length;
        }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var _row = ComponentList.get(rowIndex);
        var _value = PropertyInfoExtractor.InvokeGetter(_row.getClass(), PropertyNames[columnIndex], _row);
        return _value;
        }
    }
