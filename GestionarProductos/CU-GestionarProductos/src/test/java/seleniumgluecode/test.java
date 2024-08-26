package seleniumgluecode;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class test {
    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(test.class);

    public test() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10).toMillis());
    }

    @Given("El administrador ha iniciado sesión en la página web")
    public void cliente_inicia_sesion(){
        try {
            driver.get("http://localhost:8080/login");
            WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
            username.sendKeys("cliente");
            password.sendKeys("pass123");
            WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginButton")));
            loginButton.click();
        } catch (Exception e) {
            logger.error("Error al iniciar sesión", e);
            Assert.fail("Error al iniciar sesión: " + e.getMessage());
        }
    }

    @Given("El administrador ha gestionado las productos")
    public void cliente_agrega_productos(){
        try {
            driver.get("http://localhost:8080/products");
            WebElement addProduct1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addProduct1")));
            addProduct1.click();
            WebElement addProduct2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addProduct2")));
            addProduct2.click();
            WebElement addProduct3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addProduct3")));
            addProduct3.click();
        } catch (Exception e) {
            logger.error("Error al agregar categorías", e);
            Assert.fail("Error al agregar categorías: " + e.getMessage());
        }
    }

    @Given("Los productos están disponibles")
    public void verificar_disponibilidad_inventario(){
        try {
            driver.get("http://localhost:8080/cart");
            WebElement product1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product1")));
            WebElement product2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product2")));
            WebElement product3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product3")));
            Assert.assertTrue("La categoría 1 no está disponible", product1.getText().contains("Disponible"));
            Assert.assertTrue("La categoría 2 no está disponible", product2.getText().contains("Disponible"));
            Assert.assertTrue("La categoría 3 no está disponible", product3.getText().contains("Disponible"));
        } catch (Exception e) {
            logger.error("Error al verificar disponibilidad en inventario", e);
            Assert.fail("Error al verificar disponibilidad en inventario: " + e.getMessage());
        }
    }

    @When("El administrador procede a gestionar productos")
    public void cliente_procede_a_pagar(){
        try {
            driver.get("http://localhost:8080/cart");
            WebElement payButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("payButton")));
            payButton.click();

            wait.until(ExpectedConditions.urlContains("/payment"));

            WebElement cardNumber = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cardNumber")));
            WebElement cardHolder = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cardHolder")));
            WebElement expirationDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("expirationDate")));
            WebElement cvv = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cvv")));

            cardNumber.sendKeys("123456789");
            cardHolder.sendKeys("Cliente");
            expirationDate.sendKeys("12/23");
            cvv.sendKeys("123");

            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmButton")));
            confirmButton.click();
        } catch (Exception e) {
            logger.error("Error al proceder al pago", e);
            Assert.fail("Error al proceder al pago: " + e.getMessage());
        }
    }

    @Then("El sistema procesa la gestión de productos con éxito")
    public void verificar_pago_exitoso() {
        try {
            driver.get("http://localhost:8080/payment?cardNumber=123456789");
            WebElement cardNumber = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cardNumber")));
            WebElement cardHolder = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cardHolder")));
            WebElement expirationDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("expirationDate")));
            WebElement cvv = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cvv")));

            cardNumber.sendKeys("123456789");
            cardHolder.sendKeys("Cliente");
            expirationDate.sendKeys("12/23");
            cvv.sendKeys("123");

            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmButton")));
            confirmButton.click();

            // Esperar a que el mensaje de éxito de pago aparezca
            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message_payment")));
            Assert.assertTrue("El mensaje no contiene 'Pago exitoso'", message.getText().contains("Pago exitoso"));
        } catch (Exception e) {
            logger.error("Error al verificar el pago exitoso", e);
            Assert.fail("Error al verificar el pago exitoso: " + e.getMessage());
        }
    }

    @Then("El sistema actualiza la página web")
    public void sistema_actualiza_inventario(){
        try {
            driver.get("http://localhost:8080/products");
            WebElement product1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product1")));
            WebElement product2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product2")));
            WebElement product3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product3")));

            Assert.assertEquals("El producto 1 no está disponible", "Disponible", product1.findElement(By.className("availability")).getText());
            Assert.assertEquals("El producto 2 no está disponible", "Disponible", product2.findElement(By.className("availability")).getText());
            Assert.assertEquals("El producto 3 no está disponible", "Disponible", product3.findElement(By.className("availability")).getText());
        } catch (Exception e) {
            logger.error("Error al actualizar el inventario", e);
            Assert.fail("Error al actualizar el inventario: " + e.getMessage());
        }
    }

    @Then("El sistema notifica al administrador sobre el estado exitoso")
    public void notificar_estado_exitoso(){
        try {
            driver.get("http://localhost:8080/payment");
            WebElement cardNumber = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cardNumber")));
            WebElement cardHolder = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cardHolder")));
            WebElement expirationDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("expirationDate")));
            WebElement cvv = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cvv")));

            cardNumber.sendKeys("123456789");
            cardHolder.sendKeys("Cliente");
            expirationDate.sendKeys("12/23");
            cvv.sendKeys("123");

            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmButton")));
            confirmButton.click();

            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message_cart")));
            Assert.assertTrue("El mensaje no contiene 'Pedido exitoso'", message.getText().contains("Pedido exitoso"));
        } catch (Exception e) {
            logger.error("Error al notificar el estado exitoso del pedido", e);
            Assert.fail("Error al notificar el estado exitoso del pedido: " + e.getMessage());
        }
    }

    @Then("El sistema notifica al administrador sobre la falta de disponibilidad")
    public void notificar_falta_disponibilidad(){
        try {
            driver.get("http://localhost:8080/cart");
            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message_cart")));
            Assert.assertTrue("El mensaje no contiene 'No hay disponibilidad'", message.getText().contains("No hay disponibilidad"));
        } catch (Exception e) {
            logger.error("Error al notificar la falta de disponibilidad", e);
            Assert.fail("Error al notificar la falta de disponibilidad: " + e.getMessage());
        }
    }

    @Then("El administrador puede actualizar los productos")
    public void cliente_actualiza_carrito(){
        try {
            driver.get("http://localhost:8080/cart");
            WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("updateButton")));
            updateButton.click();
        } catch (Exception e) {
            logger.error("Error al actualizar el carrito de compras", e);
            Assert.fail("Error al actualizar el carrito de compras: " + e.getMessage());
        }
    }

    @Then("El administrador procede a gestionar nuevamente corrigiendo errores")
    public void notificar_error_pago(){
        try {
            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message_payment")));
            Assert.assertTrue("El mensaje no contiene 'Error en el pago'", message.getText().contains("Error en el pago"));
        } catch (Exception e) {
            logger.error("Error al notificar el error en el pago", e);
            Assert.fail("Error al notificar el error en el pago: " + e.getMessage());
        }
    }

    @Then("El administrador puede intentar realizar la gestión de productos nuevamente")
    public void cliente_intenta_pago_nuevamente(){
        try {
            driver.get("http://localhost:8080/payment");
            WebElement retryButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("retryButton")));
            retryButton.click();
        } catch (Exception e) {
            logger.error("Error al intentar realizar la gestión de categorías nuevamente", e);
            Assert.fail("Error al intentar realizar la gestión de categorías nuevamente: " + e.getMessage());
        }
    }

    @Given("Una categoría está no está disponible")
    public void producto_no_disponible_en_inventario(){
        try {
            driver.get("http://localhost:8080/cart");
            WebElement availability1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("availability1")));
            WebElement availability2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("availability2")));
            WebElement availability3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("availability3")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", availability1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", availability2);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", availability3);

            Assert.assertEquals("No Disponible", availability1.getText());
            Assert.assertEquals("No Disponible", availability2.getText());
            Assert.assertEquals("No Disponible", availability3.getText());
        } catch (Exception e) {
            logger.error("Error al marcar categorías como no disponibles", e);
            Assert.fail("Error al marcar categorías como no disponibles: " + e.getMessage());
        }
    }

    @When("El sistema rechaza la gestión de productos")
    public void entidad_pago_rechaza_pago(){
        try {
            driver.get("http://localhost:8080/payment?forceError=true");
            WebElement cardNumber = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cardNumber")));
            WebElement cardHolder = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cardHolder")));
            WebElement expirationDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("expirationDate")));
            WebElement cvv = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cvv")));

            cardNumber.sendKeys("1234567890123456");
            cardHolder.sendKeys("Cliente");
            expirationDate.sendKeys("12/23");
            cvv.sendKeys("123");

            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmButton")));
            confirmButton.click();

            // Esperar a que el mensaje de error de pago aparezca
            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message_payment")));
            Assert.assertTrue("El mensaje no contiene 'Error en la gestión'", message.getText().contains("Error en la gestión"));
        } catch (Exception e) {
            logger.error("Error al rechazar la gestión", e);
            Assert.fail("Error al rechazar la gestión: " + e.getMessage());
        }
    }

    @After
    public void cerrar_navegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}