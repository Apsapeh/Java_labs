package product;

public enum ProductTypeEnum {

    COTTON /*{
        public String toString() {
            return "Хлопок";
        }
    }*/,
    SUGAR_BEET /*{
        public String toString() {
            return "Сахарная свёкла";
        }
    }*/,
    MOON_RYE /*{
        public String toString() {
            return "Лунная рожь";
        }
    }*/,
    WHEAT /*{
        public String toString() {
            return "Пшеница";
        }
    }*/,
    CLOTH /*{
        public String toString() {
            return "Ткань";
        }
    }*/,
    SUGAR /*{
        public String toString() {
            return "Сахар";
        }
    }*/;

    private int number = 3;
    ProductTypeEnum() {
        number = 10;
    }
    public String toString() {
        String [] ru = {
                "Хлопок", "Сахарная свёкла", "Лунная рожь",
                "Пшеница", "Ткань", "Сахар"
        };
        //return (new String [] {"a", "b"})[1];
        return ru[ordinal()];
    }
}
