
package uniandes.dpoo.hamburguesas.tests;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ProductoMenuTest {


    private ProductoMenu producto;

    @BeforeEach
    public void setUp() {
        producto = new ProductoMenu("corral",20000);
    }

    @Test
    @DisplayName("Prueba de obtención del nombre del producto")
    public void testGetNombre() {
        assertEquals("corral", producto.getNombre(), "El nombre del producto debe ser 'corral'");
    }

    @Test
    @DisplayName("Prueba de obtención del precio del producto")
    public void  testGetPrcio(){
        assertEquals(20000, producto.getPrecio(), "El precio del producto debe ser 20000");
    }

    @Test
    void testGenerarTextoFactura() {
        String esperado = "corral\n" + "            " + 20000 + "\n";
        assertEquals(esperado, producto.generarTextoFactura(), "El texto de la factura debe ser 'corral\n            20000\n'");

    }

    @Test
    @DisplayName("Prueba de nombre no nulo")
    public void testNombreNoNulo(){
        assertNotNull(producto.getNombre(), "El nombre del producto no debe ser nulo");
    }

    @Test
    @DisplayName("Prueba de precio no nulo")
    void testPrecioPositivo(){
        assertTrue(producto.getPrecio() > 0, "El precio del producto debe ser positivo");
    }

    
}
