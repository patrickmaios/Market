package domain;

public class Product {

        private String desc;
        private String name;
        private float price;
        private int key;
        private int qty;

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String type) {
            this.desc = type;
        }

        public Product(String desc, String name, float price, int key, int qty) {
            this.desc = desc;
            this.name = name;
            this.price = price;
            this.key=key;
            this.qty=qty;
        }


        public Product(Product anotherItem) {
            this(anotherItem.desc, anotherItem.name, anotherItem.price, anotherItem.key, anotherItem.qty);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

    }

