package cz.fi.muni.pa165.tasks;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Category;
import cz.fi.muni.pa165.entity.Product;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class Task02 extends AbstractTestNGSpringContextTests {

    private Category electro;
    private Category kitchen;
    private Product flashlight;
    private Product kitchenRobot;
    private Product plate;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @BeforeClass
    public void beforeClass() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            electro = persitCategoryWithName(em, "Electro");
            kitchen = persitCategoryWithName(em, "Kitchen");

            flashlight = persitProductWithName(em, "Flashlight");
            electro.addProduct(flashlight);
//            flashlight.addCategory(electro);

            kitchenRobot = persitProductWithName(em, "Kitchen Robot");
            electro.addProduct(kitchenRobot);
            kitchen.addProduct(kitchenRobot);

            plate = persitProductWithName(em, "Plate");
            kitchen.addProduct(plate);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    private Category persitCategoryWithName(EntityManager em, String name) {
        Category cat = new Category();
        cat.setName(name);
        em.persist(cat);
        return cat;
    }

    private Product persitProductWithName(EntityManager em, String name) {
        Product product = new Product();
        product.setName(name);
        em.persist(product);
        return product;
    }

    @Test
    public void categoryElectroTest() {
        EntityManager em = emf.createEntityManager();
        Set<Product> products;
        try {
            electro = em.merge(electro);
            products = electro.getProducts();

            assertContainsProductWithName(products, "Flashlight");
            assertContainsProductWithName(products, "Kitchen Robot");

        } finally {
            em.close();
        }
    }

    @Test
    public void categoryKitchenTest() {
        EntityManager em = emf.createEntityManager();
        Set<Product> products;
        try {
            kitchen = em.merge(kitchen);
            products = kitchen.getProducts();

            assertContainsProductWithName(products, "Plate");
            assertContainsProductWithName(products, "Kitchen Robot");
        } finally {
            em.close();
        }
    }

    @Test
    public void productFlashlightTest() {
        EntityManager em = emf.createEntityManager();
        Set<Category> categories;
        try {
            flashlight = em.merge(flashlight);
            categories = flashlight.getCategories();
            assertContainsCategoryWithName(categories, "Electro");
        } finally {
            em.close();
        }
    }
    
        @Test
    public void productKitchenRobotTest() {
        EntityManager em = emf.createEntityManager();
        Set<Category> categories;
        try {
            kitchenRobot = em.merge(kitchenRobot);
            categories = kitchenRobot.getCategories();
            assertContainsCategoryWithName(categories, "Electro");
            assertContainsCategoryWithName(categories, "Kitchen");
        } finally {
            em.close();
        }
    }

        @Test
    public void productPlateTest() {
        EntityManager em = emf.createEntityManager();
        Set<Category> categories;
        try {
            plate = em.merge(plate);
            categories = plate.getCategories();
            assertContainsCategoryWithName(categories, "Kitchen");
        } finally {
            em.close();
        }
    }

    
    private void assertContainsCategoryWithName(Set<Category> categories,
            String expectedCategoryName) {
        for (Category cat : categories) {
            if (cat.getName().equals(expectedCategoryName)) {
                return;
            }
        }

        Assert.fail("Couldn't find category " + expectedCategoryName + " in collection " + categories);
    }

    private void assertContainsProductWithName(Set<Product> products,
            String expectedProductName) {

        for (Product prod : products) {
            if (prod.getName().equals(expectedProductName)) {
                return;
            }
        }

        Assert.fail("Couldn't find product " + expectedProductName + " in collection " + products);
    }

}
