package com.example.misaldo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {

    static String url = "jdbc:mysql://pyx5jmxrQHGkF6g.root:mKvfuW6F5snpt7l3@gateway01.ap-northeast-1.prod.aws.tidbcloud.com:4000/misaldo?sslMode=VERIFY_IDENTITY";

// Sustituir por variables de entorno cuando se tenga servidor
    static String usuario = "pyx5jmxrQHGkF6g.root";
    static String password = "mKvfuW6F5snpt7l3";

    Connection conexion;

    public Configuration() {
    }

    public boolean startConection() {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean closeConection() {
        try {
            this.conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
