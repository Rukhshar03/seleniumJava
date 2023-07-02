package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lib.selenium.Browser;
import lib.selenium.DropDown;
import lib.selenium.Locators;
import lib.selenium.PSM;

public class CreateLead extends PSM {
    @BeforeTest
    public void setData() {
        testCaseName = "Create a lead";
        testDescription = "Creating a lead";
        testNodes = "create";
        category = "Smoke";
        authors = "koushik";
        browserName = Browser.Chrome;
//        dataSheetName = "01";
    }

    @Test()
    public void createLead() {
    	
    	
        WebElement eleUserName = locateElement(Locators.ID, "username");
        checkDropDownSorting(eleUserName);
        type(eleUserName, "DemoSalesManager");
        WebElement elePassword = locateElement(Locators.ID, "password");
        type(elePassword, "crmsfa");
        WebElement eleLogin = locateElement(Locators.CLASSNAME, "decorativeSubmit");
        click(eleLogin);
        selectDropDown(DropDown.INDEX, eleLogin, 1);
        selectDropDown(DropDown.VISIBLETEXT, eleLogin, "values");
    }
}




