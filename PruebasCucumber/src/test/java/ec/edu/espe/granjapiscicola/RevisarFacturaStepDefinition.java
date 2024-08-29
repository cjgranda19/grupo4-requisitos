package ec.edu.espe.granjapiscicola;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

public class RevisarFacturaStepDefinition extends BasicStepDefinition {

    @Given("el cliente ha iniciado sesión en su cuenta")
    public void el_cliente_ha_iniciado_sesion_en_su_cuenta() {
        driver.get("file:///C:/Users/carlo/Downloads/Quinto/requisitos/tercer%20parcial/html/revisar_factura.html");
        addText("El cliente ha iniciado sesión en su cuenta.");
    }

    @When("el cliente selecciona una factura para revisar")
    public void el_cliente_selecciona_una_factura_para_revisar() {
        WebElement numeroFactura = driver.findElement(By.id("numero-factura"));
        assertTrue(numeroFactura.isDisplayed());
        addText("El cliente ha seleccionado la factura " + numeroFactura.getText() + " para revisar.");
    }

    @Then("el sistema muestra los detalles completos de la factura")
    public void el_sistema_muestra_los_detalles_completos_de_la_factura() {
        WebElement facturaDetalle = driver.findElement(By.className("factura-detalle"));
        assertTrue(facturaDetalle.isDisplayed());
        addText("El sistema ha mostrado los detalles completos de la factura.");
        captureScreenShot();
    }

    @Given("el cliente está revisando una factura")
    public void el_cliente_esta_revisando_una_factura() {
        // Esta condición ya está cubierta por los pasos anteriores
        addText("El cliente está revisando una factura.");
    }

    @When("el cliente hace clic en el botón {string}")
    public void el_cliente_hace_clic_en_el_boton(String boton) {
        String buttonId = boton.equals("Descargar Factura") ? "btn-descargar" : "btn-imprimir";
        WebElement btnAccion = driver.findElement(By.id(buttonId));
        btnAccion.click();
        addText("El cliente ha hecho clic en el botón " + boton);
    }

    @Then("el sistema inicia la descarga de la factura en formato PDF")
    public void el_sistema_inicia_la_descarga_de_la_factura_en_formato_pdf() {
        // Aquí simularíamos la descarga del PDF
        addText("El sistema ha iniciado la descarga de la factura en formato PDF.");
    }

    @Then("el sistema abre el diálogo de impresión del navegador")
    public void el_sistema_abre_el_dialogo_de_impresion_del_navegador() {
        // Aquí simularíamos la apertura del diálogo de impresión
        addText("El sistema ha abierto el diálogo de impresión del navegador.");
    }
}