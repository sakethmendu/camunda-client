package product.workflow.model;

public class Product {
    private String productName;
    private String productDescription;
    private String productUrl;

    public Product() {
    }

    public Product(String productName, String productDescription, String productUrl) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productUrl='" + productUrl + '\'' +
                '}';
    }
}
