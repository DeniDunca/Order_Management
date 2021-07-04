package model;

public class Orders {
    private int idOrder;
    private int idClient;
    private int idProduct;
    private int quantity;

    public Orders(int idOrder, int idClient, int idProduct, int quantity) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

}
