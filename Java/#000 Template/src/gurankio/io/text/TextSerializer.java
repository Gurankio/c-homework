package gurankio.io.text;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextSerializer {

    private static final HashMap<Object, String> instanceSupplier = new HashMap<>();
    private static final HashMap<Class<?>, Function<Object, String>> classSuppliers = new HashMap<>();

    /**
     * Makes sure to add only matching keys and function.
     *
     * @param target   The object to register.
     * @param string   The associated string.
     */
    public static void registerInstanceSupplier(Object target, String string) {
        instanceSupplier.remove(target);
        instanceSupplier.put(target, string);
    }

    /**
     * Makes sure to add only matching keys and function.
     *
     * @param target   The class to register.
     * @param function The associated supplier.
     * @param <T>      The registered type.
     */
    @SuppressWarnings("unchecked")
    public static <T> void registerClassSupplier(Class<T> target, Function<T, String> function) {
        classSuppliers.remove(target);
        // Casting to Object, should not give an exception.
        classSuppliers.put(target, (Function<Object, String>) function);
    }

    // Static initializer for the suppliers.
    static {
        registerClassSupplier(ArrayList.class, TextSerializer::fromArrayList);
        registerClassSupplier(Class.class, TextSerializer::fromClass);
        registerClassSupplier(Field.class, TextSerializer::fromField);
        registerClassSupplier(Parameter.class, TextSerializer::fromParameter);
        registerClassSupplier(Method.class, TextSerializer::fromMethod);
        registerClassSupplier(Constructor.class, TextSerializer::fromConstructor);
    }

    /**
     * Serializes a given object.
     *
     * @param object The object to serialize.
     * @return       A string representation of the object.
     */
    public static String serialize(Object object) {
        if (object == null) return "null";
        if (instanceSupplier.containsKey(object)) return instanceSupplier.get(object);
        if (classSuppliers.containsKey(object.getClass())) return classSuppliers.get(object.getClass()).apply(object);
        if (object.getClass().isArray()) return serialize(Arrays.asList(((Object[]) object)));
        return object.toString();
    }

    private static String fromArrayList(ArrayList<?> array) {
        return "[\n" + array.stream().map(TextSerializer::serialize).collect(Collectors.joining(",\n")) + "\n]";
    }

    private static String fromClass(Class<?> target) {
        return target.getSimpleName();
    }

    private static String fromField(Field field) {
        return field.getName() + (field.getClass().isArray() ? "[]" : "");
    }

    private static String fromParameter(Parameter parameter) {
        return parameter.isNamePresent() ? parameter.getName() : "parameter";
    }

    private static String fromMethod(Method method) {
        return method.getName() + "(" + Arrays.stream(method.getParameters()).map(TextSerializer::serialize).collect(Collectors.joining(", ")) + ")";
    }

    private static String fromConstructor(Constructor<?> constructor) {
        return constructor.getDeclaringClass().getSimpleName() + "(" + Arrays.stream(constructor.getParameters()).map(TextSerializer::serialize).collect(Collectors.joining(", ")) + ")";
    }

}
