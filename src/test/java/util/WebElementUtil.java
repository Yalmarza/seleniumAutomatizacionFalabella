package util;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import exception.qaFrameworkException;

public class WebElementUtil {

	public static boolean isElementPresent(WebElement webElement) {
		try {
			@SuppressWarnings("unused")
			Boolean resp = webElement.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void ingresaTexto(WebElement we, String texto) {
		if(we != null) {
			borrarCampoBackSpace(we);
			we.clear();
			//we.click();
			we.sendKeys(texto);
		}else {
			throw new qaFrameworkException("WebElement no debe ser null");
		}
	}
	
	public static void borrarCampoBackSpace(WebElement campo) {
		campo.sendKeys(Keys.BACK_SPACE);
		String valor = campo.getAttribute("value").toString().trim();
		for (int x = 0; x < valor.length(); x++) {
			campo.sendKeys(Keys.BACK_SPACE);
		}
	}
	
	public static void scrollClickElement(WebElement element, WebDriver driver) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void generaPausaSegundos(int segundos, Class<?> obj){
    	synchronized(obj){
    		try {
    			obj.wait(segundos * 1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }

	
}
