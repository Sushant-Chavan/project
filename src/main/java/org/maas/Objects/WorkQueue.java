package org.maas.Objects;

import java.util.LinkedList;
import java.util.Vector;

public class WorkQueue {
    private LinkedList<ProductStatus> workQueue;

    public WorkQueue()
    {
         this.workQueue = new LinkedList<ProductStatus>();
    }

    public void addProduct(ProductStatus productStatus)
    {
        this.workQueue.add(productStatus);
    }

    public void addProducts(Iterable<ProductStatus> products)
    {
        for (ProductStatus product : products)
        {
            this.addProduct(product);
        }
    }

    public ProductStatus getFirstProduct()
    {
        ProductStatus firstProduct = null;

        if (!this.workQueue.isEmpty())
        {
            firstProduct = this.workQueue.pop();
        }

        System.out.println("First product " + firstProduct);
        return firstProduct;
    }

    public boolean hasProducts(){
        if (this.workQueue.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    // find all items of the same type as the first item
    // return all the items of the same stage or null if workQueue is empty
    public Vector<ProductStatus> getProductBatch()
    {
        Vector<ProductStatus> batch = null;

        if (!this.workQueue.isEmpty())
        {
            ProductStatus firstProduct = this.workQueue.peek();
            batch = new Vector<ProductStatus>();
            String curType = firstProduct.getProduct().getGuid();

            //System.out.println("Type  " + curType);

            for (ProductStatus productStatus : this.workQueue)
            {
                if (productStatus.getProduct().getGuid().equals(curType))
                {
                    batch.add(productStatus);
                    //this.workQueue.remove(productStatus);  // should we check so that we dont remove the first product again?
                }
            }
        }

        return batch;
    }

	public Vector<ProductStatus> findProductStatus(String productType) {

    	Vector<ProductStatus> batch = null;

    	if (!this.workQueue.isEmpty())
        {
    		batch = new Vector<ProductStatus>();

            for (ProductStatus productStatus : this.workQueue)
            {
                if (productStatus.getProduct().getGuid().equals(productType))
                {
                   batch.add(productStatus);
                }
            }
        }

    	return batch;
    }

	// Gets all products of type productType and removes them from the workqueue
    public void removeProductStatus(String productType){

    	Vector <ProductStatus> batch = findProductStatus(productType);

    	for (ProductStatus productStatus : batch) {
    		this.workQueue.remove(productStatus);
    	}
    }

}
