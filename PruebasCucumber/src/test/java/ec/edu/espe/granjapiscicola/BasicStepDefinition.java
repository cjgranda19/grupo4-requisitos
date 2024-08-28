package ec.edu.espe.granjapiscicola;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;

public class BasicStepDefinition {
    private ChromeOptions options;
    protected WebDriver driver;
    protected Document document;
    private PdfWriter pdfWriter;

    public BasicStepDefinition() {
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    protected void createPDF(String stepName) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String dtime = formatter.format(System.currentTimeMillis());
            String output = "./evidencias/evidencia_" + stepName + "_" + dtime + ".pdf";

            if (!Files.exists(new File("./evidencias").toPath())) {
                Files.createDirectory(new File("./evidencias").toPath());
            }

            FileOutputStream fos = new FileOutputStream(output);

            document = new Document();
            pdfWriter = PdfWriter.getInstance(document, fos);
            pdfWriter.open();
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void captureScreenShot() {
        try {
            byte[] input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Image image = Image.getInstance(input);

            // Define the width as the width of the page minus the margins
            float width = PageSize.A4.getWidth() - document.leftMargin() - document.rightMargin();

            // Get the original width and height of the image
            float originalWidth = image.getWidth();
            float originalHeight = image.getHeight();

            // Calculate the new height to maintain the aspect ratio
            float height = (width / originalWidth) * originalHeight;

            // Scale the image to the calculated width and height
            image.scaleToFit(width, height);
            document.add(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addText(String text) {
        try {
            document.add(new Paragraph(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void closePDF() {
        try {
            document.close();
            pdfWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void wait(int seconds) {
        try {
            Thread.currentThread().sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
