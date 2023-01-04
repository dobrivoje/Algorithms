package algs.TIJ4th.BeanInfo;

import java.beans.*;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class IntrospectorExample {

    public static void main(String[] args) throws IntrospectionException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException,
                                                  NoSuchFieldException {
        BeanInfo beanInfo = Introspector.getBeanInfo(TheBean.class);

        Field[] declaredFields = TheBean.class.getDeclaredFields();

        TheBean orig1 = new TheBean("str1", 1, null);
        TheBean changed1 = new TheBean("str1", 2, new Date());
        for (Field field : declaredFields) {
            field.setAccessible(true);
            System.err.println(field.getName() + " -> " + field.get(orig1));
            System.err.println(field.getName() + " -> " + field.get(changed1));
        }

        TheBean orig2 = new TheBean(new Date(), "dobri", 11L);
        TheBean changed2 = new TheBean(new Date(), "krme", 37L);
        for (Field field : declaredFields) {
            field.setAccessible(true);
            System.err.println(field.getName() + " -> " + field.get(orig2));
            System.err.println(field.getName() + " -> " + field.get(changed2));
        }

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        System.err.println("-------------------------------- field names i od bazne i od izvedene klase --------------------------------");
        List<String> fNames = Arrays.stream(propertyDescriptors).map(FeatureDescriptor::getName).filter(e -> !"class".equalsIgnoreCase(e)).collect(Collectors.toList());
        System.err.println(fNames);
        System.err.println("-------------------------------- field names i od bazne i od izvedene klase --------------------------------");

        System.err.println();
        System.err.println();

        for (PropertyDescriptor propertyDesc : propertyDescriptors) {
            String fName = propertyDesc.getName();
            Method getter = propertyDesc.getReadMethod();

            if (skipAnnotated(fName, TheBean.class))
                continue;

            /*if (getter == null || "modifiedAt".equals(fName)) {
                continue;
            }*/

            Object originalValue = getter.invoke(orig2);
            Object changedValue = getter.invoke(changed2);

            if (!Objects.equals(originalValue, changedValue)) {
                System.err.println("promena na polju [" + fName + "], old=" + originalValue + ", new=" + changedValue);
            }
        }


        /*
        TheBean instance = (TheBean) Beans.instantiate(IntrospectorExample.class.getClassLoader(),
                                                       beanInfo.getBeanDescriptor().getBeanClass().getName());

        for (PropertyDescriptor propDescriptor : beanInfo.getPropertyDescriptors()) {
            String fieldName = propDescriptor.getName();
            Class<?> propertyType = propDescriptor.getPropertyType();
            String displayName = propDescriptor.getDisplayName();

            Method methodReadAccess = propDescriptor.getReadMethod();
            Method methodWriteAccess = propDescriptor.getWriteMethod();

            System.out.println("--------------------------------------------------");
            System.out.println("Filed Name: " + fieldName);
            System.out.println("Property Display Name: " + displayName);
            System.out.println("Property Type: " + propertyType);

            if ("someStr".equals(fieldName)) {
                System.out.println("Property value 2: " + methodReadAccess.invoke(instance));
                methodWriteAccess.invoke(instance, "sssssssssssss");
                System.out.println("Value for [" + fieldName + "] after setting: " + methodReadAccess.invoke(instance));
            } else if ("value".equals(fieldName)) {
                System.out.println("Property value 2: " + methodReadAccess.invoke(instance));
                methodWriteAccess.invoke(instance, 900L);
                System.out.println("Value for [" + fieldName + "] after setting: " + methodReadAccess.invoke(instance));
            }
        }
        */
    }

    private static <T extends Class<?>, A extends Class<? extends Annotation>>
    boolean skipAnnotated(String fName, T clazz) {
        if ("class".equalsIgnoreCase(fName) || fName == null) {
            return false;
        }

        boolean isDefinedOnClassLevel = clazz.isAnnotationPresent(ChangeLog.class);
        boolean isDefinedOnSuperClassLevel = clazz.getSuperclass().isAnnotationPresent(ChangeLog.class);

        boolean isNoFieldAnnotatedWithChangeLogIncluded = Arrays.stream(clazz.getDeclaredFields())
                                                                .noneMatch(a -> a.isAnnotationPresent(ChangeLog.Include.class));

        try {
            Field[] declaredSuperFields = clazz.getSuperclass().getDeclaredFields();
//            Arrays.stream(declaredSuperFields).forEach(f -> f.setAccessible(true));
            List<Field> declaredSuperclassFieldsList = Arrays.stream(declaredSuperFields)
                                                             .collect(Collectors.toList());
            Field[] declaredFields = clazz.getDeclaredFields();
            Arrays.stream(declaredFields).forEach(f -> f.setAccessible(true));
            List<Field> declaredClassFieldsList = Arrays.stream(declaredFields)
                                                        .collect(Collectors.toList());

            declaredClassFieldsList.addAll(declaredSuperclassFieldsList);

            for (Field field : declaredClassFieldsList) {
                ChangeLog superClassChangeLogAnnot = clazz.getSuperclass().getAnnotation(ChangeLog.class);

                boolean isNotPropagateToSubClass = superClassChangeLogAnnot != null &&
                    !superClassChangeLogAnnot.propagateToSubClass();
                boolean isErrorClassAnnotated = isNotPropagateToSubClass && clazz.isAnnotationPresent(ChangeLog.class);
                if (isErrorClassAnnotated)
                    throw new RuntimeException("Klasa mora ili naslediti ChangeLog anotaciju, ili.. nastavi tekst.");

                if (field.isAnnotationPresent(ChangeLog.class))
                    if (field.getAnnotation(ChangeLog.class).skip())
                        System.err.println(field.getName() + " : skipped");

                boolean fieldExcluded = field.getAnnotation(ChangeLog.Exclude.class) != null;
                if (!fieldExcluded && !isNotPropagateToSubClass) {
                    String errMsg = "ChangeLog.Exclude set on field, but not on the class (" + clazz.getSimpleName() + ")";

                    throw new RuntimeException(errMsg);
                }

                if (isDefinedOnClassLevel && !clazz.getAnnotation(ChangeLog.class).includeAllFields())
                    if (field.getAnnotations().length == 0)
                        System.err.println("excluded: " + field.getName());

                if (fieldExcluded)
                    System.err.println(field.getName() + " : excluded");
            }

            System.err.println();
        } catch (SecurityException e) {
            System.err.println("greška: " + e.toString());
        }

        return true;
    }
}