package bill;


import model.Client;
import model.Orders;
import model.Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {

    /**
     *
     * Genereaza o chitanta cu detaliile comenzii care a fost inserata in tabelul de comenzi
     * Chitanta este salvata in fisier *.txt
     * @param order
     * @param client
     * @param product
     *
     */
    public void generateBill(Orders order, Client client, Product product)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        File file = new File("Order" + order.getIdOrder() + ".txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        FileWriter filewriter;
        BufferedWriter bufferedwriter = null;
        try {
            filewriter = new FileWriter(file.getAbsoluteFile());
            bufferedwriter = new BufferedWriter(filewriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String bill = "_________BILL_________\r\n\r\n DATA: " + dateFormat.format(date)
                + "\r\n\r\n The client : FirstName: " + client.getFirstName() + "\r\n LastName: "
                + client.getLastName() + "\r\n Email: " + client.getEmail() + "\r\n\r\n ordered the product: Name: " + product.getName() + "\r\n Price: "
                + product.getPrice() + "\r\n Quantity: " + product.getQuantity();
        try {
            bufferedwriter.write(bill);
            bufferedwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
