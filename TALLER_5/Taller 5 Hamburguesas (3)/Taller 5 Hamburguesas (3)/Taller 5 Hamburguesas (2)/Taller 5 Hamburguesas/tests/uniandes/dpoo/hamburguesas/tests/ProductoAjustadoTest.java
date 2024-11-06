package uniandes.dpoo.hamburguesas.tests;


import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductoAjustadoTest {

    private ProductoAjustado productoAjustado;
    private ProductoMenu productoBase;
    private Ingrediente ingredienteExtra;
    private Ingrediente ingredienteEliminado;


    @BeforeEach
    void setUp(){
        productoBase = new ProductoMenu("corral sencilla", 15000);
        productoAjustado = new ProductoAjustado(productoBase);

        ingredienteExtra = new Ingrediente("queso", 2000);
        ingredienteEliminado = new Ingrediente("tomate", 1000);

        productoAjustado.getAgregados().add(ingredienteExtra);
        productoAjustado.getEliminados().add(ingredienteEliminado);



    }

    @Test
    @DisplayName("Test para verificar el nombre del producto ajustado")
    void testGetNombre(){
        assertEquals("corral sencilla", productoAjustado.getNombre());
    }

    @Test
    @DisplayName("Test para verificar el precio del producto ajustado")
    void tesGetPrecio(){
        int precioEsperado = 15000 + 2000;
        assertEquals(precioEsperado, productoAjustado.getPrecio(), "El precio del producto no es correcto");
    }

    @Test
    @DisplayName("Test para verificar el texto de la factura")
    void testGenerartextoFactura(){
        String textoEsperado = "corral sencilla\n" + "    +"+"queso"+"                "+2000+"\n" +"    -"+"tomate\n"+"            17000\n";
        assertEquals(textoEsperado, productoAjustado.generarTextoFactura(), "El texto de la factura no es correcto");
    }

    @Test
    @DisplayName("Test agregar ingredientes")
    void testAgregarIngrediente(){
        assertTrue(productoAjustado.getAgregados().contains(ingredienteExtra), "El ingrediente no fue agregado correctamente");
    }

    @Test
    @DisplayName("Test eliminar ingredientes")
    void testEliminarIngrediente(){
        assertTrue(productoAjustado.getEliminados().contains(ingredienteEliminado), "El ingrediente no fue agregado correctamente");
    }

    @Test
    void testSetAgregados() {
        
        ArrayList<Ingrediente> nuevosAgregados = new ArrayList<>();
        nuevosAgregados.add(new Ingrediente("Cebolla", 500));
        productoAjustado.setAgregados(nuevosAgregados);
        assertEquals(nuevosAgregados, productoAjustado.getAgregados(), "La lista de agregados no se actualizó correctamente.");
    }

    @Test
    void testSetEliminados() {

        ArrayList<Ingrediente> nuevosEliminados = new ArrayList<>();
        nuevosEliminados.add(new Ingrediente("Lechuga", 0));
        productoAjustado.setEliminados(nuevosEliminados);
        assertEquals(nuevosEliminados, productoAjustado.getEliminados(), "La lista de eliminados no se actualizó correctamente.");
    }







    
}
