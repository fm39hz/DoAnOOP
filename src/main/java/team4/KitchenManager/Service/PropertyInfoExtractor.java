package team4.KitchenManager.Service;

public class PropertyInfoExtractor {
    public static <T> String[] GetObjectPropertyNames(Class<T> objectType) {
        var _property = objectType.getDeclaredFields();
        var _names = new String[_property.length];
        for (var i = 0; i < _property.length; i++){
            _names[i] = _property[i].getName();
            }
        return _names;
        }
    public static Class<?>[] GetObjectPropertyTypes(Class<?> objectType){
        var _property = objectType.getDeclaredFields();
        var _names = new Class<?>[_property.length];
        for (var i = 0; i < _property.length; i++){
            _names[i] = _property[i].getType();
            }
        return _names;
        }
    public static Object InvokeGetter(Class<?> propertyType, String propertyName, Object object){
        Object _value = null;
        try{
            _value = propertyType.getMethod("get" + propertyName).invoke(object);
            }
        catch (Exception exception){
            exception.printStackTrace();
            }
        return _value;
        }
    }
