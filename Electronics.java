class Electronics extends Product {
    private int warranty;

    public Electronics(String id, String name, double price, int quantity, int warranty) {
        super(id, name, price, quantity);
        this.warranty = warranty;
    }

    @Override
    public String getType() { return "Electronic"; }  // ← ЭНЭ БАЙХГҮЙ БАЙСАН

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Баталгаат хугацаа: " + warranty + " сар");
    }

    @Override
    public Object[] toTableRow() {
        return new Object[]{id, name, quantity, price + "₮", "Electronic (" + warranty + " сар)"};
    }
}