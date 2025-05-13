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

        WebElement botonClientes = wait.until(ExpectedConditions.elementToBeClickable(By.id("clientes-table")));
        botonClientes.click();
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

        // Esperar que se renderice la tarjeta de la mascota "Pepito"
        WebElement tarjetaPepito = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//h5[contains(text(),'Pepito')]/ancestor::div[contains(@class,'card')]")
        ));

        // Hacer scroll a la tarjeta
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", tarjetaPepito);
        Thread.sleep(500);

        // Esperar y hacer clic en el botón "Ver Detalles" dentro de la tarjeta de Pepito
        WebElement botonVerDetalles = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//h5[contains(text(),'Pepito')]/ancestor::div[contains(@class,'card')]//a[@id='verDetalles']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonVerDetalles);

        
    }

    @Test
    public void testSuministrarAFirulais1() throws InterruptedException {
        driver.get("http://localhost:4200/veterinario/login");
    
        driver.findElement(By.id("usuario")).sendKeys("juanvet");
        driver.findElement(By.id("contrasena")).sendKeys("password");
        driver.findElement(By.id("login-button")).click();
        cerrarAlerta();
    
        WebElement botonTablaMascotas = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Ir a Tabla de Mascotas')]"))
        );
        botonTablaMascotas.click();
    
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//tr[td/a[contains(text(), 'Firulais1')]]")
        ));
    
        WebElement botonSuministrar = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//tr[td/a[contains(text(), 'Firulais1')]]//a[contains(text(), 'Suministrar tratamiento')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botonSuministrar);
        Thread.sleep(300);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonSuministrar);
    
        // Seleccionar el primer medicamento
        WebElement primerBotonSeleccionar = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//tbody/tr[1]//button[contains(text(), 'Seleccionar')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", primerBotonSeleccionar);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", primerBotonSeleccionar);
    
        // Asignar tratamiento
        WebElement btnAsignar = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//button[contains(text(),'Asignar Tratamiento')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnAsignar);
        Thread.sleep(300);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnAsignar);
    
        cerrarAlerta();
    
        // Volver al perfil
        WebElement volverPerfilBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//a[contains(text(),'Volver a perfil')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", volverPerfilBtn);
        Thread.sleep(300);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", volverPerfilBtn);
    
        Thread.sleep(5000);
            // Login como admin
        driver.get("http://localhost:4200/admin/login");
        driver.findElement(By.id("usuario")).sendKeys("simonadmin");
        driver.findElement(By.id("contrasena")).sendKeys("password");
        driver.findElement(By.id("login-button")).click();
        cerrarAlerta();
    
            // Esperar a que el div de "Ventas y Ganancias" esté visible
        WebElement ventasYGananciasCard = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Ventas y Ganancias')]"))
        );

        // Localizar los valores de Ventas y Ganancias Totales
        WebElement ventasTotalesElement = driver.findElement(By.xpath("//h3[contains(text(),'Ventas y Ganancias')]/following-sibling::div//div[contains(text(),'Ventas Totales')]"));
        WebElement gananciasTotalesElement = driver.findElement(By.xpath("//h3[contains(text(),'Ventas y Ganancias')]/following-sibling::div//div[contains(text(),'Ganancias Totales')]"));

        // Extraer los textos de los elementos
        String ventasTotalesText = ventasTotalesElement.getText();
        String gananciasTotalesText = gananciasTotalesElement.getText();

        // Eliminar el símbolo de dólar y las comas, luego convertir a números
        double ventasTotales = Double.parseDouble(ventasTotalesText.replaceAll("[^\\d.]", ""));
        double gananciasTotales = Double.parseDouble(gananciasTotalesText.replaceAll("[^\\d.]", ""));
    

        System.out.println("🧪 Total ganancias actuales: " + gananciasTotales);
    
        assert ventasTotales > 0 : "La cantidad de tratamientos no aumentó.";
        assert gananciasTotales > 0 : "Las ganancias no se reflejaron positivamente.";
    }
    }
    

