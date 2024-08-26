package seleniumgluecode;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class test {
    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(test.class);


    public test() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10).toMillis());
    }

    @Given("El cajero está en la página de revisión de pago")
    public void elCajeroEstaEnLaPaginaDeRevisionDePago() {
        driver.get("http://localhost:8080/revisar_pago");
    }

    @When("El cajero confirma el pago")
    public void elCajeroConfirmaElPago() {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("La compra debe ser marcada como confirmada")
    public void laCompraDebeSerMarcadaComoConfirmada() {
        String estado = driver.findElement(By.xpath("//p[text()='La compra ha sido confirmada.']")).getText();
        assertEquals("La compra ha sido confirmada.", estado);
    }

    @And("El cajero debe recibir una notificación de confirmación")
    public void cajero_recibe_notificacion_confirmacion() {
        String notificacion = driver.findElement(By.xpath("//p[text()='Notificación enviada al cajero.']")).getText();
        assertEquals("Notificación enviada al cajero.", notificacion);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
