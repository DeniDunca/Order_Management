package bll;

import bll.validators.Validator;
import connection.ConnectionFactory;
import dao.AbstractDAO;
import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProductBLL {

    private List<Validator<Product>> validators;
    private AbstractDAO<Product> dao;
    public ProductBLL() {
        dao = new ProductDAO();
        validators = new ArrayList<>();
    }

    /**
     * Metoda care apeleaza insertul din dao si ii adauga validatori
     * @param product
     */
    public void insert(Product product){
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        dao.insert(product);
    }

    /**
     * Metoda care apeleaza updateul din dao si ii adauga validatori
     * @param product
     */
    public void update(Product product)
    {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        dao.update(product);
    }

    /**
     * Metoda care apeleaza deleteul din dao in functie de id
     * @param id
     */
    public void delete(int id)
    {
        dao.delete(id, "id");
    }

    /**
     * Metoda care foloseste reflectia pentru a lua datele din database si a le
     * pune intr-un tabel pentru a putea fi afisat in interfata
     * @return JTable
     */
    public static JTable getAll() {
        List<Product> list = new ArrayList<Product>();

        String all = "SELECT * FROM product ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement st;
        ResultSet rs;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Product");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Quantity");

        JTable tp = new JTable(model);
        Vector<String> vector = new Vector<String>(5);
        vector.add("ID");
        vector.add("Name");
        vector.add("Price");
        vector.add("Quantity");
        model.addRow(vector);

        try {
            st = connection.prepareStatement(all);
            rs = st.executeQuery(all);

            while (rs.next()) {
                Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price"),rs.getInt("quantity"));
                list.add(product);
                model.addRow(new Object[] { rs.getInt("id"), rs.getString("name"), rs.getInt("price"),rs.getInt("quantity") });
                System.out.println(product.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tp;
    }

    /**
     * Metoda folosita pentru a verifica daca id-ul dat ca paramentru este egal cu id-ul
     * introdus. Este utilizata pentru a face comenzile
     * @param id
     * @return
     */
    public Product getProduct(int id)
    {
        String all = "SELECT * FROM product ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement st;
        ResultSet rs;

        try {
            st = connection.prepareStatement(all);
            rs = st.executeQuery(all);

            while (rs.next()) {
                if(rs.getInt("id") == id)
                {
                    Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"));
                    return product;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
