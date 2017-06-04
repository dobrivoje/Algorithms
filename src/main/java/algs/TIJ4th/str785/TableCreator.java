package algs.TIJ4th.str785;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import algs.TIJ4th.str785.annotations.Constraints;
import algs.TIJ4th.str785.annotations.DBTable;
import algs.TIJ4th.str785.annotations.SQLInteger;
import algs.TIJ4th.str785.annotations.SQLString;

/**
 *
 * @author root
 */
public class TableCreator {

    public static void main(String[] args) throws Exception {
        // unesi: algs.TIJ4th.str785.Member
        System.out.println("Unesite klasu (preko FQDN imena): ");
        Scanner scanner = new Scanner(System.in);
        String className = scanner.next();
        System.err.println("Uneta je klasa : " + className);

        Class<?> cl = Class.forName(className);
        DBTable dBTable = cl.getAnnotation(DBTable.class);
        if (dBTable == null) {
            System.err.println("No DB annotation for this table");
        } else {
            String tableName = dBTable.name().length() < 1 ? dBTable.name() : cl.getName().toUpperCase();

            List<String> colDefs = new ArrayList<>();
            
            for (Field f : cl.getDeclaredFields()) {
                String colName = null;
                Annotation[] anns = f.getDeclaredAnnotations();
                if (anns.length > 0) {
                    if (anns[0] instanceof SQLInteger) {
                        SQLInteger sInt = (SQLInteger) anns[0];
                        colName = sInt.name().length() < 1 ? f.getName().toUpperCase() : sInt.name();
                        colDefs.add(colName + " INT " + getConstraints(sInt.constraints()));
                    }

                    if (anns[0] instanceof SQLString) {
                        SQLString sStr = (SQLString) anns[0];
                        colName = sStr.name().length() < 1 ? f.getName().toUpperCase() : sStr.name();
                        colDefs.add(colName + " NVARCHAR (" + sStr.value() + ")"
                                + getConstraints(sStr.constraints()));
                    }

                    StringBuilder createCommand = new StringBuilder(
                            "\nCREATE TABLE " + tableName + "("
                    );

                    for (String columnDef : colDefs) {
                        createCommand.append("\n    ").append(columnDef).append(", ");
                    }
                    String tableCreate = createCommand
                            .substring(0, createCommand.length() - 2)
                            .concat(")");

                    System.out.println("table creation for a class " + className
                            + ", is : " + tableCreate
                    );
                }
            }
        }
    }

    private static String getConstraints(Constraints constraints) {
        String cc = "";

        if (!constraints.allowNull()) {
            cc += " NOT NULL";
        }

        if (constraints.primaryKey()) {
            cc += " PRIMARY KEY";
        }

        if (!constraints.unique()) {
            cc += " UNIQUE";
        }

        return cc;
    }
}
