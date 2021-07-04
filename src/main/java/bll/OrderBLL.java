package bll;

import bll.validators.Validator;
import connection.ConnectionFactory;
import dao.AbstractDAO;
import dao.OrderDAO;
import model.Orders;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Clasa care implementeaza "business logic" pentru tabelul Order
 */
public class OrderBLL {

    private List<Validator<Orders>> validators;
    private AbstractDAO<Orders> dao;
    public OrderBLL() {
        this.dao = new OrderDAO();
        validators = new ArrayList<>();
    }

    /**
     * Metoda care apeleaza insertul din dao si ii adauga validatori
     * @param order
     */
    public void insert(Orders order) {
        for (Validator<Orders> v : validators) {
            v.validate(order);
        }
        dao.insert(order);
    }

    /**
     * Metoda care apeleaza updateul din dao si ii adauga validatori
     * @param order
     */
    public void update(Orders order)
    {
        for (Validator<Orders> v : validators) {
            v.validate(order);
        }
        dao.update(order);
    }

    /**
     * Metoda care apeleaza deleteul din dao in functie de id
     * @param id
     */
    public void delete(int id)
    {
        dao.delete(id, "idOrder");
    }

    /**
     * Metoda care foloseste reflectia pentru a lua datele din database si a le
     * pune intr-un tabel pentru a putea fi afisat in interfata
     * @return JTable
     */
    public static JTable getAll() {
        List<Orders> list = new ArrayList<Orders>();

        String all = "SELECT * FROM orders ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement st;
        ResultSet rs;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Orders");
        model.addColumn("ID Client");
        model.addColumn("ID Product");
        model.addColumn("Quantity");

        JTable tco = new JTable(model);
        Vector<String> vector = new Vector<String>(5);
        vector.add("idOrder");
        vector.add("idClient");
        vector.add("idProduct");
        vector.add("Quantity");
        model.addRow(vector);

        try {
            st = connection.prepareStatement(all);
            rs = st.executeQuery(all);

            while (rs.next()) {
                Orders order = new Orders(rs.getInt("idOrder"), rs.getInt("idClient"), rs.getInt("idProduct"),
                        rs.getInt("quantity"));
                list.add(order);
                model.addRow(new Object[] { rs.getInt("idOrder"), rs.getInt("idClient"), rs.getInt("idProduct"),
                        rs.getInt("quantity")});
                System.out.println(order.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tco;
    }

    /**
     * Metoda folosita pentru a verifica daca id-ul dat ca paramentru este egal cu id-ul
     * introdus. Este utilizata pentru a face comenzile
     * @param id
     * @return
     */
    public Orders getOrder(int id)
    {
        String all = "SELECT * FROM orders ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement st;
        ResultSet rs;

        try {
            st = connection.prepareStatement(all);
            rs = st.executeQuery(all);

            while (rs.next()) {
                if(rs.getInt("idOrder") == id)
                {
                    Orders order = new Orders(rs.getInt("idOrder"), rs.getInt("idClient"), rs.getInt("idProduct"), rs.getInt("quantity"));
                    return order;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
