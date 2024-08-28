package ec.edu.espe.granjapiscicola;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

public class GenerarFacturaStepDefinition extends BasicStepDefinition {

    private String nombre;
    private String correo;
    private String cantidad;
    private String monto;
    private boolean sriDisponible;

    @Given("El cliente ha realizado una compra y el sistema SRI está {string}")
    public void el_cliente_ha_realizado_una_compra_y_sri_esta(String estadoSRI) {
        createPDF("GenerarFactura");
        addText("\t\tInicio de prueba: Generar Factura.");
        addText("Como cajero, quiero generar una factura detallada para el cliente.");
        addText("Estado del sistema SRI: " + estadoSRI);

        sriDisponible = "disponible".equals(estadoSRI);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        if (sriDisponible) {
            driver.get("file:///C:/Users/carlo/Downloads/Quinto/requisitos/tercer%20parcial/html/factura.html");
        } else {
            driver.get("file:///C:/Users/carlo/Downloads/Quinto/requisitos/tercer%20parcial/html/factura_sri_no_disponible.html");
        }

        captureScreenShot();
    }

    @When("Confirmo que la compra es correcta y el sistema genera la factura con el nombre {string}, el correo {string}, la cantidad {string} y el monto {string}")
    public void confirmo_que_la_compra_es_correcta_y_el_sistema_genera_la_factura(String nombre, String correo, String cantidad, String monto) {
        this.nombre = nombre;
        this.correo = correo;
        this.cantidad = cantidad;
        this.monto = monto;

        addText("Confirmo que la compra es correcta y el sistema genera la factura con los siguientes datos:");
        addText("Nombre: " + nombre);
        addText("Correo: " + correo);
        addText("Cantidad: " + cantidad);
        addText("Monto: " + monto);

        // Validar que los datos de la factura coinciden con lo que se espera
        WebElement nombreCliente = driver.findElement(By.xpath("//p[span[contains(text(), 'Nombre:')]]"));
        WebElement correoCliente = driver.findElement(By.xpath("//p[span[contains(text(), 'Correo:')]]"));
        WebElement cantidadCompra = driver.findElement(By.xpath("//p[span[contains(text(), 'Cantidad:')]]"));
        WebElement montoCompra = driver.findElement(By.xpath("//p[span[contains(text(), 'Monto Total:')]]"));

        assertEquals("Nombre del cliente no coincide", "Nombre: " + nombre, nombreCliente.getText());
        assertEquals("Correo del cliente no coincide", "Correo: " + correo, correoCliente.getText());
        assertEquals("Cantidad de la compra no coincide", "Cantidad: " + cantidad, cantidadCompra.getText());
        assertEquals("Monto de la compra no coincide", "Monto Total: $" + monto, montoCompra.getText());

        captureScreenShot();
    }

    @Then("Se debe validar que la factura se haya {string} y se haya {string} al cliente")
    public void se_debe_validar_que_la_factura_se_haya_procesado_y_enviado(String estadoProcesamiento, String estadoEnvio) {
        addText("Validando que la factura se haya " + estadoProcesamiento + " y se haya " + estadoEnvio + " al cliente...");

        if (sriDisponible) {
            // Validar la presencia del número de factura y el estado de SRI
            WebElement numeroFactura = driver.findElement(By.xpath("//p[span[contains(text(), 'Número de Factura:')]]"));
            WebElement sriEstado = driver.findElement(By.xpath("//p[span[contains(text(), 'SRI Estado:')]]"));

            assertTrue("Número de factura no está presente", numeroFactura.isDisplayed());
            assertTrue("Estado del SRI no está presente o no es 'Aprobada'", sriEstado.getText().contains("Aprobada"));

            addText("La factura se generó correctamente y se envió al cliente.");
        } else {
            WebElement mensajeProcesando = driver.findElement(By.id("mensaje-procesando"));
            assertTrue("Mensaje de procesamiento no está presente", mensajeProcesando.isDisplayed());
            assertEquals("Mensaje de procesamiento incorrecto", "La factura se está procesando... Por favor, espere.", mensajeProcesando.getText());

            addText("La factura está en cola para ser procesada cuando el sistema SRI esté disponible.");
        }

        captureScreenShot();
        addText("Fin de la prueba.");
        driver.quit();
        closePDF();
    }
}