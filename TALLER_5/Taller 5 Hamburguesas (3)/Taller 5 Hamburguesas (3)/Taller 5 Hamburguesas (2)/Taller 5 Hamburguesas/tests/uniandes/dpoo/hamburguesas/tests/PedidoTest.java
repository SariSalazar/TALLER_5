package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Producto;

@DisplayName("Pruebas para la clase Pedido")
public class PedidoTest {
    
    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        String cliente = "Sara";
        String dir = "Cl 4 NO605";
        pedido = new Pedido(cliente, dir);
    }


    @Test
    @DisplayName("Prueba de agregar y obtener productos")
    public void testAgregarYObtenerProductos() {
        Producto producto1 = new ProductoMenu("Producto 1", 100);
        Producto producto2 = new ProductoMenu("Producto 2", 200);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        ArrayList<Producto> productos = pedido.getProductos();
        assertEquals(2, productos.size(),"El número de productos debería ser 2.");
        assertEquals(producto1, productos.get(0), "El primer producto debería ser 'Producto 1'.");
        assertEquals(producto2, productos.get(1), "El segundo producto debería ser 'Producto 2'.");
    }

    @Test
    @DisplayName("Prueba para el cálculo del precio total")
    public void testGetPrecioTotalPedido() {
        Producto producto1 = new ProductoMenu("Producto 1", 100);
        Producto producto2 = new ProductoMenu("Producto 2", 200);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        int precioNeto = 100 + 200;
        int precioIVA = (int) (precioNeto * 0.19);
        int precioTotalEsperado = precioNeto + precioIVA;

        assertEquals(precioTotalEsperado, pedido.getPrecioTotalPedido(), "El precio total del pedido debería ser " + precioTotalEsperado);
    }

    @Test
    @DisplayName("Prueba para la generación de texto de la factura")
    public void testGenerarTextoFactura() {
        Producto producto1 = new ProductoMenu("Producto 1", 100);
        Producto producto2 = new ProductoMenu("Producto 2", 200);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        String textoFacturaEsperado = 
            "Pedido ID: 0\n" +
            "Cliente: Sara\n" +
            "Dirección: Cl 4 NO605\n" +
            "----------------\n" +
            "Producto 1 -----------  100\n" +
            "Producto 2 -----------  200\n" +
            "----------------\n" +
            "Precio Neto:  300\n" +
            "IVA:          57\n" +
            "Precio Total: 357\n";

        assertEquals(textoFacturaEsperado, pedido.generarTextoFactura().replace("\r\n", "\n"), "El texto de la factura no es el esperado.");
    }


    @Test
    @DisplayName("Prueba para guardar la factura en un archivo")
    public void testGuardarFactura() throws FileNotFoundException {
        Producto producto1 = new ProductoMenu("Producto 1", 100);
        Producto producto2 = new ProductoMenu("Producto 2", 200);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        File archivo = new File("factura_test.txt");
        pedido.guardarFactura(archivo);

        assertTrue(archivo.exists(), "El archivo de la factura debería haberse creado.");

        archivo.delete();
    }
}
