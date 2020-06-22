package com.sophos.steps;

import com.sophos.page.GooglePage;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleTranslate {

    private GooglePage googlePage;

    @Step("Abrir la pagina <google>")
    public void abrirLaPagina(String namePage) {
        this.googlePage = new GooglePage();
        this.googlePage.assertExistPage(namePage);
    }

    @Step("Ingresar a google translate")
    public void ingresarAGoogleTranslate() {
        this.googlePage.ingresarGoogleTranslate();
    }

    @Step("traducir de ingles a español la palabra <house>")
    public void traducirPalabraDeInglesAEspanol(String palabra) {
        this.googlePage.traducirPalabra(palabra);
    }

    @Step("deberia dar como resultado <casa>")
    public void deberiaDarComoResultado(String palabra) {
        this.googlePage.validarResultadoTraduccion(palabra);
    }
}
