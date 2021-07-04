package bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import bll.validators.EmailValidator;
import bll.validators.NameValidator;
import bll.validators.Validator;
import connection.ConnectionFactory;
import dao.AbstractDAO;
import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Clasa care implementeaza "business logic" pentru tabelul Client
 */
public class ClientBLL {

    private AbstractDAO<Client> dao;
    private List<Validator<Client>> validators;

    public ClientBLL() {
        dao = new ClientDAO();
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new NameValidator());
    }

    /**
     * Metoda care apeleaza insertul din dao si ii adauga validatori
     * @param client
     */
    public void insert(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        dao.insert(client);
    }

    /**
     * Metoda care apeleaza update-ul din dao si ii adauga validatori
     * @param client
     */
    public void update(Client client)
    {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        dao.update(client);
    }

    /**
     * Metoda care apeleaza delete-ul din dao
     * @param id
     */
    public void delete(int id)
    {
        dao.delete(id, "id");
    }

    /**
     * Metoda care foloseste reflextia pentru a lua datele din database si a le
     * pune intr-un tabel pentru a putea fi afisat in interfata
     * @return JTable
     */
    public static JTable getAll() {
        ArrayList<Client> list = new ArrayList<Client>();

        String all = "SELECT * FROM client ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement st;
        ResultSet rs;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Firstname");
        model.addColumn("Lastname");
        model.addColumn("Email");

        JTable tc = new JTable(model);
        Vector<String> vector = new Vector<String>(5);
        vector.add("ID");
        vector.add("Firstname");
        vector.add("Lastname");
        vector.add("Email");
        model.addRow(vector);
        try {
            st = connection.prepareStatement(all);
            rs = st.executeQuery(all);

            while (rs.next()) {
                Client client = new Client(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"));
                list.add(client);
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email")});
                System.out.println(client.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tc;
    }

    /**
     * Metoda folosita pentru a verifica daca id-ul dat ca paramentru este egal cu id-ul
     * introdus. Este utilizata pentru a face comenzile
     * @param id
     * @return
     */
    public Client getClient(int id)
    {
        String all = "SELECT * FROM client ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement st;
        ResultSet rs;

        try {
            st = connection.prepareStatement(all);
            rs = st.executeQuery(all);

            while (rs.next()) {
                if(rs.getInt("id") == id)
                {
                    Client client = new Client(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"));
                    return client;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
