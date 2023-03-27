package domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> orderList=null;
    public Order(){
        orderList=new ArrayList<Product>();
    }

    public void add(Product item, int qty){
        Product itemInOrder=null;
        for(Product a:orderList){
            if(a.getKey()==item.getKey()) {
                itemInOrder=a;
                itemInOrder.setQty(itemInOrder.getQty()+1);
                break;
                }

            }
        if (itemInOrder==null){
            Product itemToOrder= new Product(item);
            itemToOrder.setQty(qty);
            orderList.add(itemToOrder);

        }
    }
    public void showlist(){
        for(int i=0;i<orderList.size();i++){
            System.out.println(orderList.get(i).getName()+" "+orderList.get(i).getQty());
        }
    }

    public void delete(Product item, int qty )
    {
        Product itemInOrder = null;

        for (Product a : orderList) {
            if (a.getKey()==item.getKey()) {
                itemInOrder = a;
                if(itemInOrder.getQty()==0)
                {
                    break;
                }
                if(itemInOrder.getQty()-qty<0)
                {
                    itemInOrder.setQty(0);
                    break;
                }
                itemInOrder.setQty(itemInOrder.getQty() - qty);
                break;
            }
        }
    }
    public void initialize(){
        orderList.clear();
    }
}
