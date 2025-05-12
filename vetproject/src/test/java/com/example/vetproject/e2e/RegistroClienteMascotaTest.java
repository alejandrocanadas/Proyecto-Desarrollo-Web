package com.example.vetproject.e2e;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=8090")
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RegistroClienteMascotaTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-extensions");
        this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void cerrarAlerta() {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            alertWait.until(driver -> {
                try {
                    Alert alert = driver.switchTo().alert();
                    alert.accept();
                    return true;
                } catch (NoAlertPresentException e) {
                    return false;
                }
            });
        } catch (Exception e) {
            System.out.println(" No se detectó ninguna alerta para cerrar.");
        }
    }

    @Test
    public void testRegistroClienteYAsociacionMascota() throws InterruptedException {
        driver.get("http://localhost:4200/veterinario/login");

        driver.findElement(By.id("usuario")).sendKeys("usuario123");
        driver.findElement(By.id("contrasena")).sendKeys("password");
        driver.findElement(By.id("login-button")).click();
        cerrarAlerta();

        driver.findElement(By.id("usuario")).clear();
        driver.findElement(By.id("contrasena")).clear();
        driver.findElement(By.id("usuario")).sendKeys("juanvet");
        driver.findElement(By.id("contrasena")).sendKeys("password");
        driver.findElement(By.id("login-button")).click();
        cerrarAlerta();

        driver.findElement(By.id("clientes-table")).click();
        driver.findElement(By.linkText("Añadir Cliente")).click();

        driver.findElement(By.id("nombre")).sendKeys("Anastacia");
        driver.findElement(By.id("apellido")).sendKeys("Rhoades");
        driver.findElement(By.id("usuario")).sendKeys("juanp");
        driver.findElement(By.id("telefono")).sendKeys("123456789");
        driver.findElement(By.id("email")).sendKeys("anastaciagarcia@gmail.com");
        driver.findElement(By.id("contrasena")).sendKeys("password");
        driver.findElement(By.id("btnGuardar")).click();
        cerrarAlerta();

        driver.findElement(By.id("usuario")).clear();
        driver.findElement(By.id("usuario")).sendKeys("anastaciar");
        driver.findElement(By.id("btnGuardar")).click();
        cerrarAlerta();

        driver.findElement(By.id("mascota-table")).click();
        driver.findElement(By.id("add-mascota")).click();

        WebElement selectElement = driver.findElement(
                By.xpath("//select[contains(@class, 'form-control') and not(@formcontrolname)]"));
        Select select = new Select(selectElement);
        select.selectByValue("anastaciagarcia@gmail.com");

        driver.findElement(By.id("nombre")).sendKeys("Pepito");
        driver.findElement(By.id("edad")).sendKeys("3");
        driver.findElement(By.id("tipo")).sendKeys("Perro");
        driver.findElement(By.id("raza")).sendKeys("Labrador");
        driver.findElement(By.id("sexo")).sendKeys("Macho");
        driver.findElement(By.id("estado")).sendKeys("Activo");
        driver.findElement(By.id("imagen")).sendKeys(
                "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg");

        
        WebElement botonGuardar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnGuardarMascota")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonGuardar);

        cerrarAlerta();

        // Login como cliente
        driver.get("http://localhost:4200/cliente/login");
        driver.findElement(By.id("usuario")).sendKeys("anastaciar");
        driver.findElement(By.id("contrasena")).sendKeys("password");
        driver.findElement(By.id("login-button")).click();
        cerrarAlerta();

        WebElement botonVerDetalles = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("verDetalles")));
        WebElement detalleNombre = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Pepito')]")));
        assert detalleNombre.isDisplayed();
    }
}
