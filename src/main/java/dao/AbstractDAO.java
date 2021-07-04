package dao;

import connection.ConnectionFactory;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementeaza metode generale pentru clasele din model si corespondentele acestora
 * folosind tehnica reflextiei.
 * @param <T> este tipul clasei din Model : Client, Product, Order
 */

public abstract class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO(){
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Insereaza informatia data ca parametru in tabelul corespunzator din baza de date
     * @param data itemul inserat in baza de date
     */
    public void insert(T data)
    {
        Connection connection = null;
        PreparedStatement ps = null;

        String query = createInsertQuery(type.getDeclaredFields());
        try
        {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(query);
            int index = 1;

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                ps.setObject(index, field.get(data));
                index++;
            }
            ps.executeUpdate();

        } catch (SQLException | IllegalAccessException t) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + t.getMessage());
        }finally {
            ConnectionFactory.close(ps);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * O metoda generala care insereaza in oricare tabel
     *
     * @param declaredFields fieldurile tabelelor
     * @return
     */
    protected String createInsertQuery(Field[] declaredFields)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ");
        builder.append(type.getSimpleName());
        builder.append(" (");

        for(int i = 0; i < declaredFields.length; i++){
            builder.append(declaredFields[i].getName());
            if(i < declaredFields.length -1){
                builder.append(", ");
            }
        }
        builder.append(") \nVALUES (");
        for(int i = 0; i < declaredFields.length; i++){
            builder.append("?");
            if(i < declaredFields.length -1){
                builder.append(", ");
            }
            else{
                builder.append(")");
            }
        }
        return builder.toString();
    }

    /**
     * Sterge randul din tabel care contine id-ul dat ca parametru
     * @param id
     * @param idd
     */
    public void delete(int id, String idd) {
        Connection connection = null;
        PreparedStatement ps = null;

        String query = createDeleteQuery(idd);
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(query);
            ps.setObject(1, id);
            ps.executeUpdate();

        } catch (SQLException t) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + t.getMessage());
        } finally {
            ConnectionFactory.close(ps);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Metoda care face stergerea in functie de id folosind SQL
     * @param id
     * @return
     */
    private String createDeleteQuery(String id) {
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM ");
        builder.append(type.getSimpleName());
        builder.append(" WHERE " + id + " =?");
        return builder.toString();
    }

    /**
     * Metoda prin care se poate modifica oricare dintre fieldurile tabelelor in functie de id
     * @param data itemul dat care urmeaza sa fie modificat
     */
    public void update(T data)
    {
        Connection connection = null;
        PreparedStatement ps = null;

        String query = createUpdateQuery(type.getDeclaredFields());
        try
        {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(query);
            int index = 1;

            Field[] fields = type.getDeclaredFields();

            for(int i = 1; i < fields.length; i++)
            {
                fields[i].setAccessible(true);
                ps.setObject(index, fields[i].get(data));
                index++;
            }
            fields[0].setAccessible(true);
            ps.setObject(index,fields[0].get(data));
            System.out.println(ps);
            ps.executeUpdate();

        } catch (SQLException | IllegalAccessException t) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + t.getMessage());
        }finally {
            ConnectionFactory.close(ps);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Metoda care foloseste limbaj SQL pentru a face update intr-un tabel in functie de un id dat
     * @param declaredFields
     * @return
     */
    protected String createUpdateQuery(Field[] declaredFields)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(type.getSimpleName());
        builder.append(" SET ");

        for(int i = 1; i < declaredFields.length; i++){
            builder.append(declaredFields[i].getName());
            builder.append(" =?");
            if(i < declaredFields.length -1){
                builder.append(", ");
            }
        }
        builder.append(" WHERE " + declaredFields[0].getName() + " =?");
        System.out.println(builder);
        return builder.toString();
    }

}
