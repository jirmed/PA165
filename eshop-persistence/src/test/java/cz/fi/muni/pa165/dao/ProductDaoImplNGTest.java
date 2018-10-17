/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Category;
import cz.fi.muni.pa165.entity.Product;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jiri21
 */

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ProductDaoImplNGTest {
    
    @Inject
    private ProductDao productDao;
    
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
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of create method, of class ProductDaoImpl.
     */
    @Test
    public void testCreate() {
        String name = "Test Product";
        Product product = new Product();
        product.setName(name);
        productDao.create(product);
        
        List<Product> products = productDao.findByName(name);
        assertEquals(products.size(), 1);
        
    }

    /**
     * Test of findAll method, of class ProductDaoImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        ProductDaoImpl instance = new ProductDaoImpl();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class ProductDaoImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        ProductDaoImpl instance = new ProductDaoImpl();
        Product expResult = null;
        Product result = instance.findById(id);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ProductDaoImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Product p = null;
        ProductDaoImpl instance = new ProductDaoImpl();
        instance.remove(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class ProductDaoImpl.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        ProductDaoImpl instance = new ProductDaoImpl();
        List expResult = null;
        List result = instance.findByName(name);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
