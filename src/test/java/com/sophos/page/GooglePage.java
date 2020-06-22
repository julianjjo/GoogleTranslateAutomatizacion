package com.sophos.page;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class GooglePage {
    private WebElement contenedorLinks;
    private WebElement aplicaciones;
    private WebElement linkTraductor;
    private WebElement linkIngles;
    private WebElement source;
    private WebElement result;
    private By contenedorLinksBy = By.xpath("//*[@id=\"gbw\"]/div/div/div[3]/iframe");
    private By aplicacionesBy = By.xpath("//*[@id=\"gbwa\"]/div/a");
    private By linkTraductorBy = By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div/c-wiz/div/div/ul[1]/li[12]/a");
    private By linkInglesBy = By.id("sugg-item-en");
    private By sourceBy = By.id("source");
    private By resultBy = By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div/span[1]/span");
    private WebDriver webDriver;

    public GooglePage() {
        this.webDriver = Driver.webDriver;
        this.irPaginaPrincipal();
        this.aplicaciones = this.webDriver.findElement(this.aplicacionesBy);
    }

    public void assertExistPage(String namePage) {
        assertThat(this.webDriver.getTitle()).contains(namePage);
    }

    public void irPaginaPrincipal() {
        String app_url = System.getenv("APP_URL");
        this.webDriver.get(app_url + "/");
    }

    public void ingresarGoogleTranslate() {
        this.aplicaciones.click();
        this.contenedorLinks = this.webDriver.findElement(this.contenedorLinksBy);
        this.webDriver.switchTo().frame(this.contenedorLinks);
        this.linkTraductor = this.webDriver.findElement(this.linkTraductorBy);
        this.linkTraductor.click();
    }

    public void traducirPalabra(String palabra) {
        this.linkIngles = this.webDriver.findElement(this.linkInglesBy);
        this.linkIngles.click();
        this.source = this.webDriver.findElement(this.sourceBy);
        this.source.sendKeys(palabra);
    }

    public void validarResultadoTraduccion(String palabra) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, 100);
        this.result = wait.until(ExpectedConditions.visibilityOfElementLocated(this.resultBy));
        assertThat(this.result.getText().equals(palabra));
    }
}
