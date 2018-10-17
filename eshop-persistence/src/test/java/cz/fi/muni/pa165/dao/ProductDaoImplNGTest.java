/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Product;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jiri21
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ProductDaoImplNGTest extends AbstractTestNGSpringContextTests {

    @Inject
    private ProductDao productDao;

    private Product PRODUCT1;
    private Product PRODUCT2;

    public ProductDaoImplNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        this.PRODUCT1 = createProductWithName("Test Product1");
        this.PRODUCT2 = createProductWithName("Test Product2");
        productDao.create(PRODUCT1);
        productDao.create(PRODUCT2);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of create method, of class ProductDaoImpl.
     */
    @Test
    public void testCreateValidProduct() {
        Product newProduct = createProductWithName("New Product");

        productDao.create(newProduct);

        List<Product> products = productDao.findAll();
        Assert.assertTrue(products.contains(newProduct));
    }

    /**
     * Tests create method with a duplicate name
     */
    @Test(expectedExceptions = javax.persistence.PersistenceException.class)
    public void testCreateDuplicateName() {
        Product duplicateProduct = createProductWithName(PRODUCT1.getName());
        productDao.create(duplicateProduct);
    }

    /**
     * Tests create method with null in name
     */
    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testCreateNullName() {
        Product nullNameProduct = createProductWithName(null);
        productDao.create(nullNameProduct);
    }

    /**
     * Test create method with null
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull() {
        productDao.create(null);
    }

    private Product createProductWithName(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

    /**
     * Test of findAll method, of class ProductDaoImpl.
     */
    @Test
    public void testFindAll() {
        List<Product> products = productDao.findAll();
        Assert.assertEquals(products.size(), 2);
        Assert.assertTrue(products.contains(PRODUCT1));
        Assert.assertTrue(products.contains(PRODUCT2));
    }

    /**
     * Test of findById method, of class ProductDaoImpl.
     */
    @Test
    public void testFindById() {
        Product product = productDao.findById(PRODUCT1.getId());
        Assert.assertEquals(product, PRODUCT1);
    }

    /**
     * Test of remove method, of class ProductDaoImpl.
     */
    @Test
    public void testRemove() {

        productDao.remove(PRODUCT1);
        List<Product> products = productDao.findAll();

        Assert.assertFalse(products.contains(PRODUCT1));
    }

    /**
     * Test of findByName method, of class ProductDaoImpl.
     */
    @Test
    public void testFindByName() {
        List<Product> products = productDao.findByName(PRODUCT1.getName());
        Assert.assertEquals(products.size(), 1);
        Assert.assertTrue(products.contains(PRODUCT1));
    }

}
