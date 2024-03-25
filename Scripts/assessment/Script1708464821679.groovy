import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testdata.TestData
import com.github.javafaker.Faker

def successfulLogin = false
def faker = new Faker()
def usernames = []
def passwords = []
def baseUrl = 'https://opensource-demo.orangehrmlive.com/web/index.php/auth/login'

int loop = 3

for (i = 1; i <= loop; i++) {
    String user = faker.name().username()
	
    String passwordFake = faker.funnyName().name()
    
    usernames.add(user)
    passwords.add(passwordFake)
	
	println(usernames)
	println(passwords)
}

usernames.add('Admin')
passwords.add('admin123')

WebUI.openBrowser(baseUrl)

for (int i = 0; i < usernames.size(); i++) {
    def username = usernames[i]
    def password = passwords[i]

    WebUI.setText(findTestObject('Object Repository/inputTextUsername'), username)
    WebUI.setText(findTestObject('Object Repository/inputTextPassword'), password)

    WebUI.click(findTestObject('Object Repository/buttonSubmit'))
	
    if (WebUI.getUrl().contains('dashboard')) {
        successfulLogin = true
        println("Login berhasil dengan username: $username dan password: $password")
    } 
}

if (!successfulLogin) {
    println("Tidak ada kombinasi username dan password yang berhasil.")
}